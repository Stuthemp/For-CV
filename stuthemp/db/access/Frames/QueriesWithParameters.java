package stuthemp.db.access.Frames;

import stuthemp.db.access.QueriesExecution.PersonDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueriesWithParameters implements ActionListener {

        JFrame queriesWithoutParams = new JFrame("Queries with parameters");

        JButton getMyRelatives, updateTable, facultiesWithBigGroups, personsSortedBySurname,
                findGroupsFromFaculty, addToTable, deleteFromTable,back;


        private void setQueriesWithoutParamsParameters() {
            queriesWithoutParams.setLayout(null);
            queriesWithoutParams.setVisible(true);
            queriesWithoutParams.setResizable(false);
            queriesWithoutParams.setSize(800,600);
            queriesWithoutParams.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private void buttonObjectsCreation(){
            getMyRelatives = new JButton("Родственники студента");
            updateTable = new JButton("Обновление");
            facultiesWithBigGroups = new JButton("Фаукльтеты с группами");
            personsSortedBySurname = new JButton("Люди отсортированные\nпо фамилии");
            findGroupsFromFaculty = new JButton("Поиск групп с  факультета");
            addToTable = new JButton("Добавление");
            deleteFromTable = new JButton("Удаление");
            back = new JButton("Назад");
        }

        private void addButtonsToFrame(){
            queriesWithoutParams.add(getMyRelatives);
            queriesWithoutParams.add(updateTable);
            queriesWithoutParams.add(facultiesWithBigGroups);
            queriesWithoutParams.add(personsSortedBySurname);
            queriesWithoutParams.add(findGroupsFromFaculty);
            queriesWithoutParams.add(addToTable);
            queriesWithoutParams.add(deleteFromTable);
            queriesWithoutParams.add(back);
        }

        private void setButtonsBounds(){
            personsSortedBySurname.setBounds(110,50,250,100);
            findGroupsFromFaculty.setBounds(450,50,230,100);
            getMyRelatives.setBounds(50,200,200,100);
            updateTable.setBounds(300,200,200,100);
            facultiesWithBigGroups.setBounds(550,200,200,100);
            addToTable.setBounds(110,350,250,100);
            deleteFromTable.setBounds(450,350,230,100);
            back.setBounds(0,520,100,50);
        }

        private void addButtonsActionListeners(){
            getMyRelatives.addActionListener(this);
            updateTable.addActionListener(this);
            facultiesWithBigGroups.addActionListener(this);
            deleteFromTable.addActionListener(this);
            addToTable.addActionListener(this);
            findGroupsFromFaculty.addActionListener(this);
            personsSortedBySurname.addActionListener(this);
            back.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            /**
             * Родственники студента с выбранным номером студенческого
             */
            if(e.getSource().equals(getMyRelatives)){
                FindRelativesFrame.create();
            }
            /**
             * Запросы на изменение данных
             */
            if(e.getSource().equals(updateTable)){
                EditingDbFrame.create();
                queriesWithoutParams.setVisible(false);
            }
            /**
             * Поиск факультетов, на которых есть группы с числом студентов больше заданного
             */
            if(e.getSource().equals(facultiesWithBigGroups)){
                FacultyByQuantity.create();
            }
            /**
             * Поиск студентов  с заданной фамилией
             */
            if(e.getSource().equals(personsSortedBySurname)){
                StudentsBySurname.create();
            }
            /**
             * Поиск групп с факультета
             */
            if(e.getSource().equals(findGroupsFromFaculty)){
                StringInput.create();
            }
            /**
             * Запросы на добавление данных
             */
            if(e.getSource().equals(addToTable)){
                AddingToDBFrame.create();
            }
            /**
             * Запросы на удаление данных
             */
            if(e.getSource().equals(deleteFromTable)){
                DeletingFromDBFrame.create();
                queriesWithoutParams.setVisible(false);
            }
            if(e.getSource().equals(back)){
                queriesWithoutParams.setVisible(false);
                MainMenu.create();
            }

        }

        public static void create(){
            QueriesWithParameters qwp = new QueriesWithParameters();
            qwp.setQueriesWithoutParamsParameters();
            qwp.buttonObjectsCreation();
            qwp.addButtonsToFrame();
            qwp.setButtonsBounds();
            qwp.addButtonsActionListeners();
        }
}

