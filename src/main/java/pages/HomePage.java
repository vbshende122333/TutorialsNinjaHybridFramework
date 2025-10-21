package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@title=\"My Account\"]")
    private WebElement myAccountDropDown;

    @FindBy(xpath = "//a[text()=\"Login\"]")
    private WebElement loginOption;

    @FindBy(xpath = "//a[text()=\"Register\"]")
    private WebElement registerOption;

    public void clickOnMyAccount(){
        myAccountDropDown.click();
    }
    public LoginPage selectLoginOption(){
        loginOption.click();
        return new LoginPage(driver);
    }
    public void selectRegisterOption(){
        registerOption.click();
    }
}
