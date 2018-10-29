package com.royarn.mini.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-23
 */
public class TimeDemo {

    public static void main(String[] args) {
        /**LocalDate date = LocalDate.now();
        System.out.println(JSON.toJSON(date));


        Period period = Period.between(LocalDate.of(2018, 03, 07),
                LocalDate.of(2018, 10, 23));
        System.out.println(period.getDays());*/

        /**LocalDate date = LocalDate.now();
        date = date.minusMonths(6);
        System.out.println(date.toString());*/

        /**LocalDate date = LocalDate.now();
        date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        date = date.with(TemporalAdjusters.lastDayOfMonth());
        date = date.with(new NextWorkingDay());*/

        //use custom date by lambda
        /**LocalDate date = LocalDate.of(2018, 10, 26);
        date = date.with(temporal -> {
            int dayToAdd = 1;
            DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            if (day == DayOfWeek.FRIDAY) {dayToAdd = 3;}
            else if (day == DayOfWeek.SATURDAY) {dayToAdd = 2;}
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date.toString());*/

        //use official API
        /**LocalDate date = LocalDate.of(2018, 10, 26);
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    int dayToAdd = 1;
                    DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    if (day == DayOfWeek.FRIDAY) {dayToAdd = 3; }
                    else if (day == DayOfWeek.SATURDAY) {dayToAdd = 2;}
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        date = date.with(nextWorkingDay);
        System.out.println(date.toString());*/

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();
        LocalDate date1 = LocalDate.parse(date.format(formatter), formatter);
        System.out.println(date1.toString());
    }
}

class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToadd = 1;
        if (day == DayOfWeek.FRIDAY) {dayToadd = 3;}
        else if (day == DayOfWeek.SATURDAY) {dayToadd = 2;}
        return temporal.plus(dayToadd, ChronoUnit.DAYS);
    }
}