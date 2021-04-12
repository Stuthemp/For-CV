package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GroupTableFrame {

    public static void create(List<Group> groups) {
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Номер группы",
                "Идентификатор факультета",
                "Количество студентов"
        };

        String[][] data = new String[groups.size()][4];

        for (int i = 0; i < groups.size(); i++) {
            data[i] = (new String[]{String.valueOf(groups.get(i).getId()),String.valueOf(groups.get(i).getGroupNumber()),String.valueOf(groups.get(i).getFacultyId()),
                    String.valueOf(groups.get(i).getStudentsQuantity())});
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
