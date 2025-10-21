package stepdefinations;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;

public class LoginStep {

    WebDriver driver;
    private LoginPage loginPage;
    private AccountPage accountPage;
    @Given("User has navigate to login page")
    public void user_has_navigate_to_login_page() {
        driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        loginPage = homePage.selectLoginOption();

    }
    @When("User enters valid email address {string} into email field")
    public void user_enters_valid_email_address_into_email_field(String email) {
        loginPage.enterUserEmail(email);

    }
    @And("User has entered valid password {string} into password field")
    public void user_has_entered_valid_password_into_password_field(String password) {
        loginPage.enterUserPassword(password);
    }
    @And("^User enters invalid email address (.*) into email field$")
    public void user_enters_invalid_email_address_into_email_field(String email) {
        loginPage.enterUserEmail(email);
    }
    @And("^User has entered invalid password (.*) into password field$")
    public void user_has_entered_invalid_password_into_password_field(String password) {
        loginPage.enterUserPassword(password);
    }
    @And("User clicks on login button")
    public void user_clicks_on_login_button() {
        accountPage = loginPage.clickLoginButton();

    }
    @Then("User should get successfully logged in")
    public void user_should_get_successfully_logged_in() {
        Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationOption());
    }
    @Then("User should get Warning message")
    public void User_should_get_Warning_message(){
        Assert.assertTrue(loginPage.errorMessageIsDisplayed());
    }
}
