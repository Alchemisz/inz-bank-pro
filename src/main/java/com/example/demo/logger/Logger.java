package com.example.demo.logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static List<String> logs = new ArrayList<>();

    public static boolean contains(String sub) {
        for(String log : logs) {
            if(log.contains(sub)) {
                return true;
            }
        }
        return false;
    }

    public static void log(String message) {
        logs.add(message);
        System.out.println(message);
    }
}
