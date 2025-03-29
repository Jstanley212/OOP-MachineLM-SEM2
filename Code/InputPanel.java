package Code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends BasePanel implements ActionListener{

    //creating button to submit

    private JButton submit_button;

    //dropdown string list
    private String[] high_low;
    private String[] positive_negative;

    // creating JComboBoxes to enter user features
    private JComboBox<String> revenueGrowth;
    private JComboBox<String> profitMargin;
    private JComboBox<String> marketSentiment;
    private JComboBox<String> debtLevel;

    private JLabel revenue_label;
    private JLabel profit_label;
    private JLabel market_label;
    private JLabel debt_label;

    //level 1 naive bayes classifier
    NaiveBayes level1_NaiveBayes = new NaiveBayes();

    @Override
    public void initializeComponents() {
        //dropdown options
        high_low = new String[]{"high", "low"};
        positive_negative = new String[]{"Positive", "Negative"};

        //initialising JComboBox to enter user features and the JLabel, will change to radios or dropdown in future
        revenueGrowth = new JComboBox<>(high_low);
        revenue_label = new JLabel("Revenue Growth");
        profitMargin = new JComboBox<>(high_low);
        profit_label = new JLabel("Profit Margin");
        marketSentiment = new JComboBox<>(positive_negative);
        market_label = new JLabel("Market Sentiment");
        debtLevel = new JComboBox<>(high_low);
        debt_label = new JLabel("Debt Level");

        //button to submit the text fields and an actionListener attached
        submit_button = new JButton("Predict");
        submit_button.addActionListener(this);
    }

    //methods
    public JButton getSubmit_entries(){
        return submit_button;
    }
    public JComboBox<String> getRevenueGrowth(){
        return revenueGrowth;
    }
    public JComboBox<String> getProfitMargin(){
        return profitMargin;
    }
    public JComboBox<String> getMarketSentiment(){
        return marketSentiment;
    }
    public JComboBox<String> getDebtLevel(){
        return debtLevel;
    }
    public JLabel getRevenue_label(){
        return revenue_label;
    }
    public JLabel getProfit_label(){
        return profit_label;
    }
    public JLabel getMarket_label(){
        return market_label;
    }
    public JLabel getDebt_label(){
        return debt_label;
    }



    //actionPerformed method to take in the users entries in the JComboBoxes when the submit button is pressed
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit_button){

            String selectedRevenue = (String) revenueGrowth.getSelectedItem();
            String selectedProfit = (String) profitMargin.getSelectedItem();
            String selectedMarket = (String) marketSentiment.getSelectedItem();
            String selectedDebt = (String) debtLevel.getSelectedItem();

            //validating text fields, !!!Can probably be removed since it impossible to get not entry?
            if (selectedRevenue == null || selectedProfit == null || selectedMarket == null || selectedDebt == null) {
                showMessage("All fields are required","Alert");
            } else  {
                //option pane to display whether the user should invest in a stock
                showMessage(//entering the users entries into the level 1 naive bayes classifier to see if they should invest
                        level1_NaiveBayes.predict(selectedRevenue, selectedProfit, selectedMarket, selectedDebt),
                        "Alert");
            }
        }
    }

}
