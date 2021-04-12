package stuthemp.db.access.QueriesExecution;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.FacultyMapper;
import stuthemp.db.access.Mappers.GroupMapper;
import stuthemp.db.access.TableClasses.Faculty;
import stuthemp.db.access.TableClasses.FullName;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {


    public static List<Faculty> showFacultyTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM faculty", new FacultyMapper());
    }

    public static void updateFaculty(String id, Faculty updatedFaculty) {
        SpringConfig.jdbcTemplate.update("UPDATE faculty SET name =?, dean=?,  deanery=? WHERE id=?",
                updatedFaculty.getName(),updatedFaculty.getDean(),updatedFaculty.getDeanery(),
                id);
    }
    public static Faculty showFaculty(String id) {
        Faculty faculty = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM faculty WHERE id=?");

            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            faculty = new Faculty();

            faculty.setId(resultSet.getString("id"));
            faculty.setName(resultSet.getString("name"));
            faculty.setDean(resultSet.getString("dean"));
            faculty.setDeanery(resultSet.getString("deanery"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return faculty;
    }

    public static void saveFaculty(Faculty faculty) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO faculty VALUES(?,?,?,?)");

            preparedStatement.setString(1,faculty.getId());
            preparedStatement.setString(2,faculty.getName());
            preparedStatement.setString(3,faculty.getDean());
            preparedStatement.setString(4,faculty.getDeanery());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM faculty WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Faculty> findSize(int quantity){
        List al = new ArrayList();
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("SELECT * FROM faculty WHERE faculty.id = ANY(SELECT groups.faculty_id FROM groups WHERE groups.students_quantity >= ?)");

            preparedStatement.setInt(1,quantity);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String dean = rs.getString("dean");
                String deanery = rs.getString("deanery");
                String id = rs.getString("id");

                //Assuming you have a user object
                Faculty fn = new Faculty();
                fn.setName(name);
                fn.setDean(dean);
                fn.setDeanery(deanery);
                fn.setId(id);

                al.add(fn);
            }
            return al;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return al;
    }
}
