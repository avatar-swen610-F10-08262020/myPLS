package com.mypls.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    Properties appProps = new Properties();  //
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "/application.properties";
    public Properties configuration(){
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps;
    }

}
