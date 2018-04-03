import helpers.Browser;
import helpers.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DraftsPage;
import pages.LoginPage;
import pages.MailboxPage;
import pages.SentMailPage;

import java.util.UUID;


public class GMailTest {

    private WebDriver driver;
    private Logger log = LoggerFactory.getLogger(GMailTest.class);

    private LoginPage loginpage;
    private MailboxPage mailbox;
    private DraftsPage draftPage;
    private SentMailPage sentpage;

    private String email = "***";
    private String password = "***";

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
        LoginPage.openPage(driver);
        loginpage = new LoginPage(driver);



        loginpage.fillEmail(email);
        loginpage.pressEmailOrPhoneNextButton();
        loginpage.fillPassword(password);
        loginpage.pressPasswordNextButton();

        mailbox = new MailboxPage(driver);
        Assert.assertTrue(mailbox.IsMailbox(email),"Title not found. The page did not load.");

    }

    @Test(priority = 2)
    public void createNewDraft(){
        int draftsCountBeforeEditing;

        draftsCountBeforeEditing = mailbox.draftsCount();
        mailbox.openDraft();

        mailbox.fillInput(mailbox.getRecipientInput(),recipient);
        mailbox.fillInput(mailbox.getSubjectboxInput(),subject);
        mailbox.fillInput(mailbox.getEmailBodyInput(),mailBody);

        mailbox.waitUntilDraftSaved(draftsCountBeforeEditing);
        mailbox.setDraftFormCloseButtonClick();

        DraftsPage.openPage(driver);

        draftPage = new DraftsPage(driver);

        Assert.assertEquals(draftPage.currentDraft(subject).getText(),subject,"There is no such draft.");

    }

    @Test(priority = 3)
    public void validateDraft() {
        boolean result = false;
        draftPage.currentDraft(subject).click();
        Assert.assertTrue(draftPage.validateForm(recipient,subject,mailBody),"Draft form is not valid.");
    }

    @Test(priority = 4)
    public void sendEmail() {
        draftPage.sendEmail();
        SentMailPage.openPage(driver);

        Assert.assertEquals(draftPage.currentDraft(subject).getText(),subject,"The Email was not sent");
        DraftsPage.openPage(driver);
        Assert.assertFalse(draftPage.waitUntilElementDeleteFromDraft(subject),"The Email was not sent");
    }
    @Test(priority = 5)
    public void logout() {
        sentpage = new SentMailPage(driver);
        sentpage.titleButtonClick();
        sentpage.logoutButtonClick();
        SentMailPage.openMainPage(driver);
        Assert.assertTrue(sentpage.isLoggedOut(email),"User is not logged out.");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
