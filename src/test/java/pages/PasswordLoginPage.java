package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PasswordLoginPage {

    public PasswordLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ap_password")
    public WebElement passwordTextBox;

    @FindBy(id = "signInSubmit")
    public WebElement signInButton;
}
