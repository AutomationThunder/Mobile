package actions;

import base.Keywords;
import base.Test;
import constants.Keys;
import constants.OS;
import exceptions.ApplicationException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;
import java.util.HashMap;

public class Swipe extends Keywords {

    private static Logger log=Logger.getLogger(Swipe.class);

    public void scrollDownToText(String searchText) throws ApplicationException {
        searchText=searchText.trim();
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID)) {
            try{
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+searchText+"\"));"
                ));
            }catch (WebDriverException e) {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+searchText+"\"));"
                ));
            }catch (Throwable ex) {
                log.error(ex.getMessage());
                throw new ApplicationException("Element not found!"+ex.getMessage());
            }
            log.info("Scrolled to the element matching the text --> "+searchText);
        }else {
            try{
                boolean found=false;
                JavascriptExecutor js=driver;
                HashMap scrollObject = new HashMap<>();
                scrollObject.put("predicateString", "value == '" + searchText + "'");
                scrollObject.put("direction", "down");
                int toleranceTime=50;
                int i=0;
                js.executeScript("mobile: scroll", scrollObject);
                while (!(driver.findElement(MobileBy.AccessibilityId(searchText)).isDisplayed())){
                    i+=1;
                    js.executeScript("mobile: scroll", scrollObject);
                    if(i>toleranceTime){
                        break;
                    }
                    if(driver.findElement(MobileBy.AccessibilityId(searchText)).isDisplayed()){
                        found=true;
                        break;
                    }
                }
                if(!found){
                    log.error("Element matching the text "+searchText+" is not found");
                    throw new ApplicationException("Element matching the text "+searchText+" is not found");
                }
                log.info("Scrolled to the element matching the text --> "+searchText);
            }catch (Throwable ex){
                log.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void vertical(int divideScreenInto,double startPointPercentage,double endPointPercentage) throws ApplicationException {
        try {
            Dimension size = driver.manage().window().getSize();
            int x = size.width/divideScreenInto;
            int startPoint = (int) (size.height * startPointPercentage);
            int endPoint = (int) (size.height * endPointPercentage);
            log.info("Swipe vertically from {"+startPoint+"} to {"+endPoint+"}");
            new TouchAction(driver).press(PointOption.point(x, startPoint)).moveTo(PointOption.point(x, endPoint)).release().perform();
            log.info("Swiped!");
        }catch(Throwable e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public void vertical(int divideScreenInto,double startPointPercentage,double endPointPercentage,double slowDownDurationInSeconds) throws ApplicationException {
        Dimension size = driver.manage().window().getSize();
        int x = size.width/divideScreenInto;
        int startPoint = (int) (size.height * startPointPercentage);
        int endPoint = (int) (size.height * endPointPercentage);
        log.info("Slowly swipe vertically from {"+startPoint+"} to {"+endPoint+"}");
        try {
            new TouchAction(driver).press(PointOption.point(x, startPoint)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds((long) slowDownDurationInSeconds))).moveTo(PointOption.point(x, endPoint)).release().perform();
        }catch (WebDriverException e){
            new TouchAction(driver).press(PointOption.point(x, startPoint)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds((long) slowDownDurationInSeconds))).moveTo(PointOption.point(x, endPoint)).release().perform();
        }catch(Throwable e) {
            throw new ApplicationException(e.getMessage());
        }
        log.info("Swiped!");
    }

}
