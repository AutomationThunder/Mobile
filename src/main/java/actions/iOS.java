package actions;

import base.Keywords;
import constants.ObjectClass;
import exceptions.ApplicationException;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import xpath.Matching;

import java.util.List;

public class iOS extends Keywords {

    private static Logger log=Logger.getLogger(iOS.class);

    public void datePicker(String date,String month,String year) throws ApplicationException {
        log.info("Picking the specified day["+date+"] month["+month+"] year["+year+"] in the IOS calendar object");
        try
        {
            MobileElement ele;
            List<WebElement> e=driver.findElementsByClassName(ObjectClass.iOSPickerWheel);
            ele = (MobileElement) e.get(0);
            ele.setValue(date.trim());
            ele = (MobileElement) e.get(1);
            ele.setValue(month.trim());
            ele = (MobileElement) e.get(2);
            ele.setValue(year.trim());
            get.elementBy(xpathOf.button(Matching.name("Done"))).click();
        }catch(Throwable e){
            log.error("Failed to pick the date!");
            throw new ApplicationException("Failed to pick the date!\n" + e.getMessage());
        }
    }

    public void selectPicker(String value) throws ApplicationException {
        log.info("Select the value {"+value+"} in IOS picker wheel");
        try{
            MobileElement ele = (MobileElement) driver.findElementsByClassName(ObjectClass.iOSPickerWheel).get(0);
            ele.setValue(value.trim());
            get.elementBy(xpathOf.button(Matching.name("Done"))).click();
            log.info("Value "+value+" is selected!");
        }catch(Throwable e){
            log.error("Failed to select the value "+value+" in IOS picker wheel");
            throw new ApplicationException(e.getMessage());
        }
    }
}
