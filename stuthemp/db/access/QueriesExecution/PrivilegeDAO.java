package stuthemp.db.access.QueriesExecution;

import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.GroupMapper;
import stuthemp.db.access.Mappers.PrivilegesMapper;
import stuthemp.db.access.TableClasses.Group;
import stuthemp.db.access.TableClasses.Privilege;
import stuthemp.db.access.TableClasses.RelativesType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrivilegeDAO {

    public static List<Privilege> showPrivilegeTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM privilege", new PrivilegesMapper());
    }

    public static Privilege showPrivilege(int id) {
        Privilege privilege = null;

        try {
            PreparedStatement preparedStatement =
                    SpringConfig.connection.prepareStatement("SELECT * FROM privilege WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            privilege = new Privilege();

            privilege.setId(resultSet.getInt("id"));
            privilege.setPrivilegeType(resultSet.getString("privilege_type"));
            privilege.setDateOfIssue(resultSet.getDate("date"));
            privilege.setReason(resultSet.getString("reason"));
            privilege.setStudentId(resultSet.getInt("student_card_number"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return privilege;
    }

    public static void updatePrivilege(int id, Privilege updatedPrivilege) {
        SpringConfig.jdbcTemplate.update("UPDATE privilege SET privilege_type=?, date=?, reason=?, student_card_number=? WHERE id=?",
                updatedPrivilege.getPrivilegeType(),updatedPrivilege.getDateOfIssue(), updatedPrivilege.getReason(),
                updatedPrivilege.getStudentId(), id);
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(id) FROM privilege", Integer.class);
    }

    public static void savePrivilege(Privilege privilege) {

        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("INSERT INTO privilege VALUES(?,?,?,?,?)");

            preparedStatement.setInt(1,privilege.getId());
            preparedStatement.setString(2,privilege.getPrivilegeType());
            preparedStatement.setDate(3,privilege.getDateOfIssue());
            preparedStatement.setString(4,privilege.getReason());
            preparedStatement.setInt(5,privilege.getStudentId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = SpringConfig.connection.prepareStatement("DELETE FROM privilege WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

