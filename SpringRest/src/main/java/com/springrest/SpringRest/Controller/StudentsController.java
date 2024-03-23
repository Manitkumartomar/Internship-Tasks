package com.springrest.SpringRest.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;// Imported exception class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.springrest.SpringRest.Entities.Students;
import com.springrest.SpringRest.Services.StudentsService;

@RestController
public class StudentsController {

    @Autowired
    private StudentsService services; // Autowired StudentsService
    
    // Mapping for home endpoint
    @GetMapping("/home")
    public String home(){
        return "Welcome to MySQL Database";
    }

    // Mapping for getting all students
    @GetMapping("/students")
    public ResponseEntity<List<Students>> getStudents(){
        List<Students> students = services.findAll();// Retrieve all students using StudentsService
        return new ResponseEntity<>(students, HttpStatus.OK); // Return students with OK status
    }

    // Mapping for getting student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable int id) throws ServiceNotFoundException {
        Optional<Students> studentsOptional = services.getStudentById(id);// Retrieve student with the given id       
        if (studentsOptional.isPresent()) {
            return new ResponseEntity<>(studentsOptional.get(), HttpStatus.OK);// Return student with OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Mapping for adding a new student
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED) // Set HTTP status code to 201 (Created)
    public void addStudents(@RequestBody Students students){
        services.addStudents(students); // Add new student using StudentsService
    }

    // Mapping for updating a student by ID
    @PutMapping("/students/{id}")
    public ResponseEntity<Void> updateStudents(@PathVariable int id, @RequestBody Students students){
        try {
            services.updateStudents(id, students); // Update student using StudentsService
            return ResponseEntity.ok().build(); // Return OK status if successful
        } catch (ServiceNotFoundException ex) { // Catch exception if student not found
            return ResponseEntity.notFound().build(); // Return not found status
        }
    }

    // Mapping for deleting a student by ID
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        try {
            services.deleteStudent(id); // Delete student using StudentsService
            return ResponseEntity.ok().build(); // Return OK status if successful
        } catch (ServiceNotFoundException ex) { // Catch exception if student not found
            return ResponseEntity.notFound().build(); // Return not found status
        }
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

    // Exception handling for StudentNotFoundException
    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(ServiceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return error message with not found status
    }
}
