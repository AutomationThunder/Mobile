package helper;

import base.Test;
import constants.Keys;
import exceptions.EnvironmentException;
import io.appium.java_client.MobileBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyReader {
    private static Logger log=Logger.getLogger(PropertyReader.class);
    public static String valueOf(String property) {
        File file=new File("src/test/resources/settings/ProjectSettings.properties");
        Properties prop=new Properties();
        InputStream input=null;
        try {
            input=new FileInputStream(file);
            prop.load(input);
        }catch (IOException ex) {
            log.error(ex);
            ex.printStackTrace();
        }finally {
            if(input!=null) {
                try{
                    input.close();
                }catch (IOException ex){
                    log.error(ex);
                    ex.printStackTrace();
                }
            }
            if(prop.getProperty(property)==null){
                try{
                    throw new EnvironmentException("Property ["+property+"] value is null in file "+file.getName());
                }catch (EnvironmentException ex){
                    log.error(ex);
                    ex.printStackTrace();
                }
            }
        }
        return prop.getProperty(property).trim();
    }

    public static String testDataOf(String valueOf) {
        File file=new File("src/test/resources/data/Data-Input.properties");
        Properties prop=new Properties();
        InputStream input=null;
        try {
            input=new FileInputStream(file);
            prop.load(input);
        }catch (IOException ex) {
            log.error(ex);
            ex.printStackTrace();
        }finally {
            if(input!=null) {
                try{
                    input.close();
                }catch (IOException ex){
                    log.error(ex);
                    ex.printStackTrace();
                }
            }
            if(prop.getProperty(valueOf)==null){
                try{
                    throw new EnvironmentException("Property value is null in file "+file.getName());
                }catch (EnvironmentException ex){
                    log.error(ex);
                    ex.printStackTrace();
                }
            }
        }
        return prop.getProperty(valueOf).trim();
    }

    public static By locatorOf(String locatorKey) {
        By locatorBy;
        String locator=null;
        ArrayList<String> locatorType=new ArrayList<>();

        locatorType.add("ID");
        locatorType.add("AID");
        locatorType.add("CLASS");
        locatorType.add("XPATH");

        File file=new File(Test.attributes.get(Keys.ObjectRepository));
        Properties prop=new Properties();
        InputStream input=null;
        try {
            input=new FileInputStream(file);
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if(input!=null) {
                try{
                    input.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }

            for (String i:locatorType) {
                try{
                    locator=prop.getProperty(locatorKey.trim()+"."+i.toUpperCase()).trim();
                }catch (NullPointerException ex){
                    continue;
                }
                if(locator!=null){
                    locatorKey=locatorKey.trim()+"."+i.toUpperCase();
                    break;
                }
            }
            if(locator==null){
                try{
                    throw new EnvironmentException("Property value is null in file "+file.getName());
                }catch (EnvironmentException ex){
                    ex.printStackTrace();
                }
            }

            String[] locatorSplit = locatorKey.trim().split("[.]");
            switch (locatorSplit[locatorSplit.length-1].trim().toUpperCase()) {
                case "ID":
                    locatorBy=By.id(locator);
                    break;
                case "AID":
                    locatorBy=MobileBy.AccessibilityId(locator);
                    break;
                case "XPATH":
                    locatorBy=By.xpath(locator);
                    break;
                case "CLASS":
                    locatorBy=By.className(locator);
                    break;
                default:
                    locatorBy=By.xpath(locator);
            }
        }
        return locatorBy;
    }
}