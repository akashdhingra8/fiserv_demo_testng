package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LandingPage;
import pageObjects.SearchedPage;
import sourceCode.genericMethods;


public class validateSearchPageContentTest extends genericMethods {
    public WebDriver driver;
    public LandingPage landing_page_obj;
    public SearchedPage searched_page_obj;
    public static Logger log = LogManager.getLogger(genericMethods.class.getName());


    @BeforeTest
    public void initialize() {
        driver = initialiseDriver();
    }

    @Test(testName = "Search Text on search engine and verify first link text on searched page")
    public void validate_first_link() throws Exception {
        String test_case_name = new Object(){}.getClass().getEnclosingMethod().getName();
        log.info("************************************************");
        log.info("Test case 1 started");
        log.info("Test case name --> " + test_case_name);
        browseURL(driver, get_value_from_property_file("resource","url"), test_case_name,0);
        log.info("url is invoked ");

        landing_page_obj = new LandingPage(driver);
        clearInput(driver, landing_page_obj.search_box, test_case_name,1);
        log.info("Input box cleared ");

        String text_to_input = get_value_from_property_file("Test_data",test_case_name+"_search_text");
        inputWithEnterPress(driver, landing_page_obj.search_box, test_case_name, text_to_input,2);
        log.info("text entered");

        searched_page_obj = new SearchedPage(driver);
        waitForVisibility(driver, searched_page_obj.first_link, 3);
        log.info("Successfully navigated");

        String expected_result = get_value_from_property_file("Test_data",test_case_name+"_expected_result");
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

}