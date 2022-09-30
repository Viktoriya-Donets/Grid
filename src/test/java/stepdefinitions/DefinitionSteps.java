package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;

public class DefinitionSteps {
    public String currentProductTitle;
    public Double currentProductPrice;
    public String currentLocation;
    WebDriver driver;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    ProductPage productPage;
    RegistrationPage registrationPage;
    GiftCardPage giftCardPage;
    ChangeLocationPage changeLocationPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        productPage=pageFactoryManager.getProductPage();
        registrationPage =pageFactoryManager.getRegistryPage();
        shoppingCartPage =pageFactoryManager.getShoppingCartPage();
        giftCardPage =pageFactoryManager.getGiftCardPage();
        changeLocationPage=pageFactoryManager.getChangeLocationPage();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @After
    public void tearDown(){
        driver.close();
    }


    @Then("User checks that search page contains {string}opened")
    public void userChecksThatSearchPageContainsItemURL(final String searchItem) {
        assertTrue(homePage.isPageOpened(searchItem));
    }

    @And("User checks that search page was not empty")
    public void userChecksThatSearchPageWasNotEmpty() {
        assertTrue(homePage.getTotalProductQuantityOnPage()>0);
    }


    @And("User search {string}")
    public void userSearchItem(final String searchString) {
        homePage.insertSearchQuery(searchString);
    }

    @Then("User checks that all menu buttons clicks in turn and opened relevant windows")
    public void userCheckThatAllMenuButtonsClicksInTurnAndOpenedRelevantWindows() {
        assertEquals(0,homePage.isMenuButtonClicksAndOpensRelevantWindows());
    }

    @Then("User checks that image visible")
    public void userChecksThatImageVisible() {
        assertTrue(productPage.isProductImageVisible());
    }

    @And("User checks that title visible")
    public void userChecksThatTitleVisible() {
        assertTrue(productPage.isProductTitleVisible());
    }

    @And("User checks that details visible")
    public void userChecksThatDetailsVisible() {
        assertTrue(productPage.isProductDetailsWindowVisible());
    }

    @And("User checks that Add to Cart button is clickable")
    public void userChecksThatAddToCartButtonIsClickable() {
        assertTrue(productPage.isAddToCartButtonClickable());
    }

    @Then("User opens product {string} page")
    public void userOpensProductProductPage(String index) {
        productPage.openProductCard(Integer.parseInt(index));
    }

    @And("User clicks SignIn")
    public void userClicksSignIn() { homePage.clickOnSignInButton();
    }

    @And("User clicks registration button")
    public void userClicksRegistryButton() { registrationPage.clickNewAccountButton();
    }

    @Then("User check that registration form is loaded")
    public void userCheckThatRegistryFormIsLoaded() {
        assertTrue(registrationPage.isRegistryFormOpen());
    }

    @And("User checks field name visibility")
    public void userChecksFieldNameVisibility() {
        assertTrue(registrationPage.isInputCustomerNameVisible());
    }

    @And("User checks field email visibility")
    public void userChecksFieldEmailVisibility() {
        assertTrue(registrationPage.isInputEmailVisible());
    }

    @And("User checks field password visibility")
    public void userChecksFieldPasswordVisibility() {
        assertTrue(registrationPage.isInputPasswordVisible());
    }

    @And("Users checks field password check visibility")
    public void usersChecksFieldPasswordCheckVisibility() {
        assertTrue(registrationPage.isInputCheckPasswordVisible());
    }

    @And("User fills field name with {string}")
    public void userFillsFieldNameWithName(String name) {
        registrationPage.inputCustomerNameInForm(name);
    }

    @And("User fills field email with {string}")
    public void userFillsFieldEmailWithEmail(String email) {
        registrationPage.inputEmailInForm(email);
    }

    @And("User fills field password with {string}")
    public void userFillsFieldPasswordWithPassword(String password) {
        registrationPage.inputPasswordInForm(password);
    }

    @And("Users fills field password check {string}")
    public void usersFillsFieldPasswordCheckCheckpassword(String password) {
        registrationPage.inputCheckPasswordInForm(password);
    }

    @And("Users clicks button confirm and checks data")
    public void usersClicksButtonConfirmAndChecksData() {
        assertFalse(registrationPage.isDataCorrect());
        registrationPage.confirmButtonClick();
    }

    @And("Users clicks button confirm and checks valid data")
    public void usersClicksButtonConfirmAndChecksValidData() {
        assertTrue(registrationPage.isDataCorrect());
        assertTrue(registrationPage.confirmButtonClick());
    }

    @Then("User checks that product add to cart")
    public void userChecksThatProductAddToCart() {
        currentProductTitle=productPage.getProductTitle();
        currentProductPrice=productPage.getProductPrice();
        assertTrue(productPage.addToCartButtonClick());
    }

    @And("User checks that product title in cart equals title in product card")
    public void userChecksThatProductTitleInCartEqualsTitleInProductCard() {
        assertEquals(currentProductTitle,shoppingCartPage.getProductTitle());
    }

    @And("User checks that product price in cart equals price in product card")
    public void userChecksThatProductPriceInCartEqualsPriceInProductCard() {
        assertEquals(currentProductPrice,shoppingCartPage.getProductPrice());
    }

    @And("User set quantity {string}")
    public void userSetQuantityQuantity(String quantity) throws InterruptedException {
        shoppingCartPage.setQuantity(Integer.parseInt(quantity));
    }

    @And("User checks that product price in cart equals price*{string}")
    public void userChecksThatProductPriceInCartEqualsPriceQuantity(String quantity) {
        double productAmount=shoppingCartPage.getProductPrice()*Integer.parseInt(quantity);
        assertEquals(String.valueOf(productAmount),String.valueOf(shoppingCartPage.getProductAmount()));
    }

    @And("User opens Gift Cards page")
    public void userOpensGiftCardsPage() {
        homePage.openGiftCardsPage();
    }

    @And("User choose filter number {string}")
    public void userChooseFilterNumberFilterIndex(String index) {
        giftCardPage.clickFilter(Integer.parseInt(index));
    }

    @Then("User counts cards and compares with {string}")
    public void userCountsCardsAndComparesWithQuantity(String quantity) {
        assertEquals(giftCardPage.countGiftCards(),Integer.parseInt(quantity));
    }

    @Then("User try to delete product from cart")
    public void userTryToDeleteProductFromCart() {
    }

    @Then("User clicks change location button")
    public void userClicksChangeLocationButton() {
        homePage.clickChangeLocationButton();
    }

    @And("Users choose location {string}")
    public void usersChooseLocationLocation(String location) {
        currentLocation=changeLocationPage.changeLocation(location);
    }

    @And("Users checks that location has changed")
    public void usersChecksThatLocationHasChanged() {
        assertEquals(currentLocation,homePage.getLocationName());
    }
}
