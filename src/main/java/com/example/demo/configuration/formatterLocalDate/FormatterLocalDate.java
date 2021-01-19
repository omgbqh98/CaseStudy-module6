package com.example.demo.configuration.formatterLocalDate;

import com.example.demo.service.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatterLocalDate implements Formatter<LocalDate>, org.springframework.format.Formatter<Object> {
    private String datePattern;
    private DateTimeFormatter dateTimeFormatter;
    public FormatterLocalDate(String datePattern) {
        this.datePattern =datePattern;
        dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(datePattern));
    }

    @Override
    public String print(Object object, Locale locale) {
        return null;
    }
//    @Override
//    public LocalDate parse(String text, Locale locale) throws ParseException {
//        return LocalDate.parse(text, DateTimeFormatter.ofPattern(datePattern));
//    }
//    @Override
//    public String print(LocalDate date, Locale locale) {
//        return date.format(dateTimeFormatter);
//    }
}