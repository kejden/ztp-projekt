package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements TaskComponent, Serializable {
    @Id
    private ObjectId id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private LocalDate dueDate;

    public Task() {}

    public Task(String name, String description, LocalDate dueDate) {
        this.id = new ObjectId();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + this.getName()+" "+this.dueDate);
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
