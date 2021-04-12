package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.RelativesType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RelativesTypeTableFrame {

    public static void create(List<RelativesType> relativesTypes) {
        JFrame frame = new JFrame("Вид родственника");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Название вида"
        };

        String[][] data = new String[relativesTypes.size()][2];

        for (int i = 0; i < relativesTypes.size(); i++) {
            data[i] = (new String[]{String.valueOf(relativesTypes.get(i).getId()),
                    String.valueOf(relativesTypes.get(i).getName())});
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
