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
        //filepath
        setFilename("vehicle_has_violation.csv");

        //connecting the filename
        connectFile(getFilename());

        //arraylist to store the loaded data
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

        // buffered reader for more efficient file writing
        try (BufferedReader reader = new BufferedReader(new FileReader(theFile))) {

            // string variable to store each line
            String line;
            // boolean true to allow the code to skip headers
            boolean isHeader = true;

            //while a line in the file is not null, execute
            while ((line = reader.readLine()) != null) {
                // if isHeader is true
                if (isHeader) {
                    // changes to false since past header row
                    isHeader = false;

                    continue;
                }

                // enter enters all the lines as objects into Data class, using parseDataLine method
                Data record = parseDataLine(line);
                // if record is not null, add it to dataset
                if(record != null) {
                    dataset.add(record);
                }

            }
        }

        return dataset;
    }

    //method to seperate each line
    private Data parseDataLine(String line) {

        // if line is null or if line is only filled with spaces
        if (line == null || line.trim().isEmpty()) {
            //so return null so to not add it to dataset
            return null;
        }

        //splits entries at the comma and enter into values
        String[] values = line.split(",");

        //if values doesnt have 5 entries
        if (values.length != 5) {
            // checking if correct amount of fields, and telling user where error is
            System.err.println("Invalid data on line " + line);
            // return null so to not add it to dataset
            return null;
        }

        // initialising dataclass object in Data
        Data dataclass = new Data();

        //enters the 5 values in the object
        dataclass.setAgeGroup(values[0].trim());
        dataclass.setVehicleType(values[1].trim());
        dataclass.setPriorViolation(values[2].trim());
        dataclass.setMaintenanceRecord(values[3].trim());
        dataclass.setHasViolation(values[4].trim());

        // return object so the features can be added dataset
        return dataclass;
    }

    /*
    // used to test if dataLoader was taking in the data
    public void printDataset() {
        System.out.println("Dataset contains " + dataset.size() + " records:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < dataset.size(); i++) {
            System.out.println("Record " + (i + 1) + ": " + dataset.get(i));
        }
        System.out.println("----------------------------------------");
    }*/


}
