package pages;

import org.checkerframework.checker.index.qual.EnsuresLTLengthOf;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class GiftCardPage extends BasePage {

    public GiftCardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//span[@class='a-list-item']/a[contains(@href,'three_browse-bin')]")
    List<WebElement> filters;

    @FindBy(xpath = "//a[contains(@class,'a-size-base a-link')]")
    List<WebElement> giftCardsList;


    @FindBy(xpath ="div[contains(@id,'acs-product-block')]")
    List<WebElement> items;

    public void clickFilter(int filterIndex){
        filters.get(filterIndex).click();
        driver.navigate().refresh();
        waitForPageLoadComplete(20);
        waitVisibilityOfElement(60,giftCardsList.get(0));
    }

    public int countGiftCards(){
        return giftCardsList.size();
    }

    public String getCarouselElementByIndex(int index){
        return items.get(index).getAttribute("id");
    }


}
