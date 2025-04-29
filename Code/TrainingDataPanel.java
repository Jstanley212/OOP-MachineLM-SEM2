package Code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TrainingDataPanel extends BasePanel implements ActionListener{

    //creating button to submit
    private JButton add_button;
    private JButton update_button;

    //dropdown string list
    private String[] age;
    private String[] car_truck;
    private String[] yes_no;
    private String[] good_poor;

    private JLabel age_label;
    private JLabel vehicle_label;
    private JLabel violation_label;
    private JLabel maintenance_label;
    private JLabel hasviolation_label;

    //creating button to add and update model
    private JButton addDataButton;
    private JButton updateModelbutton;

    //creating boxes to add more rows to dataset
    private JComboBox<String> ageGroup;
    private JComboBox<String> vehicleType;
    private JComboBox<String> priorViolation;
    private JComboBox<String> maintenanceRecord;
    private JComboBox<String> hasViolation;

    //creating dataloader and model
    private DataLoader dataLoader;
    private NaiveBayes naiveBayesClassifier;

    //constructor
    public TrainingDataPanel(NaiveBayes classifier){
        //calling constructor
        super();

        this.naiveBayesClassifier = classifier;

        this.dataLoader = new DataLoader();
        initializeComponents();
    }

    @Override
    public void initializeComponents() {
        //dropdown options
        age = new String[]{"Young", "Adult"};
        car_truck = new String[]{"Car", "Truck"};
        yes_no = new String[]{"Yes", "No"};
        good_poor = new String[]{"Good", "Poor"};

        //initialising JComboBox to enter user features into dataset and the JLabel,
        ageGroup = new JComboBox<>(age);
        age_label = new JLabel("Age Group");
        vehicleType = new JComboBox<>(car_truck);
        vehicle_label = new JLabel("Vehicle Type");
        priorViolation = new JComboBox<>(yes_no);
        violation_label = new JLabel("Prior Violation");
        maintenanceRecord = new JComboBox<>(good_poor);
        maintenance_label = new JLabel("Maintenance Record");
        hasViolation = new JComboBox<>(yes_no);
        hasviolation_label = new JLabel("Violation");

        //button to submit the text fields and an actionListener attached
        add_button = new JButton("Add Data");
        add_button.addActionListener(this);

        //initializing training button
        update_button = new JButton("Update");
        update_button.addActionListener(this);

    }

    //button for adding data
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add_button) {
            addTrainingData();
        } else if (e.getSource() == update_button) {
            updateModel();
        }
    }

    //method to add data
    private void addTrainingData() {

        //gets the values entered in the combo boxes by the user and stores them in String newData
        String newData = String.format("%s,%s,%s,%s,%s",
                ageGroup.getSelectedItem(),
                vehicleType.getSelectedItem(),
                priorViolation.getSelectedItem(),
                maintenanceRecord.getSelectedItem(),
                hasViolation.getSelectedItem()
        );

        //file writer to data to end of file, append mode is true
        try (FileWriter fw = new FileWriter("vehicle_has_violation.csv", true);

             //buffered writer and print writer for efficient writing to file
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //file being written too
            File file = new File("vehicle_has_violation.csv");

            //checks if file ends with a newline, if file doesn't have one adds it before writing
            if (file.length() > 0) {

                //random access file since we only want to know whats at the end of the file
                RandomAccessFile raf = new RandomAccessFile(file, "r");

                //moves pointer to last point of file, since we are checking the end of the file
                raf.seek(file.length() - 1);

                //reading into last byte
                byte endFile = raf.readByte();
                //close file
                raf.close();

                //checking if end of file is \n
                if (endFile != '\n') {
                    //adding newline if needed
                    out.println();
                }
            }

            //write the new data using print writer
            out.println(newData);

            JOptionPane.showMessageDialog(this,
                    "Training data added successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding training data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //method to update model with the new data
    private void updateModel() {
        try {
            naiveBayesClassifier.train(dataLoader.loadingData());
            JOptionPane.showMessageDialog(this,
                    "Model updated successfully with new training data!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error updating model: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Getters for components
    public JButton getAdd_button() {
        return add_button;
    }

    public JButton getUpdate_button() {
        return update_button;
    }

    public JLabel getAge_label() {
        return age_label;
    }

    public JComboBox<String> getAgeGroup() {
        return ageGroup;
    }

    public JLabel getVehicle_label() {
        return vehicle_label;
    }

    public JComboBox<String> getVehicleType() {
        return vehicleType;
    }

    public JLabel getViolation_label() {
        return violation_label;
    }

    public JComboBox<String> getPriorViolation() {
        return priorViolation;
    }

    public JLabel getMaintenance_label() {
        return maintenance_label;
    }

    public JComboBox<String> getMaintenanceRecord() {
        return maintenanceRecord;
    }

    public JLabel getHasviolation_label() {
        return hasviolation_label;
    }

    public JComboBox<String> getHasViolation() {
        return hasViolation;
    }
}
