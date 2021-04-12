package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddingToDBFrame implements ActionListener {

    JFrame queriesWithParameters = new JFrame("Parametrised queries");

    JButton addStudent, addPerson, addGroup, addStudentRelative, addRelativeType, addPrivilege, addFaculty,back;


    private void setQueriesWithParametersPageParameters() {
        queriesWithParameters.setLayout(null);
        queriesWithParameters.setVisible(true);
        queriesWithParameters.setResizable(false);
        queriesWithParameters.setSize(800,600);
        queriesWithParameters.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        addStudent = new JButton("Добавить студента");
        addPerson = new JButton("Добавить персону");
        addGroup = new JButton("Добавить группу");
        addStudentRelative = new JButton("Добавить родственника");
        addRelativeType = new JButton("Добавить тип родственника");
        addPrivilege = new JButton("Добавить льготу");
        addFaculty = new JButton("Добавить факультет");
        back = new JButton("Назад");
    }

    private void addButtonsToFrame(){
        queriesWithParameters.add(addStudent);
        queriesWithParameters.add(addPerson);
        queriesWithParameters.add(addGroup);
        queriesWithParameters.add(addStudentRelative);
        queriesWithParameters.add(addRelativeType);
        queriesWithParameters.add(addPrivilege);
        queriesWithParameters.add(addFaculty);
        queriesWithParameters.add(back);
    }

    private void setButtonsBounds(){
        addStudentRelative.setBounds(110,50,250,100);
        addRelativeType.setBounds(450,50,230,100);
        addStudent.setBounds(50,200,200,100);
        addPerson.setBounds(300,200,200,100);
        addGroup.setBounds(550,200,200,100);
        addPrivilege.setBounds(110,350,250,100);
        addFaculty.setBounds(450,350,230,100);
        back.setBounds(0,520,100,50);
    }

    private void addButtonsActionListeners(){
        addStudent.addActionListener(this);
        addPerson.addActionListener(this);
        addGroup.addActionListener(this);
        addFaculty.addActionListener(this);
        addPrivilege.addActionListener(this);
        addRelativeType.addActionListener(this);
        addStudentRelative.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(addStudent)){
            NewStudentFrame.create();

        }
        if(e.getSource().equals(addPerson)){
            NewPersonFrame.create();
        }
        if(e.getSource().equals(addGroup)){
            NewGroupFrame.create();
        }
        if(e.getSource().equals(addStudentRelative)){
            NewStudentsRelativeFrame.create();
        }
        if(e.getSource().equals(addRelativeType)){
            NewRelativeTypeFrame.create();
        }
        if(e.getSource().equals(addPrivilege)){
            NewPrivilegeFrame.create();
        }
        if(e.getSource().equals(addFaculty)){
            NewFacultyFrame.create();
        }
        if(e.getSource().equals(back)){
            queriesWithParameters.setVisible(false);
            QueriesWithParameters.create();
        }

    }

    public static void create(){
        AddingToDBFrame qwp = new AddingToDBFrame();
        qwp.setQueriesWithParametersPageParameters();
        qwp.buttonObjectsCreation();
        qwp.addButtonsToFrame();
        qwp.setButtonsBounds();
        qwp.addButtonsActionListeners();
    }
}
