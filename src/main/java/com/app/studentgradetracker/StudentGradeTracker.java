/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.app.studentgradetracker;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Acer
 */
public class StudentGradeTracker {

    public static void main(String[] args) {
        // Create an object of Scanner
        Scanner sc = new Scanner(System.in);
        
        // Create an object of GradeManager that use no parameterized constructor
        GradeManager gm = new GradeManager();
        // Create an object of FileManager that use no parameterized constructor
        FileManager fm = new FileManager();
        
        // This is a flag for user to use the menu as long as user want
        boolean exit = false;
       
        
        // Checking for the database file.
        // If it exists then prompt to user whether they want to import the previous data or not.
        if (fm.checkFileExists("db.txt")) {
            System.out.print("Hello! Do you want to import the file or start a fresh one? (yes/no): ");
            String startOrImport = sc.next();
            if (startOrImport.equalsIgnoreCase("yes")) {
                ArrayList<Student> studentList = fm.importDataFromFile("db.txt");
                gm.setStudentList(studentList);
            }
        }
        
        // Use do while so that the menu will prompt at least one before loop
        do {
            System.out.println("=============================================");
            System.out.println("\t  Student Grade Tracker");
            System.out.println("=============================================");
            System.out.println("1. Add a new student");
            System.out.println("2. Enter Grades for a student");
            System.out.println("3. View Grades for an Existing Student");
            System.out.println("4. View a Student's Report (Including Average)");
            System.out.println("5. View All Students");
            System.out.println("6. Export Data to File");
            System.out.println("7. Import Data from File");
            System.out.println("8. Exit");
            System.out.println("=============================================");
            
//            System.out.print("Enter your choice: ");
//            int choice = 0; // Get the user enter
            boolean validInput = false;
            int choice = 0;
            while (!validInput) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    validInput = true; // Input is valid, exit the loop
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    sc.nextLine(); // Clear the invalid input from the scanner buffer
                }
            }
            
            // Initialize the array list grades to null before reference it to the student grades
            ArrayList<Double> grades = null;
            
            switch (choice) {
                case 1:
                    String name = gm.nameValidator();
                    int personId = gm.personIdValidator();
                    String studentId = gm.studentIdValidator();
                    gm.addStudent(name, personId, studentId);
                    break;
                case 2:
                    addGradeStudent(sc, gm, grades);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentID = sc.next();
                    gm.getStudentGrades(studentID);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String id = sc.next();
                    boolean checkStudentId = gm.searchForStudent(id);
                    if (checkStudentId) {
                        gm.displayStudentInfo(id);
                    } else {
                        System.out.println("Error: Student ID not found. Please try again.");
                    }
                    
                    break;
                case 5:
                    gm.displayAllStudentInfo();
                    break;
                case 6:
                    System.out.print("Enter file name (eg. my_file): ");
                    String fileName = sc.next();
                    fm.exportDataToFile(fileName, gm.getStudentList());
                    break;
                case 7:
                    System.out.print("Enter file name to import the file (eg. my_file): ");
                    String fName = sc.next();
                    ArrayList<Student> importStudentList = fm.importDataFromFile(fName);
                    if (gm.getStudentList() != null) {
                        gm.getStudentList().addAll(importStudentList);
                    } else {
                        gm.setStudentList(importStudentList);
                    }
                    break;
                case 8:
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    String confirmation = sc.next();
                    
                    if (confirmation.equalsIgnoreCase("yes")) {
                        System.out.print("Do you want to save to the added data to the database  ? (yes/no): ");
                        String saveToDB = sc.next();
                        if (saveToDB.equalsIgnoreCase("yes")) {
                            // import existing data from db.txt first
                            ArrayList<Student> existingData = fm.importDataFromFile("db.txt");
                            
                            // Get all the student from current student array
                            ArrayList<Student> studentListArr = gm.getStudentList();
                            
                            // remove duplicate student from the student list array (compare student id)
                            fm.removeDuplicates(studentListArr, existingData);
                            // save to file
                            fm.exportDataToFile("db", gm.getStudentList());
                        }
                        
                        // Set the flag to true so that the loop will be exit
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Please select a number between 1 and 8.");
            }
        } while (!exit);
    }
    
    private static void addGradeStudent(Scanner sc, GradeManager gm, ArrayList<Double> grades) {
        System.out.print("Enter student ID: ");
        String id = sc.next(); // Get the user enter
        
        // Call method inside the GradeManager class and pass the id from the user
        // Assign the return value to the new variable to be used if the student found
        boolean found = gm.searchForStudent(id);
        
        if (found) {
            int subNum;
            // Checking if user enter less than 0, then keep asking again until they enter correct number
            do {
               System.out.print("Enter number of subject(s) for " + gm.getStudentName(id) + " : ");
               subNum = sc.nextInt(); // Get the user enter
               if (subNum < 0) {
                   System.out.println("Invalid input! Please enter a positive number.");
               }
            } while (subNum < 0);
            
            // Create an object of grade arraylist with size entered by user
//            grades = new ArrayList<>(subNum);
            
            for (int i = 0; i < subNum; i++) {
                double newGrade = gm.gradeValidator(i);
                // Call method addStudentGrade inside the GradeManager class
                gm.addStudentGrade(id, newGrade); // Pass the student id and grade
            }
            System.out.println("Student grade added successfully.");
        } else {
            System.out.println("Student not found! Please try again.");
        }
    }
    
    
}
