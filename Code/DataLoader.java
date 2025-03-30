package Code;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//class to read the csv file and to write to the csv file

public class DataLoader {
    //attributes for file names
    private String filename;
    public File theFile;

    //constructor
    public DataLoader(){
        setFilename("vehicle_has_violation.csv");

        connectFile(getFilename());

        readFile();
    }

    //methods

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    //connect string to actual file
    public void connectFile(String filename){
        theFile = new File(filename);
    }

    //adding data from file into arrayList so it can be displayed
    public ArrayList<String> readFile(){
        ArrayList<String> roleList = new ArrayList<String>();

        try {
            Scanner myReader = new Scanner(theFile);
            myReader.useDelimiter(",");

            while (myReader.hasNextLine()) {
                String data = myReader.next();
                roleList.add(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return roleList;

    }

    //method to write to the csv file
    public void write(String input) throws IOException {

        //filewrite and printwrite to enter values into file
        FileWriter fw = new FileWriter(theFile, true);

        PrintWriter p1 = new PrintWriter(fw);

        //input entered from control
        p1.println(input);

        p1.close();
    }
}
