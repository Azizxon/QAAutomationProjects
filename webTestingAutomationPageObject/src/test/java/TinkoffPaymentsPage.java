import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TinkoffPaymentsPage extends Page {

    public TinkoffPaymentsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.url="https://www.tinkoff.ru/payments/";
    }

    @FindBy(css = "[placeholder='Название или ИНН получателя платежа'][type='text']")
    public WebElement searchInput;

    @FindBy(css = "div#search-and-pay-container div[data-qa-node='Tag']")
    public WebElement searchContainer;

    public void search(String text) {
        click(searchInput);
        clear(searchInput);
        sendKeys(searchInput,text);
        submit(searchInput);
    }
}
