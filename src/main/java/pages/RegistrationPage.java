package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//a[@id='createAccountSubmit']")
    WebElement newAccountButton;

    @FindBy(xpath="//input[@name='customerName']")
    WebElement inputCustomerName;

    @FindBy(xpath="//input[@name='email']")
    WebElement inputEmail;

    @FindBy(xpath="//input[@name='password']")
    WebElement inputPassword;

    @FindBy(xpath="//input[@name='passwordCheck']")
    WebElement inputPasswordCheck;

    @FindBy(xpath="//input[@id='continue']")
    WebElement confirmButton;

    @FindBy(xpath="//span[@id='auth-continue-announce']/span")
    List<WebElement> indicatorRegistration;

    @FindBy(xpath="//form[@action='verify']")
    WebElement verifyWindow;

    @FindBy(xpath="//button[@aria-describedby='descriptionVerify'][@id='home_children_button']")
    WebElement solvePuzzleButton;

    public void clickNewAccountButton(){
        newAccountButton.click();
        waitForPageLoadComplete(10);
    }

    public boolean isRegistryFormOpen(){
        if (driver.getCurrentUrl().contains("register")) return true;
        else return false;
    }
    public boolean isInputCustomerNameVisible(){
        if (inputCustomerName.isDisplayed()) return true;
        else return false;
    }

    public boolean isInputEmailVisible(){
        if (inputEmail.isDisplayed()) return true;
        else return false;
    }

    public boolean isInputPasswordVisible(){
        if (inputPassword.isDisplayed()) return true;
        else return false;
    }

    public boolean isInputCheckPasswordVisible(){
        if(inputPasswordCheck.isDisplayed()) return true;
        else return false;
    }



    public boolean isDataCorrect(){
        int Errors=0;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (inputCustomerName.getAttribute("value").length()==0) Errors++;
        if (inputPassword.getAttribute("value").length()<6) Errors++;
        if ((!inputEmail.getAttribute("value").matches(EMAIL_PATTERN))||(inputEmail.getAttribute("value").length()==0)) Errors++;
        if (!inputPassword.getAttribute("value").equals(inputPasswordCheck.getAttribute("value"))) Errors++;
        if (Errors==0) return true;
        else return false;
    }

    public void inputCustomerNameInForm(String name){
        inputCustomerName.sendKeys(name);
        waitForTime(2);
    }

    public void inputEmailInForm(String email){
        inputEmail.sendKeys(email);
        waitForTime(2);
    }

    public void inputPasswordInForm(String password){
        inputPassword.sendKeys(password);
        waitForTime(2);
    }

    public void inputCheckPasswordInForm(String password){
        inputPasswordCheck.sendKeys(password);
        waitForTime(2);
    }

    public boolean confirmButtonClick(){
        confirmButton.sendKeys(ENTER);
        waitForTime(5);
        if (driver.getCurrentUrl().contains("https://www.amazon.com/ap/register")) return true;
        else if (verifyWindow.isDisplayed()) return true;
        else return false;
    }
}
