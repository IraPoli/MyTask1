package test;

import model.FilterRozetka;
import model.FiltersRozetka;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CurtPage;
import pages.HomePage;
import pages.ItemPage;
import pages.SearchResultPage;
import utils.WebDriverSingleton;
import utils.XMLToObject;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;


import java.util.List;

public class Parallel {

    private static final long DEFAULT_WAITING_TIME = 90;
    private static final Logger logger = LogManager.getLogger(Parallel.class);//Logger.getLogger(PropertiesReader.


    @DataProvider(name = "data", parallel = true)
    public static Object[] getData() {
        XMLToObject xmlToObject = new XMLToObject();
        FiltersRozetka filtersRozetka = xmlToObject.convert();
        List<FilterRozetka> list = filtersRozetka.getFilters();
        return list.toArray();
    }


    @Test(dataProvider = "data")
    public void parallel(FilterRozetka filterRozetka) throws Exception {
        int testId = filterRozetka.getId();
        String brand = filterRozetka.getBrand();
        logger.info("Start test parallel " + testId);

        getHomePage().enterTextToSearchField(filterRozetka.getItemName());
        getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        logger.info("Test-" + testId + " Enter search " + filterRozetka.getItemName());

        getSearchResultPage().refresh();
        getSearchResultPage().selectBrand(brand);
        logger.info("Test-" + testId + " Select brand " + brand);
        getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);

        getSearchResultPage().refresh();
        getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        getSearchResultPage().clickBuyButtonFirst();
        logger.info("Test-" + testId + " Select first product to Curt");

        getSearchResultPage().waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        getSearchResultPage().clickOpenCartButton();
        logger.info("Test-" + testId + " Open Curt page");
        int sumPrice = getCurtPage().getSumPrice();


        getCurtPage().takeSnapShot("./screenshots/testParallel_" + testId + ".png");
        Assert.assertTrue(sumPrice > filterRozetka.getSumLimit());
        logger.info("Test-" + testId + "Assert price " + sumPrice + " > " + filterRozetka.getSumLimit());


    }


    public HomePage getHomePage() {
        return new HomePage();
    }

    public SearchResultPage getSearchResultPage() {
        return new SearchResultPage();
    }

    public CurtPage getCurtPage() {
        return new CurtPage();
    }

    public ItemPage getItemPage() {
        return new ItemPage();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        WebDriverSingleton.close();
    }


}
                                                                                                                  
                                                                                                                  