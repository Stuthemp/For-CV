package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletingFromDBFrame implements ActionListener {

    JFrame deletingFromDbFrame = new JFrame("Удаление данных");

    JButton deleteStudent, deletePerson, deleteGroup, deleteStudentRelative, deleteRelativeType, deletePrivilege, deleteFaculty,back;


    private void setQueriesWithParametersPageParameters() {
        deletingFromDbFrame.setLayout(null);
        deletingFromDbFrame.setVisible(true);
        deletingFromDbFrame.setResizable(false);
        deletingFromDbFrame.setSize(800,600);
        deletingFromDbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        deleteStudent = new JButton("Удалить студента");
        deletePerson = new JButton("Удалить персону");
        deleteGroup = new JButton("Удалить группу");
        deleteStudentRelative = new JButton("Удалить родственника");
        deleteRelativeType = new JButton("Удалить тип родственника");
        deletePrivilege = new JButton("Удалить льготу");
        deleteFaculty = new JButton("Удалить факультет");
        back = new JButton("Назад");
    }

    private void addButtonsToFrame(){
        deletingFromDbFrame.add(deleteStudent);
        deletingFromDbFrame.add(deletePerson);
        deletingFromDbFrame.add(deleteGroup);
        deletingFromDbFrame.add(deleteStudentRelative);
        deletingFromDbFrame.add(deleteRelativeType);
        deletingFromDbFrame.add(deletePrivilege);
        deletingFromDbFrame.add(deleteFaculty);
        deletingFromDbFrame.add(back);
    }

    private void setButtonsBounds(){
        deleteStudentRelative.setBounds(110,50,250,100);
        deleteRelativeType.setBounds(450,50,230,100);
        deleteStudent.setBounds(50,200,200,100);
        deletePerson.setBounds(300,200,200,100);
        deleteGroup.setBounds(550,200,200,100);
        deletePrivilege.setBounds(110,350,250,100);
        deleteFaculty.setBounds(450,350,230,100);
        back.setBounds(0,520,100,50);
    }

    private void addButtonsActionListeners(){
        deleteStudent.addActionListener(this);
        deletePerson.addActionListener(this);
        deleteGroup.addActionListener(this);
        deleteFaculty.addActionListener(this);
        deletePrivilege.addActionListener(this);
        deleteRelativeType.addActionListener(this);
        deleteStudentRelative.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(deleteStudent)){
            DeletionFrame.create(1);

        }
        if(e.getSource().equals(deletePerson)){
            DeletionFrame.create(2);
        }
        if(e.getSource().equals(deleteGroup)){
            DeletionFrame.create(3);
        }
        if(e.getSource().equals(deleteStudentRelative)){
            DeletionFrame.create(4);
        }
        if(e.getSource().equals(deleteRelativeType)){
            DeletionFrame.create(5);
        }
        if(e.getSource().equals(deletePrivilege)){
            DeletionFrame.create(6);
        }
        if(e.getSource().equals(deleteFaculty)){
            DeletionFrame.create(7);
        }
        if(e.getSource().equals(back)){
            deletingFromDbFrame.setVisible(false);
            QueriesWithParameters.create();
        }

    }

    public static void create(){
        DeletingFromDBFrame dfd = new DeletingFromDBFrame();
        dfd.setQueriesWithParametersPageParameters();
        dfd.buttonObjectsCreation();
        dfd.addButtonsToFrame();
        dfd.setButtonsBounds();
        dfd.addButtonsActionListeners();
    }
}
