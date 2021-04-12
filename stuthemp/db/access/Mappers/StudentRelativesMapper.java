package stuthemp.db.access.Mappers;
import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.StudentsRelatives;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRelativesMapper implements RowMapper<StudentsRelatives> {

    @Override
    public StudentsRelatives mapRow(ResultSet resultSet, int i) throws SQLException {
        StudentsRelatives studentsRelatives = new StudentsRelatives();

        studentsRelatives.setId(resultSet.getInt("id"));
        studentsRelatives.setPersonId(resultSet.getInt("person_id"));
        studentsRelatives.setRelativeTypeId(resultSet.getInt("relative_type_id"));
        studentsRelatives.setStudentId(resultSet.getInt("student_card_number"));

        return studentsRelatives;
    }
}
