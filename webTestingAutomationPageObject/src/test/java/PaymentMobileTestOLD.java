/*
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentMobileTest extends BaseRunner {

    private void switchTab(String parentHandle) {
        for (String childHandle : driver.get().getWindowHandles()) {
            if (!childHandle.equals(parentHandle)) {
                driver.get().switchTo().window(childHandle);
            }
        }
    }

    @Test
    public void paymentMobileFillSumField_WarningsShouldBeEqualByCssSelector() {
        driver.get().get(baseUrl);

        driver.get().findElement(By.cssSelector("#lst-ib")).click();
        driver.get().findElement(By.cssSelector("#lst-ib")).clear();
        driver.get().findElement(By.cssSelector("#lst-ib")).sendKeys("tinkoff");
        driver.get().findElement(By.cssSelector("#lst-ib")).submit();

        // wait until the google page shows the result
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        driver.get().findElement(By.cssSelector("[href='" + tinkoffUrl + "']")).click();
        String parentHandle = driver.get().getWindowHandle();
        switchTab(parentHandle);

        driver.get().findElement(By.cssSelector("a[href='/payments/'][data-qa-file='Link'] > span")).click();
        driver.get().findElement(By.cssSelector("[placeholder='Название или ИНН получателя платежа'][type='text']")).click();
        driver.get().findElement(By.cssSelector("[placeholder='Название или ИНН получателя платежа'][type='text']")).clear();
        driver.get().findElement(By.cssSelector("[placeholder='Название или ИНН получателя платежа'][type='text']")).sendKeys("Тинькофф Мобайл");
        driver.get().findElement(By.cssSelector("[placeholder='Название или ИНН получателя платежа'][type='text']")).submit();
        driver.get().findElement(By.cssSelector("div#search-and-pay-container div[data-qa-node='Tag']")).click();

        // wait until the tinkoff page shows the result
        wait.until(element -> element.getTitle().equals("Оплатить мобильную связь"));


        driver.get().findElement(By.cssSelector("input[data-qa-file='StatelessInput']")).click();
        driver.get().findElement(By.cssSelector("input[data-qa-file='StatelessInput']")).clear();
        driver.get().findElement(By.cssSelector("input[data-qa-file='StatelessInput']")).sendKeys("5");
        driver.get().findElement(By.cssSelector("input[data-qa-file='StatelessInput']")).submit();

        wait.until(d -> d.findElements(By.cssSelector("[class*='ui-form-field-error-message'][data-qa-file='UIFormRowError']")).size() > 1);

        List<WebElement> errorElements = driver.get().findElements(By.cssSelector("[class*='ui-form-field-error-message'][data-qa-file='UIFormRowError'"));
        assertEquals(errorElements.get(0).getText(), "Поле обязательное");
        assertEquals(errorElements.get(1).getText(), "Минимум — 10 ₽");
    }

    @Test
    public void paymentMobileFillSumField_WarningsShouldBeEqualByXpath() {
        driver.get().get(baseUrl);

        driver.get().findElement(By.xpath("//*[@id='lst-ib']")).click();
        driver.get().findElement(By.xpath("//*[@id='lst-ib']")).clear();
        driver.get().findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("tinkoff");
        driver.get().findElement(By.xpath("//*[@id='lst-ib']")).submit();

        // wait until the google page shows the result
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        driver.get().findElement(By.xpath("//*[@href='" + tinkoffUrl + "']")).click();
        String parentHandle = driver.get().getWindowHandle();
        switchTab(parentHandle);

        driver.get().findElement(By.xpath("//a[@href='/payments/'][@data-qa-file='Link']//span")).click();

        driver.get().findElement(By.xpath("//*[@placeholder='Название или ИНН получателя платежа'][@type='text']")).click();
        driver.get().findElement(By.xpath("//*[@placeholder='Название или ИНН получателя платежа'][@type='text']")).clear();
        driver.get().findElement(By.xpath("//*[@placeholder='Название или ИНН получателя платежа'][@type='text']")).sendKeys("Тинькофф Мобайл");
        driver.get().findElement(By.xpath("//*[@placeholder='Название или ИНН получателя платежа'][@type='text']")).submit();
        driver.get().findElement(By.xpath("//*[@data-qa-file='SuggestEntry']//*[contains(text(), 'Тинькофф Мобайл')]")).click();

        // wait until the tinkoff page shows the result
        wait.until(element -> element.getTitle().equals("Оплатить мобильную связь"));

        driver.get().findElement(By.xpath("//input[@data-qa-file='StatelessInput']")).click();
        driver.get().findElement(By.xpath("//input[@data-qa-file='StatelessInput']")).clear();
        driver.get().findElement(By.xpath("//input[@data-qa-file='StatelessInput']")).sendKeys("5");
        driver.get().findElement(By.xpath("//input[@data-qa-file='StatelessInput']")).submit();

        wait.until(d -> d.findElements(By.xpath("//*[@data-qa-file='UIFormRowError']")).size() > 1);

        List<WebElement> errorElements = driver.get().findElements(By.xpath("//*[@data-qa-file='UIFormRowError']"));
        assertEquals(errorElements.get(0).getText(), "Поле обязательное");
        assertEquals(errorElements.get(1).getText(), "Минимум — 10 ₽");
    }

    @Test
    public void extraTask() {
        driver.get().get(tinkoffUrl);
        driver.get().findElement(By.xpath("//span[@data-qa-file='FirstMenu']//*[contains(text(),'Мобайл')]")).click();
        driver.get().findElement(By.xpath("//*[@href='/mobile-operator/tariffs/' and @data-qa-file='Link']")).click();

        WebElement header = driver.get().findElement(By.xpath("//div[contains(text(),'Тарифы Тинькофф Мобайл')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver.get();

        js.executeScript("arguments[0].scrollIntoView(true);", header);

        String priceBefore =getPrice();

        driver.get().findElement(By.xpath("//div[contains(text(),'Звонки')]/../..//li[1]")).click();
        driver.get().findElement(By.xpath("//div[contains(text(),'Интернет')]/../..//li[1]")).click();

        driver.get().findElements(By.xpath("//*[contains(@class,'toggle_checked_true')]"))
                .stream()
                .forEach(webElement -> webElement.click());

        String priceAfter = getPrice();


        boolean isDisabled=driver.get().findElements(By.xpath("//button[@disabled]/span[contains(text(),'Получить SIM-карту')]"))
                                        .size()>0?true:false;

        wait.until(d->isDisabled);

        js.executeScript("arguments[0].scrollIntoView(true);", header);
        js.executeScript("arguments[0].scrollIntoView(false);", header);

        driver.get().navigate().refresh();

        String priceAfterRefresh = getPrice();

        assertEquals(priceBefore,priceAfterRefresh);
        assertEquals(isDisabled,true);
    }
    private String getPrice()
    {
       return (driver.get().findElement(
                By.xpath("//span[contains(text(),'Итого в месяц')]//span[@data-qa-file='TMobileStickButton']"))
                .getText().split(" ")[0]);
    }
}


*/