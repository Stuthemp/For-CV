package stuthemp.db.access.QueriesExecution;

import org.springframework.jdbc.core.JdbcTemplate;
import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.PersonMapper;
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

public class StudentDAO {


    public static List<Student> showStudentTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM student", new StudentMapper());
    }

    public static Student showStudent(int id) {
        Student student = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM student WHERE student_card_number=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            student = new Student();

            student.setId(resultSet.getInt("student_card_number"));
            student.setGroupNumber(resultSet.getInt("group_number"));
            student.setPersonId(resultSet.getInt("person_id"));
            student.setFacultyId(resultSet.getString("faculty_id"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return student;
    }

    public static List<Student> showRelativesStudents(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM student WHERE person_id = (SELECT person_id FROM student_relatives WHERE student_relatives.person_id = student.person_id)", new StudentMapper());
    }

    public static void updateStudent(int id, Student updatedStudent) {
        SpringConfig.jdbcTemplate.update("UPDATE student SET group_number=?, person_id=?,  faculty_id=?  WHERE student_card_number=?",
                updatedStudent.getGroupNumber(),updatedStudent.getPersonId(),updatedStudent.getFacultyId(), id);
    }

    public static List<Student> findStudentsWithSurname(String surname){
        List al = new ArrayList();
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("SELECT * FROM student WHERE person_id = (SELECT id FROM person WHERE person.surname = ?");

            preparedStatement.setString(1,surname);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cardN = rs.getInt("student_card_number");
                int groupN = rs.getInt("group_number");
                int personId = rs.getInt("person_id");
                String facultyId = rs.getString("faculty_id");


                //Assuming you have a user object
                Student fn = new Student();
                fn.setId(cardN);
                fn.setGroupNumber(groupN);
                fn.setFacultyId(facultyId);
                fn.setPersonId(personId);

                al.add(fn);
            }
            return al;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return al;
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(student_card_number) FROM student", Integer.class);
    }

    public static void saveStudent(Student student) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");

            preparedStatement.setInt(1,student.getId());
            preparedStatement.setInt(2,student.getGroupNumber());
            preparedStatement.setInt(3,student.getPersonId());
            preparedStatement.setString(4,student.getFacultyId());


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM student WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
