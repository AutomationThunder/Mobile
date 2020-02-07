package pages;

import base.Keywords;
import exceptions.ApplicationException;

public class PageVirtualCardRegistrationReview extends Keywords {

    private String keyLblEmailAddress="Getgo.CreateVirtualCardReview.LblEmailAddress";
    private String keyLblFullName="Getgo.CreateVirtualCardReview.LblFullName";
    private String keyLblDOB="Getgo.CreateVirtualCardReview.LblDOB";
    private String keyLblNationality="Getgo.CreateVirtualCardReview.LblNationality";
    private String keyLblGender="Getgo.CreateVirtualCardReview.LblGender";
    private String keyLblMobile="Getgo.CreateVirtualCardReview.LblMobile";
    private String keyBtnSubmit="Getgo.CreateVirtualCardReview.BtnSubmit";

    public void reviewDetails(String emailID,String fullName,String dob, String mobile, String nationality, String gender) throws ApplicationException {
        verify.elementTextMatching(keyLblEmailAddress,emailID);
        verify.elementTextMatching(keyLblFullName,fullName);
        swipe.vertical(2,0.9,0.4);
        verify.elementTextMatching(keyLblDOB,dob);
        verify.elementTextMatching(keyLblMobile,mobile);
        verify.elementTextMatching(keyLblNationality,nationality);
        verify.elementTextMatching(keyLblGender,gender);
    }

    public void clickSubmit() throws ApplicationException {
        click.elementBy(keyBtnSubmit);
    }
}
