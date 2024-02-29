package com.springrest.SpringRest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Students {
    @Id
    private int id; // ID of the student

    private String name; // Name of the student

    private String city; // City of the student

    // Default constructor
    public Students() {

    }

    // Parameterized constructor
    public Students(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Setter for city
    public void setCity(String city) {
        this.city = city;
    }

    // Override toString method to provide string representation of the object
    @Override
    public String toString() {
        return "Students [id=" + id + ", name=" + name + ", city=" + city + "]";
    }
}
