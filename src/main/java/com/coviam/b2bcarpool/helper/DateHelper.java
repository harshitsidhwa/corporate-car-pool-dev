package com.coviam.b2bcarpool.helper;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Component("com.coviam.b2bcarpool.helper.DateHelper")
public class DateHelper {

    public String getDateFormatted(int noOfDays) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(calendar.DAY_OF_MONTH, noOfDays);
        Date tomorrow = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mmm");
        String formattedDate = formatter.format(tomorrow);
        String[] TomorrowDate = formattedDate.split(" ");
        formattedDate = TomorrowDate[0] + "T" + TomorrowDate[1] + "Z";
        return formattedDate;
    }

    public static String getDateNowStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(c.getTime());
    }

    public static Date getDateNow() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getDateTimeAfterHours(int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, hours);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getDateTimeBeforeHours(int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, -1 * hours);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getDateTimeAfterHours(Date startTime, int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.HOUR, hours);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getDateTimeBeforeHours(Date startTime, int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.HOUR, -1 * hours);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(sdf.format(c.getTime()));
    }
}
