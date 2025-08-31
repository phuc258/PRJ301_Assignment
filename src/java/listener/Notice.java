/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 *
 * @author SE190585
 */
public class Notice implements ServletContextListener{
     @Override
    public void contextDestroyed(ServletContextEvent sce) {        
        System.out.println("We are temporarily pausing for maintenance and bug fixes. We apologize for the inconvenience.");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {         
         System.out.println("The system has been successfully updated. We are ready to serve you.");
    }
}
