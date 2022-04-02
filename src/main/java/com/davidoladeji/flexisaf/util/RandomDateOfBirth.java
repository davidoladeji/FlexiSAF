package com.davidoladeji.flexisaf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class RandomDateOfBirth {


    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static Date randDob() throws ParseException {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1996, 2003);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        String dateString = gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateOfBirth = sdf.format(formatter.parse(dateString));

        return sdf.parse(dateOfBirth);
    }
}