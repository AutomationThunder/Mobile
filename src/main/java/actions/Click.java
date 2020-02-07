package actions;

import base.Keywords;
import exceptions.ApplicationException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Click extends Keywords{

    private static Logger log=Logger.getLogger(Click.class);

    public void elementBy(String locatorKey) throws ApplicationException {
        log.info("Click element ["+locatorKey+"]");
        try{
            get.elementBy(locatorKey).click();
        }catch (StaleElementReferenceException ex){
            get.elementBy(locatorKey).click();
        }
        log.info("Click Successful!");
    }

    public void elementBy(By locator) throws ApplicationException {
        log.info("Click element ["+locator+"]");
        try{
            get.elementBy(locator).click();
        }catch (StaleElementReferenceException ex){
            get.elementBy(locator).click();
        }
        log.info("Click Successful!");
    }
}
