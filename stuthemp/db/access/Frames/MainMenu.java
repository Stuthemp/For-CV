package stuthemp.db.access.Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {


    JFrame mainPage = new JFrame("Main menu");

    JButton defaultTablesAccess, queriesWithoutParameters, queriesWithParameters;

    private void setMainPageParameters(){
        mainPage.setLayout(null);
        mainPage.setVisible(true);
        mainPage.setResizable(false);
        mainPage.setSize(800,600);
        mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonObjectsCreation(){
        defaultTablesAccess = new JButton("Запросы на данные таблиц");
        queriesWithoutParameters = new JButton("Запросы без праметров");
        queriesWithParameters = new JButton("Запросы с параметрами");
    }

    private void addButtonsToFrame(){
        mainPage.add(defaultTablesAccess);
        mainPage.add(queriesWithoutParameters);
        mainPage.add(queriesWithParameters);
    }

    private void setButtonsBounds(){
        defaultTablesAccess.setBounds(40,150,200,200);
        queriesWithoutParameters.setBounds(280,150,200,200);
        queriesWithParameters.setBounds(520,150,200,200);
    }

    private void addButtonsActionListeners(){
        defaultTablesAccess.addActionListener(this);
        queriesWithoutParameters.addActionListener(this);
        queriesWithParameters.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(defaultTablesAccess)){
            DefaultTablesAccess.create();
            mainPage.setVisible(false);
        }
        if(e.getSource().equals(queriesWithoutParameters)){
            QueriesWithoutParameters.create();
            mainPage.setVisible(false);
        }
        if(e.getSource().equals(queriesWithParameters)){
            QueriesWithParameters.create();
            mainPage.setVisible(false);
        }
    }

    public static void create(){
        MainMenu mm = new MainMenu();
        mm.setMainPageParameters();
        mm.buttonObjectsCreation();
        mm.addButtonsToFrame();
        mm.setButtonsBounds();
        mm.addButtonsActionListeners();
    }
}
