package com.springrest.SpringRest.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.management.ServiceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springrest.SpringRest.Controller.StudentsController;
import com.springrest.SpringRest.Entities.Students;
import com.springrest.SpringRest.Services.StudentsService;

@SpringBootTest
public class StudentsControllerTest {

    @Mock
    private StudentsService studentsService;

    @InjectMocks
    private StudentsController studentsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudents() {
        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(1, "Alice", "Mumabi"));
        studentsList.add(new Students(2, "Bob", "Delhi"));
        
        when(studentsService.findAll()).thenReturn(studentsList);

        ResponseEntity<List<Students>> responseEntity = studentsController.getStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentsList, responseEntity.getBody());
    }

    @Test
    public void testUpdateStudents() throws ServiceNotFoundException {
        Students student = new Students(1, "Alice", "Mumbai");

        ResponseEntity<Void> responseEntity = studentsController.updateStudents(1, student);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(studentsService).updateStudents(1, student);
    }

    @Test
    public void testDeleteStudent() throws ServiceNotFoundException {
        ResponseEntity<Void> responseEntity = studentsController.deleteStudent(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(studentsService).deleteStudent(1);
    }
}
