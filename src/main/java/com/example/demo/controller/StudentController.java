package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/create")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") UUID id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") UUID id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {

        studentService.updateStudent(id, name, email);
    }
}
