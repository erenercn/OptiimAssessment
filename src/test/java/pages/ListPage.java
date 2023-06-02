package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ListPage {
    public ListPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "pab-IC0JO70R2ABAE")
    public WebElement buyOptionsButton;

    @FindBy(id = "pab-I3BTCDL535J32D")
    public WebElement addToCartButton;

    @FindBy(xpath = "//*[text()='Sepete Eklendi']")
    public WebElement addedToCartMessage;

    @FindBy(id = "nav-cart-count")
    public WebElement cartButton;
}
