package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditingFrame extends JFrame implements ActionListener {

    public static JTextField idField;
    JFrame editingFrame = new JFrame();
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
        editingFrame.setLayout(null);
        editingFrame.setVisible(true);
        editingFrame.setResizable(false);
        editingFrame.setSize(200,130);
        editingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        idField = new JTextField();
        if(type!=7)
            SpringConfig.createNumbersOnlyField(idField);
        idField.setText("99");
        idField.setToolTipText("Id изменяемого объекта(Номер студенческого для студента)");
        setDialogSetting(idField);
        saveButton = new JButton("Изменить");
    }

    private void addButtonsToFrame(){
        editingFrame.add(idField);
        editingFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        idField.setBounds(1,1,200,50);
        saveButton.setBounds(1,50,200,50);
    }



    public static void create(int type){
        EditingFrame edb = new EditingFrame();
        EditingFrame.type = type;
        edb.setFrameParameters();
        edb.createButtons();
        edb.addButtonsToFrame();
        edb.setButtonsBounds();
        edb.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            int intId = 0;
            String stringId = "";
            if(type!=7) {
                try {
                    intId = Integer.parseInt(idField.getText());
                } catch (Exception ex) {
                    Frame frame = getFrames()[0];
                    frame.setVisible(false);
                    DeletionFrame.create(type);
                }
            }
            else{
                stringId = idField.getText();
            }
            switch (type){
                case(1) : {
                    NewStudentFrame.create(intId);
                    break;
                }
                case (2): {
                    NewPersonFrame.create(intId);

                    break;
                }
                case (3): {
                    NewGroupFrame.create(intId);
                    break;
                }
                case (4): {
                    NewStudentsRelativeFrame.create(intId);
                    break;
                }
                case (5): {
                    NewRelativeTypeFrame.create(intId);
                    break;
                }
                case (6): {
                    NewPrivilegeFrame.create(intId);
                    break;
                }
                case (7): {
                    NewFacultyFrame.create(stringId);
                    break;
                }
            }
            editingFrame.setVisible(false);
        }
    }

}
