package pages;

import helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class ProductPage extends PageObject {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "options[Size]")
    public WebElement optionsByName;

    @FindBy(xpath = "//button[@name='add_cart_product']")
    public WebElement addToCartButton;

    public By optionSelector() {
        return By.className("options");
    }

    public void selectOptionSize(int index) {
        Select options = new Select(optionsByName);
        options.selectByIndex(index);
    }

    public void addProductToCart() {
        (wait.until(ExpectedConditions.elementToBeClickable(addToCartButton))).click();
    }
}
