package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver){
        this.driver = driver;
    }

    public HomePage getHomePage(){return new HomePage(driver);}
    public ProductPage getProductPage(){return new ProductPage(driver);}
    public GiftCardPage getGiftCardPage(){return new GiftCardPage(driver);}
    public ShoppingCartPage getShoppingCartPage(){return new ShoppingCartPage(driver);}
    public RegistrationPage getRegistryPage(){return new RegistrationPage(driver);}
    public ChangeLocationPage getChangeLocationPage(){return new ChangeLocationPage(driver);}

}
