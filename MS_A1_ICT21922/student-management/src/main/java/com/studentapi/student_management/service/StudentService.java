package com.studentapi.student_management.service;

import com.studentapi.student_management.dto.StudentDTO;
import com.studentapi.student_management.entity.Student;
import com.studentapi.student_management.exception.ResourceNotFoundException;
import com.studentapi.student_management.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // GET ALL STUDENTS (with pagination and sorting)
    public Page<StudentDTO> getAllStudents(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(student -> modelMapper.map(student, StudentDTO.class));
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return modelMapper.map(studentList, new TypeToken<List<StudentDTO>>() {}.getType());
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        studentRepository.save(modelMapper.map(studentDTO,Student.class));
        return studentDTO;
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // MANUAL UPDATE
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setCourse(studentDTO.getCourse());
        existingStudent.setAge(studentDTO.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }


    public String deleteStudent(Long id) {
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
        return "Student deleted successfully";
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    public List<StudentDTO> searchByNameOrCourse(String keyword) {
        List<Student> students = studentRepository.searchByNameOrCourse(keyword);
        return modelMapper.map(students, new TypeToken<List<StudentDTO>>() {}.getType());
    }



}
