package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftWindow extends AbstractGmailInboxPage {

    public DraftWindow(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="colgroup+tbody div[role=\"button\"]")
    private WebElement sendEmailButton;

    @FindBy(css="[name=\"subjectbox\"]")
    private WebElement subjectFields;

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


    public String getRecipientText() {
        return this.driver.findElement(By.cssSelector("[role=\"dialog\"] [email]")).getAttribute("email");
    }

    public String getSubjectText() {
        return this.driver.findElement(By.cssSelector("[name=\"subject\"]")).getAttribute("value");
    }

    public String getBodyText() {
        return this.driver.findElement(By.xpath("//*[@role='dialog']//*[@role='textbox']")).getText();
    }


    public boolean draftIsOpen() {
        return subjectFields.isDisplayed();
    }

    public void setDraftFormCloseButtonClick() {
        draftFormCloseButton.click();
    }

    public boolean validateForm(String recipient, String subject, String bodyText){
        boolean result = false;
        if (this.getRecipientText().equals(recipient)) {
            result = true;
        } else if (this.getSubjectboxInput().equals(subject)) {
            result = true;
        } else if (this.getBodyText().equals(bodyText)){
            result = true;
        }
        return result;
    }

    public void sendEmail() {
        sendEmailButton.click();
        //wait.until(ExpectedConditions.visibilityOf(messageWasSentLable));
    }
}
