package stuthemp.db.access.QueriesExecution;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.GroupMapper;
import stuthemp.db.access.Mappers.RelativesTypeMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Person;
import stuthemp.db.access.TableClasses.RelativesType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RelativesTypeDAO {

    public static List<RelativesType> showRelativeTypeTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM relatives_type", new RelativesTypeMapper());
    }

    public static RelativesType showRelativesTypes(int id) {
        RelativesType relativesType = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM relatives_type WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            relativesType = new RelativesType();

            relativesType.setId(resultSet.getInt("id"));
            relativesType.setName(resultSet.getString("type_name"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return relativesType;
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(id) FROM relatives_type", Integer.class);
    }

    public static void saveRelativeType(RelativesType relativesType) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO relatives_type VALUES(?,?)");

            preparedStatement.setInt(1,relativesType.getId());
            preparedStatement.setString(2,relativesType.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateRelativesType(int id, RelativesType updatedRelativesType) {
        SpringConfig.jdbcTemplate.update("UPDATE relatives_type SET type_name=? WHERE id=?",
                updatedRelativesType.getName(),
                id);
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM relatives_type WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
