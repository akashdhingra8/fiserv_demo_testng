package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LandingPage;
import pageObjects.SearchedPage;
import sourceCode.genericMethods;
import sourceCode.runnerConfiguration;


public class validateSearchPageContentTest extends genericMethods {
    public WebDriver driver;
    public LandingPage landing_page_obj;
    public SearchedPage searched_page_obj;
    public static Logger log = LogManager.getLogger(genericMethods.class.getName());


    @BeforeTest
    public void initialize() {
        driver = initialiseDriver();
    }

    @Test(dataProvider = "getData", testName = "Search Text on search engine and verify first link text on searched page")
    public void validate_first_link(String text_to_input, String expected_result) throws Exception {
        String test_case_name = new Object(){}.getClass().getEnclosingMethod().getName();
        log.info("************************************************");
        log.info("Test case 1 started");
        log.info("Test case name --> " + test_case_name);
        browseURL(driver, get_value_from_property_file("url"), test_case_name,0);
        log.info("url is invoked ");

        landing_page_obj = new LandingPage(driver);
        clearInput(driver, landing_page_obj.search_box, test_case_name,1);
        log.info("Input box cleared ");

        inputWithEnterPress(driver, landing_page_obj.search_box, test_case_name, text_to_input,2);
        log.info("text entered");

        searched_page_obj = new SearchedPage(driver);
        waitForVisibility(driver, searched_page_obj.first_link, 3);
        log.info("Successfully navigated");

        String actual_result = getText(driver, searched_page_obj.first_link, test_case_name,4);
        Assert.assertEquals(actual_result, expected_result);


        log.info("Test case 1 ended");
        log.info("Test case name --> " + test_case_name);
        log.info("************************************************");

    }


    @AfterTest
    public void teardown() {
        TearDownBrowser();
    }

    @DataProvider
    public Object[][] getData() {
        // Row stands for how many data types test should run
        //column stands for how many values per each test

        Object[][] data = new Object[1][2];
        //0th row
        data[0][0] = "Python";
        data[0][1] = "Welcome to Python.org";

        return data;

    }
}