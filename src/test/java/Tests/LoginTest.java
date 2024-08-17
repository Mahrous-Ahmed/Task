package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest{
    HomePage homePage;
    LoginPage loginPage;
    @BeforeMethod
    public void initialization() throws IOException {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("LoginUsingNonRegisteredEmail")
    public void LoginUsingNonRegisteredEmail(){
        homePage.HoverOnAccountAndList();
        homePage.clickOnSignInButton();
        loginPage.enterEmail();
        loginPage.clickOnContinueButton();
        loginPage.checkDisplayedTray(driver.getCurrentUrl());
    }

}
