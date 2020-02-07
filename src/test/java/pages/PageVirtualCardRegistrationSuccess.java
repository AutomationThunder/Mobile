package pages;

import base.Keywords;
import base.Test;
import exceptions.ApplicationException;

public class PageVirtualCardRegistrationSuccess extends Keywords {

    private String keyLblMessage2="Getgo.CreateVirtualCardSuccess.LblMessage";
    private String keyLblMessage1="Getgo.CreateVirtualCardSuccess.LblCongrats";
    private String keyBtnVerifyNow="Getgo.CreateVirtualCardSuccess.BtnVerifyNow";

    public void isRegistrationSuccess() throws ApplicationException {
        verify.elementTextMatching(keyLblMessage1,"Congratulations!");
        String actualMsg=get.elementText(keyLblMessage2);
        actualMsg=actualMsg.replaceAll("\n"," ").replaceAll("\\u00A0"," ").replaceAll("  "," ");
        String expectedMsg="All you need to do is verify your email and you're set to shop online with your GetGo Virtual Prepaid Card.";
        verify.isMatching(expectedMsg,actualMsg);
    }

    public void verifyNow() throws ApplicationException {
        click.elementBy(keyBtnVerifyNow);
    }
}
