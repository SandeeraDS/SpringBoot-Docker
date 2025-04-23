package com.ds.springbootdocker.repositories;

import com.ds.springbootdocker.beans.StudentBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentBean, Long> {
}
