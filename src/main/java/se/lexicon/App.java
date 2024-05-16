package se.lexicon;

import se.lexicon.data.impl.PeopleDaoImpl;
import se.lexicon.data.impl.TodoItemDaoImpl;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        //Person person1 = new Person("Elnaz2", "Azizi2");
        //Person createdPerson = peopleDao.create(person1);
        //System.out.println(createdPerson);
        //Person foundPersonById = peopleDao.findById(2);
        //System.out.println(foundPersonById);
        //Collection<Person> people = peopleDao.findByName("Elnaz Azizi");
        // System.out.println(people);

        //Person person=new Person(1,"Nickan", "Javan");
        //Person person1= peopleDao.update(person);
        //System.out.println(person1);

        TodoItemDaoImpl todoItemDao = new TodoItemDaoImpl();
        //Person foundPersonById = peopleDao.findById(2);
        //TodoItem todoItem = new TodoItem("Complete TodoAssignment", "Finish coding task", LocalDate.now(), false, foundPersonById);
        //TodoItem createdTodoItem = todoItemDao.create(todoItem);
        //System.out.println(createdTodoItem);
        Collection<TodoItem> allTodoItems = todoItemDao.findAll();
        for (TodoItem todoItem : allTodoItems) {
            System.out.println(todoItem);
        }


    }
}
