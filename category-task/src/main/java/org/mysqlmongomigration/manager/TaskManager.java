package org.mysqlmongomigration.manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mysqlmongomigration.model.Task;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class TaskManager {
    private final MongoCollection<Document> taskCollection;

    public TaskManager(MongoDatabase database) {
        this.taskCollection = database.getCollection("task");
    }

    // Insert a task
    public void insertTask(Task task) {
        Document doc = new Document("creationDate", task.getCreationDate())
                .append("deadline", task.getDeadline())
                .append("name", task.getName())
                .append("description", task.getDescription())
                .append("category", task.getCategory())
                .append("subtasks", task.getSubtasks().stream()
                        .map(st -> new Document("name", st.getName()).append("description", st.getDescription()))
                        .collect(Collectors.toList()));
        taskCollection.insertOne(doc);
    }

    // Update a task
    public void updateTask(String taskId, Task task) {
        Document updateDoc = createTaskDocument(task);
        taskCollection.updateOne(Filters.eq("_id", new ObjectId(taskId)), new Document("$set", updateDoc));
    }

    private Document createTaskDocument(Task task) {
        return new Document("creationDate", task.getCreationDate())
                .append("deadline", task.getDeadline())
                .append("name", task.getName())
                .append("description", task.getDescription())
                .append("category", task.getCategory())
                .append("subtasks", task.getSubtasks().stream()
                        .map(st -> new Document("name", st.getName()).append("description", st.getDescription()))
                        .collect(Collectors.toList()));
    }

    public void displayAllTasks() {
        taskCollection.find().forEach(System.out::println);
    }

    public void displayOverdueTasks() {
        taskCollection.find(Filters.lt("deadline", LocalDate.now())).forEach(System.out::println);
    }

    public void displayTasksByCategory(String category) {
        taskCollection.find(Filters.eq("category", category)).forEach(System.out::println);
    }

    public void searchTaskByDescription(String keyword) {
        taskCollection.find(Filters.text(keyword)).forEach(System.out::println);
    }

    //delete task
    public void deleteTaskByName(String taskName) {
        taskCollection.findOneAndDelete(Filters.eq("name", taskName));
    }
}