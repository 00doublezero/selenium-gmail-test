package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleMainPage extends  AbstractPage {

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoggedOut(String email) {
        return driver.findElements(By.cssSelector("[title*='"+email+"']")).size() == 0;
    }
}
