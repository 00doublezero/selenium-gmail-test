package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;

    public AbstractPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 7);
        this.log = LoggerFactory.getLogger(AbstractPage.class);
        PageFactory.initElements(driver,this);
    }

    public void fillInput(WebElement input, String text) {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(text);
    }

}
