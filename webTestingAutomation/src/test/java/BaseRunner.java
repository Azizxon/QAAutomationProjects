import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseRunner {

    final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    WebDriverWait wait;
    final String baseUrl = "https://www.google.ru/";
    final String tinkoffUrl = "https://www.tinkoff.ru/";
    private final String browserName =System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");

    @Before
    public void setUp() {
        driver.set(BrowserFactory.getDriver(browserName));
        wait=new WebDriverWait(driver.get(),60);
        driver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get().manage().window().maximize();    }

    @After
    public void tearDown() {
        driver.get().quit();
    }
}