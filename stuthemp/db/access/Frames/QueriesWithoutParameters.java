package stuthemp.db.access.Frames;

import stuthemp.db.access.QueriesExecution.*;
import stuthemp.db.access.TableClasses.FullName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueriesWithoutParameters implements ActionListener {

    JFrame queriesWithoutParams = new JFrame("Queries without parameters");

    JButton getMyRelatives, personBornIn2000, groupsWithMoreThan20Students, personsSortedBySurname,
            averageGroupSize, RelativesStudents, getFacultyTableInfo,back;


    private void setQueriesWithoutParamsParameters() {
        queriesWithoutParams.setLayout(null);
        queriesWithoutParams.setVisible(true);
        queriesWithoutParams.setResizable(false);
        queriesWithoutParams.setSize(800,600);
        queriesWithoutParams.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        getMyRelatives = new JButton("Родственники 8181037");
        personBornIn2000 = new JButton("Люди 2000 г.р.");
        groupsWithMoreThan20Students = new JButton("Группы > 20 студентов");
        personsSortedBySurname = new JButton("Люди отсортированные\nпо фамилии");
        averageGroupSize = new JButton("Среднее число студентов");
        RelativesStudents = new JButton("Студенты-родственники");
        getFacultyTableInfo = new JButton("Самый молодой человек");
        back = new JButton("Назад");
    }

    private void addButtonsToFrame(){
        queriesWithoutParams.add(getMyRelatives);
        queriesWithoutParams.add(personBornIn2000);
        queriesWithoutParams.add(groupsWithMoreThan20Students);
        queriesWithoutParams.add(personsSortedBySurname);
        queriesWithoutParams.add(averageGroupSize);
        queriesWithoutParams.add(RelativesStudents);
        queriesWithoutParams.add(getFacultyTableInfo);
        queriesWithoutParams.add(back);
    }

    private void setButtonsBounds(){
        personsSortedBySurname.setBounds(110,50,250,100);
        averageGroupSize.setBounds(450,50,230,100);
        getMyRelatives.setBounds(50,200,200,100);
        personBornIn2000.setBounds(300,200,200,100);
        groupsWithMoreThan20Students.setBounds(550,200,200,100);
        RelativesStudents.setBounds(110,350,250,100);
        getFacultyTableInfo.setBounds(450,350,230,100);
        back.setBounds(0,520,100,50);
    }

    private void addButtonsActionListeners(){
        getMyRelatives.addActionListener(this);
        personBornIn2000.addActionListener(this);
        groupsWithMoreThan20Students.addActionListener(this);
        getFacultyTableInfo.addActionListener(this);
        RelativesStudents.addActionListener(this);
        averageGroupSize.addActionListener(this);
        personsSortedBySurname.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        /**
         * Родственники студента с номером студенческого 8181037
         */
        if(e.getSource().equals(getMyRelatives)){
            FullNameFrame.create(PersonDAO.showMyRelatives());
        }
        /**
         * Люди, родившееся в 2000 году
         */
        if(e.getSource().equals(personBornIn2000)){
            PersonTableFrame.create(PersonDAO.peopleBornIn2000());
        }
        /**
         * Группы более чем с 20 студентами
         */
        if(e.getSource().equals(groupsWithMoreThan20Students)){
            GroupTableFrame.create(GroupDAO.groupsWithMoreThan20Students());
        }
        /**
         * Люди, отсортированные по фамилии
         */
        if(e.getSource().equals(personsSortedBySurname)){
            PersonTableFrame.create(PersonDAO.peopleSortedBySurname());
        }
        /**
         * Среднее количество студентов в группах
         */
        if(e.getSource().equals(averageGroupSize)){
            GroupSizeFrame.create(GroupDAO.averageGroupSize());
        }
        /**
         * Поиск студентов, являющихся родственниками других студентов
         */
        if(e.getSource().equals(RelativesStudents)){
            StudentTableFrame.create(StudentDAO.showRelativesStudents());
        }
        /**
         * Поиск самого молодого человека
         */
        if(e.getSource().equals(getFacultyTableInfo)){
            FullNameFrame.create(PersonDAO.findYoungestPerson());
        }
        if(e.getSource().equals(back)){
            queriesWithoutParams.setVisible(false);
            MainMenu.create();
        }

    }

    public static void create(){
        QueriesWithoutParameters qwp = new QueriesWithoutParameters();
        qwp.setQueriesWithoutParamsParameters();
        qwp.buttonObjectsCreation();
        qwp.addButtonsToFrame();
        qwp.setButtonsBounds();
        qwp.addButtonsActionListeners();
    }
}
