package com.studentapi.student_management.repository;

import com.studentapi.student_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // JPQL (Java Persistence Query Language)
//    @Query("SELECT s FROM Student s WHERE " +
//            "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "lower(s.course) LIKE lower(CONCAT('%', :keyword, '%') ) ")
//    List<Student> searchByNameOrCourse(@Param("keyword") String keyword);

    // Native SQL Query
    @Query(value = "SELECT * FROM student WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(course) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Student> searchByNameOrCourse(@Param("keyword") String keyword);

    Page<Student> findAll(Pageable pageable);

}
