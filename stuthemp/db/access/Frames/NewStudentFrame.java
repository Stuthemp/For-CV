package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.QueriesExecution.StudentDAO;
import stuthemp.db.access.TableClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewStudentFrame extends JFrame implements ActionListener {
    public static JTextField groupNumberField, facultyField, personIdField;
    public static JButton saveButton;
    int id = -1;
    JFrame newStudentFrame =  new JFrame("Новый студент");

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newStudentFrame.setLayout(null);
        newStudentFrame.setVisible(true);
        newStudentFrame.setResizable(false);
        newStudentFrame.setSize(200,400);
        newStudentFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        groupNumberField = new JTextField(2);
        groupNumberField.setText("11");
        groupNumberField.setToolTipText("Номер группы");
        SpringConfig.createNumbersOnlyField(groupNumberField);
        facultyField =new JTextField(6);
        facultyField.setToolTipText("Факультет");
        facultyField.setText("ПИН");
        personIdField =new JTextField(20);
        personIdField.setToolTipText("Идентификатор персоны");
        personIdField.setText("0");
        SpringConfig.createNumbersOnlyField(personIdField);
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        facultyField.setFont(new Font("Dialog", Font.PLAIN, 14));
        facultyField.setHorizontalAlignment(JTextField.LEFT);
        groupNumberField.setFont(new Font("Dialog", Font.PLAIN, 14));
        groupNumberField.setHorizontalAlignment(JTextField.LEFT);
        personIdField.setFont(new Font("Dialog", Font.PLAIN, 14));
        personIdField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void createButtons(int id){
        if(PrivilegeDAO.getMaxId() < id){
            newStudentFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        Student student = StudentDAO.showStudent(id);
        groupNumberField = new JTextField(2);
        groupNumberField.setText(String.valueOf(student.getGroupNumber()));
        groupNumberField.setToolTipText("Номер группы");
        SpringConfig.createNumbersOnlyField(groupNumberField);
        facultyField =new JTextField(6);
        facultyField.setToolTipText("Факультет");
        facultyField.setText(student.getFacultyId());
        personIdField =new JTextField(20);
        personIdField.setToolTipText("Идентификатор персоны");
        personIdField.setText(String.valueOf(student.getPersonId()));
        personIdField.setEditable(false);
        SpringConfig.createNumbersOnlyField(personIdField);
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        facultyField.setFont(new Font("Dialog", Font.PLAIN, 14));
        facultyField.setHorizontalAlignment(JTextField.LEFT);
        groupNumberField.setFont(new Font("Dialog", Font.PLAIN, 14));
        groupNumberField.setHorizontalAlignment(JTextField.LEFT);
        personIdField.setFont(new Font("Dialog", Font.PLAIN, 14));
        personIdField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void addButtonsToFrame(){
        newStudentFrame.add(facultyField);
        newStudentFrame.add(groupNumberField);
        newStudentFrame.add(personIdField);
        newStudentFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        facultyField.setBounds(1,1,200,50);
        groupNumberField.setBounds(1,50,200,50);
        personIdField.setBounds(1,100,200,50);
        saveButton.setBounds(1,300,200,50);

    }

    public static void create(int id)
    {
        NewStudentFrame ngf = new NewStudentFrame();
        ngf.id = id;
        ngf.setFrameParameters();
        ngf.createButtons(id);
        ngf.addButtonsToFrame();
        ngf.setButtonsBounds();
        ngf.addActionListeners();
    }

    public static void create()
    {
        NewStudentFrame ngf = new NewStudentFrame();
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
            int personId = 1;
            try{
                groupNumber = Integer.parseInt(groupNumberField.getText());
            }
            catch (Exception ex){
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
            Student student = new Student();
            student.setFacultyId(faculty);
            student.setGroupNumber(groupNumber);
            student.setPersonId(personId);
            if(id > 0){
                StudentDAO.updateStudent(id,student);
            }
            else {
                student.setId(1 + StudentDAO.getMaxId());
                StudentDAO.saveStudent(student);
            }
            newStudentFrame.setVisible(false);
        }
    }


}
