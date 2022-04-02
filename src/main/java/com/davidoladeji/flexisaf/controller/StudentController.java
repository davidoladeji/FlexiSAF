package com.davidoladeji.flexisaf.controller;

import com.davidoladeji.flexisaf.data.SpecificationEnum;
import com.davidoladeji.flexisaf.model.Student;
import com.davidoladeji.flexisaf.service.MailService;
import com.davidoladeji.flexisaf.service.StudentService;
import com.davidoladeji.flexisaf.specification.SearchCriteria;
import com.davidoladeji.flexisaf.specification.StudentSpecification;
import com.davidoladeji.flexisaf.util.GeneralUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.time.Month;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MailService notificationService;


    private static final Gson gson = new Gson();

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }


    @GetMapping("/search")
    public List<Student> searchStudents(@RequestBody Student studentDetails){
        return studentService.doSearch(studentDetails);
    }


    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody Student student)
            throws URISyntaxException {
        Date today = new Date();
        //Convert today and the birthdate to Localdate
        LocalDate todayL = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dobL = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = GeneralUtil.calculateAge(dobL, todayL);

        if (age >= 18 && age <= 25) {
            Student createdStudent = studentService.createStudent(student);
            if (createdStudent == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok()
                        .body(createdStudent);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

   //@Scheduled(fixedRate = 10000) // Every 10 seconds
     //@Scheduled(cron="*/10 * * * * *") // Every 10 seconds

    @Scheduled(cron="0 0 0 * * *") // Once everyday
   // @Scheduled(cron = "@daily")  // Also Once everyday
    public void birthDayMessenger(){
        List<Student> allStudents = studentService.getStudents();
        System.out.println("Checking birthdays for the day ...");
        if(!allStudents.isEmpty()){

            for (Iterator<Student> iter = allStudents.iterator(); iter.hasNext(); ) {
                Student student = iter.next();

                Date today = new Date();
                LocalDate todayL = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


                int dateToday = todayL.getDayOfMonth();
                Month monthToday = todayL.getMonth();

                LocalDate dobL = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int dateBirth = dobL.getDayOfMonth();
                Month monthBirth = dobL.getMonth();

                if(dateToday == dateBirth && monthToday == monthBirth) {
                    try {
                        notificationService.sendEmail(student);
                    } catch (MailException mailException) {
                        System.out.println(mailException);
                    }

                }
            }
        }



    }

    @GetMapping("{Id}")
    public ResponseEntity<Object> getStudentById(@PathVariable  long Id){
        Student student = studentService.getStudent(Id);


        return ResponseEntity.ok(student);
    }


    @PutMapping("{Id}")
    public ResponseEntity<String> updateStudent(@PathVariable long Id,@RequestBody Student studentDetails) {
        studentService.update(Id, studentDetails);
        return new ResponseEntity<String>(studentService.getStudent(Id).toString(), HttpStatus.OK);
    }


    @DeleteMapping("{Id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long Id){
        studentService.delete(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}