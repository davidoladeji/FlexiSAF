package com.davidoladeji.flexisaf.service;


import com.davidoladeji.flexisaf.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {


    List<Student> doSearch(Student student);

    List<Student> getStudents();

    Student createStudent(Student student);

    Student getStudent(long Id);

    void update(long Id, Student student);

    void delete(long Id);
}