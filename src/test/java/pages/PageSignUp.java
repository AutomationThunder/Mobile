package pages;

import base.Keywords;
import exceptions.ApplicationException;

public class PageSignUp extends Keywords {

    private String keyImgGetgoCard = "Getgo.SignUp.ImgGetgoCard";
    private String keyLblPageCaption1 = "Getgo.SignUp.LblCaption1";
    private String KeyLblPageCaption2 = "Getgo.SignUp.LblCaption2";
    private String keyBtnCreateVirutalCard = "Getgo.SignUp.BtnCreateVirtualCard";
    private String keyBtnRegisterPesoCard = "Getgo.SignUp.BtnRegisterPhysicalCard";

    public void doesPageContains(String caption, String caption2, String createVirutalCardBtnTxt, String registerPhysicalCardBtnTxt) throws ApplicationException
    {
        verify.elementIsPresent(keyImgGetgoCard);
        verify.elementTextMatching(keyBtnCreateVirutalCard,createVirutalCardBtnTxt);
        verify.elementTextMatching(keyBtnRegisterPesoCard,registerPhysicalCardBtnTxt);
        verify.elementTextMatching(keyLblPageCaption1,caption);
        verify.elementTextMatching(KeyLblPageCaption2,caption2);
    }

    public void registerVirtualCard() throws ApplicationException {
        click.elementBy(keyBtnCreateVirutalCard);
    }
}
