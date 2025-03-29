package Code;

import javax.swing.*;


public class MainFrame extends Control{

    //initialising the main screen JFrame
    JFrame main_screen = new JFrame("Primary Window");

    //initializing the main panel JPanel
    JPanel input_panel = new JPanel();

    //class for GUI user inputs fields
    InputPanel input_class = new InputPanel();



    //constructor
    public MainFrame(){

        //adding all the text fields and buttons from the input class to the input panel
        input_panel.add(input_class.revenueGrowth);
        input_panel.add(input_class.profitMargin);
        input_panel.add(input_class.marketSentiment);
        input_panel.add(input_class.debtLevel);
        input_panel.add(input_class.submit_entries);

        //adding input panel to frame
        main_screen.add(input_panel);

        //setting size of frame and setting it to visible
        main_screen.setSize(600, 400);
        main_screen.setVisible(true);
    }
}
