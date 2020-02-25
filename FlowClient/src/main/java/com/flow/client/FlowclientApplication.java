package com.flow.client;

import com.flow.client.controller.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class FlowclientApplication {

    public static void main(String[] args) {
        //SwingUtilities.invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
        SpringApplication.run(FlowclientApplication.class, args);
    }

}
