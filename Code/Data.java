package Code;

public class Data {

    // the label for the data
    private String ageGroup;
    private String vehicleType;
    private String priorViolation;
    private String maintenanceRecord;
    private String hasViolation;

    //Constructor
    public Data(){
    }

    // getters and setters
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setPriorViolation(String priorViolation) {
        this.priorViolation = priorViolation;
    }

    public String getPriorViolation() {
        return priorViolation;
    }

    public void setMaintenanceRecord(String maintenanceRecord) {
        this.maintenanceRecord = maintenanceRecord;
    }

    public String getMaintenanceRecord() {
        return maintenanceRecord;
    }

    public void setHasViolation(String hasViolation) {
        this.hasViolation = hasViolation;
    }

    public String getHasViolation() {
        return hasViolation;
    }

    /*
    @Override
    public String toString() {
        return String.format("Age: %s, Vehicle: %s, Prior Violations: %s, Maintenance: %s, Has Violation: %s",
                ageGroup, vehicleType, priorViolation, maintenanceRecord, hasViolation);
    }
     */
}
