package Code;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelEvaluation {

    //creating variables for 150 training data and 50 test data
    private List<Data> training_data = new ArrayList<>();
    private List<Data> test_data = new ArrayList<>();


    private NaiveBayes classifier;
    private DataLoader dataLoader;

    private JTextArea resultsArea;

    //constructor
    public ModelEvaluation() {
        dataLoader = new DataLoader();
        classifier = new NaiveBayes();

        //creating a scrollable text for results
        resultsArea = new JTextArea(20, 80);
        resultsArea.setEditable(false);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);

        //creating a scroll pane for text
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
    }

    public void evaluate_model() throws IOException {

        //clearing previous results
        resultsArea.setText("");

        //load data
        List<Data> totalData = dataLoader.loadingData();

        //lists too store split of data
        List<Data> yesList = new ArrayList<>();
        List<Data> noList = new ArrayList<>();

        //splitting data
        for (Data d : totalData) {
            if (d.getHasViolation().equals("Yes")) {
                yesList.add(d);
            } else {
                noList.add(d);
            }
        }

        //calculating ratio
        int totalRecords = totalData.size();
        int totalYes = yesList.size();
        double yesRatio = (double) totalYes / totalRecords;

        //shuffle lists
        Collections.shuffle(yesList);
        Collections.shuffle(noList);

        //calculating number of yes or no's needed for both sets
        int trainYes = (int) Math.round(150 * yesRatio);
        int testYes = yesList.size() - trainYes;
        int trainNo = 150 - trainYes;
        int testNo = noList.size() -trainNo;

        //clearing yes examples existing data
        training_data.clear();
        test_data.clear();

        //adding yes examples to training set and test set
        training_data.addAll(yesList.subList(0, trainYes));
        //test set created with remaining rows
        test_data.addAll(yesList.subList(trainYes, trainYes + testYes));

        //adding no examples to training set and test set
        training_data.addAll(noList.subList(0, trainNo));
        //test set created with remaining rows
        test_data.addAll(noList.subList(trainNo, trainNo + testNo));

        //training model on 150 rows
        classifier.train(0, 150, training_data);

        //results string
        StringBuilder results = new StringBuilder();
        //headers
        results.append("Evaluation Results");
        results.append("==================");
        results.append("Age\tVehicle\tPrior\tMaintenance\tActual\tPredicted\tProb");
        results.append("--------------------------------------------------------\n");

        //counting number of corrects guess
        int correct = 0;

        //going through all test_data rows to get what the models probabilities for the 50 rows are and appending them
        //to results string to be displayed
        for (Data d : test_data) {
            //storing each of the values in string
            String prediction = classifier.predict(
                    d.getAgeGroup(),
                    d.getVehicleType(),
                    d.getPriorViolation(),
                    d.getMaintenanceRecord()
            );


            // split the string at the comma
            String[] parts = prediction.split(",");
            String predictedClass = parts[0];  // will be Yes or No
            double probability = Double.parseDouble(parts[1].trim());  // parse the probability

            //print the prediction details
            results.append(String.format("Age: %s\tVehicle: %s\tPrior: %s\tMaintenance: %s\tActual: %s\tPredicted: %s\tProb: %.3f%n",
                    d.getAgeGroup(),
                    d.getVehicleType(),
                    d.getPriorViolation(),
                    d.getMaintenanceRecord(),
                    d.getHasViolation(),
                    predictedClass,
                    probability
            ));

            //counting how many are yes
            String predictedLabel = prediction.contains("Yes") ? "Yes" : "No";
            if (predictedLabel.equals(d.getHasViolation())) {
                correct++;
            }


        }

        //calculating accuracy model
        double accuracy = (double) correct / test_data.size();
        results.append("Accuracy: " + (accuracy * 100) + "%");

        //setting the results text
        resultsArea.setText(results.toString());
    }

    //method for scroll pane with the results
    public JScrollPane getResultsPane() {
        return new JScrollPane(resultsArea);
    }
}
