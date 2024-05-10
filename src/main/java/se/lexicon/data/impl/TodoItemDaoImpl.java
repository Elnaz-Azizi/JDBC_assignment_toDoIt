package se.lexicon.data.impl;

import se.lexicon.data.TodoItemDao;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.List;

public class TodoItemDaoImpl implements TodoItemDao {
    

    @Override
    public TodoItem create(TodoItem todo) {
        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return null;
    }

    @Override
    public TodoItem findById(int id) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int personId) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByAssignedTodoItems() {
        return null;
    }

    @Override
    public TodoItem update(TodoItem todo) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
