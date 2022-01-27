package sourceCode;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
//    System.out.println(runnerConfiguration.reports_dir);
    ExtentTest test;
    String date = runnerConfiguration.set_get_date();
    String time = runnerConfiguration.set_get_time();
    String path = runnerConfiguration.create_and_set_reports_directory();

    ExtentReports extent=extentReporterNG.getReportObject(path,time);
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        String test_case_name = result.getMethod().getMethodName();
        runnerConfiguration.create_and_set_screenshot_directory(test_case_name);
        test= extent.createTest(test_case_name);
        extentTest.set(test);

    }

    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        //Screenshot
        extentTest.get().fail(result.getThrowable());
        WebDriver driver =null;
        String testMethodName =result.getMethod().getMethodName();

        try {
            driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extent.flush();
    }


}
