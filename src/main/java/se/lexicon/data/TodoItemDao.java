package se.lexicon.data;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.Collection;

public interface TodoItemDao {
    TodoItem create(TodoItem todo);

    Collection<TodoItem> findAll();

    TodoItem findById(int id);

    Collection<TodoItem> findByDoneStatus(boolean status);

    Collection<TodoItem> findByAssignee(int personId);

    Collection<TodoItem> findByAssignee(Person person);

    Collection<TodoItem> findByAssignedTodoItems();

    TodoItem update(TodoItem todo);

    boolean deleteById(int id);


}
