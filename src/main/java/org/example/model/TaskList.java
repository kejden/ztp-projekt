package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements TaskComponent {
    @Id
    private ObjectId id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private LocalDate dueDate;
    @JsonProperty
    private List<TaskComponent> tasks;

    public TaskList(){}

    public TaskList(String name, String description, LocalDate dueDate) {
        this.id = new ObjectId();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + this.getName()+" "+this.description+" ( "+this.dueDate+" )");
        for (TaskComponent task : tasks) {
            task.display(indent + "    ");
        }
    }

    public void addTask(TaskComponent task) {
        tasks.add(task);
    }
}
