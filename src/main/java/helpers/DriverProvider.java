package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverProvider {

    private static Map<Browser, WebDriver> driverMap = new HashMap<>();

    private DriverProvider() {}

    public static WebDriver getDriver(Browser browser, int implWait) {
        WebDriver driver = driverMap.get(browser);
        if (driver == null ){
            switch (browser) {
                case IE:
                    driver = new InternetExplorerDriver();
                    break;
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
            }
            driverMap.put(browser, driver);
        }
        driver.manage().timeouts().implicitlyWait(implWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver(Browser browser) {
        driverMap.get(browser).quit();
        driverMap.put(browser,null);
    }
}
