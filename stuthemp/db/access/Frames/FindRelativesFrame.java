package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.PersonDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindRelativesFrame extends JFrame implements ActionListener {

    public static JTextField idField;
    JFrame deletionFrame = new JFrame();
    public JButton saveButton;
    private static int type;

    private void addActionListeners() {
        saveButton.addActionListener(this);
    }

    private void setDialogSetting(JTextField jTextField) {
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void setFrameParameters() {
        deletionFrame.setLayout(null);
        deletionFrame.setVisible(true);
        deletionFrame.setResizable(false);
        deletionFrame.setSize(200, 130);
        deletionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons() {
        idField = new JTextField();
        SpringConfig.createNumbersOnlyField(idField);
        idField.setText("99");
        idField.setToolTipText("Номер студенческого");
        setDialogSetting(idField);
        saveButton = new JButton("Найти");
    }

    private void addButtonsToFrame() {
        deletionFrame.add(idField);
        deletionFrame.add(saveButton);
    }

    private void setButtonsBounds() {
        idField.setBounds(1, 1, 200, 50);
        saveButton.setBounds(1, 50, 200, 50);
    }


    public static void create() {
        FindRelativesFrame fr = new FindRelativesFrame();
        fr.setFrameParameters();
        fr.createButtons();
        fr.addButtonsToFrame();
        fr.setButtonsBounds();
        fr.addActionListeners();
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
            }
            FullNameFrame.create(PersonDAO.findRelatives(id));
        }
    }
}
