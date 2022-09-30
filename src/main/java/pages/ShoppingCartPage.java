package pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//span[@class='a-truncate-cut']")
    WebElement productTitle;
    @FindBy(xpath="//p[@class='a-spacing-mini']//span[@class='a-price-whole']")
    WebElement wholePartOfPrice;
    @FindBy(xpath="//p[@class='a-spacing-mini']//span[@class='a-price-fraction']")
    WebElement decimalPartOfPrice;
    @FindBy(xpath="//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-whole']")
    WebElement wholePartOfAmount;
    @FindBy(xpath="//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-fraction']")
    WebElement decimalPartOfAmount;
    @FindBy(xpath="//span[@class='a-button-text a-declarative']//span[@class='a-dropdown-prompt']")
    WebElement quantityField;
    @FindBy (xpath="//select[@name='quantity']")
    WebElement quantitySelect;
    @FindBy (xpath="//input[@name='proceedToRetailCheckout']")
    WebElement checkOutButton;

    public String getProductTitle(){
        return productTitle.getText();
    }

    public Double getProductPrice(){
        waitVisibilityOfElement(100,wholePartOfPrice);
        waitVisibilityOfElement(60,decimalPartOfPrice);
       return Double.parseDouble((wholePartOfPrice.getText()+"."+decimalPartOfPrice.getText()).replaceAll(",",""));
    }

    public Double getProductAmount(){
        waitVisibilityOfElement(100,wholePartOfAmount);
        waitVisibilityOfElement(60,decimalPartOfAmount);
        return Double.parseDouble((wholePartOfAmount.getText()+"."+decimalPartOfAmount.getText()).replaceAll(",",""));
    }

    public void setQuantity (int quantity) throws InterruptedException {
        Actions builder = new Actions(driver);
        Select productQuantity = new Select(quantitySelect);
        productQuantity.selectByIndex(quantity);
        builder.pause(Duration.ofSeconds(5)).build().perform();
    }

}
