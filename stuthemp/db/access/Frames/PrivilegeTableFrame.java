package stuthemp.db.access.Frames;

import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Privilege;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrivilegeTableFrame {

    public static void create(List<Privilege> privileges) {
        JFrame frame = new JFrame("Privilege");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        String[] columnNames = {
                "id",
                "Вид льготы",
                "Дата выдачи",
                "Причина выдачи",
                "Номер студенческого"
        };

        String[][] data = new String[privileges.size()][5];

        for (int i = 0; i < privileges.size(); i++) {
            data[i] = (new String[]{String.valueOf(privileges.get(i).getId()),String.valueOf(privileges.get(i).getPrivilegeType()),
                    String.valueOf(privileges.get(i).getDateOfIssue()),String.valueOf(privileges.get(i).getReason()),
                    String.valueOf(privileges.get(i).getStudentId())});
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
