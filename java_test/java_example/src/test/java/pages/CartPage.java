package pages;

import helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends PageObject {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open() {
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        return this;
    }

    @FindBy(xpath = "//li[@class='shortcut']")
    public List<WebElement> shortcuts;

    public WebElement shortcutByPosition(int position) {
        return driver.findElement(By.xpath(String.format("//li[@class='shortcut'][%s]/a", position)));
    }

    @FindBy(xpath = "//button[@name='remove_cart_item']")
    public WebElement removeButton;

    @FindBy(xpath = "//table[@class='dataTable rounded-corners']//tr[2]")
    public WebElement firstCartTableRow;

    public void removeProduct() {
        (wait.until(ExpectedConditions.elementToBeClickable(removeButton))).click();
    }

    public  void  clickOnFirstProductInShortcut() {
        wait.until(ExpectedConditions.visibilityOf(shortcutByPosition(1))).click();
    }
}