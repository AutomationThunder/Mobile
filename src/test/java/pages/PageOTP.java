package pages;

import base.Keywords;
import base.Test;
import constants.Keys;
import constants.OS;
import exceptions.ApplicationException;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class PageOTP extends Keywords {

    public void enterOTP() throws ApplicationException {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID)){
            if(get.elementBy(By.id("com.unionbankph.getgopay.qat:id/ivCircle1")).isDisplayed()){
                AndroidDriver aDriver= (AndroidDriver) driver;
                aDriver.pressKeyCode(7);
                aDriver.pressKeyCode(7);
                aDriver.pressKeyCode(7);
                aDriver.pressKeyCode(7);
                aDriver.pressKeyCode(7);
                aDriver.pressKeyCode(7);
            }
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){

        }
    }
}
