package com.training.carfactory.model.dao.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private Logger(){

    }

    public static void log(String message){
        String fullmessage = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " " +  " " + message ;
        System.out.println(fullmessage);
    }
}
