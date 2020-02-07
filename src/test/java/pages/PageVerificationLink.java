package pages;

import base.Keywords;
import base.Test;
import constants.Keys;
import exceptions.ApplicationException;
import helper.PropertyReader;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;

public class PageVerificationLink extends Keywords {

    AppiumDriver<WebElement> browser;
    WebDriverWait browserWait;

    public void openOutlook(String username, String password) throws ApplicationException{
        driver.quit();
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion",PropertyReader.valueOf("Device."+Test.attributes.get(Keys.Device)+".Version"));
        cap.setCapability("deviceName", PropertyReader.valueOf("Device."+ Test.attributes.get(Keys.Device)+".Name"));
        cap.setCapability("udid", PropertyReader.valueOf("Device."+ Test.attributes.get(Keys.Device)+".ID"));
        cap.setCapability("newCommandTimeout",Integer.parseInt(PropertyReader.valueOf("Driver.Appium.CommandTimeout")));
        cap.setCapability("browserName",BrowserType.CHROME);
        try{
            browser=new AppiumDriver<>(new URL(PropertyReader.valueOf("Driver.ServerAddress")),cap);
        }catch (Throwable ex){
            throw new ApplicationException("Failed to open chrome browser on the device");
        }
        browser.get("https://outlook.office.com");
        browserWait=new WebDriverWait(browser,30);
        getWebElement(By.name("loginfmt")).sendKeys(username);
        getWebElement(By.id("idSIButton9")).click();
        WAIT.forSeconds(2);
        getWebElement(By.id("i0118")).sendKeys(password);
        getWebElement(By.id("idSIButton9")).click();
        WAIT.forSeconds(2);
        getWebElement(By.id("idBtn_Back")).click();
    }

    public void openVerificationEmail(String fullName) throws ApplicationException {
        getWebElement(By.xpath("//span[contains(text(),'"+fullName+"')]")).click();
        String verificationLink=getWebElement(By.xpath("//a[contains(text(),'https://')]")).getText();
        browser.get(verificationLink);
    }

    public void isVerificationSuccess() throws ApplicationException {
        String expectedValue="Email Verified";
        String actualValue=getWebElement(By.xpath("//h1[@class='text-ggp mb-4']")).getText().trim();
        Assert.assertEquals(actualValue,expectedValue);
        browser.quit();
    }

    private WebElement getWebElement(By locator) throws ApplicationException {
        WebElement ele;
        try{
            ele=browserWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (WebDriverException ex){
            ele=browserWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Throwable ex){
            throw new ApplicationException("Couldn't find the element --> "+locator);
        }
        return ele;
    }
}