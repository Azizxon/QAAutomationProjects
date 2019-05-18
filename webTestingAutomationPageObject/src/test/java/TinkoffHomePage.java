import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TinkoffHomePage extends Page {

    public TinkoffHomePage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
        this.url="https://www.tinkoff.ru/";
    }

    @FindBy(css = "a[href='/payments/'][data-qa-file='Link'] > span")
    public WebElement payments;
}
