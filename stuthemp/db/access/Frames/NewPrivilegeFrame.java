package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.TableClasses.Privilege;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewPrivilegeFrame extends JFrame implements ActionListener {
    public static JTextField privilegeTypeField, reasonField, studentCardNumberField;
    public static JButton saveButton;
    public static JFormattedTextField dateFormattedTextField;
    static final String patternString = "##.##.####";
    private int id = -1;
    JFrame newPrivilegeFrame =  new JFrame("Новая льгота");

    private void setDialogSetting(JTextField jTextField){
        jTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        jTextField.setHorizontalAlignment(JTextField.LEFT);
    }

    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newPrivilegeFrame.setLayout(null);
        newPrivilegeFrame.setVisible(true);
        newPrivilegeFrame.setResizable(false);
        newPrivilegeFrame.setSize(300,400);
        newPrivilegeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        dateFormattedTextField = new JFormattedTextField(format);
        try {
            dateFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(patternString)));
        } catch (ParseException e) {
            dateFormattedTextField.setText("01.01.2000");
            e.printStackTrace();
        }
        dateFormattedTextField.setText("01.01.2000");
        privilegeTypeField =new JTextField(30);
        privilegeTypeField.setToolTipText("Тип льготы");
        privilegeTypeField.setText("Материальная помощь");
        reasonField =new JTextField(30);
        reasonField.setToolTipText("Причина выдачи");
        reasonField.setText("Сложное материальное положение");
        studentCardNumberField = new JTextField();
        SpringConfig.createNumbersOnlyField(studentCardNumberField);
        studentCardNumberField.setToolTipText("Номер студенческого");
        studentCardNumberField.setText("8181037");
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        setDialogSetting(privilegeTypeField);
        setDialogSetting(reasonField);
        setDialogSetting(studentCardNumberField);
    }

    private void createButtons(int id) {
        if(PrivilegeDAO.getMaxId() < id){
            newPrivilegeFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        Privilege privilege = PrivilegeDAO.showPrivilege(id);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        dateFormattedTextField = new JFormattedTextField(format);
        try {
            dateFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(patternString)));
        } catch (ParseException e) {
            dateFormattedTextField.setText("01.01.2000");
            e.printStackTrace();
        }
        dateFormattedTextField.setEditable(false);
        privilegeTypeField =new JTextField(30);
        privilegeTypeField.setToolTipText("Тип льготы");
        privilegeTypeField.setText(privilege.getPrivilegeType());
        reasonField =new JTextField(30);
        reasonField.setToolTipText("Причина выдачи");
        reasonField.setText(privilege.getReason());
        studentCardNumberField = new JTextField();
        SpringConfig.createNumbersOnlyField(studentCardNumberField);
        studentCardNumberField.setToolTipText("Номер студенческого");
        studentCardNumberField.setText(String.valueOf(privilege.getStudentId()));
        studentCardNumberField.setEditable(false);
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        setDialogSetting(privilegeTypeField);
        setDialogSetting(reasonField);
        setDialogSetting(studentCardNumberField);
    }

    private void addButtonsToFrame(){
        newPrivilegeFrame.add(privilegeTypeField);
        newPrivilegeFrame.add(reasonField);
        newPrivilegeFrame.add(saveButton);
        newPrivilegeFrame.add(dateFormattedTextField);
        newPrivilegeFrame.add(studentCardNumberField);
    }

    private void setButtonsBounds(){
        privilegeTypeField.setBounds(1,1,300,50);
        dateFormattedTextField.setBounds(1,50,300,50);
        reasonField.setBounds(1,100,300,50);
        studentCardNumberField.setBounds(1,150,300,50);
        saveButton.setBounds(1,300,300,50);

    }


    public static void create(int id)
    {
        NewPrivilegeFrame npf = new NewPrivilegeFrame();
        npf.id = id;
        npf.setFrameParameters();
        npf.createButtons(id);
        npf.addButtonsToFrame();
        npf.setButtonsBounds();
        npf.addActionListeners();
    }

    public static void create()
    {
        NewPrivilegeFrame npf = new NewPrivilegeFrame();
        npf.setFrameParameters();
        npf.createButtons();
        npf.addButtonsToFrame();
        npf.setButtonsBounds();
        npf.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String privilegeType = privilegeTypeField.getText();
            String reason  = reasonField.getText();
            int studentCardNumber = 1;
            try{
                studentCardNumber = Integer.parseInt(studentCardNumberField.getText());
            }
            catch (Exception ex){
                Frame frame = getFrames()[0];
                frame.setVisible(false);
                NewGroupFrame.create();
            }

            Privilege privilege = new Privilege();
            privilege.setPrivilegeType(privilegeType);
            privilege.setReason(reason);
            privilege.setStudentId(studentCardNumber);

            if(id > 0){
                Privilege oldPrivilege = PrivilegeDAO.showPrivilege(id);
                privilege.setDateOfIssue(oldPrivilege.getDateOfIssue());
                privilege.setId(id);
                PrivilegeDAO.updatePrivilege(id,privilege);
            }
            else {
                java.util.Date date = new Date(1);
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormattedTextField.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                Date  dateTime = new Date(1);
                dateTime = new java.sql.Date(date.getTime());
                privilege.setDateOfIssue(dateTime);
                privilege.setId(1 + PrivilegeDAO.getMaxId());
                PrivilegeDAO.savePrivilege(privilege);
            }
            newPrivilegeFrame.setVisible(false);
        }

    }


}
