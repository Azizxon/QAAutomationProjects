import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomePageTest extends BaseRunner {

    private GoogleHomePage googleHomePage;

    @Before
    public void setUp() {
        googleHomePage = new GoogleHomePage(driver.get(), wait);
    }

    @Test
    public void search() {

        googleHomePage.navigate();
        googleHomePage.search("Тинькофф");
        waitForVisibility();
    }

    public void waitForVisibility() throws Error {
        WebElement resultStats =googleHomePage.resultStats;
                wait.until(ExpectedConditions.visibilityOf(resultStats));
    }
}
