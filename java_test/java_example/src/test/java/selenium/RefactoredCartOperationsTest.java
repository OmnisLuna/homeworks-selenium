package selenium;

import helper.Autotest;
import org.junit.Test;
import pages.CartPage;
import pages.CartWidget;
import pages.MainPage;
import pages.ProductPage;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class RefactoredCartOperationsTest extends Autotest {

    @Test
    public void test() {
        MainPage main = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);
        CartWidget cartWidget = new CartWidget(driver);
        ProductPage productPage = new ProductPage(driver);

        main.open();

        // цикл для добавления 3х продуктов в корзину
        if (isElementPresent(driver, main.content()))
        for (int i = 1; i <= 3; i++) {
            main.goToProductPage(i);

            if (isElementPresent(driver, productPage.optionSelector())) {
                productPage.selectOptionSize(1);
            }

            productPage.addProductToCart();
            cartWidget.checkProductsInCartCount(i);
            driver.navigate().back();
        }
        cartWidget.goToCartPage();

        var elements = cartPage.shortcuts;

        //удаляем каждый элемент по очереди
        for (int i = 1; i <= elements.size(); i++) {
            if (i != elements.size()) {
                cartPage.clickOnFirstProductInShortcut();
            }
            cartPage.removeProduct();
            wait.until(stalenessOf(cartPage.firstCartTableRow));
        }
    }
}
