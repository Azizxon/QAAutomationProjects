import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentMobileTest extends BaseRunner {
    private GoogleHomePage googleHomePage;
    private GoogleSearchPage googleSearchPage;
    private TinkoffHomePage tinkoffHomePage;
    private TinkoffPaymentsPage tinkoffPaymentsPage;
    private TinkoffProviderMobilePage tinkoffProviderMobilePage;

    @Before
    public void init() {
        googleHomePage = new GoogleHomePage(driver.get(), wait);
        googleSearchPage = new GoogleSearchPage(driver.get(), wait, "Тинькофф");
        tinkoffHomePage = new TinkoffHomePage(driver.get(), wait);
        tinkoffPaymentsPage = new TinkoffPaymentsPage(driver.get(), wait);
        tinkoffProviderMobilePage = new TinkoffProviderMobilePage(driver.get(), wait);
    }

    @Test
    public void paymentMobileTest() {
        googleHomePage.navigate();
        googleHomePage.search("tinkoff");
        wait.until(ExpectedConditions.visibilityOf(googleHomePage.resultStats));

        googleSearchPage.setLink("https://www.tinkoff.ru/");
        googleSearchPage.click(googleSearchPage.linkElement);

        switchWindow("Лучший интернет-банк");

        tinkoffHomePage.click(tinkoffHomePage.payments);

        tinkoffPaymentsPage.search("Тинькофф Мобайл");
        wait.until(ExpectedConditions.visibilityOf(tinkoffPaymentsPage.searchContainer));
        tinkoffPaymentsPage.click(tinkoffPaymentsPage.searchContainer);

        wait.until(element -> element.getTitle().equals("Оплатить мобильную связь"));

        tinkoffProviderMobilePage.pay("968", "5");
        wait.until(ExpectedConditions.visibilityOfAllElements(tinkoffProviderMobilePage.errorElements));
        Assert.assertEquals(tinkoffProviderMobilePage.errorPhone.getText(), "Поле неправильно заполнено");
        Assert.assertEquals(tinkoffProviderMobilePage.errorSum.getText(), "Минимум — 10 ₽");

        validateTest();
    }
    public void validateTest() {
        tinkoffProviderMobilePage.pay("11", "11");
        wait.until(ExpectedConditions.visibilityOfAllElements(tinkoffProviderMobilePage.errorElements));
        Assert.assertEquals(tinkoffProviderMobilePage.validatePhone(), false);
        Assert.assertEquals(tinkoffProviderMobilePage.validateSum(), true);

        tinkoffProviderMobilePage.pay("9685364383", "");
        wait.until(ExpectedConditions.visibilityOfAllElements(tinkoffProviderMobilePage.errorElements));
        Assert.assertEquals(tinkoffProviderMobilePage.validatePhone(), false);
        Assert.assertEquals(tinkoffProviderMobilePage.validateSum(), false);

        tinkoffProviderMobilePage.pay("", "");
        wait.until(ExpectedConditions.visibilityOfAllElements(tinkoffProviderMobilePage.errorElements));
        Assert.assertEquals(tinkoffProviderMobilePage.validatePhone(), false);
        Assert.assertEquals(tinkoffProviderMobilePage.validateSum(), false);

        tinkoffProviderMobilePage.pay("9685364383", "11");
        wait.until(ExpectedConditions.visibilityOfAllElements(tinkoffProviderMobilePage.errorElements));
        Assert.assertEquals(tinkoffProviderMobilePage.validatePhone(), true);
        Assert.assertEquals(tinkoffProviderMobilePage.validateSum(), true);


    }

}
