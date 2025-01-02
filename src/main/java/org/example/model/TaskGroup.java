package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskGroup implements TaskComponent, Serializable {
    @Id
    private ObjectId id;

    @JsonProperty
    private final String name;
    @JsonProperty
    private final List<TaskComponent> components;

    public TaskGroup() {
        this.id = new ObjectId();
        this.name = null;
        this.components = new ArrayList<>();
    }

    public TaskGroup(String name) {
        this.id = new ObjectId();
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void add(TaskComponent taskComponent) {
        components.add(taskComponent);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + this.name +":");
        for (TaskComponent task : components) {
            task.display(indent + "    ");
        }
    }
}
