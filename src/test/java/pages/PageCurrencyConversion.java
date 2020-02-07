package pages;

import base.Keywords;
import base.Test;
import com.cucumber.listener.Reporter;
import exceptions.ApplicationException;
import xpath.Matching;

import java.text.DecimalFormat;

public class PageCurrencyConversion extends Keywords {

    private String keyLblPageTitle="Getgo.CurrencyConverter.LblTitle";
    private String keyLblAvailableBalance="Getgo.CurrencyConverter.LblAvailableBalance";
    private String keyBtnFromCurrencyDropdown="Getgo.CurrencyConverter.BtnFromCurrencyDropdown";
    private String keyBtnToCurrencyDropdown="Getgo.CurrencyConverter.BtnToCurrencyDropdown";
    private String keyTxtFromCurrency="Getgo.CurrencyConverter.TxtFromCurrency";
    private String keyTxtToCurrency="Getgo.CurrencyConverter.TxtToCurrency";
    private String keyLblExchangeRate="Getgo.CurrencyConverter.LblExchangeRate";
    private String keyBtnNext="Getgo.CurrencyConverter.BtnNext";

    private double balanceBeforeConversion=0.00;
    private String exchangeRate=null;
    private double amount=0.00;
    private double toAmount=0.00;

    public void isPageLoaded() throws ApplicationException {
        WAIT.forSeconds(5);
        verify.elementTextMatching(keyLblPageTitle,"Currency Converter");
        balanceBeforeConversion=Double.parseDouble(get.elementText(keyLblAvailableBalance).replaceAll(",",""));
        keyboard.hideIfAndroid();
        Reporter.addStepLog("Balance before conversion is "+balanceBeforeConversion);
    }

    public void selectFromCurrency(String fromCurrency) throws ApplicationException {
        click.elementBy(keyBtnFromCurrencyDropdown);
        click.elementBy(xpathOf.textView(Matching.youDecide(fromCurrency.trim())));
    }

    public void selectToCurrency(String toCurrency) throws ApplicationException {
        click.elementBy(keyBtnToCurrencyDropdown);
        WAIT.forSeconds(1);
        try{
            driver.findElement(xpathOf.textView(Matching.youDecide(toCurrency.trim()))).click();
        }catch (Exception ex){
            swipe.vertical(4,0.7,0.4,1);
            click.elementBy(xpathOf.textView(Matching.youDecide(toCurrency.trim())));
        }
    }

    public void enterAmount(String amount) throws ApplicationException {
        this.amount=Double.parseDouble(amount);
        type.data(keyTxtFromCurrency,amount);
    }

    public void verifyIfToAmountIsAutoPopulatedBasedonExchangeRate() throws ApplicationException {
        WAIT.forSeconds(3);
        exchangeRate=get.elementText(keyLblExchangeRate);

        String calcAmountInString=String.valueOf(Double.parseDouble(exchangeRate.split(" ")[0].trim()) * amount);
        String[] i=calcAmountInString.split("[.]");
        if(i.length<2){
            calcAmountInString="0"+calcAmountInString;
        }

        double expectedAmount=Double.parseDouble(new DecimalFormat("#0.00").format(Float.parseFloat(calcAmountInString)));
        double actualAmount=Double.parseDouble(get.elementText(keyTxtToCurrency).replaceAll(",",""));
        verify.isMatching(expectedAmount,actualAmount);
        toAmount=expectedAmount;
    }

    public void clickNext() throws ApplicationException {
        click.elementBy(keyBtnNext);
    }

    public double getBalanceBeforeConversion() {
        return balanceBeforeConversion;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public String getAmount() {
        return new DecimalFormat("#0.00").format(amount);
    }

    public String getToAmount() {
        return new DecimalFormat("#0.00").format(toAmount);
    }
}
