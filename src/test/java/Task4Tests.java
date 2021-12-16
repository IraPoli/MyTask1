

import model.FilterRozetka;
import model.FiltersRozetka;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CurtPage;
import pages.HomePage;
import pages.ItemPage;
import pages.SearchResultPage;
import utils.PropertiesReader;
import utils.WebDriverSingleton;
import utils.XMLToObject;


import java.util.List;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Task4Tests {

    private static final long DEFAULT_WAITING_TIME = 90;
    private static final long DOUBLE_DEFAULT_WAITING_TIME = 3900;

    private WebDriver driver;

    @DataProvider(name = "data")//, parallel = true)
    public static  Object[] getData(){
        XMLToObject xmlToObject = new XMLToObject();
        FiltersRozetka filtersRozetka = xmlToObject.convert();
        List<FilterRozetka> list = filtersRozetka.getFilters();
        return list.toArray();
    }



    @Test(dataProvider = "data")
    public void task4Test(FilterRozetka filterRozetka) throws InterruptedException {

        getHomePage().enterTextToSearchField(filterRozetka.getItemName());
        getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);
       // getSearchResultPage().selectBrand(filterRozetka.getBrand());
      //  getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        getSearchResultPage().addFirstProduct();
        getSearchResultPage().clickOpenCartButton();
        int sumPrice = getCurtPage().getSumPrice();

        System.out.println(sumPrice);
        Assert.assertTrue(sumPrice  > 500);
        System.out.println(filterRozetka.getItemName());
        System.out.println(filterRozetka.getSumLimit());

    }

    @AfterMethod(alwaysRun = true)
    public void close(){
        WebDriverSingleton.close();
    }



    public HomePage getHomePage(){
        return new HomePage();
    }
    public SearchResultPage getSearchResultPage(){
        return new SearchResultPage();
    }
    public CurtPage getCurtPage(){
        return new CurtPage();
    }
    public ItemPage getItemPage(){
        return new ItemPage();
    }



}

