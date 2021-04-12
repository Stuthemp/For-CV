package stuthemp.db.access.Mappers;

import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Faculty;
import stuthemp.db.access.TableClasses.FullName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullNameMapper implements RowMapper<FullName> {

    @Override
    public FullName mapRow(ResultSet resultSet, int i) throws SQLException {
        FullName fullName =  new FullName();

        fullName.setSurname(resultSet.getString("surname"));
        fullName.setName(resultSet.getString("name"));
        fullName.setMiddlename(resultSet.getString("middlename"));

        return fullName;
    }
}