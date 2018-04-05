package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AbstractGmailInboxPage extends AbstractPage {

    public AbstractGmailInboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@jscontroller]//div[@role='button']")
    private WebElement draftButton;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/#drafts']")
    private WebElement draftsListElement;

    @FindBy(css="#link_vsm")
    private WebElement messageWasSentLable;

    @FindBy(css="[href^=\"https://accounts.google.com/SignOutOptions?\"]")
    private WebElement titleButton;

    @FindBy(css = "[href^='https://accounts.google.com/Logout?']")
    private WebElement logoutButton;

    public void openDraft() {
        draftButton.click();
    }

    public int draftsCount() {
        int result;
        String draftsCountString = draftsListElement.getText().replaceAll("[\\D]", "");

        if (draftsCountString.equals("") ) {
            result = 0;
        } else {
            result  =  Integer.parseInt(draftsCountString);
        }

        return result;
    }

    public void waitUntilDraftSaved(int draftsCountBeforeEditing) {
        String substring = String.valueOf(draftsCountBeforeEditing + 1);
        wait.until(ExpectedConditions.textToBePresentInElement(draftsListElement,substring ));
    }

    public void waitUntilMailWasSend(){
        wait.until(ExpectedConditions.visibilityOf(messageWasSentLable));
    }

    public void titleButtonClick() {
        titleButton.click();
    }

    public void logoutButtonClick() {
        logoutButton.click();
    }

    public boolean IsMailbox(String mail){
        WebElement title = this.driver.findElement(By.cssSelector(
                "a[title$=\""+ mail + ")\"][role=\"button\"][href^=\"https\"]"));
        return title.isDisplayed();
    }
}
