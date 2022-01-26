package sourceCode;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporterNG {
    static ExtentReports extent;

    public static ExtentReports getReportObject(String path,String time)
    {
        String filename = path + "/" + "Automation_" + time +".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(filename);
        reporter.config().setReportName("Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Akash Dhingra");
        extent.setSystemInfo("OS", "Windows");

        return extent;

    }
}

