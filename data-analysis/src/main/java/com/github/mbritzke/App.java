package com.github.mbritzke;

import com.github.mbritzke.configuration.AppConfig;
import com.github.mbritzke.service.MonitorDirectoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MonitorDirectoryService monitorDirectoryService = (MonitorDirectoryService) applicationContext.getBean("monitorDirectory");
        monitorDirectoryService.findFile();
    }
}
