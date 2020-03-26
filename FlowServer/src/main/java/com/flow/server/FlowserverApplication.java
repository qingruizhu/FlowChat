package com.flow.server;

import com.flow.server.controller.Server;
import com.flow.server.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication(scanBasePackages = "com.flow")
@MapperScan(basePackages="com.flow.bgd.mapper,com.flow.server.dao")
public class FlowserverApplication {

        public static void main(String[] args) {
            new SpringApplicationBuilder(FlowserverApplication.class).headless(false).run(args);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        //获取ServerFrame界面实例并显示
//                        SpringContextUtils.getBean(ServerFrame.class).setVisible(true);
                        SpringContextUtils.getBean(Server.class).listening();//开启服务器监听
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


}
