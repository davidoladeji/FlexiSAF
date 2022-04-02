package com.davidoladeji.flexisaf.util;

import com.davidoladeji.flexisaf.model.Student;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class FullnameGenerated implements ValueGenerator<String> {



    public String generateValue(Session session, Object object)
    {
        Student student = (Student) object;

        return  student.getFirstName() + " " + student.getOtherName() + " " + student.getLastName();
    }


}
