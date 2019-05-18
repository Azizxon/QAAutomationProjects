import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


final class BrowserFactory {

    static WebDriver getDriver(String browserName) {
        {
            switch (browserName) {
                case "chrome":
                    ChromeOptions chromeOtions = new ChromeOptions();
                    chromeOtions.addArguments("--disable-notifications");
                    return new ChromeDriver(chromeOtions);
                default:
                   FirefoxOptions ffOptions=new FirefoxOptions();
                   ffOptions.addPreference("dom.webnotifications.enabled", false);
                   return new FirefoxDriver(ffOptions);
            }
        }
    }
}