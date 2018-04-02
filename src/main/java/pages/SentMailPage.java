package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentMailPage extends  DraftsPage{

    @FindBy(css="[href^=\"https://accounts.google.com/SignOutOptions?\"]")
    private WebElement titleButton;
    @FindBy(css = "[href^='https://accounts.google.com/Logout?']")
    private WebElement logoutButton;


    private static final String SENT_URL = "https://mail.google.com/mail/u/0/#sent";
    private static final String GOOGLE_URL = "https://google.com/";

    public SentMailPage(WebDriver driver) {
        super(driver);
    }

    public void titleButtonClick() {
        titleButton.click();
    }
    public void logoutButtonClick() {
        logoutButton.click();
    }

    public static SentMailPage openPage(WebDriver driver) {
        driver.get(SENT_URL);
        return new SentMailPage(driver);
    }

    public static SentMailPage openMainPage(WebDriver driver) {
        driver.get(GOOGLE_URL);

        return new SentMailPage(driver);
    }

    public boolean isLoggedOut(String email) {
        return driver.findElement(By.cssSelector("[title*='"+email+"']")).isDisplayed();
    }

}
