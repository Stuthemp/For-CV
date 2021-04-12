package stuthemp.db.access.QueriesExecution;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Frames.GroupTableFrame;
import stuthemp.db.access.Mappers.GroupMapper;
import stuthemp.db.access.Mappers.StudentMapper;
import stuthemp.db.access.TableClasses.FullName;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Person;
import stuthemp.db.access.TableClasses.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {

    public static List<Group> showGroupsTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM groups", new GroupMapper());
    }

    public static List<Group> findGroupsFromFaculty(String facultyId){
        List al = new ArrayList();
        //return SpringConfig.jdbcTemplate.query("SELECT * FROM groups WHERE (groups.faculty_id) = ALL (SELECT faculty.id FROM faculty WHERE faculty.name = ?)", new GroupMapper());
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("SELECT * FROM groups WHERE (groups.faculty_id) = ALL (SELECT faculty.id FROM faculty WHERE faculty.name = ?)");

            preparedStatement.setString(1,facultyId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id =  rs.getInt("id");
                int groupNumber = rs.getInt("group_number");
                String facId = rs.getString("faculty_id");
                int quantity = rs.getInt("students_quantity");

                //Assuming you have a user object
                Group g = new Group();
                g.setId(id);
                g.setGroupNumber(groupNumber);
                g.setFacultyId(facId);
                g.setStudentsQuantity(quantity);

                al.add(g);
            }
            return al;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return al;
    }

    public static Group showGroup(int id) {
        Group group = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM groups WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            group = new Group();

            group.setId(resultSet.getInt("id"));
            group.setGroupNumber(resultSet.getInt("group_number"));
            group.setFacultyId(resultSet.getString("faculty_id"));
            group.setStudentsQuantity(resultSet.getInt("students_quantity"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return group;
    }

    public static void updateGroup(int id, Group updatedGroup) {
        SpringConfig.jdbcTemplate.update("UPDATE groups SET group_number=?, faculty_id=?,  students_quantity=? WHERE id=?",
                updatedGroup.getGroupNumber(),updatedGroup.getFacultyId(),updatedGroup.getStudentsQuantity(),
                id);
    }

    public static List<Group> groupsWithMoreThan20Students(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM groups WHERE students_quantity > 20", new GroupMapper());
    }

    public static int averageGroupSize(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT AVG(students_quantity) FROM groups",Integer.class);
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(id) FROM groups", Integer.class);
    }

    public static void saveGroup(Group group) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO groups VALUES(?,?,?,?)");

            preparedStatement.setInt(1,group.getId());
            preparedStatement.setInt(2,group.getGroupNumber());
            preparedStatement.setString(3,group.getFacultyId());
            preparedStatement.setInt(4,group.getStudentsQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM groups WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
