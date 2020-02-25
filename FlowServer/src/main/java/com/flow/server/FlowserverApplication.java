package com.flow.server;

import com.flow.server.controller.ServerFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class FlowserverApplication {

    public static void main(String[] args) {
        //SwingUtilities.invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerFrame();
            }
        });
        SpringApplication.run(FlowserverApplication.class, args);
    }

}
