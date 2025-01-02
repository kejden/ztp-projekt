package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.UuidRepresentation;
import org.example.db.DBConnection;
import org.example.model.Task;
import org.example.model.TaskComponent;
import org.example.model.TaskGroup;
import org.mongojack.JacksonMongoCollection;

import java.time.LocalDate;
public class Main {

    public static void loadData(MongoCollection<TaskComponent> groupCollection){
        Task task1 = new Task("Test 1", "Opis zadania test 1", LocalDate.of(2024, 10, 21));
        Task task2 = new Task("Test 2", "Opis zadania test 2", LocalDate.of(2024, 10, 24));
        Task task3 = new Task("Test 3", "Opis zadania test 3", LocalDate.of(2024, 10, 28));
        Task task4 = new Task("Test 4", "Opis zadania test 4", LocalDate.of(2024, 10, 28));
        Task task5 = new Task("Test 5", "Opis zadania test 5", LocalDate.of(2024, 10, 28));

        TaskGroup group1 = new TaskGroup("Grupa 1");
        group1.add(task1);
        group1.add(task2);

        TaskGroup group2 = new TaskGroup("Grupa 2");
        group2.add(task3);
        group2.add(task4);

        TaskGroup group3 = new TaskGroup("Grupa 3");
        group3.add(task5);

        group2.add(group3);

        groupCollection.insertOne(group1);
        groupCollection.insertOne(group2);
    }

    public static TaskGroup findTaskGroup(MongoCollection<TaskComponent> groupCollection, String name){
        return (TaskGroup) groupCollection.find(Filters.eq("name", name)).first();
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.NON_FINAL,
                        com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        DBConnection dbConnection = DBConnection.getInstance();

        MongoCollection<TaskComponent> groupCollection = JacksonMongoCollection.builder()
                .withObjectMapper(objectMapper)
                .build(dbConnection.getMongoClient(), "ztp", "groupCollection", TaskComponent.class, UuidRepresentation.STANDARD);

//        loadData(groupCollection);

        TaskGroup group = findTaskGroup(groupCollection, "Grupa 3");

        if (group != null) {
            group.display("");
        } else {
            System.out.println("Group not found!");
        }

        dbConnection.close();
    }
}