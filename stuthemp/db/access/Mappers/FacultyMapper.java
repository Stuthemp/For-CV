package stuthemp.db.access.Mappers;

import stuthemp.db.access.TableClasses.Faculty;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty mapRow(ResultSet resultSet, int i) throws SQLException {
        Faculty faculty = new Faculty();

        faculty.setId(resultSet.getString("id"));
        faculty.setDean(resultSet.getString("dean"));
        faculty.setDeanery(resultSet.getString("deanery"));
        faculty.setName(resultSet.getString("name"));

        return faculty;
    }
}
