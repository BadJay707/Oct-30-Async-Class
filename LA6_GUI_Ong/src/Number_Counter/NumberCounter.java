package Number_Counter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberCounter extends JFrame {
    private int count = 0;

    private JPanel pnlNumberCounter;
    private JLabel countLabel;
    private JButton increaseButton;
    private JButton decreaseButton;

    public NumberCounter(){
        setContentPane(pnlNumberCounter);
        setTitle("Number Counter");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        countLabel.setName("countLabel");
        increaseButton.setName("increaseButton");
        decreaseButton.setName("decreaseButton");

        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                countLabel.setText(String.valueOf(count));
            }
        });

        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                countLabel.setText(String.valueOf(count));
            }
        });
    }

    public static void main(String[] args){
        new NumberCounter();
    }
}
