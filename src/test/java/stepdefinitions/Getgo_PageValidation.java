package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.PropertyReader;
import pages.PageLogin;
import pages.PageSignUp;
import pages.PageWelcome;

public class Getgo_PageValidation
{

    private static PageWelcome welcomePage=new PageWelcome();
    private static PageSignUp signUpPage=new PageSignUp();
    private static PageLogin loginPage =new PageLogin();

    @Given("^I'm on Getgo landing page$")
    public void i_m_on_Getgo_landing_page() throws Throwable
    {
        welcomePage.launchGetgo();
    }

    @Then("^I should see two buttons \"([^\"]*)\" \"([^\"]*)\", Getgo logo, a caption \"([^\"]*)\"$")
    public void i_should_see_two_buttons_Getgo_logo_a_caption(String loginBtnTxt, String signUpBtnTxt, String captionTxt) throws Throwable
    {
        welcomePage.doesPageContains(loginBtnTxt,signUpBtnTxt,captionTxt);
    }

    @Given("^I'm on Getgo SignUp page$")
    public void i_m_on_Getgo_SignUp_page() throws Throwable
    {
        welcomePage.clickSignUp();
    }

    @Then("^I should see two button \"([^\"]*)\" \"([^\"]*)\", Getgo card image, two messages \"([^\"]*)\" & \"([^\"]*)\"$")
    public void i_should_see_two_button_Getgo_card_image_two_messages(String register, String create, String caption1, String caption2) throws Throwable
    {
        signUpPage.doesPageContains(caption1,caption2,create,register);
    }

    @Given("^I'm on Getgo login page$")
    public void i_m_on_Getgo_login_page() throws Throwable
    {
        welcomePage.clickLogin();
    }

    @Then("^I should see back arrow, page title as \"([^\"]*)\", a caption \"([^\"]*)\", a text box with inner text \"([^\"]*)\", a link \"([^\"]*)\", a \"([^\"]*)\" button & a sign up link \"([^\"]*)\"$")
    public void i_should_see_back_arrow_page_title_as_a_caption_a_text_box_with_inner_text_a_link_a_button_a_sign_up_link(String pageTitle, String caption, String emailBoxInnerTxt, String havingProblemsLinkTxt, String nextBtnTxt, String signUpLinkTxt) throws Throwable
    {
        loginPage.doesUserNameScreenContains(pageTitle, caption, emailBoxInnerTxt, havingProblemsLinkTxt, nextBtnTxt, signUpLinkTxt);
    }

    @When("^I enter a \"([^\"]*)\" & click next$")
    public void i_enter_a_click_next(String emailID) throws Throwable
    {
        loginPage.enterEmail(PropertyReader.testDataOf(emailID));
        loginPage.clickNext();
    }

    @Then("^I should see back arrow, page title as \"([^\"]*)\", a caption \"([^\"]*)\", a text box with inner text \"([^\"]*)\", a link \"([^\"]*)\", a \"([^\"]*)\" button & a password visibility toggle button$")
    public void i_should_see_back_arrow_page_title_as_a_caption_a_text_box_with_inner_text_a_link_a_button_a_password_visibility_toggle_button(String pageTitle, String caption, String passwordInnerTxt, String havingProblemsLinkTxt, String loginBtnTxt) throws Throwable
    {
        loginPage.doesPasswordScreenContains(pageTitle, caption, passwordInnerTxt, havingProblemsLinkTxt, loginBtnTxt);
    }
}