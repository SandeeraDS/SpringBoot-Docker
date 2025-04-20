package com.ds.springbootdocker;

import com.ds.springbootdocker.utils.StudentData;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerApplication.class, args);
    }

    @PostConstruct
    public void createStudentData() {
        StudentData.createStudentData();
    }

}
