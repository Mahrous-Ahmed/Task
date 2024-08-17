package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class TodayDealPage extends BasePage{

    String ProductTitle = "";
    String ProductPrice= "";
    int QTY = 0;


    public TodayDealPage(WebDriver driver) throws IOException {
        super(driver);
    }



    @Step("click On Specific Category")
    public void clickOnSpecificCategory(){
        List<WebElement> Categories = webActions.getListOFWebElement(By.xpath("//span[@class = 'a-label a-radio-label']"));
        int categoryIdx = Integer.valueOf(propertyReader.getValue("CategoryIndex"));
        webActions.scrollToElement(Categories.get(categoryIdx));
        String SpecificCategory = Categories.get(categoryIdx).getText();
        webActions.clickOnWebElement(Categories.get(categoryIdx));
        WebElement SelectedCategory =  driver.findElement(By.xpath("//div[@class= 'RefinementsBar-module__label_Dgd8ko5VJP86sfSu1IW0']/following-sibling::button[1]"));
        webActions.scrollToElement(SelectedCategory);
        Assert.assertEquals(SpecificCategory ,SelectedCategory.getText() );
    }
    @Step("click On Specific Product")
    public void clickOnSpecificProduct(){
        List<WebElement> Products =  driver.findElements(By.xpath("//div[@data-a-input-name='brands']"));
        int productIdx =  Integer.valueOf(propertyReader.getValue("ProductIndex"));
        webActions.scrollToElement(Products.get(productIdx));
        String SpecificProduct = Products.get(productIdx).getText();
        webActions.clickOnWebElement(Products.get(productIdx));
        WebElement SelectedCategory =  driver.findElement(By.xpath("//div[@class= 'RefinementsBar-module__label_Dgd8ko5VJP86sfSu1IW0']/following-sibling::button[2]"));
        webActions.scrollToElement(SelectedCategory);
        Assert.assertEquals(SpecificProduct ,SelectedCategory.getText() );
    }


    @Step("click On Specific Item")
    public void clickOnSpecificItem(){
        List<WebElement> Items =  driver.findElements(By.xpath("//a[@data-testid = 'product-card-link']"));
        int productIdx =  Integer.valueOf(propertyReader.getValue("ItemIndex"));
        webActions.scrollToElement(Items.get(productIdx));
        String URL = Items.get(productIdx).getAttribute("href");
        String CurrentURL = driver.getCurrentUrl();
        webActions.clickOnWebElement(Items.get(productIdx));
        webActions.waitElementToChangeTheCurrentURL(CurrentURL);
        Assert.assertTrue(driver.getCurrentUrl().contains(URL));
    }


    @Step("Select Quantity For THe Product")
    public void SelectQuantityForTHeProduct(){
        WebElement DropDown =  webActions.getWebElement(By.id("a-autoid-0-announce"));
        webActions.clickOnWebElement(DropDown);
        List<WebElement> Quantities = webActions.getListOFWebElement(By.xpath("//li[@class = 'a-dropdown-item']"));
        int QuantityIdx = Integer.valueOf(propertyReader.getValue("QTY"));
        QTY = Integer.valueOf(Quantities.get(QuantityIdx).getText());
        webActions.clickOnWebElement(Quantities.get(QuantityIdx));
        Assert.assertTrue(DropDown.getText().contains(""+QTY));

        ProductPrice =  webActions.getWebElement(By.xpath("(//span[contains(@class, 'a-price-whole')])[1]")).getText();
        ProductPrice = ProductPrice + "." + webActions.getWebElement(By.xpath("(//span[contains(@class, 'a-price-fraction')])[1]")).getText();
        ProductTitle = webActions.getWebElement(By.id("productTitle")).getText();
    }


    @Step("Add ITem To Cart ")
    public void AddItemToCart(){
        WebElement addToCartButton =  driver.findElement(By.id("add-to-cart-button"));
        webActions.clickOnWebElement(addToCartButton);
        WebElement GoToBasket = driver.findElement(By.id("sw-gtc"));
        webActions.clickOnWebElement(GoToBasket);
    }


    @Step("Check Shopping Cart")
    public void CheckShoppingCart(){
        WebElement SelectedTitleItem = webActions.getWebElement(By.className("a-truncate-cut"));
        Assert.assertEquals(SelectedTitleItem.getText() , ProductTitle);

        WebElement SelectedPriceItem = webActions.getWebElement(By.className("sc-badge-price-to-pay"));
        Assert.assertEquals(SelectedPriceItem.getText().split(" ")[1] , ProductPrice);

        WebElement DropDown =  webActions.getWebElement(By.id("a-autoid-0-announce"));
        Assert.assertTrue(DropDown.getText().contains(""+QTY));
        WebElement SubTotalLabel = webActions.getWebElement(By.id("sc-subtotal-label-activecart"));
        Assert.assertTrue(SubTotalLabel.getText().contains(""+QTY));

        WebElement SubtotalValue = webActions.getWebElement(By.id("sc-subtotal-amount-activecart"));
        Double Expectedprice = Double.valueOf(ProductPrice) * QTY;
        Assert.assertTrue(SubtotalValue.getText().contains(String.format("%.2f", Expectedprice)));
    }






}
