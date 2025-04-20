package com.ds.springbootdocker.utils;

import com.ds.springbootdocker.dtos.StudentDto;
import com.ds.springbootdocker.exceptions.StudentRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentData {
    private static final Map<Long, StudentDto> studentData = new HashMap<>();
    private static long studentId = 0;

    private StudentData() {
        // No need
    }

    public static void createStudentData() {
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setStudentId(getStudentId());
        studentDto1.setFirstName("Sandeera");
        studentDto1.setLastName("Jayampathi");
        studentDto1.setGrade("10");

        StudentDto studentDto2 = new StudentDto();
        studentDto2.setStudentId(getStudentId());
        studentDto2.setFirstName("Uthpala");
        studentDto2.setLastName("Hewage");
        studentDto2.setGrade("11");

        StudentDto studentDto3 = new StudentDto();
        studentDto3.setStudentId(getStudentId());
        studentDto3.setFirstName("Nadun");
        studentDto3.setLastName("Dananjaya");
        studentDto3.setGrade("10");

        StudentDto studentDto4 = new StudentDto();
        studentDto4.setStudentId(getStudentId());
        studentDto4.setFirstName("Nadun");
        studentDto4.setLastName("Senarathne");
        studentDto4.setGrade("9");

        studentData.put(studentDto1.getStudentId(), studentDto1);
        studentData.put(studentDto2.getStudentId(), studentDto2);
        studentData.put(studentDto3.getStudentId(), studentDto3);
        studentData.put(studentDto4.getStudentId(), studentDto4);
    }

    private static synchronized long getStudentId() {
        return ++studentId;
    }

    public static void updateStudent(StudentDto studentDto) {
        studentData.put(studentDto.getStudentId(), studentDto);
    }

    public static void addStudent(StudentDto studentDto) {
        studentDto.setStudentId(getStudentId());
        studentData.put(studentDto.getStudentId(), studentDto);
    }

    public static List<StudentDto> getAllStudents() {
          return new ArrayList<>(studentData.values());
    }

    public static StudentDto getStudentById(long id) {
        if (studentData.containsKey(id)) {
            return studentData.get(id);
        } else {
            throw new StudentRuntimeException("Student not found.");
        }
    }

    public static StudentDto removeStudentById(long id) {
        if (studentData.containsKey(id)) {
            StudentDto studentDto = studentData.get(id);
            studentData.remove(id);
            return studentDto;
        } else {
            throw new StudentRuntimeException("Student not found.");
        }
    }


}
