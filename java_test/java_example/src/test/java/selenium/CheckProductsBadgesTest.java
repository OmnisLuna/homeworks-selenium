package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;

public class CheckProductsBadgesTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public boolean conditionCheck(WebElement product) {
        boolean conditionOne =  product.findElement(By.xpath("//a/div/div[contains (@class,'sticker')]")).isDisplayed();
        String count = product.findElement(By.xpath("count(//a/div/div[contains (@class,'sticker')])")).toString();
//        int countElements = Integer.parseInt(count);
        System.out.println("КОЛИЧЕСТВО ЭЛЕМЕНТОВ:" + count);
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();//считываем целое число a
        int b = in.nextInt();//считываем целое число b
        return conditionOne & (count.equals("1"));
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkProductsBadgesTest() {

        driver.navigate().to("http://localhost:8080/litecart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content")));
        if (driver.findElement(By.className("content")).isDisplayed()) {

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,250)");
            var products = driver.findElements(By.className("product.column.shadow.hover-light"));
            for (WebElement product : products) {
                System.out.println("GJIIDJD" + product.findElement(By.xpath("count(//a/div/div[contains (@class,'sticker')])")));
                Assert.assertTrue(conditionCheck(product));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
