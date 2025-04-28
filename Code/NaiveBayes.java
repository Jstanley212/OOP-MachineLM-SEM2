package Code;

import java.util.Map;
import java.util.*;

import static java.lang.Math.round;

public class NaiveBayes implements Predictor{

    //attributes

    //creating attributes to store probabilities of the increase being yes or no
    private double hasViolationYes;
    private double hasViolationNo;

    //creating hashmap attributes to store the probabilities that a feature was yes or no
    private Map<String, Double> ageGroupYes;
    private Map<String, Double> ageGroupNo;

    private Map<String, Double> vehicleTypeYes;
    private Map<String, Double> vehicleTypeNo;

    private Map<String, Double> priorViolationYes;
    private Map<String, Double> priorViolationNo;

    private Map<String, Double> maintenanceRecordYes;
    private Map<String, Double> maintenanceRecordNo;

    private double prob_Yes;
    private double prob_No;

    public NaiveBayes(){

        /*
        //level 1 functionality with hard code probabilities from the dataset
        hasViolationYes = 0.61;
        hasViolationNo = 0.39;

        //probabilities for each label
        ageGroupYes = Map.of("Young", 0.44, "Adult", 0.56);
        ageGroupNo = Map.of("Young", 0.60, "Adult", 0.40);

        vehicleTypeYes = Map.of("Car", 0.43, "Truck", 0.57);
        vehicleTypeNo = Map.of("Car", 0.81, "Truck", 0.19);

        //should rework the data so no prior violation doesn't have such high odds
        priorViolationYes = Map.of("Yes", 0.77, "No", 0.23);
        prioViolationsNo = Map.of("Yes", 0.01, "No", 0.99);

        maintenanceRecordYes = Map.of("Good", 0.22, "Poor", 0.78);
        maintenanceRecordNo = Map.of("Good", 0.97, "Poor", 0.03);
        */

        //initialize empty maps
        ageGroupYes = new HashMap<>();
        ageGroupNo = new HashMap<>();
        vehicleTypeYes = new HashMap<>();
        vehicleTypeNo = new HashMap<>();
        priorViolationYes = new HashMap<>();
        priorViolationNo = new HashMap<>();
        maintenanceRecordYes = new HashMap<>();
        maintenanceRecordNo = new HashMap<>();
    }

    public void train(List<Data> trainingData) {

        //total amount of records
        int totalRecords = trainingData.size();
        //counter for violation count
        int violationYesCount = 0;

        // Count maps for each feature
        Map<String, Integer> ageYesCount = new HashMap<>();
        Map<String, Integer> ageNoCount = new HashMap<>();
        Map<String, Integer> vehicleYesCount = new HashMap<>();
        Map<String, Integer> vehicleNoCount = new HashMap<>();
        Map<String, Integer> priorViolationYesCount = new HashMap<>();
        Map<String, Integer> priorViolationNoCount = new HashMap<>();
        Map<String, Integer> maintenanceYesCount = new HashMap<>();
        Map<String, Integer> maintenanceNoCount = new HashMap<>();

        //counting all occurrences
        for (Data record : trainingData) {
            if (record.getHasViolation().equals("Yes")) {
                violationYesCount++;
                incrementCount(ageYesCount, record.getAgeGroup());
                incrementCount(vehicleYesCount, record.getVehicleType());
                incrementCount(priorViolationYesCount, record.getPriorViolation());
                incrementCount(maintenanceYesCount, record.getMaintenanceRecord());
            } else {
                incrementCount(ageNoCount, record.getAgeGroup());
                incrementCount(vehicleNoCount, record.getVehicleType());
                incrementCount(priorViolationNoCount, record.getPriorViolation());
                incrementCount(maintenanceNoCount, record.getMaintenanceRecord());
            }
        }

        //calculating probabilities
        hasViolationYes = (double) violationYesCount / totalRecords;
        hasViolationNo = (double) (totalRecords - violationYesCount) / totalRecords;

        //converting counts to probabilities
        ageGroupYes = convertToProbabilities(ageYesCount, violationYesCount);
        ageGroupNo = convertToProbabilities(ageNoCount, totalRecords - violationYesCount);
        vehicleTypeYes = convertToProbabilities(vehicleYesCount, violationYesCount);
        vehicleTypeNo = convertToProbabilities(vehicleNoCount, totalRecords - violationYesCount);
        priorViolationYes = convertToProbabilities(priorViolationYesCount, violationYesCount);
        priorViolationNo = convertToProbabilities(priorViolationNoCount, totalRecords - violationYesCount);
        maintenanceRecordYes = convertToProbabilities(maintenanceYesCount, violationYesCount);
        maintenanceRecordNo = convertToProbabilities(maintenanceNoCount, totalRecords - violationYesCount);
    }

    private void incrementCount(Map<String, Integer> map, String key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    private Map<String, Double> convertToProbabilities(Map<String, Integer> countMap, int total) {
        Map<String, Double> probMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            probMap.put(entry.getKey(), (double) entry.getValue() / total);
        }
        return probMap;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }


    public String predict(String ageGroup, String vehicleType, String priorViolation, String maintenanceRecord){

        //calculating if the probability based on the user entries if there is a violation
        prob_Yes = hasViolationYes * ageGroupYes.get(ageGroup) * vehicleTypeYes.get(vehicleType)
                * priorViolationYes.get(priorViolation) * maintenanceRecordYes.get(maintenanceRecord);

        prob_No = hasViolationNo * ageGroupNo.get(ageGroup) * vehicleTypeNo.get(vehicleType)
                * priorViolationNo.get(priorViolation) * maintenanceRecordNo.get(maintenanceRecord);

        //return based on which probability is greater
        return (prob_Yes > prob_No) ? "Yes there is a violation, " + roundAvoid(prob_Yes, 3) : "No there is no violation, " + roundAvoid(prob_No, 3);
    }
}
