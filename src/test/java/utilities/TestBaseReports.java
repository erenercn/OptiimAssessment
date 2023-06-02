package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBaseReports {


    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        extentReports = new ExtentReports();
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/target/Report/report" + date + ".html";

        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);

        extentReports.setSystemInfo("Environment", "Test");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Automation Engineer", "Eren");
        extentHtmlReporter.config().setDocumentTitle("Report");
        extentHtmlReporter.config().setReportName("TestNG Reports");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestContext context) throws IOException {
        for (ITestResult result : context.getFailedTests().getAllResults()) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotLocation = ReusableMethods.getScreenshot(result.getName());
                extentTest.fail(result.getName());
                extentTest.addScreenCaptureFromPath(screenshotLocation);
                extentTest.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test Case is skipped: " + result.getName());
            }
        }
    }


    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        extentReports.flush();
    }
}
