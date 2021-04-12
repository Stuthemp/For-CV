package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonTableFrame {

    public static void create(List<Person> people) {
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Surname",
                "Name",
                "Middle name",
                "Date of Birth",
                "Address",
                "Gender"
        };

        String[][] data = new String[people.size()][7];

        for (int i = 0; i < people.size(); i++) {
            data[i] = (new String[]{String.valueOf(people.get(i).getId()),String.valueOf(people.get(i).getSurname()),
                    String.valueOf(people.get(i).getName()),String.valueOf(people.get(i).getMiddlename()),
                    String.valueOf(people.get(i).getWasBorn()),String.valueOf(people.get(i).getAddress()),
                    String.valueOf(people.get(i).getGender())});
        }

        JTable table = new JTable(data,columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




    }

}
