package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//input[@data-action='delete']")
    public WebElement deleteButton;


    @FindBy(xpath = "//*[@class='a-spacing-mini a-spacing-top-base']")
    public WebElement emptyCartMessage;
}
