package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditingDbFrame implements ActionListener {

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
        addStudent = new JButton("Изменить студента");
        addPerson = new JButton("Изменить персону");
        addGroup = new JButton("Изменить группу");
        addStudentRelative = new JButton("Изменить родственника");
        addRelativeType = new JButton("Изменить тип родственника");
        addPrivilege = new JButton("Изменить льготу");
        addFaculty = new JButton("Изменить факультет");
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
            EditingFrame.create(1);

        }
        if(e.getSource().equals(addPerson)){
            EditingFrame.create(2);
        }
        if(e.getSource().equals(addGroup)){
            EditingFrame.create(3);
        }
        if(e.getSource().equals(addStudentRelative)){
            EditingFrame.create(4);
        }
        if(e.getSource().equals(addRelativeType)){
            EditingFrame.create(5);
        }
        if(e.getSource().equals(addPrivilege)){
            EditingFrame.create(6);
        }
        if(e.getSource().equals(addFaculty)){
            EditingFrame.create(7);
        }
        if(e.getSource().equals(back)){
            queriesWithParameters.setVisible(false);
            QueriesWithParameters.create();
        }

    }

    public static void create(){
        EditingDbFrame edb = new EditingDbFrame();
        edb.setQueriesWithParametersPageParameters();
        edb.buttonObjectsCreation();
        edb.addButtonsToFrame();
        edb.setButtonsBounds();
        edb.addButtonsActionListeners();
    }
}
