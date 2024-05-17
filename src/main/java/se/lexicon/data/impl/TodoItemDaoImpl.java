package se.lexicon.data.impl;

import se.lexicon.data.TodoItemDao;
import se.lexicon.db.MySQLDBConnection;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemDaoImpl implements TodoItemDao {


    @Override
    public TodoItem create(TodoItem todo) {
        String query = "insert into todo_item(title, description, deadline, done, assignee_id) values (?,?,?,?,?)";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)

        ) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getTaskDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(todo.getDeadLine()));
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssignee().getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int todoId = generatedKeys.getInt(1);
                    todo.setId(todoId);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todo;
    }

    @Override
    public Collection<TodoItem> findAll() {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "select * from todo_item";

        try (Connection connection = MySQLDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();

        ) {
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(resultSet.getInt("Todo_id"));
                todoItem.setTitle(resultSet.getString("Title"));
                todoItem.setTaskDescription(resultSet.getString("Description"));
                todoItem.setDeadLine(resultSet.getDate("Deadline").toLocalDate());
                todoItem.setDone(resultSet.getBoolean("Done"));
                int assigneeId = resultSet.getInt("assignee_id");
                Person assignee = findAssigneeById(assigneeId);
                todoItem.setAssignee(assignee);

                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todoItems;
    }

    private Person findAssigneeById(int assigneeId) {
        String query = "SELECT * FROM person WHERE person_id = ?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, assigneeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person assignee = new Person();
                    assignee.setId(resultSet.getInt("person_id"));
                    assignee.setFirstName(resultSet.getString("first_name"));
                    assignee.setLastName(resultSet.getString("last_name"));
                    return assignee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TodoItem findById(int id) {
        TodoItem todoItem = null;
        String query = "SELECT * FROM todo_item WHERE todo_id = ?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    todoItem.setId(resultSet.getInt("Todo_id"));
                    todoItem.setTitle(resultSet.getString("Title"));
                    todoItem.setTaskDescription(resultSet.getString("Description"));
                    todoItem.setDeadLine(resultSet.getDate("Deadline").toLocalDate());
                    todoItem.setDone(resultSet.getBoolean("Done"));
                    int assigneeId = resultSet.getInt("assignee_id");
                    Person assignee = findAssigneeById(assigneeId);
                    todoItem.setAssignee(assignee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE done = ?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setBoolean(1, status);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TodoItem todoItem = new TodoItem();
                    todoItem.setId(resultSet.getInt("Todo_id"));
                    todoItem.setTitle(resultSet.getString("Title"));
                    todoItem.setTaskDescription(resultSet.getString("Description"));
                    todoItem.setDeadLine(resultSet.getDate("Deadline").toLocalDate());
                    todoItem.setDone(resultSet.getBoolean("Done"));
                    int assigneeId = resultSet.getInt("assignee_id");
                    Person assignee = findAssigneeById(assigneeId);
                    todoItem.setAssignee(assignee);
                    todoItems.add(todoItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int personId) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE assignee_id = ?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TodoItem todoItem = new TodoItem();
                    todoItem.setId(resultSet.getInt("Todo_id"));
                    todoItem.setTitle(resultSet.getString("Title"));
                    todoItem.setTaskDescription(resultSet.getString("Description"));
                    todoItem.setDeadLine(resultSet.getDate("Deadline").toLocalDate());
                    todoItem.setDone(resultSet.getBoolean("Done"));
                    Person assignee = findAssigneeById(personId);
                    todoItem.setAssignee(assignee);
                    todoItems.add(todoItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        if (person == null || person.getId() == 0) {
            return new ArrayList<>();
        }
        return findByAssignee(person.getId());
    }

    @Override
    public Collection<TodoItem> findByAssignedTodoItems() {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE assignee_id IS NOT NULL";

        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(resultSet.getInt("Todo_id"));
                todoItem.setTitle(resultSet.getString("Title"));
                todoItem.setTaskDescription(resultSet.getString("Description"));
                todoItem.setDeadLine(resultSet.getDate("Deadline").toLocalDate());
                todoItem.setDone(resultSet.getBoolean("Done"));
                int assigneeId = resultSet.getInt("assignee_id");
                Person assignee = findAssigneeById(assigneeId);
                todoItem.setAssignee(assignee);

                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todoItems;
    }

    @Override
    public TodoItem update(TodoItem todo) {
        String query = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?";
        try (
                Connection connection = MySQLDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getTaskDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(todo.getDeadLine()));
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssignee().getId());
            preparedStatement.setInt(6, todo.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return todo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM todo_item WHERE todo_id = ?";
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
