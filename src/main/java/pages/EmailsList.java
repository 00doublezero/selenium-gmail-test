package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailsList extends AbstractGmailInboxPage {

    public EmailsList(WebDriver driver) {
        super(driver);
    }

    public WebElement currentDraft(String subject){
        return this.driver.findElement(By.xpath("//span[text()='"+subject+"']"));
    }
}
