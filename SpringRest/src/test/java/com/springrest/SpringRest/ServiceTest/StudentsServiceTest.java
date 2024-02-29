package com.springrest.SpringRest.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.dao.EmptyResultDataAccessException;

import com.springrest.SpringRest.Entities.Students;
import com.springrest.SpringRest.Services.StudentsRepo;
import com.springrest.SpringRest.Services.StudentsService;

public class StudentsServiceTest {

    @Mock
    private StudentsRepo studentsRepo;

    @InjectMocks
    private StudentsService studentsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(1, "Alice", "Mumbai"));
        studentsList.add(new Students(2, "Bob", "Delhi"));

        when(studentsRepo.findAll()).thenReturn(studentsList);

        List<Students> result = studentsService.findAll();

        assert result.equals(studentsList);
    }

    @Test
    public void testUpdateStudents_Success() throws ServiceNotFoundException {
        Students student = new Students(1, "Alice", "Mumbai");

        when(studentsRepo.existsById(1)).thenReturn(true);

        studentsService.updateStudents(1, student);

        verify(studentsRepo).save(student);
    }

    @Test
    public void testUpdateStudents_NotFound() {
        Students student = new Students(1, "Alice", "Mumbai");

        when(studentsRepo.existsById(1)).thenReturn(false);

        assertThrows(ServiceNotFoundException.class, () -> studentsService.updateStudents(1, student));
    }

    @Test
    public void testDeleteStudent() throws ServiceNotFoundException {
        studentsService.deleteStudent(1);

        verify(studentsRepo).deleteById(1);
    }

    @Test
    public void testDeleteStudent_NotFound() {
        doThrow(EmptyResultDataAccessException.class).when(studentsRepo).deleteById(1);

        assertThrows(ServiceNotFoundException.class, () -> studentsService.deleteStudent(1));
    }

}
