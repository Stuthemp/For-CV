package stuthemp.db.access.Frames;

import stuthemp.db.access.QueriesExecution.FacultyDAO;
import stuthemp.db.access.QueriesExecution.StudentDAO;
import stuthemp.db.access.TableClasses.Faculty;
import stuthemp.db.access.TableClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFacultyFrame extends JFrame implements ActionListener {
    public static JTextField facultyNameField, facultyIdField, deanField,deaneryField;
    public static JButton saveButton;
    JFrame newFacultyFrame =  new JFrame("Новый студент");
    String id = "";

    private void setDialogSetting(JTextField jTextField){
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newFacultyFrame.setLayout(null);
        newFacultyFrame.setVisible(true);
        newFacultyFrame.setResizable(false);
        newFacultyFrame.setSize(200,400);
        newFacultyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        facultyNameField = new JTextField(2);
        facultyNameField.setText("Лингвистика");
        facultyNameField.setToolTipText("Название факультета");
        facultyIdField =new JTextField(6);
        facultyIdField.setToolTipText("Факультет");
        facultyIdField.setText("Л");
        deanField =new JTextField(20);
        deanField.setToolTipText("Декан");
        deanField.setText("Беспалов");
        deaneryField = new JTextField();
        deaneryField.setToolTipText("Деканат");
        deaneryField.setText("Кафедра лингвистики");
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        setDialogSetting(facultyIdField);
        setDialogSetting(facultyNameField);
        setDialogSetting(deanField);
        setDialogSetting(deaneryField);
    }

    private void createButtons(String id){

        Faculty faculty = FacultyDAO.showFaculty(id);
        facultyNameField = new JTextField(2);
        facultyNameField.setText(faculty.getName());
        facultyNameField.setToolTipText("Название факультета");
        facultyIdField =new JTextField(6);
        facultyIdField.setToolTipText("Факультет");
        facultyIdField.setText(faculty.getId());
        deanField =new JTextField(20);
        deanField.setToolTipText("Декан");
        deanField.setText(faculty.getDean());
        deaneryField = new JTextField();
        deaneryField.setToolTipText("Деканат");
        deaneryField.setText(faculty.getDeanery());
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        setDialogSetting(facultyIdField);
        setDialogSetting(facultyNameField);
        setDialogSetting(deanField);
        setDialogSetting(deaneryField);
    }

    private void addButtonsToFrame(){
        newFacultyFrame.add(facultyIdField);
        newFacultyFrame.add(facultyNameField);
        newFacultyFrame.add(deanField);
        newFacultyFrame.add(saveButton);
        newFacultyFrame.add(deaneryField);
    }

    private void setButtonsBounds(){
        facultyIdField.setBounds(1,1,200,50);
        facultyNameField.setBounds(1,50,200,50);
        deanField.setBounds(1,100,200,50);
        deaneryField.setBounds(1,150,200,50);
        saveButton.setBounds(1,300,200,50);

    }


    public static void create()
    {
        NewFacultyFrame nff = new NewFacultyFrame();
        nff.setFrameParameters();
        nff.createButtons();
        nff.addButtonsToFrame();
        nff.setButtonsBounds();
        nff.addActionListeners();
    }

    public static void create(String id)
    {
        NewFacultyFrame nff = new NewFacultyFrame();
        nff.id = id;
        nff.setFrameParameters();
        nff.createButtons(id);
        nff.addButtonsToFrame();
        nff.setButtonsBounds();
        nff.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String facultyId = facultyIdField.getText();
            String facultyName = facultyNameField.getText();
            String dean = deanField.getText();
            String deanery = deaneryField.getText();
            Faculty faculty = new Faculty();
            faculty.setId(facultyId);
            faculty.setName(facultyName);
            faculty.setDean(dean);
            faculty.setDeanery(deanery);
            if(id.equals("")) {
                FacultyDAO.saveFaculty(faculty);
            }
            else{
                FacultyDAO.updateFaculty(id,faculty);
            }
            newFacultyFrame.setVisible(false);
        }
    }


}
