import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage extends Page {

    public GoogleSearchPage(WebDriver driver, WebDriverWait wait, String question)
    {
        super(driver,wait);
        this.url="https://www.google.ru/search?q="+question;
    }

    public WebElement linkElement;

    public void setLink(String link)
    {
       this.linkElement=driver.get().findElements(By.cssSelector("[href='"+link+"']")).get(0);
    }
}
