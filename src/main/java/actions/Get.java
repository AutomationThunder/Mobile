package actions;

import base.Keywords;
import exceptions.ApplicationException;
import helper.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Get extends Keywords{

    private static Logger log=Logger.getLogger(Get.class);
    private static List<WebElement> testObj=null;
    private static WebElement singleTestObj=null;

    public WebElement elementBy(String locatorKey) throws ApplicationException {
        try{
            singleTestObj=wait.until(ExpectedConditions.presenceOfElementLocated(PropertyReader.locatorOf(locatorKey)));
        }
        catch(TimeoutException ex) {
            log.error("Element ["+locatorKey+"] not found!");
            throw new ApplicationException("Element ["+locatorKey+"] not found!");
        }catch(WebDriverException ex){
            singleTestObj=wait.until(ExpectedConditions.presenceOfElementLocated(PropertyReader.locatorOf(locatorKey)));
        }
        return singleTestObj;
    }

    public WebElement elementBy(By locator) throws ApplicationException {
        try{
            singleTestObj=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch(TimeoutException ex) {
            log.error("Element ["+locator+"] not found!");
            throw new ApplicationException("Element ["+locator+"] not found!");
        }catch(WebDriverException ex){
            singleTestObj=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        return singleTestObj;
    }

    public String elementText(String locatorKey) throws ApplicationException {
        log.info("Retrived the value of text property from the element ["+locatorKey+"]");
        String txt=null;
        try{
            txt=elementBy(locatorKey).getText();
        }catch (WebDriverException ex){
            txt=elementBy(locatorKey).getText();
        }
        log.info("Text ["+txt+"] retrieved successfully!");
        return txt.trim();
    }

    public String elementText(By locator) throws ApplicationException {
        log.info("Retrieve the value of text property from the element ["+locator+"]");
        String txt=null;
        try{
            txt=elementBy(locator).getText();
        }catch (WebDriverException ex){
            txt=elementBy(locator).getText();
        }
        log.info("Text retrieved successfully!");
        return txt.trim();
    }

    public String elementAttribute(String locatorKey, String whichAttribute) throws ApplicationException {
        log.info("Retrieve the value of ["+whichAttribute+"] property from the element ["+locatorKey+"]");
        String txt=null;
        try{
            txt=elementBy(locatorKey).getAttribute(whichAttribute.trim());
        }catch (WebDriverException ex){
            txt=elementBy(locatorKey).getAttribute(whichAttribute.trim());
        }
        log.info("Value retrieved successfully!");
        return txt.trim();
    }

    public String elementAttribute(By locator, String whichAttribute) throws ApplicationException {
        log.info("Retrieve the value of ["+whichAttribute+"] property from the element ["+locator+"]");
        String txt=null;
        try{
            txt=elementBy(locator).getAttribute(whichAttribute.trim());
        }catch (WebDriverException ex){
            txt=elementBy(locator).getAttribute(whichAttribute.trim());
        }
        log.info("Value retrieved successfully!");
        return txt.trim();
    }
}