package driver;

import base.Test;
import constants.Keys;
import helper.PropertyReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class DriverManager {

    protected AppiumDriver<WebElement> driver;
    protected WebDriverWait wait;
    protected DesiredCapabilities capabilities = new DesiredCapabilities();

    protected abstract void createDriver() throws Exception;

    public AppiumDriver getDriver() throws Exception {
        if (null == driver) {
            capabilities.setCapability("platformVersion",PropertyReader.valueOf("Device."+Test.attributes.get(Keys.Device)+".Version"));
            capabilities.setCapability("deviceName", PropertyReader.valueOf("Device."+ Test.attributes.get(Keys.Device)+".Name"));
            capabilities.setCapability("udid", PropertyReader.valueOf("Device."+ Test.attributes.get(Keys.Device)+".ID"));
            capabilities.setCapability("newCommandTimeout",Integer.parseInt(PropertyReader.valueOf("Driver.Appium.CommandTimeout")));
            createDriver();
            wait=new WebDriverWait(driver,Integer.parseInt(PropertyReader.valueOf("Driver.Timeout").trim()));
        }
        return driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }
}