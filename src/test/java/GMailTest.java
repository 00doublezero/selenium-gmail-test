import helpers.Browser;
import helpers.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.util.UUID;


public class GMailTest {

    private WebDriver driver;
    private Logger log = LoggerFactory.getLogger(GMailTest.class);

    private LoginPage loginpage;
    private MailboxMainPage mailbox;
    private EmailsList emailsList;
    private GoogleMainPage gmanpage;
    private DraftWindow draftwin;

    private String email = "vj1hao5g@gmail.com";
    private String password = "WrerEDq9";

    private String recipient = "ftsiganok@gmail.com";
    private String subject = UUID.randomUUID().toString();
    private String mailBody = UUID.randomUUID().toString();

    @BeforeTest
    public void initBrowser() {
        System.setProperty("webdriver.gecko.driver","/home/doublezero/.seleniumDrivers/geckodriver");
        driver = DriverProvider.getDriver(Browser.FIREFOX, 15);
        //log.info("Начало теста!");
    }

    @Test(priority=1)
    public void login(){
        //временно использовать driver.get()
        //LoginPage.openPage(driver);
        driver.get("https://www.google.com/gmail/");
        loginpage = new LoginPage(driver);

        loginpage.fillEmail(email);
        loginpage.pressEmailOrPhoneNextButton();
        loginpage.fillPassword(password);
        loginpage.pressPasswordNextButton();

        mailbox = new MailboxMainPage(driver);
        Assert.assertTrue(mailbox.IsMailbox(email),"Title not found. The page did not load.");

    }

    @Test(priority = 2)
    public void createNewDraft(){
        int draftsCountBeforeEditing;

        draftsCountBeforeEditing = mailbox.draftsCount();
        mailbox.openDraft();

        draftwin = new DraftWindow(driver);

        draftwin.fillInput(draftwin.getRecipientInput(),recipient);
        draftwin.fillInput(draftwin.getSubjectboxInput(),subject);
        draftwin.fillInput(draftwin.getEmailBodyInput(),mailBody);

        mailbox.waitUntilDraftSaved(draftsCountBeforeEditing);
        draftwin.setDraftFormCloseButtonClick();

        driver.get("https://mail.google.com/mail/#drafts");

        emailsList = new EmailsList(driver);

        Assert.assertEquals(emailsList.currentDraft(subject).getText(),subject,"There is no such draft.");

    }

    @Test(priority = 3)
    public void validateDraft() {
        emailsList.currentDraft(subject).click();
        Assert.assertTrue(draftwin.validateForm(recipient,subject,mailBody),"Draft form is not valid.");
    }

    @Test(priority = 4)
    public void sendEmail() {
        draftwin.sendEmail();
        mailbox.waitUntilMailWasSend();
        driver.get("https://mail.google.com/mail/#sent");

        Assert.assertEquals(emailsList.currentDraft(subject).getText(),subject,"The Email was not sent");
    }
    @Test(priority = 5)
    public void logout() {
        gmanpage = new GoogleMainPage(driver);
        mailbox.titleButtonClick();
        mailbox.logoutButtonClick();
        //SentMailPage.openMainPage(driver);
        driver.get("https://google.com");
        Assert.assertTrue(gmanpage.isLoggedOut(email),"User is not logged out.");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
