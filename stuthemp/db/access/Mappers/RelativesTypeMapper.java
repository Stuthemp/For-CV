package stuthemp.db.access.Mappers;

import org.springframework.jdbc.core.RowMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.RelativesType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelativesTypeMapper implements RowMapper<RelativesType> {
    @Override
    public RelativesType mapRow(ResultSet resultSet, int i) throws SQLException {
        RelativesType relativesType = new RelativesType();

        relativesType.setId(resultSet.getInt("id"));
        relativesType.setName(resultSet.getString("type_name"));

        return relativesType;
    }
}
