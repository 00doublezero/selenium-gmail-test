package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    private static final String LOGIN_URL = "https://www.google.com/gmail/";

    @FindBy(id="identifierId")
    private WebElement EmailOrPhoneField;

    @FindBy(id="identifierNext")
    private WebElement EmailOrPhoneFieldNextButton;

    public static LoginPage openPage(WebDriver driver) {
        driver.get(LOGIN_URL);
        //log.info();
        return new LoginPage(driver);
    }

    public void fillEmail(String email) {

        wait.until(ExpectedConditions.visibilityOf(EmailOrPhoneField));
        //log.info("Set 'Email or phone' field with value: {}",email);
        EmailOrPhoneField.sendKeys(email);
    }

    public WebElement getEmailOrPhoneField() {
        return EmailOrPhoneField;
    }

    public void pressEmailOrPhoneNextButton(){

        EmailOrPhoneFieldNextButton.click();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="input[name=\"password\"]")
    private WebElement passwordField;
    @FindBy(css="#passwordNext")
    private WebElement passwordFieldNext;


    public void pressPasswordNextButton() {
        passwordFieldNext.click();
    }

    public void fillPassword(String password) {

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }
}
