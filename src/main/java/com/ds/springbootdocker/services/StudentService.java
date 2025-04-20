package com.ds.springbootdocker.services;

import com.ds.springbootdocker.dtos.StudentDto;
import com.ds.springbootdocker.exceptions.StudentRuntimeException;
import com.ds.springbootdocker.utils.StudentData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    Logger logger = LogManager.getLogger(StudentService.class);

    public StudentDto getStudentById(long id) {
        logger.info("get student by student id {}", id);
        if (id <= 0) {
            logger.error("Invalid student id {}", id);
            throw new StudentRuntimeException("Invalid Student Id.");
        } else {
            return StudentData.getStudentById(id);
        }
    }

    public StudentDto addStudent(StudentDto studentDto) {
        logger.info("add student {}", studentDto);
        if (studentDto.getFirstName() == null || studentDto.getFirstName().isBlank()
                || studentDto.getLastName() == null || studentDto.getLastName().isBlank()
                || studentDto.getGrade() == null || studentDto.getGrade().isBlank()) {
            logger.error("invalid student data");
            throw new StudentRuntimeException("Invalid Student Data.");
        } else {
            StudentData.addStudent(studentDto);
            logger.info("New student added with student id {}", studentDto.getStudentId());
            return studentDto;
        }
    }

    public List<StudentDto> getAllStudents() {
        logger.info("get all students");
        return StudentData.getAllStudents();
    }

    public StudentDto removeStudentById(long id) {
        logger.info("remove student by student id {}", id);
        if (id <= 0) {
            logger.error("Invalid student id {}", id);
            throw new StudentRuntimeException("Invalid Student Id.");
        } else {
            return StudentData.removeStudentById(id);
        }
    }


    public StudentDto updateStudent(long studentId, StudentDto studentDto) {
        logger.info("update student {} -- {}", studentId, studentDto);

        StudentDto studentDtoDb = getStudentById(studentId);

        if (studentDto.getFirstName() == null || studentDto.getFirstName().isBlank()
                || studentDto.getLastName() == null || studentDto.getLastName().isBlank()
                || studentDto.getGrade() == null || studentDto.getGrade().isBlank()) {
            logger.error("invalid student data");
            throw new StudentRuntimeException("Invalid Student Data.");
        } else {
            studentDto.setStudentId(studentId);
            removeStudentById(studentId);
            StudentData.updateStudent(studentDto);
            logger.info("Student updated with student id {}", studentDto.getStudentId());
            return studentDto;
        }
    }

}
