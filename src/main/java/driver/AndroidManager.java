package driver;

import base.Test;
import constants.Keys;
import helper.PropertyReader;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class AndroidManager extends DriverManager {

    @Override
    public void createDriver() throws Exception {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName","UiAutomator2");
        setAppCapabilities();
        Test.attributes.put(Keys.ObjectRepository,"src/test/resources/object-repository/Locators-Android.properties");
        driver = new AndroidDriver(new URL(PropertyReader.valueOf("Driver.ServerAddress").trim()), capabilities);
    }

    private void setAppCapabilities()
    {
        if(PropertyReader.valueOf("Driver.InstallMobileAppForEveryRun").trim().equalsIgnoreCase("yes")){
            capabilities.setCapability("app","src/test/resources/apps/"+PropertyReader.valueOf("Application.Name").trim()+".apk");
        }else{
            capabilities.setCapability("appPackage",PropertyReader.valueOf("Application.Android.Package").trim());
            capabilities.setCapability("appActivity",PropertyReader.valueOf("Application.Android.LaunchActivity").trim());
        }
    }
}