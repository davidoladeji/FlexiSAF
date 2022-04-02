package com.davidoladeji.flexisaf.service.implementation;

import com.davidoladeji.flexisaf.data.SpecificationEnum;
import com.davidoladeji.flexisaf.exception.ResourceNotFoundException;
import com.davidoladeji.flexisaf.model.Student;
import com.davidoladeji.flexisaf.repository.StudentRepository;
import com.davidoladeji.flexisaf.service.StudentService;
import com.davidoladeji.flexisaf.specification.SearchCriteria;
import com.davidoladeji.flexisaf.specification.StudentSpecification;
import com.davidoladeji.flexisaf.util.GeneralUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        student = studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students = studentRepository.findAll();
        return students;
    }


    //
    @Override
    public List<Student> doSearch(Student studentDetails) {

        StudentSpecification studentsFirstNameQuery = new StudentSpecification();
        StudentSpecification studentsLastNameQuery = new StudentSpecification();
        StudentSpecification studentsGenderQuery = new StudentSpecification();
        studentsFirstNameQuery.add(new SearchCriteria("firstName", studentDetails.getFirstName(), SpecificationEnum.EQUAL));
        studentsLastNameQuery.add(new SearchCriteria("lastName", studentDetails.getLastName(), SpecificationEnum.EQUAL));
        studentsGenderQuery.add(new SearchCriteria("gender", studentDetails.getGender(), SpecificationEnum.EQUAL));

        List<Student> usersByFirstName = studentRepository.findAll(studentsFirstNameQuery);
        List<Student> allResults = new ArrayList<>(usersByFirstName);

        List<Student> usersByLastName = studentRepository.findAll(studentsLastNameQuery);

        for (Iterator<Student> iter = usersByLastName.iterator(); iter.hasNext(); ) {
            Student student = iter.next();
            //if the user has already been found with firstname match don't add to list again
            //The search is flexible and not strict on combined attributes so if
            if(!allResults.contains(student)){
                allResults.add(student);
            }

        }



        return allResults;
    }


    @Override
    public Student getStudent(long Id) {
        Student student = new Student();
        if (studentRepository.findById(Id).isPresent()) {
            student = studentRepository.findById(Id).get();
        }
        return student;
    }

    @Override
    public void update(long Id, Student studentDetails) {
        if (studentRepository.findById(Id).isPresent()) {
            Student updateStudent = studentRepository.findById(Id).get();
            updateStudent.setFirstName(studentDetails.getFirstName());
            updateStudent.setLastName(studentDetails.getLastName());
            updateStudent.setGender(studentDetails.getGender());
            studentRepository.save(updateStudent);
        } else {
            throw new IllegalStateException("A user with that id does not exist: " + Id);
        }

    }

    @Override
    public void delete(long Id) {
        if (studentRepository.findById(Id).isPresent()) {
            studentRepository.deleteById(Id);
        } else {
            throw new IllegalStateException("A user with that id does not exist: " + Id);
        }
    }
}