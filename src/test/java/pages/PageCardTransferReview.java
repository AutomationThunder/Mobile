package pages;

import base.Keywords;
import base.Test;
import constants.Keys;
import constants.OS;
import exceptions.ApplicationException;
import xpath.Contains;
import xpath.Matching;

import java.text.ParseException;

public class PageCardTransferReview extends Keywords {

    private String keyBtnTransfer="Getgo.CardTransferReview.BtnTransfer";
    private String keyTransferFrom="Getgo.CardTransferReview.LblTransferFrom";
    private String keyTransferTo="Getgo.CardTransferReview.LblTransferTo";
    private String keyTransferFees="Getgo.CardTransferReview.LblTransferFees";
    private String keyTransferAmount="Getgo.CardTransferReview.LblTransferAmount";
    private String keyTransferDate="Getgo.CardTransferReview.LblTransferDate";
    private String keyTransferMessage="Getgo.CardTransferReview.LblMessage";

    public void fromDetails(String fromCard,String fromUser) throws ApplicationException {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            verify.elementTextContains(keyTransferFrom,fromUser);
            verify.elementTextContains(keyTransferFrom,fromCard);
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){
            verify.elementTextContains(xpathOf.textView(Contains.name(fromUser.substring(0,4))),fromUser);
            verify.elementTextContains(xpathOf.textView(Contains.name(fromCard)),fromCard);
        }
    }

    public void toDetails(String toCard,String toUser) throws ApplicationException {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            verify.elementTextContains(keyTransferTo,toUser);
            verify.elementTextContains(keyTransferTo,toCard);
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){
            verify.elementTextContains(xpathOf.textView(Contains.name(toUser.substring(0,4))),toUser);
            verify.elementTextContains(xpathOf.textView(Contains.name(toCard)),toCard);
        }
    }

    public void transferFees(double transferFees) throws ApplicationException
    {
        String expectedValue=Test.tools.pesoAmount(transferFees);
        String actualValue=Test.tools.nbspRemove(get.elementBy(keyTransferFees).getText());
        verify.isMatching(expectedValue,actualValue);
    }

    public void transferMessage(String message) throws ApplicationException {
        verify.elementTextContains(keyTransferMessage,message);
    }

    public void transferAmount(double transferAmount) throws ApplicationException
    {
            String expectedValue=Test.tools.pesoAmount(transferAmount);
            String actualValue=Test.tools.nbspRemove(get.elementBy(keyTransferAmount).getText());
            verify.isMatching(expectedValue,actualValue);
    }

    public void transferDate(String day,String month,String year) throws ParseException, ApplicationException {
        String transferDate=null;
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            transferDate = Test.tools.getDateInFormat(day,month,year,"MMMM dd, YYYY");
            verify.elementTextContains(keyTransferDate,transferDate);

        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS))
        {
            transferDate = Test.tools.getDateInFormat(day,month,year,"MMM dd, YYYY");
            verify.elementTextContains(xpathOf.textView(Matching.name(transferDate)),transferDate);
        }
    }

    public void clickTransfer() throws ApplicationException {
        click.elementBy(keyBtnTransfer);
    }
}