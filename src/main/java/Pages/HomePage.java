package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) throws IOException {
        super(driver);
   }

    @Step("Hover Over Account&List from tab bar")
    public void HoverOnAccountAndList(){
       WebElement accountLink =  webActions.getWebElement(By.id("nav-link-accountList"));
        webActions.MoveToElement(accountLink);
    }


    @Step("click On Sign In Button")
    public void clickOnSignInButton() {
        WebElement signInButton = webActions.getWebElement(By.id("nav-al-signin"));
        webActions.clickOnWebElement(signInButton);
        Assert.assertTrue(driver.getCurrentUrl().contains(propertyReader.getValue("signInURl")));
    }

    @Step("click On All Tab Button")
    public void clickOnAllTab() {
        WebElement AllTabMenu = webActions.getWebElement(By.id("nav-hamburger-menu"));
        webActions.clickOnWebElement(AllTabMenu);
        WebElement Menu = webActions.getWebElement(By.id("hmenu-canvas"));
        Assert.assertTrue(Menu.isDisplayed());
    }


    @Step("click OnToday Deal")
    public void clickOnTodayDeal() {
        WebElement TodayDeals = webActions.getWebElement(By.xpath("//a[@href = '/-/en/gp/goldbox/?ref_=nav_em_navm_deals_0_1_1_24']"));
        webActions.scrollToElement(TodayDeals);
        String navigationLink = TodayDeals.getAttribute("href");
        String urlLink = driver.getCurrentUrl();
        webActions.clickOnWebElement(TodayDeals);
        webActions.waitElementToChangeTheCurrentURL(urlLink);
        Assert.assertTrue(driver.getCurrentUrl().contains(navigationLink));
    }


}
