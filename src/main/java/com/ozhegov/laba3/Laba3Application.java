package com.ozhegov.laba3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import com.ozhegov.laba3.dao.HibernateUtil;

@FacesConfig
@ApplicationScoped
public class Laba3Application {
    public static void main(String[] args) {
        try {
            HibernateUtil.getSessionFactory();
            
            System.out.println("Laba3 Application initialized successfully");
            
            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Error initializing application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
} 