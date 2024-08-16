package com.juro.study.server;

import com.juro.study.config.AppConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ControllerContext {

    private AppConfig config;

    private Map<String,Object> map;

    public ControllerContext(AppConfig config) {
        this.config = config;
        map = new HashMap<String,Object>();
    }

    public AppConfig getConfig() {
        return config;
    }

    public void set(String name, Object obj) {
        map.put(name, obj);
    }

    public Object get(String name) {
        return map.get(name);
    }

}
