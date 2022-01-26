package sourceCode;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class genericMethods extends utility {
    public WebDriverWait wait; // wait variable
    public static Logger log =LogManager.getLogger(genericMethods.class.getName());

    // TAKING SCREENSHOT
    public void screenCapture(WebDriver driver, String testCaseName,int step_num)
    {

        try{
            String path = runnerConfiguration.screenshot_dir_path;
            TakesScreenshot ts=(TakesScreenshot) driver;
            File source =ts.getScreenshotAs(OutputType.FILE);
            String destinationFile = path + "/" + testCaseName + "__Step_Number_" + step_num +".png";
            FileUtils.moveFile(source,new File(destinationFile));

        }
        catch (Exception e) {
            log.error(e);
            log.error("error in screenshot");
        }

    }

    // METHOD TO WAIT FOR PAGE LOAD
    public void waitForPageLoad(final WebDriver driver)
    {
        wait = new WebDriverWait(driver, 120);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    // METHOD TO PUT THE URL IN ADDRESS BAR OF BROWSER.
    public void browseURL(WebDriver driver,String url,String image_name,int step_num) throws Exception {
        try {
            driver.get(url);
            waitForPageLoad(driver);
            screenCapture(driver,image_name,step_num);
            log.info("URL is invoked " + url);
        } catch (Exception e) {
            screenCapture(driver,image_name,step_num);
            log.error("Error while opening URL"+url);
            log.error(e);
            throw new Exception("URL incorrect please check ");

        }
    }

    // METHOD FOR WAIT FOR VISIBILITY
    public boolean waitForVisibility(WebDriver driver,By identifier_value, int step_num){
        try {
            wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(identifier_value)));
            log.info("Wait for visibility success");

            return true;
        }
        catch (Exception e) {

            log.error("Wait for visibility not successful");
            log.error(e);
            return false;
        }
    }

    // METHOD FOR WAIT FOR VISIBILITY
    public boolean waitForClickability(WebDriver driver,By identifier_value, int step_num) {
        try {
            wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(identifier_value)));
            log.info("Wait for clickable success");

            return true;
        }
        catch (Exception e) {
            log.error("Wait for clickable not successful");
            log.error(e);
            return false;
        }
    }

    public void click(WebDriver driver,By identifier_value, String image_name, int step_num) throws Exception {
        try {
                Boolean result = waitForClickability(driver,identifier_value,step_num);
                if(result)
                {
                    driver.findElement(identifier_value).click();
                    log.info("Click successful at step number "+step_num);
                    screenCapture(driver, image_name,step_num);
                }
                else
                {
                    log.info("Element not clickable at step number "+step_num );
                    throw new Exception("Element not clickable ");
                }
        }
        catch (Exception e) {
            screenCapture(driver,image_name,step_num);
            log.error("Element not clickable");
            log.error(e);
            throw new Exception("Element not clickable");
        }
    }


    public void inputWithEnterPress(WebDriver driver,By identifier_value, String image_name, String value, int step_num) throws Exception {
        try {
            Boolean result = waitForClickability(driver,identifier_value,step_num);
            if(result)
            {
                WebElement element = driver.findElement(identifier_value);
                element.sendKeys(value,Keys.ENTER);
                log.info("input successful");
                screenCapture(driver, image_name,step_num);
            }
            else
            {
                log.info("Element not clickable");
                throw new Exception("Element not clickable ");
            }
        }
        catch (Exception e) {
            screenCapture(driver,image_name,step_num);
            log.error("not able to input");
            log.error(e);
            throw new Exception("input to Object is not done");
        }
    }
    public void clearInput(WebDriver driver,By identifier_value, String image_name, int step_num) throws Exception {
        try {
            Boolean result = waitForClickability(driver,identifier_value,step_num);
            if(result)
            {
                WebElement element = driver.findElement(identifier_value);
                element.clear();
                log.info("input successfully");
                screenCapture(driver, image_name,step_num);
            }
            else
            {
                log.info("Element not clickable");
                throw new Exception("Element not clickable ");
            }
        }
        catch (Exception e) {
            screenCapture(driver,image_name,step_num);
            log.error("not able to clear");
            log.error(e);
            throw new Exception("Not able to clear Input object");
        }
    }

    public String getText(WebDriver driver,By identifier_value, String image_name, int step_num) {
        try {
            Boolean result = waitForVisibility(driver,identifier_value,step_num);
            if(result)
            {
                WebElement element = driver.findElement(identifier_value);
                String res = element.getText();
                log.info("result is "+res);
                screenCapture(driver, image_name,step_num);
                return res;
            }
            else
            {
                log.info("Element not visible");
                throw new Exception("element not visible ");

            }
        }
        catch (Exception e) {
            screenCapture(driver,image_name,step_num);
            log.error("Not able to fetch text");
            log.error(e);
            return "";
        }
    }



}


