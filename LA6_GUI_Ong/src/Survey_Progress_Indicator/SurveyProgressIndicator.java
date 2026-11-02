package Survey_Progress_Indicator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SurveyProgressIndicator extends JFrame{
    private static final String FILEPATH = "src/Survey_Progress_Indicator/data.txt";

    private JPanel pnlSurveyProgressIndicator;
    private JProgressBar progressBar;
    private JTextField tfFullName;
    private JButton btnSubmit;
    private JLabel lblFullName;
    private JLabel lblBirthDate;
    private JTextField tfBirthDate;
    private JLabel lblGender;
    private JTextField tfGender;
    private JLabel lblContactNum;
    private JTextField tfContactNum;
    private JLabel lblEmail;
    private JTextField tfEmail;
    private JLabel lblMessage;

    public SurveyProgressIndicator(){
        setContentPane(pnlSurveyProgressIndicator);
        setTitle("Survey Progress Indicator");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        progressBar.setName("progressBar");
        tfFullName.setName("tfFullName");
        tfBirthDate.setName("tfBirthDate");
        tfGender.setName("tfGender");
        tfContactNum.setName("tfContactNum");
        tfEmail.setName("tfEmail");
        lblMessage.setName("lblMessage");
        btnSubmit.setName("btnSubmit");

        progressBar.setMinimum(0);
        progressBar.setMaximum(5);

        ensureFileExists();

        DocumentListener progress = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateProgress();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateProgress();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateProgress();
            }
        };

        tfFullName.getDocument().addDocumentListener(progress);
        tfBirthDate.getDocument().addDocumentListener(progress);
        tfGender.getDocument().addDocumentListener(progress);
        tfContactNum.getDocument().addDocumentListener(progress);
        tfEmail.getDocument().addDocumentListener(progress);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!areAllFieldsFilled()){
                    lblMessage.setText("All fields are required.");
                }
                else{
                    lblMessage.setText("Thank you for answering!");
                    saveEntry();
                }
            }
        });
    }

    private void updateProgress(){
        int count = 0;

        if(!tfFullName.getText().trim().isEmpty()){
            count++;
        }
        if(!tfBirthDate.getText().trim().isEmpty()){
            count++;
        }
        if(!tfGender.getText().trim().isEmpty()){
            count++;
        }
        if(!tfContactNum.getText().trim().isEmpty()){
            count++;
        }
        if(!tfEmail.getText().trim().isEmpty()){
            count++;
        }

        progressBar.setValue(count);
    }

    private boolean areAllFieldsFilled(){
        return  !tfFullName.getText().trim().isEmpty() &&
                !tfBirthDate.getText().trim().isEmpty() &&
                !tfGender.getText().trim().isEmpty() &&
                !tfContactNum.getText().trim().isEmpty() &&
                !tfEmail.getText().trim().isEmpty();
    }

    private void ensureFileExists(){
        File file = new File(FILEPATH);
        File parentDir = file.getParentFile();

        if(parentDir != null && !parentDir.exists()){
            parentDir.mkdirs();
        }

        if(!file.exists()){
            try{
                file.createNewFile();
            }
            catch(IOException e){
                System.out.println("Error: Failed to create file");
            }
        }
    }

    private void saveEntry(){
        File file = new File(FILEPATH);

        ensureFileExists();

        String entry =  tfFullName.getText().trim() + "," +
                        tfBirthDate.getText().trim() + "," +
                        tfGender.getText().trim() + "," +
                        tfContactNum.getText().trim() + "," +
                        tfEmail.getText().trim();

        try(PrintWriter w = new PrintWriter(new FileWriter(file, true))){
            w.println(entry);
        }
        catch(IOException e){
            System.out.println("Error: File not found.");
        }
    }

    static void main(String[] args) {
        new SurveyProgressIndicator();
    }
}
