package se.lexicon.data.impl;

import se.lexicon.data.PeopleDao;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.List;

public class PeopleDaoImpl implements PeopleDao {
    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return List.of();
    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        return List.of();
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
