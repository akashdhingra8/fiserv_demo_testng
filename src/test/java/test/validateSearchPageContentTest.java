package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.GoogleEngine;
import pageObjects.BingEngine;
import pageObjects.locators;
import sourceCode.genericMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class validateSearchPageContentTest extends genericMethods {
    public WebDriver driver;
    public String engine;
    public locators obj;
    public Map<String, locators> whatToDo;
    public static Logger log = LogManager.getLogger(genericMethods.class.getName());


    @BeforeTest
    public void initialize() throws IOException {
        driver = initialiseDriver();
        whatToDo = new HashMap<String, locators>();
        {
            // Populate my map.
            whatToDo.put("google_engine", new GoogleEngine(driver));
            whatToDo.put("bing_engine", new BingEngine(driver));
        }
    }

    @Test(groups = {"Smoke"},dataProvider = "getData",testName = "Search Text on search engine and verify first link text on searched page" )
    public void validate_first_link(String text_to_input,String expected_result) throws Exception {
        String test_case_name = new Object(){}.getClass().getEnclosingMethod().getName();
        log.info("************************************************");
        log.info("Test case 1 started");
        log.info("Test case name --> " + test_case_name);
        engine = get_command_line_env_variable();

        browseURL(driver, get_value_from_property_file("resource",engine), test_case_name,0);
        log.info("url is invoked ");

//        landing_page_obj = new GoogleEngine(driver);
        obj = whatToDo.get(engine);


        clearInput(driver, (By) obj.getClass().getDeclaredField("search_box").get(obj), test_case_name,1);
        log.info("Input box cleared ");

//        String text_to_input = get_value_from_property_file("Test_data",test_case_name+"_search_text");
        inputWithEnterPress(driver, (By) obj.getClass().getDeclaredField("search_box").get(obj), test_case_name, text_to_input,2);
        log.info("text entered");
//
//        searched_page_obj = new BingEngine(driver);
        waitForVisibility(driver, (By) obj.getClass().getDeclaredField("first_link").get(obj), 3);
        log.info("Successfully navigated");

//        String expected_result = get_value_from_property_file("Test_data",test_case_name+"_expected_result");
        String actual_result = getText(driver, (By) obj.getClass().getDeclaredField("first_link").get(obj), test_case_name,4);
        System.out.println("Expected result is "+expected_result);
        System.out.println("Actual result is "+actual_result);
        if (actual_result.equalsIgnoreCase(expected_result))
        {
            System.out.print("True, test case Passed");
        }
        else
        {
            System.out.print("False, test case Failed");
        }
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

        Object[][] data = new Object[2][2];
        //0th row
        data[0][0] = "Python";
        data[0][1] = "Welcome to Python.org";

        data[1][0] = "Selenium";
        data[1][1] = "Selenium";

        return data;
    }

}