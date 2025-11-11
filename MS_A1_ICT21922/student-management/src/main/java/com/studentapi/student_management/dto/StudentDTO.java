package com.studentapi.student_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    //     id, name, email, course, age
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required" )
    @Email(message = "Email should be in valid format")
    private String email;

    @NotBlank(message = "Course is mandatory")
    private String course;

    @NotNull(message = "Age id required")
    @Min(value = 18 , message = "Age must be at least 18")
    private Integer age;
}
