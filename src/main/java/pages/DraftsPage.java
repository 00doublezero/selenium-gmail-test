package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DraftsPage extends MailboxPage {

    @FindBy(css="colgroup+tbody div[role=\"button\"]")
    private WebElement sendEmailButton;

    @FindBy(xpath="//*[@href='https://mail.google.com/mail/#sent']/../../../..")
    private WebElement sentMailListElement;

    @FindBy(css="#link_vsm")
    private WebElement messageWasSentLable;

    private static final String DRAFTS_URL = "https://mail.google.com/mail/#drafts";

    public static SentMailPage openPage(WebDriver driver) {
        driver.get(DRAFTS_URL);
        return new SentMailPage(driver);
    }

    public WebElement currentDraft(String subject){
        return this.driver.findElement(By.xpath("//span[text()='"+subject+"']"));
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

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public void sendEmail() {
        sendEmailButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageWasSentLable));
    }
    public void sentMailClick() {
        wait.until(ExpectedConditions.elementToBeClickable(this.sentMailListElement));
        this.sentMailListElement.click();

    }

    public boolean waitUntilElementDeleteFromDraft(String subject) {


        return this.driver.findElements(By.xpath("//span[text()='"+subject+"']")).size() >0;
    }
}
