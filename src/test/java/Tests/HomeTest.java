package Tests;

import Pages.HomePage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomeTest extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    public void initialization() throws IOException {
        homePage = new HomePage(driver);
    }

    @Description("Select Today Deal From All Tab")
    @Test
    public void SelectTodayDealFromAllTab() {
        homePage.clickOnAllTab();
        homePage.clickOnTodayDeal();
    }

}
