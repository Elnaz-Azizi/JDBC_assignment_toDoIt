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
        Person person1 = new Person("Elnaz2", "Azizi2");
        //Person createdPerson = peopleDao.create(person1);
        //System.out.println(createdPerson);
        //Person foundPersonById = peopleDao.findById(2);
        //System.out.println(foundPersonById);
        //Collection<Person> people = peopleDao.findByName("Elnaz Azizi");
        // System.out.println(people);

        Person person=new Person(1,"Nickan", "Javan");
        //Person person1= peopleDao.update(person);
        //System.out.println(person1);

        TodoItemDaoImpl todoItemDao = new TodoItemDaoImpl();
        Person foundPersonById = peopleDao.findById(1);
       TodoItem todoItem = new TodoItem("Complete TodoAssignment", "Finish coding task", LocalDate.now(), false, foundPersonById);
        //TodoItem createdTodoItem = todoItemDao.create(todoItem);
        //System.out.println(createdTodoItem);
        //Collection<TodoItem> allTodoItems = todoItemDao.findAll();
        // for (TodoItem todoItem : allTodoItems) {
        //System.out.println(todoItem);
        // }

       /* int todoIdToFind = 5;
        TodoItem foundTodoItem = todoItemDao.findById(todoIdToFind);

        if (foundTodoItem != null) {
            System.out.println("Found TodoItem:");
            System.out.println(foundTodoItem);
        } else {
            System.out.println("TodoItem not found.");
        }

    */

        /*boolean doneStatusToFind = true; // Change this to the desired done status
        Collection<TodoItem> todoItemsWithStatus = todoItemDao.findByDoneStatus(doneStatusToFind);


        if (!todoItemsWithStatus.isEmpty()) {
            System.out.println("TodoItems with done status " + doneStatusToFind + ":");
            for (TodoItem todoItem : todoItemsWithStatus) {
                System.out.println(todoItem);
            }
        } else {
            System.out.println("No todo items found with done status " + doneStatusToFind);
        }


        int assigneeIdToFind = 1;
        Collection<TodoItem> todoItemsByAssignee = todoItemDao.findByAssignee(assigneeIdToFind);
        if (!todoItemsByAssignee.isEmpty()) {
            System.out.println("TodoItems with assignee ID " + assigneeIdToFind + ":");
            for (TodoItem todoItem : todoItemsByAssignee) {
                System.out.println(todoItem);
            }
        } else {
            System.out.println("No todo items found with assignee ID " + assigneeIdToFind);
        }*/

      /*  Person personToFind = peopleDao.findById(1); // Change this to the desired assignee Person
        if (personToFind != null) {
            Collection<TodoItem> todoItemsByAssigneePerson = todoItemDao.findByAssignee(personToFind);

            if (!todoItemsByAssigneePerson.isEmpty()) {
                System.out.println("TodoItems assigned to person " + personToFind.getFirstName() + " " + personToFind.getLastName() + ":");
                for (TodoItem todoItem1 : todoItemsByAssigneePerson) {
                    System.out.println(todoItem);
                }
            } else {
                System.out.println("No todo items found assigned to person " + personToFind.getFirstName() + " " + personToFind.getLastName());
            }
        } else {
            System.out.println("No person found with ID 1");
        }*/

        /*Collection<TodoItem> assignedTodoItems = todoItemDao.findByAssignedTodoItems();
        assignedTodoItems.forEach(todoItem1 -> System.out.println(todoItem));*/


      /*  Person assignee = new Person();
        assignee.setId(1);

        TodoItem todoItem1 = new TodoItem();
        todoItem.setId(1);
        todoItem.setTitle("Create reports");
        todoItem.setTaskDescription("Go in details and find all don tasks and report them to the manager");
        todoItem.setDeadLine(LocalDate.now().plusDays(7));
        todoItem.setDone(false);
        todoItem.setAssignee(assignee);

        TodoItem updatedTodoItem = todoItemDao.update(todoItem);
        System.out.println(updatedTodoItem);*/

        boolean isDeleted = todoItemDao.deleteById(1); // Assuming the TodoItem with ID 1 exists
        System.out.println("TodoItem deleted: " + isDeleted);
    }
}


