/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.studentgradetracker;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Student extends Person {
    private String studentId;
    private ArrayList<Double> grades;
    private double average;
    
    public Student() {} // Default constructor
    
    // Parameterized constructor
    public Student(String name, int personId, String studentId) {
        super(name, personId);
        this.studentId = studentId;
        this.grades = new ArrayList<>();
    }
    
    // Getter method to get student id
    public String getStudentId() {
        return studentId;
    }
    
    // Getter method to get list of grades
    public ArrayList<Double> getGrades() {
        return grades;
    }
    
    // Method to add grade into the arrayList
    public void addGrade(double grade) {
        grades.add(grade);
    }
    
    // Method to calculate average from the array list
    public double calculateAverage() {
        double sum = 0.0;
        
        for (double grade : grades) {
            sum += grade;
        }
        
        this.average = sum / grades.size();
        return this.average;
    }
}
