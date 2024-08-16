package com.juro.study.server;


import com.juro.study.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    @Autowired
    private AppConfig config;

    private ControllerContext context;

    private Controller(){
        context = new ControllerContext(config);
        this.config = context.getConfig();
    }

    public AppConfig getConfig(){
        return this.config;
    }

}
