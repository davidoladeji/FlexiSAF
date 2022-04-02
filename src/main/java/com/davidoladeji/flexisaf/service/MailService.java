package com.davidoladeji.flexisaf.service;


import com.davidoladeji.flexisaf.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    private JavaMailSender javaMailSender;

    /**
     *
     * @param javaMailSender
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * This function is used to send mail without attachment.
     * @param student
     * @throws MailException
     */

    public void sendEmail(Student student) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */

        String genderL = student.getGender().equals("M") ? "dude" : "gal";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("flexisaf@codesett.com");
        mail.setTo(student.getEmailAddress());
        mail.setSubject( "Hey, " + student.getFirstName() + " Happy Birthday!!!");
        mail.setText("Hurray ! Its your birthday "+ genderL+" ...");

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }


}
