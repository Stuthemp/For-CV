package stuthemp.db.access.QueriesExecution;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import stuthemp.db.access.Config.SpringConfig;
import stuthemp.db.access.Mappers.FullNameMapper;
import stuthemp.db.access.Mappers.PersonMapper;
import stuthemp.db.access.Mappers.StudentRelativesMapper;
import stuthemp.db.access.TableClasses.*;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonDAO {

    public static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Uni","postgres", "postgres");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private final JdbcTemplate jdbcTemplate;
//
//    public PersonDAO(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public static Person showPerson(int id) {
        Person person = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setSurname(resultSet.getString("surname"));
            person.setMiddlename(resultSet.getString("middlename"));
            person.setGender(resultSet.getString("gender"));
            person.setAddress(resultSet.getString("address"));
            person.setWasBorn(resultSet.getDate("born"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

    public static List<Person> showPersonTable(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public static List<Person> peopleSortedBySurname(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM person ORDER BY surname",  new PersonMapper());
    }

    public static List<Person> peopleBornIn2000(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM person WHERE born BETWEEN '2000-01-01' AND  '2000-12-31'",
                new PersonMapper());
    }

    public static List<FullName> showMyRelatives(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM person, student_relatives WHERE person.id = student_relatives.person_id AND student_relatives.student_card_number = 8181037", new FullNameMapper());
    }

    public static List<FullName> findRelatives(int studentCardNumber){
        List al = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person, student_relatives WHERE person.id = student_relatives.person_id AND student_relatives.student_card_number = ?");

            preparedStatement.setInt(1,studentCardNumber);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String middlename = rs.getString("middlename");;

                //Assuming you have a user object
                FullName fn = new FullName();
                fn.setName(name);
                fn.setSurname(surname);
                fn.setMiddlename(middlename);

                al.add(fn);
            }
            return al;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return al;
    }

    public static List<FullName> findYoungestPerson(){
        return SpringConfig.jdbcTemplate.query("SELECT * FROM person WHERE born = (SELECT MAX(born) FROM person)", new FullNameMapper());
    }

    public static int getMaxId(){
        return SpringConfig.jdbcTemplate.queryForObject("SELECT MAX(id) FROM person", Integer.class);
    }


    public static void savePerson(Person person) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,person.getId());
            preparedStatement.setString(2,person.getSurname());
            preparedStatement.setString(3,person.getName());
            preparedStatement.setString(4,person.getMiddlename());
            preparedStatement.setDate(5,person.getWasBorn());
            preparedStatement.setString(6,person.getAddress());
            preparedStatement.setString(7,person.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updatePerson(int id, Person updatedPerson) {
        SpringConfig.jdbcTemplate.update("UPDATE person SET surname=?, name=?,  middlename=?, born=?, address=?, gender=?  WHERE id=?",
                updatedPerson.getSurname(),updatedPerson.getName(),updatedPerson.getMiddlename(),
                updatedPerson.getWasBorn(),updatedPerson.getAddress(),updatedPerson.getGender(),
                id);
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
