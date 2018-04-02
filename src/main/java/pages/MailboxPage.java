package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailboxPage extends AbstractPage {

    private static final String LOGOUT_URL = "https://mail.google.com/mail/#drafts";

    @FindBy(css="a[title$=\"\"")
    private WebElement title;

    @FindBy(xpath = "//div[@jscontroller]//div[@role='button']")
    private WebElement draftButton;

    @FindBy(css="[name=\"subjectbox\"]")
    private WebElement subjectFields;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/#drafts']")
    private WebElement draftsListElement;

    @FindBy(css="textarea[name=\"to\"]")
    private WebElement recipientInput;

    @FindBy(xpath="//input[@name='subjectbox']")
    private WebElement subjectboxInput;

    @FindBy(css="[role=\"dialog\"] [role=\"textbox\"]")
    private WebElement emailBodyInput;

    @FindBy(css="[role=\"dialog\"] [src=\"images/cleardot.gif\"][id]:nth-child(3)")
    private WebElement draftFormCloseButton;

    public WebElement getRecipientInput() {
        return this.recipientInput;
    }

    public WebElement getSubjectboxInput() {
        return this.subjectboxInput;
    }

    public WebElement getEmailBodyInput() {
        return this.emailBodyInput;
    }

    public MailboxPage(WebDriver driver) {
        super(driver);
    }
    public void openDraft() {
        draftButton.click();
    }

    public boolean draftIsOpen() {
        return subjectFields.isDisplayed();
    }

    public int draftsCount() {
        int i  =  Integer.parseInt(draftsListElement.getText().replaceAll("[\\D]", ""));
        //log.info(String.valueOf(i));
        return i;
    }

    public void setDraftFormCloseButtonClick() {
        draftFormCloseButton.click();
    }

    public void waitUntilDraftSaved(int draftsCountBeforeEditing) {
        String substring = String.valueOf(draftsCountBeforeEditing + 1);
        wait.until(ExpectedConditions.textToBePresentInElement(draftsListElement,substring ));
    }

    public boolean IsMailbox(String mail){
        WebElement title = this.driver.findElement(By.cssSelector(
                "a[title$=\""+ mail + ")\"][role=\"button\"][href^=\"https\"]"));
        return title.isDisplayed();
    }



}
