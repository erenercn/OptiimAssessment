package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.*;




public class AmazonTest extends TestBaseReports {

    HomePage homePage;
    LoginPage loginPage;

    PasswordLoginPage passwordLoginPage;
    ExcelUtils excelUtils;

    SearchPage searchPage;
    SecondSearchPage secondSearchPage;
    ProductPage productPage;

    ListPage listPage;
    CartPage cartPage;



    @Test
    public void openHomePage() {
        extentTest = extentReports.createTest("Amazon Test", "Opening homepage");
        Driver.getDriver().get(ConfigReader.getProperty("appUrl"));
        Assert.assertEquals(Driver.getDriver().getTitle(), "Amazon.com.tr: Elektronik, bilgisayar, akıllı telefon, kitap, oyuncak, yapı market, ev, mutfak, oyun konsolları ürünleri ve daha fazlası için internet alışveriş sitesi");
        extentTest.info("Homepage is opened");
    }

    @Test(dependsOnMethods = "openHomePage")
    public void login() {
        extentTest = extentReports.createTest("Amazon Test", "Login");
        homePage = new HomePage();
        loginPage = new LoginPage();
        passwordLoginPage = new PasswordLoginPage();

        homePage.accountList.click();

        excelUtils = new ExcelUtils("./src/test/java/resources/loginCredentials.xlsx", "Sheet1");
        String email = excelUtils.getCellData(0, 0);

        loginPage.emailTextBox.sendKeys(email);
        loginPage.continueButton.click();

        String password = excelUtils.getCellData(0, 1);
        passwordLoginPage.passwordTextBox.sendKeys(password);
        passwordLoginPage.signInButton.click();

        extentTest.info("Login is successful");

    }

    @Test(dependsOnMethods = "login")
    public void searchProduct() {
        extentTest = extentReports.createTest("Amazon Test", "Search product");
        homePage = new HomePage();

        homePage.searchBox.sendKeys("samsung");
        homePage.searchButton.click();
        extentTest.info("Product is searched");
    }

    @Test(dependsOnMethods = "searchProduct")
    public void selectCategory() {
        extentTest = extentReports.createTest("Amazon Test", "Select category");
        searchPage = new SearchPage();
        searchPage.cellPhones.click();
        Assert.assertTrue(searchPage.resultInfoBar.isDisplayed());
        extentTest.info("Category is selected");
    }

    @Test(dependsOnMethods = "selectCategory")
    public void navigateToSecondPage() {
        extentTest = extentReports.createTest("Amazon Test", "Navigate to second page");
        searchPage = new SearchPage();
        secondSearchPage = new SecondSearchPage();

        searchPage.navigateToSecondPage.click();
        Assert.assertTrue(secondSearchPage.currentPage2.isDisplayed());
        extentTest.info("Second page is opened");
    }


    @Test(dependsOnMethods = "navigateToSecondPage")
    public void selectProduct() {
        extentTest = extentReports.createTest("Amazon Test", "Select product");
        secondSearchPage = new SecondSearchPage();
        secondSearchPage.searchResult.get(4).click();
        extentTest.info("Product is selected");
    }

    @Test(dependsOnMethods = "selectProduct")
    public void addToList() {
        extentTest = extentReports.createTest("Amazon Test", "Add to list");
        productPage = new ProductPage();
        listPage = new ListPage();
        productPage.addToWishListButton.click();
        Assert.assertEquals(productPage.wishListText.getText(), "1 ürün şuraya eklendi:");
        productPage.viewListButton.click();
        extentTest.info("Product is added to list");

    }

    @Test(dependsOnMethods = "addToList")
    public void addToCart() {
        extentTest = extentReports.createTest("Amazon Test", "Add to cart");
        listPage = new ListPage();
        listPage.addToCartButton.click();
        ReusableMethods.waitForVisibility(listPage.addedToCartMessage,4);
        Assert.assertTrue(listPage.addedToCartMessage.isDisplayed());
        extentTest.info("Product is added to cart");


    }

    @Test(dependsOnMethods = "addToCart")
    public void goToCartPage() {
        extentTest = extentReports.createTest("Amazon Test", "Go to cart page");
        cartPage = new CartPage();
        listPage = new ListPage();
        listPage.cartButton.click();

        ReusableMethods.clickWithJS(cartPage.deleteButton);
        Assert.assertEquals(cartPage.emptyCartMessage.getText(), "Amazon sepetiniz boş.");
        extentTest.info("Cart page is opened, product is deleted from cart");

        Driver.closeDriver();

    }




}
