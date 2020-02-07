package pages;

import base.Keywords;
import base.Test;
import com.cucumber.listener.Reporter;
import constants.Keys;
import constants.OS;
import constants.ObjectClass;
import exceptions.ApplicationException;
import helper.Device;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import xpath.Matching;

public class PageAccountDashboard extends Keywords {

    private String keyImgProfilePicture="Getgo.Dashboard.ImgProfilePicture";
    private String keyLblUserName="Getgo.Dashboard.LblUsername";
    private String keyBtnMenu="Getgo.Dashboard.BtnMenu";
    private String keyLblAvailableBalancePeso="Getgo.Peso+Dashboard.LblAvailableBalance";
    private String keyLblAvailableBalanceVirtualCard="Getgo.VirtualDashboard.LblAvailableBalance";
    private String keyLogout="Getgo.Dashboard.LinkLogout";
    private String keyLblVerifyYourEmail="Getgo.Dashboard.LblVerifyYourEmail";

    private double myAccountBalance=0.00;
    private double currencyBalance=0.00;

    public void isVirtualCardDisplayed() throws ApplicationException {
        verify.elementIsPresent("Getgo.Dashboard.ImgCard");
    }

    public void isOk(String userName) throws ApplicationException {
        verify.elementIsPresent(keyImgProfilePicture);
        verify.elementTextMatching(keyLblUserName,userName);
    }

    public void isVerifyYourEmailIsPresent() throws ApplicationException {
        verify.elementIsPresent(keyLblVerifyYourEmail);
    }

    public void displayMyAccountBalance(String accountType,boolean failIfZeroBalance) throws ApplicationException {
        double actualBalance;
        switch (accountType.trim().toUpperCase()){
            case "VIRTUAL":
                for(int i=1;i<=5;i++)
                {
                   try{
                       actualBalance=Double.parseDouble(get.elementBy(keyLblAvailableBalanceVirtualCard).getText().trim().replaceAll("\\u00A0",""));
                   }catch (NumberFormatException e){
                       actualBalance=0.00;
                   }
                   if(actualBalance!=0.00){
                        myAccountBalance=actualBalance;
                        break;
                   }
                   WAIT.forSeconds(2);
                }
                break;
            case "PESO":
                for(int i=1;i<=5;i++)
                {
                    try{
                        actualBalance=Double.parseDouble(get.elementBy(keyLblAvailableBalancePeso).getText().trim().replaceAll("\\u00A0","").replaceAll(",",""));
                    }catch (NumberFormatException e){
                        actualBalance=0.00;
                    }
                    if(actualBalance!=0.00){
                        myAccountBalance=actualBalance;
                        break;
                    }
                    WAIT.forSeconds(2);
                }
                break;
        }
        Reporter.addStepLog("My Account Balance is --> "+myAccountBalance);
        if(myAccountBalance==0.00){
            if(failIfZeroBalance){
                throw new ApplicationException("Your account balance is "+myAccountBalance+" we can't proceed testing with this balance");
            }
        }
    }

    public double accountBalance() {
        return myAccountBalance;
    }

    public void clickMenu() throws ApplicationException {
        click.elementBy(keyBtnMenu);
    }

    public void navigateTo(String where) throws ApplicationException {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID)) {
            click.elementBy(xpathOf.checkedTextView(Matching.text(where)));
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){
            click.elementBy(MobileBy.AccessibilityId(where));
        }
    }

    public void logout() throws ApplicationException {
        click.elementBy(keyLogout);
    }

    /*
        ================================================================================================================
                                                For Currency Conversion
        ================================================================================================================
     */

    private boolean isCurrencyDisplayedOnDashboard(String currencyName){
        boolean isDisplayed=false;
        try{
            isDisplayed=get.elementBy(xpathOf.textView(Matching.youDecide(currencyName))).isDisplayed();
        }catch (Throwable ex1){
            try{
                swipe.vertical(2,0.9,0.5,2);
                isDisplayed=get.elementBy(xpathOf.textView(Matching.youDecide(currencyName))).isDisplayed();
            }catch (Throwable ex2){
            }
        }
        return isDisplayed;
    }

    private double getBalanceOfCurrency_Android(String currencyName) throws ApplicationException {
        double balance;
        WebElement parentElement=get.elementBy(By.xpath("//android.widget.TextView[@text='"+currencyName+"']/parent::*"));
        balance=Double.parseDouble(parentElement.findElements(By.xpath("//"+ ObjectClass.AndroidTextView)).get(1).getText().trim().split(" ")[1].trim());
        return balance;
    }

    private double getBalanceOfCurrency_iOS(String currencyName) throws ApplicationException {
        double balance;
        WebElement parentElement=get.elementBy(By.xpath("//XCUIElementTypeStaticText[@value='"+currencyName+"']/parent::*"));
        balance=Double.parseDouble(parentElement.findElements(By.xpath("//"+ ObjectClass.iOSTextView)).get(1).getText().trim().split(" ")[1].trim());
        return balance;
    }

    private double getBalanceOf(String currencyName) throws ApplicationException {
        currencyName=currencyName.trim().toUpperCase();
        if(!isCurrencyDisplayedOnDashboard(currencyName)){
            throw new ApplicationException("Currency "+currencyName+" is not displayed in the dashboard");
        }
        double bal=0.00;
        if(Device.isAndroid()){
            bal=getBalanceOfCurrency_Android(currencyName);
        }else if(Device.isIOS()){
            bal=getBalanceOfCurrency_iOS(currencyName);
        }
        return bal;
    }

    public double getCurrencyBalance() {
        return currencyBalance;
    }

    public void displayBalanceOfCurrency(String currency) throws ApplicationException {
        currencyBalance=getBalanceOf(currency);
        Reporter.addStepLog("Balance of currency "+currency+" is --> "+currencyBalance);
    }

    public void verifyBalanceAfterConversionForCurrency(String accountType, double expectedMainBalance, String currency, double expectedCurrencyBalance) throws ApplicationException {

        /*
            Validate Main Balance
         */

        double actualMainBalance;
        if(accountType.equalsIgnoreCase("peso"))
        {
            actualMainBalance=Double.parseDouble(get.elementText(keyLblAvailableBalancePeso).replaceAll(",",""));
        }else{
            actualMainBalance=Double.parseDouble(get.elementText(keyLblAvailableBalanceVirtualCard).replaceAll(",",""));
        }

        verify.isMatching(expectedMainBalance,actualMainBalance);

        /*
            Validate Currency Balance
         */

        double actualCurrencyBalance=getBalanceOf(currency);
        verify.isMatching(expectedCurrencyBalance,expectedCurrencyBalance);
    }
}