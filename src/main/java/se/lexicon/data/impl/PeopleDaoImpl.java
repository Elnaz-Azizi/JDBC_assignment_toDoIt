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
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Person person = new Person();
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
        //Query
        String query = "Select * from person where person_id =?";
        Person person = new Person();

        // connection
        try (
                Connection connection = MySQLDBConnection.getConnection();
                //preparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            //set param
            preparedStatement.setInt(1, id);
            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();
            // get from result set and set to person if exist
            if (resultSet.next()) {
                person.setId(resultSet.getInt("person_id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getNString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public Collection<Person> findByName(String name) {
        List<Person> people = new ArrayList<>();
        String query = "Select * from person where CONCAT(first_name, ' ' , last_name) = ?";

        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("person_id"));
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
    public Person update(Person person) {
        String query = "UPDATE person SET first_name=?, last_name=? WHERE person_id=?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM person WHERE person_id=?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
