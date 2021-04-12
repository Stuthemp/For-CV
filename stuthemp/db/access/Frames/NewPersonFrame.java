package stuthemp.db.access.Frames;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.QueriesExecution.PersonDAO;
import stuthemp.db.access.QueriesExecution.PrivilegeDAO;
import stuthemp.db.access.TableClasses.Person;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class NewPersonFrame extends JFrame implements ActionListener {

    public static JTextField surnameField,nameField,middleNameField,addressField,genderField;
    public static JFormattedTextField dateTextField;
    JFrame  newPersonFrame =  new JFrame("Новый человек");
    public static JButton saveButton;
    static final String pattern = "##.##.####";
    private final Pattern genderPattern = Pattern.compile("Муж|Жен");
    private int id = -1;


    private void addActionListeners(){
        saveButton.addActionListener(this);
    }

    private void setFrameParameters(){
        newPersonFrame.setLayout(null);
        newPersonFrame.setVisible(true);
        newPersonFrame.setResizable(false);
        newPersonFrame.setSize(200,400);
        newPersonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createButtons(){
        surnameField = new JTextField();
        surnameField.setToolTipText("Фамилия");
        nameField =new JTextField(20);
        nameField.setToolTipText("Имя");
        middleNameField=new JTextField(20);
        middleNameField.setToolTipText("Отчество");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        dateTextField = new JFormattedTextField(format);
        try {
            dateTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
        } catch (ParseException e) {
            dateTextField.setText("01.01.2000");
            e.printStackTrace();
        }
        dateTextField.setText("01.01.2000");
        addressField =new JTextField(20);
        addressField.setToolTipText("Адрес");
        genderField =new JTextField(4);
        genderField.setToolTipText("Пол");
        saveButton = new JButton("Добавить");
        // Настройка шрифта
        SpringConfig.setDialogText(nameField);
        SpringConfig.setDialogText(surnameField);
        SpringConfig.setDialogText(middleNameField);
        SpringConfig.setDialogText(genderField);
        SpringConfig.setDialogText(addressField);
    }

    private void createButtons(int id){
        if(PersonDAO.getMaxId() < id){
            newPersonFrame.setVisible(false);
            EditingFrame.create(6);
            ErrorFrame.create(1);
            return;
        }
        Person person = PersonDAO.showPerson(id);
        surnameField = new JTextField();
        surnameField.setText(person.getSurname());
        surnameField.setToolTipText("Фамилия");
        nameField =new JTextField(20);
        nameField.setToolTipText("Имя");
        nameField.setText(person.getName());
        middleNameField=new JTextField(20);
        middleNameField.setToolTipText("Отчество");
        middleNameField.setText(person.getMiddlename());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        dateTextField = new JFormattedTextField(format);
        try {
            dateTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
        } catch (ParseException e) {
            dateTextField.setText("01.01.2000");
            e.printStackTrace();
        }
        dateTextField.setEditable(false);
        addressField =new JTextField(20);
        addressField.setToolTipText("Адрес");
        addressField.setText(person.getAddress());
        genderField =new JTextField(4);
        genderField.setToolTipText("Пол");
        genderField.setText(person.getGender());
        saveButton = new JButton("Обновить");
        // Настройка шрифта
        SpringConfig.setDialogText(nameField);
        SpringConfig.setDialogText(surnameField);
        SpringConfig.setDialogText(middleNameField);
        SpringConfig.setDialogText(genderField);
        SpringConfig.setDialogText(addressField);
    }

    private void addButtonsToFrame(){
        newPersonFrame.add(nameField);
        newPersonFrame.add(surnameField);
        newPersonFrame.add(middleNameField);
        newPersonFrame.add(dateTextField);
        newPersonFrame.add(addressField);
        newPersonFrame.add(genderField);
        newPersonFrame.add(saveButton);
    }

    private void setButtonsBounds(){
        nameField.setBounds(1,1,200,50);
        surnameField.setBounds(1,50,200,50);
        middleNameField.setBounds(1,100,200,50);
        dateTextField.setBounds(1,150,200,50);
        addressField.setBounds(1,200,200,50);
        genderField.setBounds(1,250,200,50);
        saveButton.setBounds(1,300,200,50);
    }


    public static void create()
    {
        NewPersonFrame npf = new NewPersonFrame();
        npf.setFrameParameters();
        npf.createButtons();
        npf.addButtonsToFrame();
        npf.setButtonsBounds();
        npf.addActionListeners();
    }

    public static void create(int id)
    {
        NewPersonFrame npf = new NewPersonFrame();
        npf.setFrameParameters();
        npf.createButtons(id);
        npf.addButtonsToFrame();
        npf.setButtonsBounds();
        npf.addActionListeners();
        npf.id = id;
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame frame = new JFrame("Person creation");
//        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        // Создание текстовых полей
//        surnameField = new JTextField();
//        surnameField.setToolTipText("Фамилия");
//        nameField =new JTextField(20);
//        nameField.setToolTipText("Имя");
//        middleNameField=new JTextField(20);
//        middleNameField.setToolTipText("Отчество");
//        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        dateTextField = new JFormattedTextField(format);
//        try {
//            dateTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
//        } catch (ParseException e) {
//            dateTextField.setText("01.01.2000");
//            e.printStackTrace();
//        }
//        dateTextField.setText("01.01.2000");
//        addressField =new JTextField(20);
//        addressField.setToolTipText("Адрес");
//        genderField =new JTextField(20);
//        genderField.setToolTipText("Пол");
//        saveButton = new JButton("Добавить");
//        // Настройка шрифта
//        nameField.setFont(new Font("Dialog", Font.PLAIN, 14));
//        nameField.setHorizontalAlignment(JTextField.LEFT);
//        // Создание панели с текстовыми полями
//        frame.add(nameField);
//        frame.add(surnameField);
//        frame.add(middleNameField);
//        frame.add(dateTextField);
//        frame.add(addressField);
//        frame.add(genderField);
//        frame.add(saveButton);
//
//        nameField.setToolTipText("Имя");
//        nameField.setBounds(1,1,200,50);
//        surnameField.setBounds(1,50,200,50);
//        middleNameField.setBounds(1,100,200,50);
//        dateTextField.setBounds(1,150,200,50);
//        addressField.setBounds(1,200,200,50);
//        genderField.setBounds(1,250,200,50);
//        saveButton.setBounds(1,300,200,50);

        npf.addActionListeners();

        // Определяем размер окна и выводим его на экран
//        frame.setLayout(null);
//        frame.setVisible(true);
//        frame.setSize(200,400);
//        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        frame.setResizable(false);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String middleName = middleNameField.getText();
            String address = addressField.getText();
            String gender = genderField.getText();
            if(!(gender.equals("Муж.") || gender.equals("Жен."))){
                newPersonFrame.setVisible(false);
                if(id > 0) NewPersonFrame.create(id);
                else NewPersonFrame.create();
                ErrorFrame.create(2);
                return;
            }
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date date = new Date(1);
            if(id > 0){
                Person oldPerson = PersonDAO.showPerson(id);
                Person person = new Person();
                person.setSurname(surname);
                person.setName(name);
                person.setWasBorn(oldPerson.getWasBorn());
                person.setMiddlename(middleName);
                person.setAddress(address);
                person.setGender(gender);
                person.setId(id);
                PersonDAO.updatePerson(id,person);
            }
            else {
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dateTextField.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                Date dateTime = new Date(1);
                dateTime = new java.sql.Date(date.getTime());
                Person person = new Person();
                person.setSurname(surname);
                person.setName(name);
                person.setWasBorn(dateTime);
                person.setMiddlename(middleName);
                person.setAddress(address);
                person.setGender(gender);
                person.setId(PersonDAO.getMaxId() + 1);
                PersonDAO.savePerson(person);
            }
            newPersonFrame.setVisible(false);
        }
    }


}
