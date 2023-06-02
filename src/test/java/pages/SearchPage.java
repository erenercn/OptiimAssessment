package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SearchPage {

    public SearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath ="//span[text()='Cep Telefonları']")
    public WebElement cellPhones;

    @FindBy(xpath = "//div[@data-cel-widget='UPPER-RESULT_INFO_BAR-0']")
    public WebElement resultInfoBar;

    @FindBy(xpath ="//a[@aria-label='2 sayfasına git']")
    public WebElement navigateToSecondPage;



}
