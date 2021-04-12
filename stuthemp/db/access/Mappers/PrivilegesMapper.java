package stuthemp.db.access.Mappers;

import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Privilege;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrivilegesMapper implements RowMapper<Privilege> {
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        Privilege privilege = new Privilege();

        privilege.setId(resultSet.getInt("id"));
        privilege.setPrivilegeType(resultSet.getString("privilege_type"));
        privilege.setReason(resultSet.getString("reason"));
        privilege.setDateOfIssue(resultSet.getDate("date"));
        privilege.setStudentId(resultSet.getInt("student_card_number"));

        return privilege;
    }
}
