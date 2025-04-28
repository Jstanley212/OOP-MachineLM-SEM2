package Code;

//interface predictor to allow for changes later

public interface Predictor {
    //method to allow for the user inputs to be entered in the NaiveBayes class and calculations to be done
    String predict(String ageGroup, String vehicleType, String priorViolation, String maintenanceRecord);
}
