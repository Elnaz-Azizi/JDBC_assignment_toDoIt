package se.lexicon.data;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.model.Person;

import java.util.Collection;

public interface TodoItemDao {
    Todo create(Todo todo);

    Collection<Todo> findAll();

    Todo findById(int id);

    Collection<Todo> findByDoneStatus(boolean status);

    Collection<Todo> findByAssignee(int personId);

    Collection<Todo> findByAssignee(Person person);

    Collection<Todo> findByAssignedTodoItems();

    Todo update(Todo todo);

    boolean deleteById(int id);


}
