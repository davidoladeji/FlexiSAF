package com.davidoladeji.flexisaf.repository;

import com.davidoladeji.flexisaf.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {

    Optional<Student> findByMatricNumber(String matricNumber);
    Student getStudentByMatricNumber(String matricNumber);
}