package Tests;

import Pages.TodayDealPage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TodayDealTest extends BaseTest{
    TodayDealPage todayDealPage;
    @BeforeClass
    public void initialization() throws IOException {
        todayDealPage = new TodayDealPage(driver);
    }

    @Test
    @Description("Verify that Items are added to cart correctly")
    public void VerifItemsInCart(){
        todayDealPage.clickOnSpecificCategory();
        todayDealPage.clickOnSpecificProduct();
        todayDealPage.clickOnSpecificItem();
        todayDealPage.SelectQuantityForTHeProduct();
        todayDealPage.AddItemToCart();
        todayDealPage.CheckShoppingCart();
    }

}
