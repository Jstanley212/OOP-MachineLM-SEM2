package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//abstract class for all the panels to inherit from

public abstract class BasePanel extends JPanel implements ActionListener {
    //Common components
    private JButton submit_button;

    //Initialisation
    public BasePanel(){
        initializeComponents();
    }

    //methods to initialize components
    public void initializeComponents() {
    }

    //Common method for showing messages
    public void showMessage(String message, String title){
        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    //Common action handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit_button) {

        }
    }




}
