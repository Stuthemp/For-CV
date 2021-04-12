package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatingDBFrame implements ActionListener {

    JFrame updatingDbFrame = new JFrame("Обновление данных");

    JButton changeStudent, changePerson, changeGroup, changeStudentRelative, changeRelativeType, changePrivilege, changeFaculty,back;


    private void setQueriesWithParametersPageParameters() {
        updatingDbFrame.setLayout(null);
        updatingDbFrame.setVisible(true);
        updatingDbFrame.setResizable(false);
        updatingDbFrame.setSize(800,600);
        updatingDbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        changeStudent = new JButton("Добавить студента");
        changePerson = new JButton("Добавить персону");
        changeGroup = new JButton("Добавить группу");
        changeStudentRelative = new JButton("Добавить родственника");
        changeRelativeType = new JButton("Добавить тип родственника");
        changePrivilege = new JButton("Добавить льготу");
        changeFaculty = new JButton("Добавить факультет");
        back = new JButton("Назад");
    }

    private void addButtonsToFrame(){
        updatingDbFrame.add(changeStudent);
        updatingDbFrame.add(changePerson);
        updatingDbFrame.add(changeGroup);
        updatingDbFrame.add(changeStudentRelative);
        updatingDbFrame.add(changeRelativeType);
        updatingDbFrame.add(changePrivilege);
        updatingDbFrame.add(changeFaculty);
        updatingDbFrame.add(back);
    }

    private void setButtonsBounds(){
        changeStudentRelative.setBounds(110,50,250,100);
        changeRelativeType.setBounds(450,50,230,100);
        changeStudent.setBounds(50,200,200,100);
        changePerson.setBounds(300,200,200,100);
        changeGroup.setBounds(550,200,200,100);
        changePrivilege.setBounds(110,350,250,100);
        changeFaculty.setBounds(450,350,230,100);
        back.setBounds(0,520,100,50);
    }

    private void addButtonsActionListeners(){
        changeStudent.addActionListener(this);
        changePerson.addActionListener(this);
        changeGroup.addActionListener(this);
        changeFaculty.addActionListener(this);
        changePrivilege.addActionListener(this);
        changeRelativeType.addActionListener(this);
        changeStudentRelative.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(changeStudent)){
            NewStudentFrame.create();

        }
        if(e.getSource().equals(changePerson)){
            NewPersonFrame.create();
        }
        if(e.getSource().equals(changeGroup)){
            NewGroupFrame.create();
        }
        if(e.getSource().equals(changeStudentRelative)){
            NewStudentsRelativeFrame.create();
        }
        if(e.getSource().equals(changeRelativeType)){
            NewRelativeTypeFrame.create();
        }
        if(e.getSource().equals(changePrivilege)){
            NewPrivilegeFrame.create();
        }
        if(e.getSource().equals(changeFaculty)){
            NewFacultyFrame.create();
        }
        if(e.getSource().equals(back)){
            updatingDbFrame.setVisible(false);
            MainMenu.create();
        }

    }

    public static void create(){
        UpdatingDBFrame udf = new UpdatingDBFrame();
        udf.setQueriesWithParametersPageParameters();
        udf.buttonObjectsCreation();
        udf.addButtonsToFrame();
        udf.setButtonsBounds();
        udf.addButtonsActionListeners();
    }
}
