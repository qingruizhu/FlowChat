package com.flow.server;

import com.flow.server.controller.ServerFrame;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication(scanBasePackages = "com.flow")
@MapperScan("com.flow.bgd.mapper")
public class FlowserverApplication {

        public static void main(String[] args) {
            new SpringApplicationBuilder(FlowserverApplication.class).headless(false).run(args);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        //获取ServerFrame界面实例并显示
                        SpringContextUtils.getBean(ServerFrame.class).setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


}
