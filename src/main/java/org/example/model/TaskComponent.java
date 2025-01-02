package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Task.class, name = "Task"),
        @JsonSubTypes.Type(value = Note.class, name= "Note"),
        @JsonSubTypes.Type(value = TaskList.class, name= "TaskList"),
        @JsonSubTypes.Type(value = TaskGroup.class, name = "TaskGroup")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public interface TaskComponent {

    String getName();

    void display(String indent);

}
