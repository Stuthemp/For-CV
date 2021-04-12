package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Group;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GroupSizeFrame {

    public static void create(int quantity) {
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "Среднее число студентов"
        };

        String[][] data = new String[1][1];

            data[0] = (new String[]{String.valueOf(quantity)});


        JTable table = new JTable(data,columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




    }
}
