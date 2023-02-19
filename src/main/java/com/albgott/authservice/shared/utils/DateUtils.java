package com.albgott.authservice.shared.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class DateUtils {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static LocalDateTime getCurrentDateTimeMinusHours(long hours) {
        return LocalDateTime.now().minusHours(hours);
    }

    public static LocalTime getCurrentTimeMinusHours(long hours) {
        return LocalTime.now().minus(hours, ChronoUnit.HOURS);
    }

    public static LocalDateTime getCurrentDateTimePlusHours(long hours) {
        return LocalDateTime.now().plusHours(hours);
    }

    public static LocalTime getCurrentTimePlusHours(long hours) {
        return LocalTime.now().plus(hours, ChronoUnit.HOURS);
    }

    public static boolean hasDatePassed(LocalDateTime dateTime) {
        return !dateTime.isAfter(getCurrentDateTime());
    }
}
