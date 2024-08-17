package Pages;

import CommanUsedFunctions.PropertyReader;
import CommanUsedFunctions.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class BasePage {
    WebDriver driver;
    WebActions webActions;
    PropertyReader propertyReader;

    public BasePage(WebDriver driver) throws IOException {
        this.driver = driver;
        webActions = new WebActions(driver);
        propertyReader = new PropertyReader("./src/test/resources/PropertieFiles/PropertyFile1.Properties");

    }

}
