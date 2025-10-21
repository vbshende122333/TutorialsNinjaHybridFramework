package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By userEmail = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@type=\"submit\"]");
    private By invalidLoginError = By.xpath("//div[text()=\"Warning: No match for E-Mail Address and/or Password.\"]");
    public void enterUserEmail(String email){
        driver.findElement(userEmail).sendKeys(email);
    }

    public void enterUserPassword(String userPassword){
        driver.findElement(password).sendKeys(userPassword);
    }

    public AccountPage clickLoginButton(){
        driver.findElement(loginBtn).click();
        return new AccountPage(driver);
    }

    public boolean errorMessageIsDisplayed(){
        return driver.findElement(invalidLoginError).isDisplayed();
    }
}
