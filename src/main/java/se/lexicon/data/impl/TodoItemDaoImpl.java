package se.lexicon.data.impl;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.data.TodoItemDao;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.List;

public class TodoItemDaoImpl implements TodoItemDao {
    @Override
    public Todo create(Todo todo) {
        return null;
    }

    @Override
    public Collection<Todo> findAll() {
        return List.of();
    }

    @Override
    public Todo findById(int id) {
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean status) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByAssignee(int personId) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByAssignedTodoItems() {
        return List.of();
    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
