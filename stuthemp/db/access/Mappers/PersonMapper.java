package stuthemp.db.access.Mappers;

import stuthemp.db.access.TableClasses.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setSurname(resultSet.getString("surname"));
        person.setMiddlename(resultSet.getString("middlename"));
        person.setAddress(resultSet.getString("address"));
        person.setWasBorn(resultSet.getDate("born"));
        person.setGender(resultSet.getString("gender"));

        return person;
    }
}
