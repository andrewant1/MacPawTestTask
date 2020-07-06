package com.macpaw.properties;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesConfig {

    Properties properties =new Properties();
    public void loadProperties(){

        try {
            InputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            properties.load(new InputStreamReader(file, Charset.forName("UTF-8")));
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}
