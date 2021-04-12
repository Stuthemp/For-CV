package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class DeletionFrame extends JFrame implements ActionListener {

    public static JTextField idField;
    JFrame deletionFrame = new JFrame();
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
        deletionFrame.setLayout(null);
        deletionFrame.setVisible(true);
        deletionFrame.setResizable(false);
        deletionFrame.setSize(200,130);
        deletionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        idField = new JTextField();
        SpringConfig.createNumbersOnlyField(idField);
        idField.setText("99");
        idField.setToolTipText("Id удаляемого объекта(Номер студенческого для студента)");
        setDialogSetting(idField);
        saveButton = new JButton("Удалить");
    }

    private void addButtonsToFrame(){
        deletionFrame.add(idField);
        deletionFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        idField.setBounds(1,1,200,50);
        saveButton.setBounds(1,50,200,50);
    }



    public static void create(int type){
        DeletionFrame df = new DeletionFrame();
        DeletionFrame.type = type;
        df.setFrameParameters();
        df.createButtons();
        df.addButtonsToFrame();
        df.setButtonsBounds();
        df.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            int id  = 0;
            try{
                id = Integer.parseInt(idField.getText());
            }
            catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                DeletionFrame.create(type);
            }
            switch (type){
                case(1) : {
                    StudentDAO.delete(id);
                    break;
                }
                case (2): {
                    PersonDAO.delete(id);
                    break;
                }
                case (3): {
                    GroupDAO.delete(id);
                    break;
                }
                case (4): {
                    StudentRelativesDAO.delete(id);
                    break;
                }
                case (5): {
                    RelativesTypeDAO.delete(id);
                    break;
                }
                case (6): {
                    PrivilegeDAO.delete(id);
                    break;
                }
                case (7): {
                    FacultyDAO.delete(id);
                    break;
                }
            }
            deletionFrame.setVisible(false);
        }
    }

}
