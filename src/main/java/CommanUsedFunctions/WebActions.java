package CommanUsedFunctions;

import Pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebActions{
    WebDriverWait wait;
    WebDriver driver;
    Actions actions;
   public WebActions(WebDriver driver){
       this.driver = driver;
       wait = new WebDriverWait(driver , Duration.ofSeconds(30));
       actions = new Actions(driver);
    }

    public void waitElementToBeClickable(WebElement GenricElement) {
        wait.until(ExpectedConditions.elementToBeClickable(GenricElement));
    }


    public void waitElementToBeVisable(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitElementsToBeVisable(By by) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    public void waitElementToChangeTheCurrentURL(String currentURL) {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
    }

    public void EnterText(WebElement GenricElement, String Text) {
       clickOnWebElement(GenricElement);
        GenricElement.clear();
        GenricElement.sendKeys(Text);
    }

    public void clickOnWebElement(WebElement GenricElement) {
        waitElementToBeClickable(GenricElement);
        GenricElement.click();
    }

    public String getText(By by) {
        waitElementToBeVisable(by);
        return getWebElement(by).getText();
    }

    public void MoveToElement(WebElement GenricElement) {
        actions.moveToElement(GenricElement).build().perform();
    }


    public void scrollToElement(WebElement GenricElement) {
        actions.scrollToElement(GenricElement).build().perform();
    }


    public WebElement getWebElement(By by){
       waitElementToBeVisable(by);
       WebElement element = driver.findElement(by);

       return element;
    }


    public List<WebElement> getListOFWebElement(By by){
        waitElementsToBeVisable(by);
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }



}
