package com.example.demo.model;

import com.example.demo.dto.RequestStudentDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Email
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;

    public Student(RequestStudentDto requestStudentDto) {
        this.name = requestStudentDto.name();
        this.email = requestStudentDto.email();
        this.dateOfBirth = requestStudentDto.dateOfBirth();
    }

    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
