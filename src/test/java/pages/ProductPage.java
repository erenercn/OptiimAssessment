package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductPage {

    public ProductPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id= "add-to-wishlist-button-submit")
    public WebElement addToWishListButton;

    @FindBy(xpath = "//*[text() = '1 ürün şuraya eklendi:']")
    public WebElement wishListText;

    @FindBy(id = "huc-view-your-list-button")
    public WebElement viewListButton;
}
