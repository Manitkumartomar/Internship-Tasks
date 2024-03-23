package com.springrest.SpringRest.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException; // Imported exception class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springrest.SpringRest.Entities.Students;

@Service
public class StudentsService {

    @Autowired
    private StudentsRepo studentsRepo; // Autowired StudentsRepo

    // Method to find all students
    public List<Students> findAll() {
        List<Students> students = new ArrayList<>();
        studentsRepo.findAll().forEach(students::add); // Retrieve all students and add to the list
        return students; // Return the list of students
    }

    // Method to add a new student
    public void addStudents(Students students) {
        studentsRepo.save(students); // Save the new student
    }

    // Method to get student by student id
    public Optional<Students> getStudentById(int id) throws ServiceNotFoundException{
        try {
            Optional<Students> student = studentsRepo.findById(id);;
            return student;
        } catch (Exception e) {
           throw new ServiceNotFoundException("Student not found with id: "+ id);
        }   
    }

    // Method to update a student by ID
    public void updateStudents(int id, Students students) throws ServiceNotFoundException {
        try {
            if (!studentsRepo.existsById(id)) { // Check if student with given ID exists
                throw new ServiceNotFoundException("Student with id " + id + " not found"); // Throw exception if not found
            }
            students.setId(id); // Set ID for the student
            studentsRepo.save(students); // Save the updated student
        } catch (Exception e) {
            throw new ServiceNotFoundException("Failed to update student with id " + id); // Throw exception for any other failure
        }
    }

    // Method to delete a student by ID
    public void deleteStudent(int id) throws ServiceNotFoundException {
        try {
            studentsRepo.deleteById(id); // Delete student by ID
        } catch (EmptyResultDataAccessException e) {
            throw new ServiceNotFoundException("Student with id " + id + " not found"); // Throw exception if student not found
        } catch (Exception e) {
            throw new ServiceNotFoundException("Failed to delete student with id " + id); // Throw exception for any other failure
        }
    }
}
