package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException(("email already exist."));
        }
        studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        boolean studentExist = studentRepository.existsById(id);
        if (!studentExist) {
            throw new IllegalStateException("Student with id " + id + " does not exist.");
        }
        studentRepository.deleteById(id);
    }
    public void deleteAllStudents() {
        Long studentsExists = studentRepository.count();
        if (studentsExists > 0) {
            studentRepository.deleteAll();
        } else {
            throw new IllegalStateException("Table has no students.");
        }
    }

    @Transactional
    public void updateStudent(UUID id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist."));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException(("email already exist."));
            }
            student.setEmail(email);
        }
    }
}
