package com.studentapi.student_management.controller;

import com.studentapi.student_management.dto.StudentDTO;
import com.studentapi.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    // GET ALL STUDENTS WITH PAGINATION & SORTING
    @GetMapping("/all")
    public ResponseEntity<Page<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Page<StudentDTO> students = studentService.getAllStudents(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        String result = studentService.deleteStudent(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchByNameOrCourse(@RequestParam String keyword) {
        List<StudentDTO> students = studentService.searchByNameOrCourse(keyword);
        return ResponseEntity.ok(students);

    }



}
