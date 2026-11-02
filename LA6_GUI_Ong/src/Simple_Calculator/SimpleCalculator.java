package Simple_Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SimpleCalculator extends JFrame {
    private static final String FILEPATH = "src/Simple_Calculator/calculator.txt";

    private JTextField tfNumber1;
    private JComboBox cbOperations;
    private JTextField tfNumber2;
    private JButton btnCompute;
    private JLabel lbl1stNum;
    private JLabel lblResultHeader;
    private JLabel lbl2ndNum;
    private JLabel lblResult;
    private JPanel pnlSimpleCalculator;

    public SimpleCalculator(){
        setContentPane(pnlSimpleCalculator);
        setTitle("Simple Calculator");
        setSize(500,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        tfNumber1.setName("tfNumber1");
        tfNumber2.setName("tfNumber2");
        btnCompute.setName("btnCompute");
        lblResult.setName("lblResult");
        cbOperations.setName("cbOperations");

        cbOperations.addItem("+");
        cbOperations.addItem("-");
        cbOperations.addItem("*");
        cbOperations.addItem("/");

        loadSave();

        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int num1 = Integer.parseInt(tfNumber1.getText().trim());
                    int num2 = Integer.parseInt(tfNumber2.getText().trim());
                    String op = (String) cbOperations.getSelectedItem();
                    int res = 0;

                    if("+".equals(op)){
                        res = num1 + num2;
                    }
                    else if("-".equals(op)){
                        res = num1 - num2;
                    }
                    else if("*".equals(op)){
                        res = num1 * num2;
                    }
                    else{
                        if(num2 != 0){
                            res = num1 / num2;
                        }
                        else{
                            lblResult.setText("Error");
                            return;
                        }
                    }
                    lblResult.setText(String.valueOf(res));
                    save(tfNumber1.getText().trim(), op, tfNumber2.getText().trim(), String.valueOf(res));
                }
                catch(NumberFormatException ex){
                    lblResult.setText("Error");
                }
            }
        });
    }

    private void save(String num1, String op, String num2, String result){
        File file = new File(FILEPATH);
        File parentDir = file.getParentFile();

        if(parentDir != null && !parentDir.exists()){
            parentDir.mkdirs();
        }

        try(PrintWriter w = new PrintWriter(new FileWriter(FILEPATH))){
            w.println(num1);
            w.println(op);
            w.println(num2);
            w.println(result);
        }
        catch(IOException e){
            System.out.println("Error: File not found.");
        }
    }

    private void loadSave(){
        File file = new File(FILEPATH);
        if(!file.exists()){
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String num1 = br.readLine();
            String op = br.readLine();
            String num2 = br.readLine();
            String result = br.readLine();

            if(num1 != null){
                tfNumber1.setText(num1);
            }
            if(op != null){
                cbOperations.setSelectedItem(op);
            }
            if(num2 != null){
                tfNumber2.setText(num2);
            }
            if(result != null){
                lblResult.setText(result);
            }
        }
        catch(IOException e){
            System.out.println("Error: File not found.");
        }
    }

    static void main(String[] args) {
        new SimpleCalculator();
    }
}
