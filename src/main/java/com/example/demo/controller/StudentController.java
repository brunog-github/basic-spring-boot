package com.example.demo.controller;

import com.example.demo.dto.RequestStudentDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addNewStudent(@RequestBody @Valid RequestStudentDto requestStudentDto) {
        Student student = new Student(requestStudentDto);
        studentService.addNewStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("created.");
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("student has been deleted.");
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") UUID id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {

        studentService.updateStudent(id, name, email);
        return ResponseEntity.status(HttpStatus.OK).body("student has been updated.");
    }
}
