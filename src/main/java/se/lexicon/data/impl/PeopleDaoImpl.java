package se.lexicon.data.impl;

import se.lexicon.data.PeopleDao;
import se.lexicon.db.MySQLDBConnection;
import se.lexicon.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Person> people = new ArrayList<>();
        String query = "Select * from person";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ResultSet resultSet=preparedStatement.executeQuery();
        ) {
            while (resultSet.next()){
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
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
