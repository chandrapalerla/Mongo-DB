package org.mysqlmongomigration;

import com.mongodb.client.MongoDatabase;
import org.mysqlmongomigration.config.MongoDBConnection;
import org.mysqlmongomigration.manager.TaskManager;
import org.mysqlmongomigration.model.Subtask;
import org.mysqlmongomigration.model.Task;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TaskManagerApp {
    public static void main(String[] args) {
        MongoDatabase database = MongoDBConnection.connect();
        TaskManager taskManager = new TaskManager(database);

        //insert
        // taskManager.insertTask(getTask());
        //Display on console all tasks
        taskManager.displayAllTasks();
        //Display overdue tasks.
        taskManager.displayOverdueTasks();
        //Display all tasks with a specific category (query parameter).
        taskManager.displayTasksByCategory("Work");
        taskManager.searchTaskByDescription("sample");

        //update the office record
         //taskManager.updateTask("66d9d8bd10df9b1e354230f2", getUpdateTask());

        //delete the office record
       taskManager.deleteTaskByName("office");
    }

    private static Task getTask() {
        Subtask subtask1 = Subtask.builder()
                .name("cleaning")
                .description("first time cleaning")
                .build();
        return Task.builder()
                .name("office")
                .category("coffee machine clean")
                .creationDate(LocalDate.now())
                .deadline(LocalDate.now().plusDays(10))
                .subtasks(List.of(subtask1))
                .description("office related tasks").build();
    }

    private static Task getUpdateTask() {

        Subtask subtask1 = Subtask.builder()
                .name("cleaning")
                .description("first time cleaning")
                .build();

        Subtask subtask2 = Subtask.builder()
                .name("floor cleaning")
                .description("first time floor   cleaning")
                .build();

        return Task.builder()
                .name("office")
                .category("coffee machine clean")
                .creationDate(LocalDate.now())
                .deadline(LocalDate.now().plusDays(10))
                .subtasks(Arrays.asList(subtask1, subtask2))
                .description("office related tasks").build();
    }
}
