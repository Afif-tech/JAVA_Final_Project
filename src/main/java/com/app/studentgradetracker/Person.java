/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.studentgradetracker;

/**
 *
 * @author Acer
 */
public class Person {
    private String name;
    private int personId;
    
    public Person() {} // Default constructor
    
    // Parameterized constructor
    public Person(String name, int personId) {
        this.name = name;
        this.personId = personId;
    }
    
    // Getter method to get person name
    public String getName() {
        return name;
    }
    
    // Getter method to get person id
    public int getPersonId() {
        return personId;
    }
    
}
