package keywords;

import base.Keywords;
import exceptions.ApplicationException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetgoKeywords extends Keywords {

    public void scrollToRecipient_iOS(String recipientID) throws ApplicationException {
        try{
            boolean found=false;
            String elementText=null;
            List<WebElement> allRecipients=driver.findElementsByXPath("//");
            for(int i=0;i<=allRecipients.size();i++){
                WebElement currentElement=allRecipients.get(i);
                if(currentElement.getText().contentEquals(recipientID.trim())){
                    if(currentElement.isDisplayed()){
                        currentElement.click();
                        found=true;
                        break;
                    }else{
                        swipe.vertical(2,0.9,0.4);
                    }
                }
            }
            if(!found){
                throw new ApplicationException("Not found the element");
            }

        }catch (Throwable ex)
        {
            throw new ApplicationException("Could not file the recipient "+ex.getMessage());
        }
    }
}
