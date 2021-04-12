package stuthemp.db.access.Mappers;

import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();

        group.setId(resultSet.getInt("id"));
        group.setGroupNumber(resultSet.getInt("group_number"));
        group.setStudentsQuantity(resultSet.getInt("students_quantity"));
        group.setFacultyId(resultSet.getString("faculty_id"));

        return group;
    }
}
