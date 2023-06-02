package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SecondSearchPage {

    public SecondSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@aria-label='Geçerli sayfa, sayfa 2']")
    public WebElement currentPage2;

    @FindBy(xpath = "//*[@data-component-type='s-search-result']")
    public List<WebElement> searchResult;

}
