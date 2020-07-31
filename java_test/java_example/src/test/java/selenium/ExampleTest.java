package selenium;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ExampleTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void exampleTest() {
        String logoutLocator = "//div[@id='box-account']//a[contains(text(), 'Logout')]";
        driver.navigate().to("http://localhost:8080/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("someone@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath(logoutLocator)).isDisplayed();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}