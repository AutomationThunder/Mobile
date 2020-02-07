package stepdefinitions;

import base.Test;
import constants.Keys;
import constants.OS;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PageAccountDashboard;
import pages.PageCardTransfer;
import pages.PageManageRecipient;
import projectconstants.MenuItem;

public class Getgo_ManageRecipient
{
    private static PageAccountDashboard dashboard=new PageAccountDashboard();
    private static PageCardTransfer transfer=new PageCardTransfer();
    private static PageManageRecipient manageRecipient=new PageManageRecipient();

    @When("^I Choose Manage Recipients option$")
    public void iChooseManageRecipientsOption() throws Throwable
    {
        transfer.clickAddRecipient();
    }

    @And("^Add a recipient and tag it as a favorite$")
    public void addARecipientAndTagItAsAFavorite() throws Throwable
    {
        manageRecipient.addNewRecipient();
        manageRecipient.addToFavourites(manageRecipient.getNewlyAddedRecipientCard());
        manageRecipient.goBack();
    }

    @Then("^Recipient should be displayed in my favourites$")
    public void recipientShouldBeDisplayedInMyFavourites() throws Throwable
    {
        manageRecipient.isRecipientAvailableInFavourites(manageRecipient.getNewlyAddedRecipientCard());
    }

    @Given("^I'm on Beneficiaries page$")
    public void iMOnBeneficiariesPage() throws Throwable
    {
        dashboard.clickMenu();
        dashboard.navigateTo(MenuItem.Beneficiaries());
    }

    @When("^I Choose Manage Recipients option again$")
    public void iChooseManageRecipientsOptionAgain() throws Throwable
    {
        transfer.clickAddRecipient();
    }

    @When("^I open Beneficiaries page again$")
    public void iOpenBeneficiariesPageAgain() throws Throwable
    {
        if(Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS)){
            dashboard.clickMenu();
        }
        dashboard.navigateTo(MenuItem.Beneficiaries());
    }

    @When("^I add a recipient and tag it as a favorite$")
    public void iAddARecipientAndTagItAsAFavorite() throws Throwable {
        manageRecipient.addNewRecipient();
        manageRecipient.addToFavourites(manageRecipient.getNewlyAddedRecipientCard());
        manageRecipient.goBack();
    }
}
