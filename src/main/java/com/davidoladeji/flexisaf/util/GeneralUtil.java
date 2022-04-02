package com.davidoladeji.flexisaf.util;

import java.time.LocalDate;
import java.time.Period;

public class GeneralUtil {

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
