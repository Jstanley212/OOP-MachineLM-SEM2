package Code;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends Component implements ActionListener{

    public JTextField revenueGrowth;
    public JTextField profitMargin;
    public JTextField marketSentiment;
    public JTextField debtLevel;

    public JButton submit_entries;

    //level 1 naive bayes classifier
    NaiveBayes level1_NaiveBayes = new NaiveBayes();


    public InputPanel(){

        //initialising Jtextfields to enter in the user features, will change to radios or dropdown in future
        revenueGrowth = new JTextField(16);
        profitMargin = new JTextField(16);
        marketSentiment = new JTextField(16);
        debtLevel = new JTextField(16);

        //button to submit the text fields and an actionListener attached
        submit_entries = new JButton("Submit");
        submit_entries.addActionListener(this);
    }


    //methods

    /*
    @Override
    public String toString() {
        return "The Revenue Growth is "+ revenueGrowth.getText()+" the Profit Margin is "+ profitMargin.getText()+" the Market Sentiment is "+ marketSentiment.getText()+" the Debt level is "+ debtLevel.getText();
    }*/

    //actionPerformed method to take in the users entries in the textfields when the submit button is pressed
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit_entries){

            //validating text fields
            if (revenueGrowth.getText().isEmpty() || profitMargin.getText().isEmpty() || marketSentiment.getText().isEmpty() || debtLevel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required",
                        "Alert",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else  {

                //option pane to display whether the user should invest in a stock
                JOptionPane.showMessageDialog(
                        this,
                        //entering the users entries into the level 1 naive bayes classifier to see if they should invest
                        level1_NaiveBayes.predict(revenueGrowth.getText(), profitMargin.getText(), marketSentiment.getText(), debtLevel.getText()),
                        "Alert",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        }
    }


}
