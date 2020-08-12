package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {


    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addProductTest() throws InterruptedException {

        driver.navigate().to("http://localhost:8080/litecart");
        driver.findElement(By.xpath("//a[contains(.,'New customers click here')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Random rand = new Random();
        String email = "alalal" + ((char)rand.nextInt(26) + 'a') + "@gmail.com";
        String password = "123123";
        Thread.sleep(1000);

        // Registration
        driver.findElement(By.name("firstname")).sendKeys("Someone");
        driver.findElement(By.name("lastname")).sendKeys("Someone");

        driver.findElement(By.name("address1")).sendKeys("123 Newest Street, apartment 22");
        driver.findElement(By.name("postcode")).sendKeys("12222");
        driver.findElement(By.name("city")).sendKeys("Santa Monica");
        Select priceCode = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
        priceCode.selectByVisibleText("United States");

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+19225673232");

        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);

        driver.findElement(By.name("create_account")).click();

        // эта часть кода сделана потому что в приложении баг
        // и при выборе United States не раздизейбливается селектор с зонами
        if (driver.findElement(By.xpath("//div[contains(.,' You must select a zone.')]")).isDisplayed()) {
            Thread.sleep(1000);

            Select zones = new Select(driver.findElement(By.xpath("//select[@name='zone_code']")));
            zones.selectByVisibleText("California");

            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("confirmed_password")).sendKeys(password);
            driver.findElement(By.name("create_account")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Logout')]")).isDisplayed());

        // Logout
        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();

        // Authorization
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        String logoutLocator = "//div[@id='box-account']//a[contains(text(), 'Logout')]";
        Assert.assertTrue(driver.findElement(By.xpath(logoutLocator)).isDisplayed());

        // Logout
        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
