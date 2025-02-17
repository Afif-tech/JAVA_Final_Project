/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.studentgradetracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class GradeManager {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Student> studentList;
    
    // Default constructor
    public GradeManager() {
        studentList = new ArrayList<>(); // Create new object of ArrayList
    }
    
    // Parameterized constructor
    public GradeManager(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    // Setter method to set the student array list
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    // Getter method to get the student array list
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    // Methid to addStudent with required paramenters
    public void addStudent(String name, int personId, String studentId) {
        // Call method getStudentById to get the student or null
        Student student = getStudentById(studentId);
        
        // If student not null, then the student with the id already exists
        // If not then add to the array list
        if (student != null) {
            System.out.println("Cannot add student as student with id " + studentId + " already exists");
        } else {
            studentList.add(new Student(name, personId, studentId.toUpperCase()));
            System.out.println("Student added successfully!");
        }
    }
    
    public String nameValidator() {
        while (true) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();
            if (name.matches("[a-zA-Z\\s]+")) { // Only letters and spaces allowed.numbers and special characters will prompt to error message in else statement.
                return name;
            } else {
                System.out.println("Invalid name! Name should only contain letters and spaces. Please try again.");
            }
        }
    }
    
    public int personIdValidator() {
        while (true) {
            System.out.print("Enter person ID: ");
            try {
                int personId = Integer.parseInt(sc.nextLine().trim());
                if (personId > 0) { // Ensure person ID is a positive number
                    return personId;
                } else {
                    System.out.println("Invalid person ID! Person ID must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Person ID must be a number. Please try again.");
            }
        }
    }
    
    public String studentIdValidator() {
        while (true) {
            System.out.print("Enter student ID: ");
            String studentId = sc.nextLine().trim();
            if (studentId.matches("[A-Za-z0-9]+")) { // Allow only letters and numbers
                return studentId.toUpperCase();
            } else {
                System.out.println("Invalid student ID! Student ID should only contain letters and numbers. Please try again.");
            }
        }
    }
    
    public double gradeValidator(int i) {
    while (true) {
        try {
            System.out.print("Enter grade " + (i + 1) + ": ");
            double grade = Double.parseDouble(sc.nextLine().trim());
            if (grade > -1 && grade < 101) { // Ensure the number is positive
                return grade;
            } else {
                System.out.println("Invalid grade number. Please enter number between 0 to 100");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade input! The grade must be a positive double number. Please try again.");
        }
    }
}
    
    // Method to get student by id
    public Student getStudentById(String studentId) {
        if (!studentList.isEmpty()) {
            for (Student student : studentList) {
                if (student.getStudentId().equalsIgnoreCase(studentId)) {
                    return student; // return student object
                }
            }
        }
        
        // Student not found, return null
        return null;
    }
    
    // Method to search for student by give the student id in the parameter
    public boolean searchForStudent(String studentId) {
        if (studentList.isEmpty()) {
            System.out.println("Student list if currently empty. Please add a student first.");
            return false;
        }
        
        if (studentList != null) {
            for (Student student : studentList) {
                if (student.getStudentId().equalsIgnoreCase(studentId)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // Method to display info for specific student
    public void displayStudentInfo(String studentId) {
        if (!studentList.isEmpty()) {
            Student student = getStudentById(studentId);
            System.out.println("Name: " + student.getName());
            System.out.println("Person ID: " + student.getPersonId());
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("======== All Grades ========");
            if (student.getGrades() != null && !student.getGrades().isEmpty()) {
                displayAllGrades(student.getGrades());
                System.out.println("Average grade: " + student.calculateAverage());
            } else {
                System.out.println("Grade list empty.");
            }
            
        } 
    }
    
    // Method to display all grades for a student
    public void displayAllGrades(ArrayList<Double> grades) {
        if (!grades.isEmpty()) {
            int counter = 1;
            for (double grade : grades) {
                System.out.println("Grade " + counter +": " + grade);
                counter++;
            }
        } else {
            System.out.println("Grade list empty.");
        }
    }
    
    // Method to add grade for the specific student
    public void addStudentGrade(String studentId, double grade) {
        Student existingStudent = getStudentById(studentId);
        
        if (existingStudent == null) {
            System.out.println("Student not found");
        } else {
            existingStudent.addGrade(grade);
        }
    }
    
    // Method to get all grades by student Id
    public void getStudentGrades(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                System.out.println("Found student: " + student.getName());
                //student.getGrades();
                displayAllGrades(student.getGrades());
                return;
            }
        }
        System.out.println("Error: Student ID not found. Please try again.");
    }
    
    // Method to get student name by student Id
    public String getStudentName(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student.getName();
            }
        }
        return "Error: Student name not found. Please try again.";
    }
    
    // Method to display all student info that exists in the student list
    public void displayAllStudentInfo() {
        if (!studentList.isEmpty()) {
//            System.out.println("Person ID\tStudent ID\tStudent Name\tAverage Grade");
//            for (Student student : studentList) {
//                System.out.println(student.getPersonId() + "\t" + student.getStudentId() + "\t" + student.getName() + "\t" + student.calculateAverage());
//                System.out.println("Person ID: " + student.getPersonId());
//                System.out.println("Student ID: " + student.getStudentId());
//                System.out.println("Student Name: " + student.getName());
//                System.out.println("Average Grade: " + student.calculateAverage());
           // }
            System.out.println(String.format("%-12s %-12s %-18s %-12s", "Person ID", "Student ID", "Student Name", "Average Grade"));
for (Student student : studentList) {
    String averageDisplay = ( Double.isNaN(student.calculateAverage()) ) ? "N/A" : String.format("%.2f", student.calculateAverage());
    System.out.println(String.format("%-12s %-12s %-18s %-12s", 
        student.getPersonId(), 
        student.getStudentId(), 
        student.getName(), 
        averageDisplay));
}
        } else {
            System.out.println("No Student have been added yet.");
        }
    }
}
