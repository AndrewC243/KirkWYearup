package com.pluralsight;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        var now = new Date();
        var format = new SimpleDateFormat("MM/dd/yyyy");
        var format2 = new SimpleDateFormat("yyyy-MM-dd");
        var format3 = new SimpleDateFormat("MMMM dd, yyyy");
        var format4 = new SimpleDateFormat("EEEEEE, MMM dd, yyyy hh:mm");
        var challengeFormat = DateTimeFormatter.ofPattern("hh:mm 'on' dd-MMM-yyyy");
        System.out.println(format.format(now));
        System.out.println(format2.format(now));
        System.out.println(format3.format(now));
        System.out.println(format4.format(now));
        System.out.println("\n" + challengeFormat.format(now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }
}