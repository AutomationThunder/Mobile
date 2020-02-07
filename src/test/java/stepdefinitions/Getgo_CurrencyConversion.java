package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;
import projectconstants.MenuItem;

public class Getgo_CurrencyConversion {

    private String fromCurrency,toCurrency;
    private static PageOTP otp=new PageOTP();
    private static PageAccountDashboard dashboard=new PageAccountDashboard();
    private PageCurrencyConversion cc=new PageCurrencyConversion();
    private PageCurrencyConversionReview review=new PageCurrencyConversionReview();
    private PageCurrencyConversionSuccess success=new PageCurrencyConversionSuccess();

    @Given("^I'm on Currency conversion screen after noting down the balance of \"([^\"]*)\" currency$")
    public void iMOnCurrencyConversionScreenAfterNotingDownTheBalanceOfCurrency(String toCurrency) throws Throwable {
        Thread.sleep(1000);
        dashboard.displayBalanceOfCurrency(toCurrency);
        dashboard.clickMenu();
        dashboard.navigateTo(MenuItem.CurrencyConverter());
    }

    @When("^I choose \"([^\"]*)\" currency and \"([^\"]*)\" currency$")
    public void iChooseCurrencyAndCurrency(String fromCurrency, String toCurrency) throws Throwable {
        this.fromCurrency=fromCurrency.trim().toUpperCase();
        this.toCurrency=toCurrency.trim().toUpperCase();
        cc.isPageLoaded();
        cc.selectFromCurrency(fromCurrency);
        cc.selectToCurrency(toCurrency);
    }

    @And("^I enter the conversion amount \"([^\"]*)\"$")
    public void iEnterTheConversionAmount(String amount) throws Throwable {
        cc.enterAmount(amount.trim());
    }

    @Then("^Conversion amount will be automatically populated under To Amount field$")
    public void conversionAmountWillBeAutomaticallyPopulatedUnderToAmountField() throws Throwable {
        cc.verifyIfToAmountIsAutoPopulatedBasedonExchangeRate();
        cc.clickNext();
    }

    @When("^I review conversion details and submit$")
    public void iReviewConversionDetailsAndSubmit() throws Throwable {
        //review.transferFrom(PropertyReader.testDataOf("Peso_FullName"),PropertyReader.testDataOf("Peso_CardNumber"));
        review.transferAmount(fromCurrency + " " + String.valueOf(cc.getAmount()));
        review.convertedAmount(toCurrency + " " + String.valueOf(cc.getToAmount()));
        review.conversionRate("1 Philippine Peso equals "+cc.getExchangeRate());
        review.clickConvert();
        otp.enterOTP();
    }

    @Then("^Amount should be converted & displayed in the dashboard$")
    public void amountShouldBeConvertedDisplayedInTheDashboard() throws Throwable {
        success.isConversionSuccess();
        success.gotoDashboard();
        double mainBalance=cc.getBalanceBeforeConversion()-Double.parseDouble(cc.getAmount());
        double currencyBalance=dashboard.getCurrencyBalance()+Double.parseDouble(cc.getToAmount());
        dashboard.verifyBalanceAfterConversionForCurrency("peso",mainBalance,toCurrency,currencyBalance);
    }
}
