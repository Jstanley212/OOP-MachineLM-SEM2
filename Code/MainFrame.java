package Code;

import javax.swing.*;
import java.awt.GridLayout;


public class MainFrame {

    //creating the main screen JFrame
    private JFrame main_screen;

    //creating the main panel JPanel
    private JPanel input_panel;

    //creating for GUI user inputs fields
    private InputPanel input_class;


    //constructor
    public MainFrame(){

        main_screen = new JFrame("Primary Window");
        input_panel = new JPanel();
        input_class = new InputPanel();

        //adding all the dropdowns and buttons from the input class to the input panel
        input_panel.add(input_class.getRevenue_label());
        input_panel.add(input_class.getRevenueGrowth());
        input_panel.add(input_class.getProfit_label());
        input_panel.add(input_class.getProfitMargin());
        input_panel.add(input_class.getMarket_label());
        input_panel.add(input_class.getMarketSentiment());
        input_panel.add(input_class.getDebt_label());
        input_panel.add(input_class.getDebtLevel());
        input_panel.add(input_class.getSubmit_entries());

        //adding input panel to frame
        main_screen.add(input_panel);

        //setting size of frame and setting it to visible
        main_screen.setSize(600, 400);
        main_screen.setVisible(true);
    }
}
