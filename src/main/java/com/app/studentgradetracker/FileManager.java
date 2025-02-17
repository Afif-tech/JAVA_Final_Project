/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.studentgradetracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class FileManager {
    
    public void FileManager() {}
    
    // Method to write student info to the file
//    public void exportDataToFile(String fileName, Student student) {
//        // Create new file or rewrite the existing file by the given filename
//        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
//            writer.println(student.getStudentId() + "," + 
//                    student.getPersonId() + "," +  
//                    student.getName() + "," + 
//                    student.calculateAverage());
//            System.out.println("Data exported successfully to \" " + fileName + "\"");
//        } catch (IOException ex) {
//            System.out.println("Error exporting data: " + ex.getMessage());
//        }
//    }
    
    // Method to write student info to the file
    public void exportDataToFile(String fileName, ArrayList<Student> studentList) {
        // Create new file or rewrite the existing file by the given filename
        try (PrintWriter writer = new PrintWriter(new FileWriter((fileName + ".txt"), true))) {
            // Checking if the list is not empty then write the data to the file
            // Else just print to user that user has created an empty file
            if (!studentList.isEmpty()) {
                for (Student student : studentList) {
                    writer.print(student.getStudentId() + "," + 
                        student.getPersonId() + "," +  
                        student.getName() + ",");
                    
                    for (double grade : student.getGrades()) {
                        writer.print(grade + ",");
                    }
                    // Break a new line in the file
                    writer.println();
                }
                System.out.println("Data exported successfully to \" " + fileName + "\"");
            } else {
                System.out.println("Redundant student ID removed from the list.");
            }
        } catch (IOException ex) {
            System.out.println("Error exporting data: " + ex.getMessage());
        }
    }

    // Method to read data from the existing file
    public ArrayList<Student> importDataFromFile(String filename) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String studentId = parts[0];
                int personId = Integer.parseInt(parts[1]);
                String name = parts[2];
                
                // Create new student with the name, person id, and student id
                Student student = new Student(name, personId, studentId);
                
                // Loop after the third parts[3] onwards
                // This loop will capture all the grade that has been store to the file
                for (int i = 3; i < parts.length; i++) {
                    String gradesString = parts[i].replace("[", "").replace("]", "");
                    
                    for (String grade : gradesString.split(" ")) {
                        if (!grade.isEmpty()) {
                            // Add a grade to the student grade list
                            student.addGrade(Double.parseDouble(grade));
                        }
                    }
                }
                
                // Add the created student to the list
                students.add(student);
            }
            System.out.println("Data imported from " + filename);
        } catch (IOException e) {
            System.out.println("Error importing data: " + e.getMessage());
        }
        return students;
    }
    
    // Method to check for the default file like 'db.txt'
    public boolean checkFileExists(String fileNum) {
        File file = new File(fileNum);
        return file.exists();
    }
    
    public void removeDuplicates(ArrayList<Student> studentList, ArrayList<Student> existingData) {
        // Create a list of existing student IDs from db.txt
        ArrayList<String> existingDataStudentIds = new ArrayList<>();
        for (Student student : existingData) {
            existingDataStudentIds.add(student.getStudentId());
        }

        // Remove students from studentList if their ID exists in the database
        studentList.removeIf(student -> existingDataStudentIds.contains(student.getStudentId()));
    }
}
