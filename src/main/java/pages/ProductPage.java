package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//div[@id='imgTagWrapperId']/img")
    WebElement productImage;
    @FindBy(xpath = "//span[@id='productTitle']")
    WebElement productTitle;
    @FindBy(xpath = "//div[@id='prodDetails']")
    WebElement productDetailsWindow;
    @FindBy(xpath = "//h2[contains(@class,'a-size-mini')]/a")
    List<WebElement> productList;

    @FindBy(xpath="//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']")
    WebElement wholePricePart;
    @FindBy(xpath="//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-fraction']")
    WebElement decimalPricePart;

    @FindBy(xpath="//input[@id='add-to-cart-button-ubb']")
    WebElement addToCartButton;
    @FindBy(xpath="//a[contains(@href,'cart')][@class='a-button-text']")
    WebElement confirmAddToCartButton;

    public boolean isProductImageVisible(){
        if ((productImage.isDisplayed())&&(productImage.getAttribute("src").length()>0)) return true;
        else return false;
    }
    public boolean isProductTitleVisible(){
        if (productTitle.isDisplayed()) return true;
        else return false;
    }
    public boolean isProductDetailsWindowVisible(){
        if (productDetailsWindow.isDisplayed()) return true;
        else return false;
    }
    public boolean isAddToCartButtonClickable(){
        String currentURL=driver.getCurrentUrl();
        addToCartButton.sendKeys(ENTER);
        waitForAjaxToComplete(60);
        String newURL=driver.getCurrentUrl();
        driver.get(currentURL);
        waitForPageLoadComplete(60);
        if (!newURL.equals(currentURL)) return true;
        else return false;
    }

    public void openProductCard(int i){
        driver.get(productList.get(i).getAttribute("href"));
        waitForPageLoadComplete(60);
    }

    public double getProductPrice(){
        return Double.parseDouble(wholePricePart.getText()+"."+decimalPricePart.getText());
    }

    public String getProductTitle(){
        return productTitle.getText();
    }

    public boolean addToCartButtonClick(){
        addToCartButton.sendKeys(ENTER);
        waitForPageLoadComplete(60);
        if (driver.getCurrentUrl().contains("cart")) {
            confirmAddToCartButton.click();
            waitForPageLoadComplete(60);
            return true;
        }
        return false;
    }
}
