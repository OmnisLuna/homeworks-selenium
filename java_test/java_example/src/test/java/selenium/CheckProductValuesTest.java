package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckProductValuesTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public boolean checkBiggerValue(float bigger, float smaller) {
        return (bigger > smaller);
    }

    public float stringToFloat(String value) {
        String valueFormatted = value.substring(0, value.length() - 2); //убираем px из строки - 20px
        return Float.parseFloat(valueFormatted);
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkProductValuesTest() {

        driver.navigate().to("http://localhost:8080/litecart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content")));

        if (driver.findElement(By.className("content")).isDisplayed()) {

            var product =
                    driver.findElement(By.xpath("//div[@id='box-campaigns']//li[@class='product column shadow hover-light'][1]"));

            String productName = product.findElement(By.className("name")).getText();

            WebElement productRegularPrice = product.findElement(By.className("regular-price"));
            String productRegularPriceValue = productRegularPrice.getText();

            //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой,
            // у которого в RGBa представлении одинаковые значения для каналов R, G и B)
            Assert.assertTrue(productRegularPrice.getCssValue("color")
                    .matches("^rgba\\((\\d{1,3}), (\\1\\b), (\\1\\b), (\\d*)\\)$")); //проверяем соответствие паттерну через regex
            Assert.assertEquals("line-through solid rgb(119, 119, 119)", productRegularPrice.getCssValue("text-decoration"));
            Assert.assertEquals("400", productRegularPrice.getCssValue("font-weight"));
            Assert.assertEquals("14.4px", productRegularPrice.getCssValue("font-size"));

            WebElement productCampaignPrice = product.findElement(By.className("campaign-price"));
            String productCampaignPriceValue = productCampaignPrice.getText();

            // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa
            // представлении каналы G и B имеют нулевые значения)
            // (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
            Assert.assertTrue(productCampaignPrice.getCssValue("color")
                    .matches("^rgba\\((\\d{1,3}), ([0]), ([0]), (\\d*)\\)$")); //проверяем соответствие паттерну через regex
            Assert.assertEquals("none solid rgb(204, 0, 0)", productCampaignPrice.getCssValue("text-decoration"));
            Assert.assertEquals("700", productCampaignPrice.getCssValue("font-weight"));
            Assert.assertEquals("18px", productCampaignPrice.getCssValue("font-size"));

            // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
            Assert.assertTrue(checkBiggerValue(
                    stringToFloat(productCampaignPrice.getCssValue("font-size")),
                    stringToFloat(productRegularPrice.getCssValue("font-size"))
                    )
            );

            // а) на главной странице и на странице товара совпадает текст названия товара
            // б) на главной странице и на странице товара совпадают цены (обычная и акционная)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", product);
            product.click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertEquals(driver.findElement(By.xpath("//h1[@itemprop='name']")).getText(), productName);
            Assert.assertEquals(driver.findElement(By.className("regular-price")).getText(), productRegularPriceValue);
            Assert.assertEquals(driver.findElement(By.className("campaign-price")).getText(), productCampaignPriceValue);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
