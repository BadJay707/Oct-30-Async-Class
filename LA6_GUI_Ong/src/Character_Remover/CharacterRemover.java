package Character_Remover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterRemover extends JFrame{
    private static String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private JCheckBox cbVowels;
    private JCheckBox cbConsonants;
    private JCheckBox cbNumbers;
    private JButton btnRemove;
    private JButton btnRestore;
    private JLabel lblText;
    private JPanel pnlCharacterRemoverMain;

    public CharacterRemover(){
        setContentPane(pnlCharacterRemoverMain);
        setTitle("Character Remover");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cbVowels.setName("cbVowels");
        cbConsonants.setName("cbConsonants");
        cbNumbers.setName("cbNumbers");
        btnRemove.setName("btnRemove");
        btnRestore.setName("btnRestore");
        lblText.setName("lblText");

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currText = lblText.getText();
                String res = "";

                for(int i = 0; i < currText.length(); i++){
                    char ch = currText.charAt(i);

                    boolean isVowel = "AEIOUaeiou".indexOf(ch) != -1;
                    boolean isNumber = Character.isDigit(ch);
                    boolean isConsonant = Character.isLetter(ch) && !isVowel;

                    boolean shouldRemove = (cbVowels.isSelected() && isVowel) || (cbConsonants.isSelected() && isConsonant) || (cbNumbers.isSelected() && isNumber);

                    if(!shouldRemove){
                        res += ch;
                    }
                }
                lblText.setText(res);
            }
        });

        btnRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblText.setText(text);
            }
        });
    }

    static void main(String[] args) {
        new CharacterRemover();
    }
}
