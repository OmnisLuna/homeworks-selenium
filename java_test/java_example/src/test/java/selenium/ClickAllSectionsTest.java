package selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.junit.runners.Parameterized.Parameters;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@RunWith(Parameterized.class)
public class ClickAllSectionsTest extends SeleniumAutotest {

    @Parameters
    public static Object[] data() {
        return new Object[]{
                "'Appearence'",
                "'Catalog'",
                "'Countries'",
                "'Currencies'",
                "'Customers'",
                "'Geo Zones'",
                "'Languages'",
                "'Modules'",
                "'Orders'",
                "'Pages'",
                "'Reports'",
                "'Settings'",
                "'Slides'",
                "'Tax'",
                "'Translations'",
                "'Users'",
                "'vQmods'",

        };
    }

    private final String sideBarTitleName;

    public ClickAllSectionsTest(String sideBarTitleName) {
        this.sideBarTitleName = sideBarTitleName;
    }

    public void clickSideBarSubTitle(String idName) {
        wait.until(elementToBeClickable(By.id(idName)));
        driver.findElement(By.id(idName)).click();
    }

    @Test
    public void clickAllSectionsTest() {
        String sideBarSubTitle = "//h1";
        String sideBarTitleNameLocator = "//span[@class='name' and text()=" + sideBarTitleName + "]";

        wait.until(visibilityOfElementLocated(By.id("content")));
        wait.until(elementToBeClickable(By.xpath(sideBarTitleNameLocator)));

        driver.findElement(By.xpath(sideBarTitleNameLocator)).click();

        wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
        boolean condition = driver.findElement(By.xpath(sideBarSubTitle)).isDisplayed();
        Assert.assertTrue(condition);

        if (sideBarTitleName.equals("'Appearence'")) {
            clickSideBarSubTitle("doc-logotype");
            wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
            Assert.assertTrue(condition);
        }

        if (sideBarTitleName.equals("'Catalog'")) {

            String[] elements = new String[]{
                    "doc-product_groups",
                    "doc-option_groups",
                    "doc-manufacturers",
                    "doc-suppliers",
                    "doc-delivery_statuses",
                    "doc-sold_out_statuses",
                    "doc-quantity_units",
                    "doc-csv"
            };

            for (String element : elements) {
                clickSideBarSubTitle(element);
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
                System.out.println(element);
            }
        }

            if (sideBarTitleName.equals("'Customers'")) {

                String[] elements = new String[]{
                        "doc-newsletter",
                        "doc-csv",
                };

                for (String element : elements) {
                    clickSideBarSubTitle(element);
                    wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                    Assert.assertTrue(condition);
                    System.out.println(element);
                }
            }

            if (sideBarTitleName.equals("'Languages'")) {
                clickSideBarSubTitle("doc-storage_encoding");
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
            }

            if (sideBarTitleName.equals("'Modules'")) {

                String[] elements = new String[]{
                        "doc-jobs",
                        "doc-customer",
                        "doc-shipping",
                        "doc-payment",
                        "doc-order_total",
                        "doc-order_success",
                        "doc-order_action"
                };

                for (String element : elements) {
                    clickSideBarSubTitle(element);
                    wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                    Assert.assertTrue(condition);
                    System.out.println(element);
                }
            }

       if (sideBarTitleName.equals("'Orders'")) {
            clickSideBarSubTitle("doc-order_statuses");
            wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
            Assert.assertTrue(condition);
        }

        if (sideBarTitleName.equals("'Reports'")) {

            String[] elements = new String[]{
                    "doc-most_sold_products",
                    "doc-most_shopping_customers",
            };

            for (String element : elements) {
                clickSideBarSubTitle(element);
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
            }
        }

        if (sideBarTitleName.equals("'Settings'")) {

            String[] elements = new String[]{
                    "doc-store_info",
                    "doc-defaults",
                    "doc-general",
                    "doc-listings",
                    "doc-images",
                    "doc-checkout",
                    "doc-advanced",
                    "doc-security",
            };

            for (String element : elements) {
                clickSideBarSubTitle(element);
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
            }
        }

        if (sideBarTitleName.equals("'Tax'")) {
            String[] elements = new String[]{
                    "doc-tax_classes",
                    "doc-tax_rates",
            };

            for (String element : elements) {
                clickSideBarSubTitle(element);
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
            }
        }

        if (sideBarTitleName.equals("'Translations'")) {
            String[] elements = new String[]{
                    "doc-search",
                    "doc-scan",
                    "doc-csv",
            };

            for (String element : elements) {
                clickSideBarSubTitle(element);
                wait.until(visibilityOfElementLocated(By.xpath(sideBarSubTitle)));
                Assert.assertTrue(condition);
            }
        }
        }
}
