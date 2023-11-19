package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;

    public Student() { }

    public Student(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(UUID id, String name, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
