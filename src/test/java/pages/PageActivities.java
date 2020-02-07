package pages;

import base.Keywords;
import base.Test;
import com.cucumber.listener.Reporter;
import constants.Keys;
import constants.OS;
import exceptions.ApplicationException;
import org.junit.Assert;

import java.text.ParseException;

public class PageActivities extends Keywords {

    private String keyLblTransactionReferenceNumber="Getgo.Activities.LblReferenceNumber";
    private String keyLblTransactionDate="Getgo.Activities.LblTransactionDate";
    private String keyLblDescription="Getgo.Activities.LblDescription";
    private String keyLblTransactionAmount="Getgo.Activities.LblTransactionAmount";
    private String keyLblEndingBalance="Getgo.Activities.LblEndingBalance";

    public void getTransactionReferenceNumber() throws ApplicationException {
        Reporter.addStepLog("Transaction Reference Number is --> "+get.elementBy(keyLblTransactionReferenceNumber));
    }

    public void reviewTransactionDate(String day,String month,String year) throws ParseException, ApplicationException {
        String transferDate=null;
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID))
        {
            transferDate = Test.tools.getDateInFormat(day,month,year,"MMMM dd, YYYY");
        }else if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS))
        {
            transferDate = Test.tools.getDateInFormat(day,month,year,"MMM dd, YYYY");
        }
        verify.elementTextContains(keyLblTransactionDate,transferDate);
    }

    public void reviewDescription(String description) throws ApplicationException {
        verify.elementTextMatching(keyLblDescription,description);
    }

    public void verifyTransactionAmount(double transactionAmount) throws ApplicationException {
        verify.isMatching(Test.tools.pesoAmount(transactionAmount), Test.tools.fixAmountIssue(get.elementBy(keyLblTransactionAmount).getText()));
    }

    public void verifyEndingBalance(double amount,double beforeBalance) throws ApplicationException {
        double expectedBalance= beforeBalance-amount;
        Assert.assertEquals("PHP "+Double.toString(expectedBalance), Test.tools.fixAmountIssue(get.elementBy(keyLblEndingBalance).getText()));
    }
}