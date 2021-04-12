package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.StudentsRelatives;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentRelativesTableFrame {
    public static void create(List<StudentsRelatives> relatives) {
        JFrame frame = new JFrame("Student relatives");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Идентификатор типа",
                "Идентификатор персоны",
                "Номер студенческого"
        };

        String[][] data = new String[relatives.size()][4];

        for (int i = 0; i < relatives.size(); i++) {
            data[i] = (new String[]{String.valueOf(relatives.get(i).getId()),String.valueOf(relatives.get(i).getRelativeTypeId()),
                    String.valueOf(relatives.get(i).getPersonId()), String.valueOf(relatives.get(i).getStudentId())});
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
