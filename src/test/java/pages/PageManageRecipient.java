package pages;

import base.Keywords;
import base.Test;
import constants.Keys;
import constants.OS;
import constants.ObjectClass;
import exceptions.ApplicationException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import xpath.Matching;

public class PageManageRecipient extends Keywords
{
    private String keyBtnAddNewRecipient="Getgo.ManageRecipient.BtnAdd";
    private String keyTxtRecipientCardNumber="Getgo.ManageRecipient.TxtCard";
    private String keyTxtRecipientName="Getgo.ManageRecipient.TxtRecipientName";
    private String keyBtnSave="Getgo.ManageRecipient.BtnSave";
    private String keyBtnBack="Getgo.ManageRecipient.BtnBack";

    private String keyBtnTxtRecipientCardNumber_iOS="Getgo.ManageRecipient.TxtCard";
    private String keyBtnTxtRecipientName_iOS="Getgo.ManageRecipient.TxtRecipientName";

    private String newlyAddedRecipient=null;
    private String newlyAddedRecipientCard=null;

    public void addNewRecipient() throws ApplicationException {
        newlyAddedRecipient= Test.faker.name().firstName()+" "+ Test.faker.name().lastName();
        newlyAddedRecipientCard=
                        String.valueOf(Test.faker.number().randomNumber(3,true))+
                        String.valueOf(Test.faker.number().randomNumber(10,true))+
                        String.valueOf(Test.faker.number().randomNumber(3,true));
        WAIT.forSeconds(3);
        click.elementBy(keyBtnAddNewRecipient);
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID)){
            type.data(keyTxtRecipientCardNumber,newlyAddedRecipientCard);
            type.data(keyTxtRecipientName,newlyAddedRecipient);
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){
            type.data(keyBtnTxtRecipientCardNumber_iOS,newlyAddedRecipientCard);
            type.data(keyBtnTxtRecipientName_iOS,newlyAddedRecipient);
        }
        click.elementBy(keyBtnSave);
    }

    public void addToFavourites(String recipientCard) throws ApplicationException {
        WAIT.forSeconds(2);
        swipe.scrollDownToText(recipientCard);
        WebElement parentElement;
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            parentElement=get.elementBy(By.xpath("//android.widget.TextView[@text='"+recipientCard+"']/parent::*"));
            parentElement.findElements(By.xpath("//"+ ObjectClass.AndroidImageButton)).get(0).click();
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS))
        {
            parentElement=get.elementBy(By.xpath("//XCUIElementTypeStaticText[@value='"+recipientCard+"']/parent::*"));
            parentElement.findElements(By.xpath("//"+ ObjectClass.iOSButton)).get(0).click();
        }
    }

    public void isRecipientAvailableInFavourites(String recipientCard) throws ApplicationException {
        try{
            get.elementBy(xpathOf.textView(Matching.youDecide(recipientCard))).isDisplayed();
        }catch (Throwable ex){
            swipe.scrollDownToText(recipientCard);
        }
        WebElement parentElement;
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            parentElement=get.elementBy(By.xpath("//android.widget.TextView[@text='"+recipientCard+"']/parent::*"));
            String isSelected=parentElement.findElements(By.xpath("//"+ ObjectClass.AndroidImageButton)).get(0).getAttribute("selected").trim();
            Assert.assertEquals(isSelected,"true","Recipient is not added to favourites");
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS))
        {
            parentElement=get.elementBy(By.xpath("//XCUIElementTypeStaticText[@value='"+recipientCard+"']/parent::*"));
            String isSelected=parentElement.findElements(By.xpath("//"+ ObjectClass.iOSButton)).get(0).getAttribute("name").trim();
            Assert.assertEquals(isSelected,"ic star filled","Recipient is not added to favourites");
        }
    }

    public void goBack() throws ApplicationException {
        click.elementBy(keyBtnBack);
    }

    public String getNewlyAddedRecipientCard()
    {
        return newlyAddedRecipientCard;
    }
}