package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.FullName;
import stuthemp.db.access.TableClasses.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FullNameFrame {
    public static void create(List<FullName> people) {
        JFrame frame = new JFrame("Full name");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "Surname",
                "Name",
                "Middle name"
        };

        String[][] data = new String[people.size()][3];

        for (int i = 0; i < people.size(); i++) {
            data[i] = (new String[]{String.valueOf(people.get(i).getSurname()),
                    String.valueOf(people.get(i).getName()),String.valueOf(people.get(i).getMiddlename())});
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
