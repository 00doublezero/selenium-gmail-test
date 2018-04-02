package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helpers.Browser;
import helpers.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import pages.MailboxPage;

public class GMailSteps {
    private WebDriver driver;

    private LoginPage loginpage;
    private MailboxPage mailbox ;

    @Before
    public void initBrowser() {
        System.setProperty("webdriver.gecko.driver","/home/doublezero/.seleniumDrivers/geckodriver");
        driver = DriverProvider.getDriver(Browser.FIREFOX, 15);
    }

    @Given("^user navigates to Gmail login page$")
    public void navigateTo() {
        LoginPage.openPage(driver);
        loginpage = new LoginPage(driver);
    }

    @And("^he enter text '(.+)' in field '(login|password)'$")
    public void fillInput(String text, String fieldName) {
        if(fieldName.equals("login")) {
            loginpage.fillEmail(text);
        }
        else {
            loginpage.fillPassword(text);
        }

    }

    @And("^he press login Next button$")
    public void pressLoginNextButton() {
        loginpage.pressEmailOrPhoneNextButton();
    }

    @And("^he press password Next button$")
    public void pressPasswordNextButton() {
        loginpage.pressPasswordNextButton();
    }

    @Then("^he can see '(.+)' in title of page$")
    public void checkMailbox(String email) {
       mailbox = new MailboxPage(driver);
       mailbox.IsMailbox(email);
    }

    @After
    public void closeBrowser(){
        DriverProvider.quitDriver(Browser.FIREFOX);
    }
}
