package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringInput extends JFrame implements ActionListener {

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
        idField.setText("Дизайн");
        idField.setToolTipText("Название факультета");
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
        StringInput df = new StringInput();
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
            GroupTableFrame.create(GroupDAO.findGroupsFromFaculty(input));
            stringInputFrame.setVisible(false);
        }
    }

}
