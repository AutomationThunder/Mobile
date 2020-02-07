package pages;

import base.Keywords;
import exceptions.ApplicationException;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import xpath.Matching;

public class PageVirtualCardRegistration extends Keywords {

    private String keyTxtMobileNumber="Getgo.CreateVirtualCard.TxtMobileNumber";
    private String keyTxtEmailAddress="Getgo.CreateVirtualCard.TxtEmailAddress";
    private String keyTxtNominatePassword="Getgo.CreateVirtualCard.TxtNominatePassword";
    private String keyTxtConfirmPassword="Getgo.CreateVirtualCard.TxtConfirmPassword";
    private String keyNext="Getgo.CreateVirtualCard.BtnNext";

    private String keyTxtFirstName="Getgo.CreateVirtualCard.TxtFirstName";
    private String keyTxtMiddleName="Getgo.CreateVirtualCard.TxtMiddleName";
    private String keyTxtLastName="Getgo.CreateVirtualCard.TxtLastName";
    private String keyBtnCalendar="Getgo.CreateVirtualCard.BtnCalendar";
    private String keyBtnNationality="Getgo.CreateVirtualCard.BtnNationalityDropdown";
    private String keyRadioGender_Male="Getgo.CreateVirtualCard.BtnGender.Male";
    private String keyRadionGender_Female="Getgo.CreateVirtualCard.BtnGender.Female";

    private String dob;

    public void enterBasicDetails(String mobileNumber, String emailID, String password) throws ApplicationException {
        type.data(keyTxtMobileNumber,mobileNumber);
        type.data(keyTxtEmailAddress,emailID);
        type.sensitiveData(keyTxtNominatePassword,password);
        type.sensitiveData(keyTxtConfirmPassword,password);
    }

    public void clickNext() throws ApplicationException {
        click.elementBy(keyNext);
    }

    public void enterPersonalDetails(String firstName,String middleName,String lastName,String nationality, String gender) throws ApplicationException {
        type.data(keyTxtFirstName,firstName);
        type.data(keyTxtMiddleName,middleName);
        type.data(keyTxtLastName,lastName);
        selectDOB("23","July","1992");
        selectNationality(nationality);
        selectGender(gender);
    }

    public void selectNationality(String nationality) throws ApplicationException {
        click.elementBy(keyBtnNationality);
        swipe.scrollDownToText(nationality);
        click.elementBy(xpathOf.textView(Matching.youDecide(nationality)));
    }

    public void selectGender(String gender) throws ApplicationException {
        if(gender.equalsIgnoreCase("male")){
            click.elementBy(keyRadioGender_Male);
        }else{
            click.elementBy(keyRadionGender_Female);
        }
    }

    public void selectDOB(String day,String month, String year) throws ApplicationException {
        click.elementBy(keyBtnCalendar);
        int systemYear=Integer.parseInt(get.elementText(By.id("com.unionbankph.getgopay.qat:id/mdtp_date_picker_year")));
        int maximumLoopTime=(systemYear-Integer.parseInt(year.trim()))/5;
        boolean clickedYear=false;
        for(int i=0;i<=maximumLoopTime;i++){
            try{
                driver.findElement(MobileBy.AccessibilityId(year.trim())).click();
                get.elementBy(By.id("com.unionbankph.getgopay.qat:id/mdtp_ok")).click();
                clickedYear=true;
                break;
            }catch (Throwable ex){
                swipe.vertical(2,0.4,0.9,2);
                WAIT.forSeconds(2);
            }
        }
        if(!clickedYear){
            throw new ApplicationException("Failed to select the date of birth");
        }else{
            dob=get.elementText(keyBtnCalendar);
        }
    }

    public String getDob() {
        return dob;
    }
}
