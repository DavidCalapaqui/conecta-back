package com.example.conecta_test.utils;

import org.springframework.data.convert.Jsr310Converters;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Circulation {


    public static boolean canCirculateToday(int endDigit, LocalDateTime time){

        DayOfWeek today = time.getDayOfWeek();
        System.out.println("Dia a validar: "+ today);
        List<Integer> monday = List.of(1,2);
        List<Integer> tuesday = List.of(3,4);
        List<Integer> wednesday = List.of(5,6);
        List<Integer> thursday = List.of(7,8);
        List<Integer> friday = List.of(9,0);

        //sabado o domingo circula sin problemas
        if(today==DayOfWeek.SATURDAY  || today==DayOfWeek.SUNDAY){
            System.out.println("Es sábado o domingo");
            return true;
        }

        boolean isInTimetableMorning = isWithinTimetable(time, LocalTime.of(6,0), LocalTime.of(9,30));
        boolean isInTimetableAfternoon = isWithinTimetable(time, LocalTime.of(16,0), LocalTime.of(20,0));
        boolean isLessThanMiddleDay = isLessThanMiddleDay(time);
        System.out.println("isLessThanMiddleDay: " + isLessThanMiddleDay);
        System.out.println("isInTimetableMorning: "+ isInTimetableMorning);
        System.out.println("isInTimetableAfternoon: "+ isInTimetableAfternoon);
        boolean isInTimetable = isLessThanMiddleDay ? isInTimetableMorning : isInTimetableAfternoon;



        System.out.println("isInTimetable: "+ isInTimetable);

        switch (today){
            case MONDAY:
                if(monday.contains(endDigit) && isInTimetable ){
                    return false;
                }
                break;
            case TUESDAY:
                if(tuesday.contains(endDigit) && isInTimetable ){
                    return false;
                }
                break;

            case WEDNESDAY:
                if(wednesday.contains(endDigit) && isInTimetable){
                    return false;
                }
                break;

            case THURSDAY:
                if(thursday.contains(endDigit) && isInTimetable ){
                    return false;
                }
                break;

            case FRIDAY:
                if(friday.contains(endDigit) && isInTimetable){
                    return false;
                }
                break;
        }
        System.out.println("Ninguna condicion para ningun día");
        return true;
    }

    public static boolean isWithinTimetable(LocalDateTime dateTime, LocalTime start, LocalTime end){
        LocalTime hour = dateTime.toLocalTime();
        boolean isOnTime = !hour.isBefore(start) && !hour.isAfter(end);
        //System.out.println("Is on time: "+ isOnTime);
        return isOnTime;
    }

    public static boolean isLessThanMiddleDay(LocalDateTime dateTime){
        LocalTime middleDay = LocalTime.of(12,0);
        return middleDay.isAfter(dateTime.toLocalTime());
    }

    public static boolean validateDate(LocalDateTime dateTime){
        try{
            LocalDateTime current = LocalDateTime.now();
            return !dateTime.isBefore(current);
        }catch (DateTimeException e){
            return false;
        }
    }
}
