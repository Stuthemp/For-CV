package stuthemp.db.access.Frames;

import stuthemp.db.access.QueriesExecution.GroupDAO;
import stuthemp.db.access.QueriesExecution.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsBySurname extends JFrame implements ActionListener {

    public static JTextField idField;
    JFrame stringInputFrame = new JFrame();
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
        stringInputFrame.setLayout(null);
        stringInputFrame.setVisible(true);
        stringInputFrame.setResizable(false);
        stringInputFrame.setSize(200,130);
        stringInputFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        idField = new JTextField();
        idField.setText("Усов");
        idField.setToolTipText("Фамилия");
        setDialogSetting(idField);
        saveButton = new JButton("Найти");
    }

    private void addButtonsToFrame(){
        stringInputFrame.add(idField);
        stringInputFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        idField.setBounds(1,1,200,50);
        saveButton.setBounds(1,50,200,50);
    }



    public static void create(){
        StudentsBySurname df = new StudentsBySurname();
        df.setFrameParameters();
        df.createButtons();
        df.addButtonsToFrame();
        df.setButtonsBounds();
        df.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String input = idField.getText();
            StudentTableFrame.create(StudentDAO.findStudentsWithSurname(input));
            stringInputFrame.setVisible(false);
        }
    }

}