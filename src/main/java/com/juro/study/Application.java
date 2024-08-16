package com.juro.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.juro.study.config.AppConfig;
import com.juro.study.server.Controller;

public class Application {

    public static void main(String[] args){
        
//        String configFileName = "applicationContext.xml";

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Controller controller = (Controller) context.getBean(Controller.class);

//        System.out.println(controller.getConfig().getObservedDay());
//        System.out.println(controller.getConfig().getTargetAssetType());

    }
}
