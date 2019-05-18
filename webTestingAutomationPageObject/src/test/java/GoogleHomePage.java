import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePage extends Page {

    public GoogleHomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.url = "https://www.google.ru";
    }

    @FindBy(id = "lst-ib")
    public WebElement searchInput;
    @FindBy(id = "resultStats")
    public WebElement resultStats;

    public void search(String text) {
        click(searchInput);
        clear(searchInput);
        sendKeys(searchInput, text);
        submit(searchInput);
    }
}
