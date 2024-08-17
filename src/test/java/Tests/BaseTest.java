package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;

public class BaseTest {
    static WebDriver  driver;

    @Parameters("BrowserName")
    @BeforeSuite
    public void setUp(String BrowserName) throws Exception {
        BrowserName = BrowserName.toLowerCase(Locale.ROOT);
        switch(BrowserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new Exception("Invalid Browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.eg/-/en/ref=nav_logo");
    }


    @AfterMethod
    public void takeScreenShotForFailureTestCases(ITestResult iTestResult) throws IOException {
        if(!iTestResult.isSuccess()){
            File screenFailure = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            ImageIO.write(ImageIO.read(screenFailure)  ,"PNG", new File("./Screenshots/" + iTestResult.getName()+".png"));
        }
    }



    @AfterSuite
    public void tearDown(){
        if(driver != null)
           driver.quit();
    }
}
