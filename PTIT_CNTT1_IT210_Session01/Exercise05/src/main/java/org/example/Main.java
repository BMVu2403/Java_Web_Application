package org.example;

import org.example.config.AppConfig;
import org.example.model.SystemConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SystemConfig config = context.getBean(SystemConfig.class);

        System.out.println("Ten chi nhanh: " + config.getBranchName());
        System.out.println("Gio mo cua: " + config.getOpeningHour());
    }
}