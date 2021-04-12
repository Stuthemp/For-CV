package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.GroupDAO;
import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.TableClasses.Group;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewGroupFrame extends JFrame implements ActionListener {
    public static JTextField groupNumberField, facultyField, studentsQuantityField;
    public static JButton saveButton;
    private int id = -1;
    JFrame  newGroupFrame =  new JFrame("Новая группа");

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newGroupFrame.setLayout(null);
        newGroupFrame.setVisible(true);
        newGroupFrame.setResizable(false);
        newGroupFrame.setSize(200,400);
        newGroupFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        groupNumberField = new JTextField(2);
        groupNumberField.setText("11");
        groupNumberField.setToolTipText("Номер группы");
        SpringConfig.createNumbersOnlyField(groupNumberField);
        facultyField =new JTextField(6);
        facultyField.setToolTipText("Факультет");
        facultyField.setText("ПИН");
        studentsQuantityField =new JTextField(20);
        studentsQuantityField.setToolTipText("Количество студентов");
        studentsQuantityField.setText("0");
        SpringConfig.createNumbersOnlyField(studentsQuantityField);
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        facultyField.setFont(new Font("Dialog", Font.PLAIN, 14));
        facultyField.setHorizontalAlignment(JTextField.LEFT);
        groupNumberField.setFont(new Font("Dialog", Font.PLAIN, 14));
        groupNumberField.setHorizontalAlignment(JTextField.LEFT);
        studentsQuantityField.setFont(new Font("Dialog", Font.PLAIN, 14));
        studentsQuantityField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void createButtons(int id){
        if(GroupDAO.getMaxId() < id){
            newGroupFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        Group group = GroupDAO.showGroup(id);
        groupNumberField = new JTextField(2);
        groupNumberField.setText(String.valueOf(group.getGroupNumber()));
        groupNumberField.setToolTipText("Номер группы");
        SpringConfig.createNumbersOnlyField(groupNumberField);
        facultyField =new JTextField(6);
        facultyField.setToolTipText("Факультет");
        facultyField.setText(group.getFacultyId());
        studentsQuantityField =new JTextField(20);
        studentsQuantityField.setToolTipText("Количество студентов");
        studentsQuantityField.setText(String.valueOf(group.getStudentsQuantity()));
        SpringConfig.createNumbersOnlyField(studentsQuantityField);
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        facultyField.setFont(new Font("Dialog", Font.PLAIN, 14));
        facultyField.setHorizontalAlignment(JTextField.LEFT);
        groupNumberField.setFont(new Font("Dialog", Font.PLAIN, 14));
        groupNumberField.setHorizontalAlignment(JTextField.LEFT);
        studentsQuantityField.setFont(new Font("Dialog", Font.PLAIN, 14));
        studentsQuantityField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void addButtonsToFrame(){
        newGroupFrame.add(facultyField);
        newGroupFrame.add(groupNumberField);
        newGroupFrame.add(studentsQuantityField);
        newGroupFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        facultyField.setBounds(1,1,200,50);
        groupNumberField.setBounds(1,50,200,50);
        studentsQuantityField.setBounds(1,100,200,50);
        saveButton.setBounds(1,300,200,50);

    }

    public static void create(int id)
    {
        NewGroupFrame ngf = new NewGroupFrame();
        ngf.id = id;
        ngf.setFrameParameters();
        ngf.createButtons(id);
        ngf.addButtonsToFrame();
        ngf.setButtonsBounds();
        ngf.addActionListeners();
    }

    public static void create()
    {
        NewGroupFrame ngf = new NewGroupFrame();
        ngf.setFrameParameters();
        ngf.createButtons();
        ngf.addButtonsToFrame();
        ngf.setButtonsBounds();
        ngf.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String faculty = facultyField.getText();
            int groupNumber = 1;
            int studentsQuantity = 1;
            try{
                groupNumber = Integer.parseInt(groupNumberField.getText());
            }
            catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }
            try {
                studentsQuantity = Integer.parseInt(studentsQuantityField.getText());
            } catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }

            if(!(studentsQuantity > 0 && groupNumber >0)){
                newGroupFrame.setVisible(false);
                if(id > 0) NewGroupFrame.create(id);
                else NewGroupFrame.create();
                ErrorFrame.create(3);
                return;
            }
            Group group = new Group();
            group.setFacultyId(faculty);
            group.setGroupNumber(groupNumber);
            group.setStudentsQuantity(studentsQuantity);
            if(id > 0){
                GroupDAO.updateGroup(id,group);
            }
            else {
                group.setId(1 + GroupDAO.getMaxId());
                GroupDAO.saveGroup(group);
            }
            newGroupFrame.setVisible(false);
        }
    }


}
