package Code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputPanel extends BasePanel implements ActionListener{

    //creating button to submit

    private JButton submit_button;

    //dropdown string list
    private String[] age;
    private String[] car_truck;
    private String[] yes_no;
    private String[] good_poor;

    // creating JComboBoxes to enter user features
    private JComboBox<String> ageGroup;
    private JComboBox<String> vehicleType;
    private JComboBox<String> priorViolation;
    private JComboBox<String> maintenanceRecord;

    private JLabel age_label;
    private JLabel vehicle_label;
    private JLabel violation_label;
    private JLabel maintenance_label;

    //naive bayes classifier
    private NaiveBayes naiveBayesClassifier;

    //level 1 naive bayes classifier
    //NaiveBayes level1_NaiveBayes = new NaiveBayes();

    //creating training button
    private JButton train_button;
    private DataLoader dataLoader;

    //if model has been trained or not by user
    private boolean modelTrained = false;

    //constructor
    public InputPanel(NaiveBayes classifier) {
        //calling constructor
        super();

        this.naiveBayesClassifier = classifier;

        //initialize dataLoader
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

        //initialising JComboBox to enter user features and the JLabel, will change to radios or dropdown in future
        ageGroup = new JComboBox<>(age);
        age_label = new JLabel("Age Group");
        vehicleType = new JComboBox<>(car_truck);
        vehicle_label = new JLabel("Vehicle Type");
        priorViolation = new JComboBox<>(yes_no);
        violation_label = new JLabel("Prior Violation");
        maintenanceRecord = new JComboBox<>(good_poor);
        maintenance_label = new JLabel("Maintenance Record");

        //button to submit the text fields and an actionListener attached
        submit_button = new JButton("Predict");
        submit_button.addActionListener(this);

        //initializing training button
        train_button = new JButton("Train");
        train_button.addActionListener(this);
    }

    //methods
    public JButton getSubmit_entries(){
        return submit_button;
    }
    public JComboBox<String> getAgeGroup(){
        return ageGroup;
    }
    public JComboBox<String> getVehicleType(){
        return vehicleType;
    }
    public JComboBox<String> getPriorViolation(){
        return priorViolation;
    }
    public JComboBox<String> getMaintenanceRecord(){
        return maintenanceRecord;
    }
    public JLabel getAge_label(){
        return age_label;
    }
    public JLabel getVehicle_label(){
        return vehicle_label;
    }
    public JLabel getViolation_label(){
        return violation_label;
    }
    public JLabel getMaintenance_label(){
        return maintenance_label;
    }
    public JButton getTrainButton() { return train_button; }



    //actionPerformed method to take in the users entries in the JComboBoxes when the submit button is pressed
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit_button){
            if (!modelTrained){
                showMessage("Please train model first", "Alert");
                return;
            }

            String selectedAge = (String) ageGroup.getSelectedItem();
            String selectedVehicle = (String) vehicleType.getSelectedItem();
            String selectedViolation = (String) priorViolation.getSelectedItem();
            String selectedMaintenance = (String) maintenanceRecord.getSelectedItem();

            //validating text fields, !!!Can probably be removed since it impossible to get not entry?
            if (selectedAge == null || selectedVehicle == null || selectedViolation == null || selectedMaintenance == null) {
                showMessage("All fields are required","Alert");
            } else  {
                //option pane to display whether the user should invest in a stock
                showMessage(//entering the users entries into the level 1 naive bayes classifier to see if they should invest
                        naiveBayesClassifier.predict(selectedAge, selectedVehicle, selectedViolation, selectedMaintenance),
                        "Result");
            }
            //button to train model
        } else if (e.getSource() == train_button) {
            try {
                //sends loaded data to the classifier to be trained by the train method
                naiveBayesClassifier.train(dataLoader.loadingData());
                modelTrained = true;
                showMessage("Model Trained", "Alert");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
