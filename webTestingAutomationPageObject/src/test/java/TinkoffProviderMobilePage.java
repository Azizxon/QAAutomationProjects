import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TinkoffProviderMobilePage extends Page {

    public TinkoffProviderMobilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.url="https://www.tinkoff.ru/payments/provider-tinkoff-mobile/";
    }

    @FindBy(css = "input[data-qa-file='StatelessInput']")
    public WebElement sumInput;

    @FindBy(css = "input[data-qa-file='UIInput'][id='phone']")
    public WebElement phoneInput;

    @FindBy(xpath = "//input[@id='']/../../../..//div[@data-qa-file='UIFormRowError']")
    public WebElement errorSum;

    @FindBy(xpath = "//input[@name='provider-phone']/../../../..//div[@data-qa-file='UIFormRowError']")
    public WebElement errorPhone;

    @FindAll(@FindBy(css ="[class*='ui-form-field-error-message'][data-qa-file='UIFormRowError'"))
    public List<WebElement> errorElements;

    @FindBy(xpath = "//button[contains(@class, 'pay')]")
    public WebElement payButton;

    public void pay(String phone,String amount)
    {
        sumInput.click();
        sumInput.clear();
        sumInput.sendKeys(amount);

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        click(payButton);

    }

    public boolean validatePhone() {
        String phoneNumber=phoneInput.getAttribute("value");
        String regex = "^((\\+7)(\\s|\\.*?)(\\()([0-9]){3}(\\))(\\s|\\.*?)([0-9]){3}(\\-)([0-9]){2})(\\-)([0-9]){2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()==false&&(errorPhone.getText().equals("Поле неправильно заполнено")||
                (errorPhone.getText().equals("Поле обязательное")
        )))
        {
            return false;
        }

        if(matcher.matches())return true;

        return false;
    }
    public boolean validateSum()
    {
        String sumString=sumInput.getAttribute("value");

        String regex="^[0-9]{1,255}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(sumString);

        String sumErrorText=errorSum.getText();
        if(sumErrorText.isEmpty())return true;

        if (matcher.matches()==false && sumErrorText.equals("Поле обязательное"))
        {
            return false;
        }


        if (sumString.equals("0,00") && sumErrorText.equals("Поле заполнено неверно"))
        {
            return false;
        }

        BigInteger sum=new BigInteger(sumString);

        BigInteger ten=new BigInteger("10");
        boolean t1=sum.compareTo(ten)==-1;
        boolean t2=sumErrorText.equals("Минимум — 10 ₽");
        if (t1&&t2 )
        {
            return false;
        }

        BigInteger fifteenThousand=new BigInteger("15000");
        if (sum.compareTo(fifteenThousand)==1&& sumErrorText.equals("Максимум — 15 000 ₽"))
        {
            return false;
        }
        return  true;
    }
}
