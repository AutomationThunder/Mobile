package stepdefinitions;

import base.Test;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.PropertyReader;
import pages.*;

public class Getgo_VirtualCardRegistration {

    private static PageSignUp signUp=new PageSignUp();
    private static PageVirtualCardRegistration vRegister=new PageVirtualCardRegistration();
    private static PageOTP otp=new PageOTP();
    private static PageWelcome welcome=new PageWelcome();
    private static PageVirtualCardRegistrationReview vReview=new PageVirtualCardRegistrationReview();
    private static PageVirtualCardRegistrationSuccess success=new PageVirtualCardRegistrationSuccess();
    private static PageAccountDashboard dashboard=new PageAccountDashboard();
    private static PageVerificationLink verifyAccount=new PageVerificationLink();
    private static PageLogin login=new PageLogin();

    private String mobileNumber=PropertyReader.testDataOf("RegistrationMobileNumber");
    private String emailAddress="("+Test.faker.number().randomNumber(5,true)+")"+PropertyReader.testDataOf("RegistrationEmailAddress");
    private String password=PropertyReader.testDataOf("RegistrationPassword");
    private String firstName=Test.faker.name().firstName();
    private String middleName=Test.faker.name().firstName();
    private String lastName=Test.faker.name().lastName();
    private String fullName=firstName+" "+middleName+" "+lastName;
    private String dob;
    private String gender="Male";
    private String nationality="Philippines, Republic of the";

    @When("^I enter Mobile Number, Email Address, and Nominate and Confirm password$")
    public void iEnterMobileNumberEmailAddressAndNominateAndConfirmPassword() throws Throwable {
        vRegister.enterBasicDetails(mobileNumber,emailAddress,password);vRegister.clickNext();
    }

    @And("^I enter one time password$")
    public void iEnterOneTimePassword() throws Throwable {
        otp.enterOTP();
    }

    @And("^I fill my personal details$")
    public void iFillMyPersonalDetails() throws Throwable {
        vRegister.enterPersonalDetails(firstName,middleName,lastName,nationality,gender);
        dob=vRegister.getDob();
        vRegister.clickNext();
    }

    @And("^I click verify now$")
    public void iClickVerifyNow() throws Throwable {
        success.isRegistrationSuccess();
        success.verifyNow();
    }

    @Then("^I should see the dashboard$")
    public void iShouldSeeTheDashboard() throws Throwable {
        dashboard.isVerifyYourEmailIsPresent();
    }

    @Given("^I'm on Getgo virtual card registration screen$")
    public void iMOnGetgoVirtualCardRegistrationScreen() throws Throwable {
        welcome.clickSignUp();
        signUp.registerVirtualCard();
    }

    @When("^I click the verification email link from my inbox$")
    public void iClickTheVerificationEmailLinkFromMyInbox() throws Throwable {
        String outlookUsername=PropertyReader.testDataOf("RegistrationEmailAddress");
        String outlookPassword=PropertyReader.testDataOf(outlookUsername);
        verifyAccount.openOutlook(outlookUsername,outlookPassword);
        verifyAccount.openVerificationEmail(firstName+" "+lastName);
    }

    @Then("^I should see a message account is verified$")
    public void iShouldSeeAMessageAccountIsVerified() throws Throwable {
        verifyAccount.isVerificationSuccess();
    }

    @When("^I login with the My Email next time$")
    public void iLoginWithTheMyEmailNextTime() throws Throwable {
        welcome.launchGetgoFresh();
        welcome.clickLogin();
        login.login(emailAddress,password);
    }

    @Then("^I should see my virtual card in the dashboard$")
    public void iShouldSeeMyVirtualCardInTheDashboard() throws Throwable {
        dashboard.isVirtualCardDisplayed();
    }

    @And("^I review & submit$")
    public void iReviewSubmit() throws Throwable {
        vReview.reviewDetails(emailAddress,fullName,dob,mobileNumber,nationality,gender);
        vReview.clickSubmit();
    }
}
