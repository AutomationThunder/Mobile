package pages;

import base.Keywords;
import base.Test;
import constants.Keys;
import constants.OS;
import exceptions.ApplicationException;

public class PageCardTransferSuccess extends Keywords {

    private String keyImgGetgoLogo="Getgo.CardTransferSuccess.ImgGetgoLogo";
    private String keyLblCongrats="Getgo.CardTransferSuccess.LblCongrats";
    private String keyLblMessage="Getgo.CardTransferSuccess.LblMessage";
    private String keyBtnViewDetails="Getgo.CardTransferSuccess.BtnViewDetails";
    private String keyBtnDashboard="Getgo.CardTransferSuccess.BtnDashboard";

    public void doesPageContains() throws ApplicationException {
        verify.elementIsPresent(keyImgGetgoLogo);
    }

    public void isTransferSuccess() throws ApplicationException {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            verify.elementTextContains(keyLblCongrats,"Congratulations!");
            verify.elementTextContains(keyLblMessage,"You have successfully transfer fund.");
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS))
        {
            verify.elementIsPresent(keyLblCongrats);
            verify.elementIsPresent(keyLblMessage);
        }
    }

    public void viewDetails() throws ApplicationException {
        click.elementBy(keyBtnViewDetails);
    }

    public void gotoDashboard() throws ApplicationException {
        click.elementBy(keyBtnDashboard);
    }
}
