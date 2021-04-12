package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrame extends JFrame implements ActionListener {

    public static JTextField idField;
    JFrame errorFrame = new JFrame();
    public static JButton saveButton;
    private static int type;

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setDialogSetting(JTextField jTextField){
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void setFrameParameters(){
        errorFrame.setLayout(null);
        errorFrame.setVisible(true);
        errorFrame.setResizable(false);
        errorFrame.setSize(200,130);
        errorFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(int id){
        idField = new JTextField();
        if(id == 1) {
            idField.setText("Индекс не существует");
        }
        else if(id == 2){
            idField.setText("Гендер введен неверно");
        }
        else {
            idField.setText("Ошибка ввода");
        }
        idField.setEditable(false);
        setDialogSetting(idField);
        saveButton = new JButton("Ок");
    }

    private void addButtonsToFrame(){
        errorFrame.add(idField);
        errorFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        idField.setBounds(1,1,200,50);
        saveButton.setBounds(1,50,200,50);
    }



    public static void create(int id){
        ErrorFrame df = new ErrorFrame();
        df.setFrameParameters();
        df.createButtons(id);
        df.addButtonsToFrame();
        df.setButtonsBounds();
        df.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            errorFrame.setVisible(false);
        }
    }

}
