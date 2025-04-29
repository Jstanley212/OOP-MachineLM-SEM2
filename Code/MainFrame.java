package Code;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainFrame {

    //creating the main screen JFrame
    private JFrame main_screen;

    //creating the main panel JPanel
    private JPanel input_panel;

    //creating for GUI user inputs fields
    private InputPanel input_class;

    //creating trainingDataPanel
    private TrainingDataPanel training_panel;

    private ModelEvaluation evaluator;



    //constructor
    public MainFrame(){

        //initialising panels
        main_screen = new JFrame("Primary Window");
        input_panel = new JPanel();

        //initialising JTabbedPane to toggle between predict and adding new data
        JTabbedPane three_Panes = new JTabbedPane();

        //initializing input panel and model
        NaiveBayes classifier = new NaiveBayes();
        input_class = new InputPanel(classifier);

        //adding all the dropdowns and buttons from the input class to the input panel
        input_panel.add(input_class.getAge_label());
        input_panel.add(input_class.getAgeGroup());
        input_panel.add(input_class.getVehicle_label());
        input_panel.add(input_class.getVehicleType());
        input_panel.add(input_class.getViolation_label());
        input_panel.add(input_class.getPriorViolation());
        input_panel.add(input_class.getMaintenance_label());
        input_panel.add(input_class.getMaintenanceRecord());
        input_panel.add(input_class.getTrainButton());
        input_panel.add(input_class.getSubmit_entries());

        // creating training panel
        //JPanel training_container = new JPanel();
        training_panel = new TrainingDataPanel(classifier);

        //adding training data panel dropdowns and buttons
        training_panel = new TrainingDataPanel(classifier);
        training_panel.add(training_panel.getAge_label());
        training_panel.add(training_panel.getAgeGroup());
        training_panel.add(training_panel.getVehicle_label());
        training_panel.add(training_panel.getVehicleType());
        training_panel.add(training_panel.getViolation_label());
        training_panel.add(training_panel.getPriorViolation());
        training_panel.add(training_panel.getMaintenance_label());
        training_panel.add(training_panel.getMaintenanceRecord());
        training_panel.add(training_panel.getHasviolation_label());
        training_panel.add(training_panel.getHasViolation());
        training_panel.add(training_panel.getAdd_button());
        training_panel.add(training_panel.getUpdate_button());

        evaluator = new ModelEvaluation();

        //creating evaluation button
        JPanel evaluationPanel = getEvaluationPanel();

        //adding evaluation tab
        three_Panes.addTab("Model Evaluation", evaluationPanel);

        //adding input panel to frame
        three_Panes.addTab("Predict",input_panel);
        //adding training_panel to frame
        three_Panes.addTab("Add Data",training_panel);


        main_screen.add(three_Panes);

        //setting size of frame and setting it to visible
        main_screen.setSize(600, 400);
        main_screen.setVisible(true);
    }

    private JPanel getEvaluationPanel() {
        JButton evaluateButton = new JButton("Evaluate Model");
        evaluateButton.addActionListener(e -> {
            try {
                evaluator.evaluate_model();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(main_screen,
                        "Error evaluating model: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        //create panel with button and results area
        JPanel evaluationPanel = new JPanel(new BorderLayout());
        evaluationPanel.add(evaluateButton, BorderLayout.NORTH);
        evaluationPanel.add(evaluator.getResultsPane(), BorderLayout.CENTER);
        return evaluationPanel;
    }


}
