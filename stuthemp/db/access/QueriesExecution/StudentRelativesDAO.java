package stuthemp.db.access.QueriesExecution;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.GroupMapper;
import stuthemp.db.access.Mappers.StudentRelativesMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Person;
import stuthemp.db.access.TableClasses.StudentsRelatives;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRelativesDAO {

    public static List<StudentsRelatives> showStudentRelativesTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM student_relatives", new StudentRelativesMapper());
    }

    public static StudentsRelatives showStudentRelative(int id) {
        StudentsRelatives studentsRelatives = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM student_relatives WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            studentsRelatives = new StudentsRelatives();

            studentsRelatives.setId(resultSet.getInt("id"));
            studentsRelatives.setRelativeTypeId(resultSet.getInt("relative_type_id"));
            studentsRelatives.setPersonId(resultSet.getInt("person_id"));
            studentsRelatives.setStudentId(resultSet.getInt("student_card_number"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentsRelatives;
    }

    public static void updateStudentsRelatives(int id, StudentsRelatives updatedStudentsRelatives) {
        SpringConfig.jdbcTemplate.update("UPDATE student_relatives SET relative_type_id=?, person_id=?,  student_card_number=?  WHERE id=?",
                updatedStudentsRelatives.getRelativeTypeId(),updatedStudentsRelatives.getPersonId(),
                updatedStudentsRelatives.getStudentId(), id);
    }

    public static List<StudentsRelatives> takeMyRelatives(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM student_relatives WHERE student_card_number = 8181037", new StudentRelativesMapper());
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(id) FROM student_relatives", Integer.class);
    }

    public static void saveStudentsRelative(StudentsRelatives studentsRelatives) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO student_relatives VALUES(?,?,?,?)");

            preparedStatement.setInt(1,studentsRelatives.getId());
            preparedStatement.setInt(2,studentsRelatives.getRelativeTypeId());
            preparedStatement.setInt(3,studentsRelatives.getPersonId());
            preparedStatement.setInt(4,studentsRelatives.getStudentId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM student_relatives WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
