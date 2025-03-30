package Code;

import java.util.Map;

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
    private Map<String, Double> prioViolationsNo;

    private Map<String, Double> maintenanceRecordYes;
    private Map<String, Double> maintenanceRecordNo;

    private double prob_Yes;
    private double prob_No;

    public NaiveBayes(){//level 1 functionality with hard code probabilities from the dataset
        hasViolationYes = 0.61;
        hasViolationNo = 0.39;

        //probabilities for each label
        ageGroupYes = Map.of("Young", 0.44, "Adult", 0.56);
        ageGroupNo = Map.of("Young", 0.60, "Adult", 0.40);

        vehicleTypeYes = Map.of("Car", 0.43, "Truck", 0.57);
        vehicleTypeNo = Map.of("Car", 0.81, "Truck", 0.19);

        priorViolationYes = Map.of("Yes", 0.77, "No", 0.23);
        prioViolationsNo = Map.of("Yes", 0.01, "No", 0.99);

        maintenanceRecordYes = Map.of("Good", 0.22, "Poor", 0.78);
        maintenanceRecordNo = Map.of("Good", 0.97, "Poor", 0.03);
    }


    public String predict(String revenueGrowth, String profitMargin, String marketSentiment, String debtLevel){

        //calculating if the probability based on the user entries if there is a violation
        prob_Yes = hasViolationYes * ageGroupYes.get(revenueGrowth) * vehicleTypeYes.get(profitMargin)
                * priorViolationYes.get(marketSentiment) * maintenanceRecordYes.get(debtLevel);

        prob_No = hasViolationNo * ageGroupNo.get(revenueGrowth) * vehicleTypeNo.get(profitMargin)
                * prioViolationsNo.get(marketSentiment) * maintenanceRecordNo.get(debtLevel);

        //return based on which probability is greater
        return (prob_Yes > prob_No) ? "Yes there is a violation" : "No there is no violation";
    }
}
