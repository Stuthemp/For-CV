package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.FacultyDAO;
import stuthemp.db.access.QueriesExecution.GroupDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacultyByQuantity extends JFrame implements ActionListener {

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
        SpringConfig.createNumbersOnlyField(idField);
        idField.setText("1");
        idField.setToolTipText("Количество студентов");
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
        FacultyByQuantity df = new FacultyByQuantity();
        df.setFrameParameters();
        df.createButtons();
        df.addButtonsToFrame();
        df.setButtonsBounds();
        df.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            int input = 0;
            try {
                input = Integer.parseInt(idField.getText());
            } catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                FacultyByQuantity.create();
            }
            FacultyTableFrame.create(FacultyDAO.findSize(input));
            stringInputFrame.setVisible(false);
        }
    }

}
