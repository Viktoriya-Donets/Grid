package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChangeLocationPage extends BasePage{
    public ChangeLocationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath="//span[@class='a-dropdown-container']")
    WebElement changeLocationContainer;

    @FindBy(xpath="//a[@class='a-dropdown-link'][contains(@href,'javascript')]")
    List<WebElement> locationSelector;

    @FindBy(xpath="//button[@name='glowDoneButton']")
    WebElement confirmLocationButton;

    @FindBy(xpath="//i[@class='a-icon a-icon-dropdown']")
    WebElement iconDropDown;

    public String changeLocation(String location){
        waitVisibilityOfElement(20,changeLocationContainer);
        String selectedLocation="";
        Actions builder = new Actions(driver);
        builder.click(iconDropDown).build().perform();
        for(int i=0; i<locationSelector.size();i++)
            if (locationSelector.get(i).getText().trim().equals(location.trim()))
            {
                selectedLocation=locationSelector.get(i).getText();
                builder.click(locationSelector.get(i)).build().perform();
            }
        builder.click(confirmLocationButton).build().perform();
        return selectedLocation;
    }

}
