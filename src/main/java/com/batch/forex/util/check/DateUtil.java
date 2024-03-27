package com.batch.forex.util.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static LocalDate start;
    private static LocalDate end;

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private DateUtil(){}

    public static boolean parseDate (String startDate, String endDate) {
        try {
            start = LocalDate.parse(startDate, formatter);
            end = LocalDate.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            return true;
        }
        return false;
    }

    public static boolean calcBetweenYears() {
        logger.info("start => " + start + " end => " + end);
        Period between = Period.between(start, end);
        int years = between.getYears();
        logger.info("相隔幾年 " + years);

        return years != 0;
    }

}
