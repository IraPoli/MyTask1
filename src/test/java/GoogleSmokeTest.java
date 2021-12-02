import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleSmokeTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrom.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test(priority = 1)
    public void googleImgTest() throws Exception {
        driver.findElement(By.xpath("//input[@class = 'gLFyf gsfi']")).sendKeys("carpathian mountains");
        driver.findElement(By.xpath("//input[@class = 'gLFyf gsfi']")).submit();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@ data-hveid = 'CAEQAw']")).click();

        List<WebElement> elementList = driver.findElements(By.xpath("//img[contains(@alt,'Carpathian')]"));

        Assert.assertTrue(elementList.size() > 1);
        takeSnapShotWebElement(elementList.get(2), "elem2");
    }

    public static void takeSnapShotWebElement(WebElement element, String fileName) throws Exception {
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./screenshots/" + fileName + ".png");
        FileUtils.copyFile(srcFile, destFile);
    }


    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile, destFile);
    }


    @AfterMethod
    public void closeDriver() throws Exception {
        takeSnapShot(driver, "./screenshots/test1.png");
        driver.close();
    }
}
