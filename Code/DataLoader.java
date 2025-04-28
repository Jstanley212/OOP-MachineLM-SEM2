package Code;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class to read the csv file and to write to the csv file

public class DataLoader {
    //attributes for file names
    private String filename;
    private File theFile;
    private List<Data> dataset;


    //constructor
    public DataLoader() {
        setFilename("vehicle_has_violation.csv");

        connectFile(getFilename());
        dataset = new ArrayList<>();

        //readFile();
    }


    //methods

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    //connect string to actual file
    public void connectFile(String filename) {
        theFile = new File(filename);
    }

    //method to load the data from the csv file
    public List<Data> loadingData() throws IOException{
        //clear existing data
        dataset.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(theFile))) {

            String line;
            // flag to skip header
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    // skip row
                    continue;
                }

                // enter enters all the lines as objects
                Data record = parseDataLine(line);
                if(record != null) {
                    dataset.add(record);
                }

            }
        }

        return dataset;
    }

    //method to seperate each line
    private Data parseDataLine(String line) {

        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        //split entries at the comma
        String[] values = line.split(",");
        if (values.length != 5) {
            // checking if correct amount of fields
            System.err.println("Invalid data on line " + line);
            return null;
        }

        // initialising dataclass object
        Data dataclass = new Data();

        //enters the 5 values in the object
        dataclass.setAgeGroup(values[0].trim());
        dataclass.setVehicleType(values[1].trim());
        dataclass.setPriorViolation(values[2].trim());
        dataclass.setMaintenanceRecord(values[3].trim());
        dataclass.setHasViolation(values[4].trim());


        return dataclass;
    }

    public void printDataset() {
        System.out.println("Dataset contains " + dataset.size() + " records:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < dataset.size(); i++) {
            System.out.println("Record " + (i + 1) + ": " + dataset.get(i));
        }
        System.out.println("----------------------------------------");
    }


}
