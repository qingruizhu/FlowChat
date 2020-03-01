package com.flow.client;

import com.flow.client.controller.Login;
import com.flow.client.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication(scanBasePackages = "com.flow")
@MapperScan(basePackages="com.flow.bgd.mapper,com.flow.client.dao")
public class FlowclientApplication {

//    public static void main(String[] args) {
//        //SwingUtilities.invokeLater
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Login();
//            }
//        });
//        SpringApplication.run(FlowclientApplication.class, args);
//    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(FlowclientApplication.class).headless(false).run(args);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //获取ServerFrame界面实例并显示
                    SpringContextUtils.getBean(Login.class).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
