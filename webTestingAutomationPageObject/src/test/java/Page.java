import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected WebDriverWait wait;
    protected String url;

    public Page(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.driver.set(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        this.driver.get().get(url);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clear(WebElement element) {
        element.clear();
    }

    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void submit(WebElement element) {
        element.submit();
    }

}
