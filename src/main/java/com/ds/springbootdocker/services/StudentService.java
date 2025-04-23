package com.ds.springbootdocker.services;

import com.ds.springbootdocker.beans.StudentBean;
import com.ds.springbootdocker.dtos.StudentDto;
import com.ds.springbootdocker.exceptions.StudentRuntimeException;
import com.ds.springbootdocker.repositories.StudentRepository;
import com.ds.springbootdocker.utils.StudentData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    Logger logger = LogManager.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StudentDto getStudentById(long id) {
        logger.info("get student by student id {}", id);
        if (id <= 0) {
            logger.error("Invalid student id {}", id);
            throw new StudentRuntimeException("Invalid Student Id.");
        } else {
            Optional<StudentBean> studentBean = studentRepository.findById(id);
            if (studentBean.isPresent()) {
                return StudentData.populateStudentDto(studentBean.get());
            } else {
                throw new StudentRuntimeException("Student not found.");
            }
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StudentDto addStudent(StudentDto studentDto) {
        logger.info("add student {}", studentDto);
        if (studentDto.getFirstName() == null || studentDto.getFirstName().isBlank()
                || studentDto.getLastName() == null || studentDto.getLastName().isBlank()
                || studentDto.getGrade() == null || studentDto.getGrade().isBlank()) {
            logger.error("invalid student data");
            throw new StudentRuntimeException("Invalid Student Data.");
        } else {
            StudentBean studentBean = StudentData.populateStudentBean(studentDto);
            studentRepository.save(studentBean);
            studentDto.setStudentId(studentBean.getStudentId());
            logger.info("New student added with student id {}", studentDto.getStudentId());
            return studentDto;
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<StudentDto> getAllStudents() {
        logger.info("get all students");
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentBean> studentBeanList = studentRepository.findAll();
        logger.info("size of the student list {}", studentBeanList.size());
        for (StudentBean student : studentBeanList) {
            studentDtoList.add(StudentData.populateStudentDto(student));
        }
        return studentDtoList;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StudentDto removeStudentById(long id) {
        logger.info("remove student by student id {}", id);
        if (id <= 0) {
            logger.error("Invalid student id {}", id);
            throw new StudentRuntimeException("Invalid Student Id.");
        } else {
            Optional<StudentBean> studentBean = studentRepository.findById(id);
            if (studentBean.isPresent()) {
                StudentDto studentDto = StudentData.populateStudentDto(studentBean.get());
                studentRepository.deleteById(id);
                return studentDto;
            } else {
                throw new StudentRuntimeException("Student not found.");
            }
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StudentDto updateStudent(long studentId, StudentDto studentDto) {
        logger.info("update student {} -- {}", studentId, studentDto);
        if (studentDto.getFirstName() == null || studentDto.getFirstName().isBlank()
                || studentDto.getLastName() == null || studentDto.getLastName().isBlank()
                || studentDto.getGrade() == null || studentDto.getGrade().isBlank()) {
            logger.error("invalid student data");
            throw new StudentRuntimeException("Invalid Student Data.");
        } else {
            Optional<StudentBean> studentBean = studentRepository.findById(studentId);
            if (studentBean.isPresent()) {
                StudentData.updateStudentBean(studentBean.get(), studentDto);
                studentDto.setStudentId(studentId);
                logger.info("Student updated with student id {}", studentDto.getStudentId());
                return studentDto;
            } else {
                throw new StudentRuntimeException("Student not found.");
            }
        }
    }

}
