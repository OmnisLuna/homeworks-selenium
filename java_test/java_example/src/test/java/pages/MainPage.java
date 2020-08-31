package pages;

import helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get("https://litecart.stqa.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(content()));
        return this;
    }

    public By content() {
        return By.className("content");
    }

    public WebElement product(int positionInList) {
        return
                wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(
                                "//li[@class='product column shadow hover-light'][%s]", positionInList)))));
    }

    public void goToProductPage(int positionInList) {
        product(positionInList).click();
    }
}
