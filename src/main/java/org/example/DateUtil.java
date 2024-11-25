package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Помощник для первода из даты в строку и обратно
 */
public class DateUtil {
    /**
     * Формат даты
     */
    public static final String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * Получение строкового представления даты
     *
     * @param date - обеъкт даты
     *
     * @return String
     */
    public static String getStringFromDate(Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Получение даты из строки
     *
     * @param date - строковое предстваление даты
     *
     * @return Date
     */
    public static Date getDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("Ошибка парсинга даты");
            return null;
        }
    }
}
