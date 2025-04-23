package com.ds.springbootdocker.beans;


import jakarta.persistence.*;

@Entity
@Table(name = "tbl_student_data")
public class StudentBean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_student_id_seq")
    @SequenceGenerator(name = "tbl_student_id_seq", sequenceName = "tbl_student_id_seq", allocationSize = 1)
    @Column(name = "STUDENT_ID")
    private long studentId;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "GRADE")
    private String grade;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
