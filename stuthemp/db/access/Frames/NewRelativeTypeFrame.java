package stuthemp.db.access.Frames;

import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.QueriesExecution.RelativesTypeDAO;
import stuthemp.db.access.TableClasses.RelativesType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRelativeTypeFrame extends JFrame implements ActionListener {
    public static JTextField typeNameField;
    public static JButton saveButton;
    private int id = -1;
    JFrame newRelativeTypeFrame =  new JFrame("Новй вид родственника");

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newRelativeTypeFrame.setLayout(null);
        newRelativeTypeFrame.setVisible(true);
        newRelativeTypeFrame.setResizable(false);
        newRelativeTypeFrame.setSize(200,400);
        newRelativeTypeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        typeNameField =new JTextField(20);
        typeNameField.setToolTipText("Имя типа");
        typeNameField.setText("Двоюродный дедушка");
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        typeNameField.setFont(new Font("Dialog", Font.PLAIN, 14));
        typeNameField.setHorizontalAlignment(JTextField.LEFT);

    }

    private void createButtons(int id){
        if(RelativesTypeDAO.getMaxId() < id){
            newRelativeTypeFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        RelativesType relativesType = RelativesTypeDAO.showRelativesTypes(id);
        typeNameField =new JTextField(20);
        typeNameField.setToolTipText("Имя типа");
        typeNameField.setText(relativesType.getName());
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        typeNameField.setFont(new Font("Dialog", Font.PLAIN, 14));
        typeNameField.setHorizontalAlignment(JTextField.LEFT);

    }

    private void addButtonsToFrame(){
        newRelativeTypeFrame.add(typeNameField);
        newRelativeTypeFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        typeNameField.setBounds(1,1,200,50);

        saveButton.setBounds(1,300,200,50);

    }

    public static void create(int id)
    {
        NewRelativeTypeFrame nrtf = new NewRelativeTypeFrame();
        nrtf.id = id;
        nrtf.setFrameParameters();
        nrtf.createButtons(id);
        nrtf.addButtonsToFrame();
        nrtf.setButtonsBounds();
        nrtf.addActionListeners();
    }

    public static void create()
    {
        NewRelativeTypeFrame nrtf = new NewRelativeTypeFrame();
        nrtf.setFrameParameters();
        nrtf.createButtons();
        nrtf.addButtonsToFrame();
        nrtf.setButtonsBounds();
        nrtf.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String typeName = typeNameField.getText();
            RelativesType relativesType = new RelativesType();
            relativesType.setName(typeName);
            if(id > 0){
                RelativesTypeDAO.updateRelativesType(id,relativesType);
            }
            else {
                relativesType.setId(1 + RelativesTypeDAO.getMaxId());
                RelativesTypeDAO.saveRelativeType(relativesType);
            }
            newRelativeTypeFrame.setVisible(false);
        }
    }


}
