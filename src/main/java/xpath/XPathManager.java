package xpath;

import base.Test;
import constants.Keys;
import constants.OS;
import constants.ObjectClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class XPathManager {

    private static Logger log=Logger.getLogger(XPathManager.class);
    String os= Test.attributes.get(Keys.OS);

    public By checkedTextView()
    {
        log.debug("Get XPATH of checkedTextView object");
        String a="//"+ ObjectClass.AndroidCheckedTextView;
        log.debug("XPATH of checkedTextView object is --> "+a);
        return By.xpath(a);
    }

    public By checkedTextView(Matching matching)
    {
        log.debug("Get XPATH of checkedTextView object matching the string --> "+matching.getText());
        String a="//"+ ObjectClass.AndroidCheckedTextView+matching.getText();
        log.debug("XPATH of checkedTextView object matching the string --> "+a);
        return By.xpath(a);
    }

    public By checkedTextView(Contains contains)
    {
        log.debug("Get XPATH of checkedTextView object contains the string --> "+contains.getText());
        String a="//"+ ObjectClass.AndroidCheckedTextView+contains.getText();
        log.debug("XPATH of checkedTextView object contains the string --> "+a);
        return By.xpath(a);
    }

    public By textView()
    {
        log.debug("Get XPATH of textView object");
        String a="//"+textViewClassName();
        log.debug("XPATH of textView object is --> "+a);
        return By.xpath(a);
    }

    public By textView(Matching matching)
    {
        log.debug("Get XPATH of textView object matching the string --> "+matching.getText());
        String a="//"+textViewClassName()+matching.getText();
        log.debug("XPATH of textView object matching the string --> "+a);
        return By.xpath(a);
    }

    public By textView(Contains contains)
    {
        log.debug("Get XPATH of textView object contains the string --> "+contains.getText());
        String a="//"+textViewClassName()+contains.getText();
        log.debug("XPATH of textView object contains the string --> "+a);
        return By.xpath(a);
    }

    public By button()
    {
        log.debug("Get XPATH of button object");
        String a="//"+buttonClassName();
        log.debug("XPATH of button object is --> "+a);
        return By.xpath(a);
    }

    public By button(Matching matching)
    {
        log.debug("Get XPATH of button object matching the string --> "+matching.getText());
        String a="//"+buttonClassName()+matching.getText();
        log.debug("XPATH of button object matching the string --> "+a);
        return By.xpath(a);
    }

    public By button(Contains contains)
    {
        log.debug("Get XPATH of button object contains the string --> "+contains.getText());
        String a="//"+buttonClassName()+contains.getText();
        log.debug("XPATH of button object contains the string --> "+a);
        return By.xpath(a);
    }

    private String textViewClassName()
    {
        log.debug("Get textView class name");

        String className;
        if(os.equalsIgnoreCase(OS.ANDROID))
        {
            className= ObjectClass.AndroidTextView;
        }else
        {
            className= ObjectClass.iOSTextView;
        }
        log.debug("TextView class name is --> "+className);
        return className;
    }

    private String buttonClassName()
    {
        log.debug("Get button class name");
        String className;
        if(os.equalsIgnoreCase(OS.ANDROID))
        {
            className= ObjectClass.AndroidButton;
        }else
        {
            className= ObjectClass.iOSButton;
        }
        log.debug("Button class name is --> "+className);
        return className;
    }
}