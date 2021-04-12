package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultTablesAccess implements ActionListener {

    JFrame defaultQueryPage = new JFrame("Default queries");

    JButton getStudentsTableInfo, getPersonsTableInfo, getGroupsTableInfo, getStudentsRelativesTableInfo,
            getRelativesTypeTableInfo, getPrivilegesTableInfo, getFacultyTableInfo,back;


    private void setDefaultQueryPageParameters() {
        defaultQueryPage.setLayout(null);
        defaultQueryPage.setVisible(true);
        defaultQueryPage.setResizable(false);
        defaultQueryPage.setSize(800,600);
        defaultQueryPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        getStudentsTableInfo = new JButton("Таблица \"Студенты\"");
        getPersonsTableInfo = new JButton("Таблица \"Люди\"");
        getGroupsTableInfo = new JButton("Таблица \"Группы\"");
        getStudentsRelativesTableInfo = new JButton("Таблица \"Родственники студентов\"");
        getRelativesTypeTableInfo = new JButton("Таблица \"Тип родственников\"");
        getPrivilegesTableInfo = new JButton("Таблица \"Льготы\"");
        getFacultyTableInfo = new JButton("Таблица \"Факультеты\"");
        back = new JButton("Назад");
    }

    private void addButtonsToFrame(){
        defaultQueryPage.add(getStudentsTableInfo);
        defaultQueryPage.add(getPersonsTableInfo);
        defaultQueryPage.add(getGroupsTableInfo);
        defaultQueryPage.add(getStudentsRelativesTableInfo);
        defaultQueryPage.add(getRelativesTypeTableInfo);
        defaultQueryPage.add(getPrivilegesTableInfo);
        defaultQueryPage.add(getFacultyTableInfo);
        defaultQueryPage.add(back);
    }

    private void setButtonsBounds(){
        getStudentsRelativesTableInfo.setBounds(110,50,250,100);
        getRelativesTypeTableInfo.setBounds(450,50,230,100);
        getStudentsTableInfo.setBounds(50,200,200,100);
        getPersonsTableInfo.setBounds(300,200,200,100);
        getGroupsTableInfo.setBounds(550,200,200,100);
        getPrivilegesTableInfo.setBounds(110,350,250,100);
        getFacultyTableInfo.setBounds(450,350,230,100);
        back.setBounds(0,520,100,50);
    }

    private void addButtonsActionListeners(){
        getStudentsTableInfo.addActionListener(this);
        getPersonsTableInfo.addActionListener(this);
        getGroupsTableInfo.addActionListener(this);
        getFacultyTableInfo.addActionListener(this);
        getPrivilegesTableInfo.addActionListener(this);
        getRelativesTypeTableInfo.addActionListener(this);
        getStudentsRelativesTableInfo.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(getStudentsTableInfo)){
            StudentTableFrame.create(StudentDAO.showStudentTable());

        }
        if(e.getSource().equals(getPersonsTableInfo)){
            PersonTableFrame.create(PersonDAO.showPersonTable());
        }
        if(e.getSource().equals(getGroupsTableInfo)){
            GroupTableFrame.create(GroupDAO.showGroupsTable());
        }
        if(e.getSource().equals(getStudentsRelativesTableInfo)){
            StudentRelativesTableFrame.create(StudentRelativesDAO.showStudentRelativesTable());
        }
        if(e.getSource().equals(getRelativesTypeTableInfo)){
            RelativesTypeTableFrame.create(RelativesTypeDAO.showRelativeTypeTable());
        }
        if(e.getSource().equals(getPrivilegesTableInfo)){
            PrivilegeTableFrame.create(PrivilegeDAO.showPrivilegeTable());
        }
        if(e.getSource().equals(getFacultyTableInfo)){
            FacultyTableFrame.create(FacultyDAO.showFacultyTable());
        }
        if(e.getSource().equals(back)){
            defaultQueryPage.setVisible(false);
            MainMenu.create();
        }

    }

    public static void create(){
        DefaultTablesAccess dta = new DefaultTablesAccess();
        dta.setDefaultQueryPageParameters();
        dta.buttonObjectsCreation();
        dta.addButtonsToFrame();
        dta.setButtonsBounds();
        dta.addButtonsActionListeners();
    }
}
