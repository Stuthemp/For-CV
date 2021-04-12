package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.GroupDAO;
import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.QueriesExecution.StudentRelativesDAO;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.StudentsRelatives;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewStudentsRelativeFrame extends JFrame implements ActionListener {
    public static JTextField relativeTypeIdField, studentIdField, personIdField;
    public static JButton saveButton;
    private int id = -1;
    JFrame newStudentsRelativeFrame =  new JFrame("Новый родственник");

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setDialogSetting(JTextField jTextField){
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void setFrameParameters(){
        newStudentsRelativeFrame.setLayout(null);
        newStudentsRelativeFrame.setVisible(true);
        newStudentsRelativeFrame.setResizable(false);
        newStudentsRelativeFrame.setSize(200,400);
        newStudentsRelativeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        relativeTypeIdField = new JTextField(2);
        SpringConfig.createNumbersOnlyField(relativeTypeIdField);
        relativeTypeIdField.setText("10");
        relativeTypeIdField.setToolTipText("Тип родственника");
        studentIdField =new JTextField(7);
        SpringConfig.createNumbersOnlyField(studentIdField);
        studentIdField.setToolTipText("Номер студенческого");
        studentIdField.setText("8181037");
        personIdField =new JTextField(2);
        SpringConfig.createNumbersOnlyField(personIdField);
        personIdField.setToolTipText("Идентификатор персоны");
        personIdField.setText("10");
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        setDialogSetting(relativeTypeIdField);
        setDialogSetting(studentIdField);
        setDialogSetting(personIdField);
    }

    private void createButtons(int id){
        if(StudentRelativesDAO.getMaxId() < id){
            newStudentsRelativeFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        StudentsRelatives studentsRelatives = StudentRelativesDAO.showStudentRelative(id);
        relativeTypeIdField = new JTextField(2);
        SpringConfig.createNumbersOnlyField(relativeTypeIdField);
        relativeTypeIdField.setText(String.valueOf(studentsRelatives.getRelativeTypeId()));
        relativeTypeIdField.setToolTipText("Тип родственника");
        studentIdField =new JTextField(7);
        SpringConfig.createNumbersOnlyField(studentIdField);
        studentIdField.setToolTipText("Номер студенческого");
        studentIdField.setEditable(false);
        studentIdField.setText(String.valueOf(studentsRelatives.getStudentId()));
        personIdField =new JTextField(2);
        SpringConfig.createNumbersOnlyField(personIdField);
        personIdField.setToolTipText("Идентификатор персоны");
        personIdField.setEditable(false);
        personIdField.setText(String.valueOf(studentsRelatives.getPersonId()));
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        setDialogSetting(relativeTypeIdField);
        setDialogSetting(studentIdField);
        setDialogSetting(personIdField);
    }

    private void addButtonsToFrame(){
        newStudentsRelativeFrame.add(studentIdField);
        newStudentsRelativeFrame.add(relativeTypeIdField);
        newStudentsRelativeFrame.add(personIdField);
        newStudentsRelativeFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        studentIdField.setBounds(1,1,200,50);
        relativeTypeIdField.setBounds(1,50,200,50);
        personIdField.setBounds(1,100,200,50);
        saveButton.setBounds(1,300,200,50);

    }

    public static void create(int id)
    {
        NewStudentsRelativeFrame nsrf = new NewStudentsRelativeFrame();
        nsrf.id = id;
        nsrf.setFrameParameters();
        nsrf.createButtons(id);
        nsrf.addButtonsToFrame();
        nsrf.setButtonsBounds();
        nsrf.addActionListeners();
    }

    public static void create()
    {
        NewStudentsRelativeFrame nsrf = new NewStudentsRelativeFrame();
        nsrf.setFrameParameters();
        nsrf.createButtons();
        nsrf.addButtonsToFrame();
        nsrf.setButtonsBounds();
        nsrf.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            int typeId = 1;
            int studentCardNumber = 1;
            int personId =  1;
            try{
                typeId = Integer.parseInt(relativeTypeIdField.getText());
            }
            catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }
            try {
                studentCardNumber = Integer.parseInt(studentIdField.getText());
            } catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }
            try {
                personId = Integer.parseInt(personIdField.getText());
            } catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }
            StudentsRelatives studentsRelatives  = new StudentsRelatives();
            studentsRelatives.setStudentId(studentCardNumber);
            studentsRelatives.setRelativeTypeId(typeId);
            studentsRelatives.setPersonId(personId);
            if(id > 0){
                StudentRelativesDAO.updateStudentsRelatives(id,studentsRelatives);
            }
            else {
                studentsRelatives.setId(1 + StudentRelativesDAO.getMaxId());
                StudentRelativesDAO.saveStudentsRelative(studentsRelatives);
            }
            newStudentsRelativeFrame.setVisible(false);
        }
    }


}
