package com.ds.springbootdocker.controllers;

import com.ds.springbootdocker.dtos.StudentDto;
import com.ds.springbootdocker.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public StudentDto getStudentById(@PathVariable long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @PutMapping("/{studentId}")
    public StudentDto updateStudent(@PathVariable long studentId, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentId, studentDto);
    }

    @DeleteMapping("/{studentId}")
    public StudentDto removeStudentById(@PathVariable long studentId) {
        return studentService.removeStudentById(studentId);
    }


}
