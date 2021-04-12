package stuthemp.db.access.Mappers;

import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getInt("student_card_number"));
        student.setGroupNumber(resultSet.getInt("group_number"));
        student.setPersonId(resultSet.getInt("person_id"));
        student.setFacultyId(resultSet.getString("faculty_id"));

        return student;
    }
}
