package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    WebElement menuButton;

    @FindBy(xpath="//div[@id='nav-xshop']/a[contains(@class,'nav-a')]")
    List<WebElement> mainMenuItemsButtons;

    @FindBy(xpath ="//div[contains(@class,'s-product-image-container')]")
    List<WebElement> productList;

    @FindBy(xpath="//input[@id='twotabsearchtextbox']")
    WebElement searchString;

    @FindBy(xpath="//input[@id='nav-search-submit-button']")
    WebElement searchButton;

    @FindBy(xpath="//h1")
    WebElement h1Field;

    @FindBy(xpath="//a[@data-nav-ref='nav_ya_signin']")
    WebElement signInButton;

    @FindBy(xpath="//div[@id='nav-xshop']/a[contains(@href,'gift-cards')]")
    WebElement giftCardsPage;

    @FindBy(xpath="//a[@id='nav-global-location-popover-link']")
    WebElement changeLocationButton;

    @FindBy(xpath="//span[contains(@class,'nav-line-2 nav-progressive-content')]")
    WebElement locationName;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void clickOnSignInButton()
    {
        driver.navigate().refresh();
        waitForPageLoadComplete(60);
        waitVisibilityOfElement(60,signInButton);
        signInButton.click();
        waitForPageLoadComplete(60);
    }
    public void openHomePage(String url) {
        driver.get(url);
        waitForPageLoadComplete(60);
    }

    public void clickMenuButton(){
        menuButton.click();
        waitForPageLoadComplete(60);
    }

    public void insertSearchQuery(String searchQuery){
        waitVisibilityOfElement(60,searchString);
        searchString.clear();
        searchString.sendKeys(searchQuery);
        waitForTime(20);
        searchButton.click();
        waitForPageLoadComplete(60);
    }

    public int isMenuButtonClicksAndOpensRelevantWindows(){
        int Errors=5;
        String currentItem = mainMenuItemsButtons.get(0).getText();
        for (int i=0; i<mainMenuItemsButtons.size();i++){
            currentItem = mainMenuItemsButtons.get(i).getText();
            mainMenuItemsButtons.get(i).sendKeys(ENTER);
            waitForPageLoadComplete(60);
            if (driver.getCurrentUrl().contains(currentItem.toLowerCase().trim().replaceAll(" ","-"))) Errors--;
            else if (h1Field.getText().contains(currentItem)) Errors--;
        }
        return Errors;
    }

    public boolean isPageOpened(String urlPart){
        if (driver.getCurrentUrl().contains(urlPart)) return true;
        else return false;
    }

    public int getTotalProductQuantityOnPage(){
        return productList.size();
    }

    public void openGiftCardsPage() {
        waitVisibilityOfElement(60,giftCardsPage);
        driver.get(giftCardsPage.getAttribute("href"));
        waitForPageLoadComplete(60);
    }

    public void clickChangeLocationButton(){
        Actions builder = new Actions(driver);
        builder.click(changeLocationButton).build().perform();
        waitForPageLoadComplete(60);
    }

    public String getLocationName(){
        driver.navigate().refresh();
        waitVisibilityOfElement(60,locationName);
        return locationName.getText();
    }
}
