package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage extends BasePage{



    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void enterEmail(){
        WebElement Email_Input = webActions.getWebElement(By.name("email"));
        webActions.EnterText(Email_Input , propertyReader.getValue( "NonRegisteredEmail"));
    }

    @Step("click On Continue Button")
    public void clickOnContinueButton()  {
        WebElement continueButton = webActions.getWebElement(By.id("continue"));
        String currentURl = driver.getCurrentUrl();
        webActions.clickOnWebElement(continueButton);
        webActions.waitElementToChangeTheCurrentURL(currentURl);
    }

    @Step("check Error Message IS Displayed")
    public void checkErrorMessageISDisplayed(){
        WebElement ErrorBox =  webActions.getWebElement(By.id("auth-error-message-box"));
        Assert.assertTrue(ErrorBox.isDisplayed());

        WebElement AlertIcon =  webActions.getWebElement(By.cssSelector("i.a-icon.a-icon-alert"));
        Assert.assertTrue(AlertIcon.isDisplayed());

        WebElement ErrorTitle = webActions.getWebElement(By.cssSelector("h4.a-alert-heading"));
        Assert.assertTrue(ErrorTitle.getText().contains(propertyReader.getValue("ErrorTitle")));


        WebElement ErrorDescription = webActions.getWebElement(By.cssSelector("span.a-list-item"));
        Assert.assertTrue(ErrorDescription.getText().contains(propertyReader.getValue("ErrorDescription")));
    }
    


    public void checkDisplayedTray(String URL){
        if(URL.contains("https://www.amazon.eg/ap/signin"))
            checkErrorMessageISDisplayed();
        else if (URL.contains("https://www.amazon.eg/ap/register"))
            webActions.getWebElement(By.id("ap_register_form"));
        else if (URL.contains("https://www.amazon.eg/ax/claim/intent"))
            webActions.getWebElement(By.id("intent-confirmation-container"));
        else
            Assert.assertTrue(false , "Goes to invalid URL");

    }




}
