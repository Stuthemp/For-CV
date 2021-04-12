package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Faculty;
import stuthemp.db.access.TableClasses.Group;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FacultyTableFrame {
    public static void create(List<Faculty> faculties) {
        JFrame frame = new JFrame("Faculty");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "Идентификатор факультета",
                "Название факультета",
                "Декан",
                "Деканат"
        };

        String[][] data = new String[faculties.size()][4];

        for (int i = 0; i < faculties.size(); i++) {
            data[i] = (new String[]{String.valueOf(faculties.get(i).getId()),String.valueOf(faculties.get(i).getName()),
                    String.valueOf(faculties.get(i).getDean()), String.valueOf(faculties.get(i).getDeanery())});
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
