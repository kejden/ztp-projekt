package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

public class Note implements TaskComponent {
    @Id
    private ObjectId id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;

    public Note(){}

    public Note(String name, String description) {
        this.id = new ObjectId();
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + this.name + " " + this.description);
    }
}
