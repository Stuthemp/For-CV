package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentTableFrame {

    public static void create(List<Student> students) {
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Номрер группы",
                "Идентификатор персоны",
                "Идентификатор факультета",
        };

        String[][] data = new String[students.size()][4];

        for (int i = 0; i < students.size(); i++) {
            data[i] = (new String[]{String.valueOf(students.get(i).getId()),String.valueOf(students.get(i).getGroupNumber()),
                    String.valueOf(students.get(i).getPersonId()),String.valueOf(students.get(i).getFacultyId())});
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
