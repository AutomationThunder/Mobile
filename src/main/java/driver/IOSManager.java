package driver;

import base.Test;
import constants.Keys;
import helper.PropertyReader;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class IOSManager extends DriverManager {

    @Override
    public void createDriver() throws Exception {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName","XCUITest");
        setAppCapabilities();
        Test.attributes.put(Keys.ObjectRepository,"src/test/resources/object-repository/Locators-IOS.properties");
        driver = new IOSDriver(new URL(PropertyReader.valueOf("Driver.ServerAddress").trim()), capabilities);
    }

    private void setAppCapabilities()
    {
        if(PropertyReader.valueOf("Driver.InstallMobileAppForEveryRun").trim().equalsIgnoreCase("yes")){
            capabilities.setCapability("app","src/test/resources/apps/"+PropertyReader.valueOf("Application.Name").trim()+".ipa");
        }else{
            capabilities.setCapability("bundleId",PropertyReader.valueOf("Application.IOS.BundleID").trim());
        }
    }
}