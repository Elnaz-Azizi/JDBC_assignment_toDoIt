package se.lexicon.data.impl;

import se.lexicon.data.PeopleDao;
import se.lexicon.db.MySQLDBConnection;
import se.lexicon.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class PeopleDaoImpl implements PeopleDao {


    @Override
    public Person create(Person person) {
        String query = "insert into person(first_name, last_name) values(?, ?)";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());

            //Execute insert query
            preparedStatement.executeUpdate();

            try (
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            ) {
                if (generatedKeys.next()) {
                    int personId = generatedKeys.getInt(1);
                    person.setId(personId);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {

        return null;
    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
