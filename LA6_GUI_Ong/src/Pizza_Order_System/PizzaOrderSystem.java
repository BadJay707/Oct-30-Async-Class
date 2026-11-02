package Pizza_Order_System;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaOrderSystem extends JFrame {
    private JComboBox cbExtraCheese;
    private JComboBox cbPizzaToppings;
    private JComboBox cbPizzaSize;
    private JLabel lblTotal;
    private JButton btnCalculate;
    private JPanel pnlPizzaOrderMain;

    public PizzaOrderSystem(){
        setContentPane(pnlPizzaOrderMain);
        setTitle("Pizza Order System");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultComboBoxModel<String> sizeModel = new DefaultComboBoxModel<>(new String[]{"Small", "Medium", "Large"});
        cbPizzaSize.setModel(sizeModel);
        DefaultComboBoxModel<String> toppingsModel = new DefaultComboBoxModel<>(new String[]{"Mushrooms", "Pepperoni", "Onions"});
        cbPizzaToppings.setModel(toppingsModel);
        DefaultComboBoxModel<String> cheeseModel = new DefaultComboBoxModel<>(new String[]{"Yes", "No"});
        cbExtraCheese.setModel(cheeseModel);

        cbPizzaSize.setName("cbPizzaSize");
        cbPizzaToppings.setName("cbPizzaToppings");
        cbExtraCheese.setName("cbExtraCheese");
        lblTotal.setName("lblTotal");
        btnCalculate.setName("btnCalculate");

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int total = 0;

                String selectedSize = (String) cbPizzaSize.getSelectedItem();
                if("Small".equals(selectedSize)){
                    total += 10;
                }
                else if("Medium".equals(selectedSize)){
                    total += 15;
                }
                else{
                    total += 20;
                }

                if(cbPizzaToppings.getSelectedItem() != null){
                    total += 2;
                }

                String selectedCheese = (String) cbExtraCheese.getSelectedItem();
                if("Yes".equals(selectedCheese)){
                    total += 3;
                }

                lblTotal.setText(String.valueOf(total));
            }
        });
    }

    static void main(String[] args) {
        new PizzaOrderSystem();
    }
}
