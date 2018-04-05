package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final String LOGIN_URL = "https://www.google.com/gmail/";

    @FindBy(id="identifierId")
    private WebElement EmailOrPhoneField;

    @FindBy(id="identifierNext")
    private WebElement EmailOrPhoneFieldNextButton;

    @FindBy(css="input[name=\"password\"]")
    private WebElement passwordField;

    @FindBy(css="#passwordNext")
    private WebElement passwordFieldNext;

    public static LoginPage openPage(WebDriver driver) {
        driver.get(LOGIN_URL);
        return new LoginPage(driver);
    }

    public void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(EmailOrPhoneField));
        //log.info("Set 'Email or phone' field with value: {}",email);
        EmailOrPhoneField.sendKeys(email);
    }

    public void pressEmailOrPhoneNextButton(){
        EmailOrPhoneFieldNextButton.click();
    }

    public void pressPasswordNextButton() {
        passwordFieldNext.click();
    }

    public void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }
}
