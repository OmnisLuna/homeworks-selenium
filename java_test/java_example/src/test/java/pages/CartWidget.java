package pages;

import helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartWidget extends PageObject {

    public CartWidget(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='link' and contains(.,'Checkout')]")
    public WebElement checkoutLink;

    public By quantity() {
        return By.className("quantity");
    }

    public  void  checkProductsInCartCount(int count) {
        wait.until(ExpectedConditions.textToBe(quantity(), String.valueOf(count)));
    }

    public  void goToCartPage() {
        wait.until(ExpectedConditions.visibilityOf(checkoutLink)).click();
    }
}
