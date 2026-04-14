package DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ThreadLocalDriverFactory {
	
	public static String browser;
	static ExtentTest testnew;
	public static String browsername;
	
	// ThreadLocal for thread-safe driver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    public static void setBrowser(String browser) {
        browserName.set(browser);
    }

    public static String getBrowser() {
        return browserName.get();
    }
    
    
    public static void setDriver(String browser) {
    	
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver.set(new ChromeDriver());
                    browsername ="chrome";
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    browsername ="firefox";
                    break;
                case "edge":
                    driver.set(new EdgeDriver());
                    browsername ="edge";
                    break;
            }
        }
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get().manage().window().maximize();
        
        
    }
    
    public static WebDriver getDriver() {
    	return driver.get();
    }
    

    	    	
    // Quit driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // ✅ VERY IMPORTANT
        }
        if (extent != null) {
            extent.flush();
        }
    }
    
	public static void addScreenShottoReport() throws IOException {
		File sourcefile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshots/Screenshot.png");
		FileHandler.copy(sourcefile, dest);
		String filepath = dest.getAbsolutePath(); 
		extent.createTest("Screen shot attached").fail("Information").addScreenCaptureFromPath(filepath);
	}
	
		
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;

	public synchronized static ExtentReports getExtent() {
	
		if (extent == null) {
			spark = new ExtentSparkReporter("test-output/extent-report/Spark.html");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
			spark.config().setDocumentTitle("Automation Test Report");
			spark.config().setReportName("Selenium Test Results");
			spark.config().setTheme(Theme.STANDARD);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Browser", "Chrome");

		}
		return extent;
	}
	
	public static void setTest(ExtentTest extentTest) {
	    test.set(extentTest);
	}

	public static ExtentTest getTest() {
	    return test.get();
	}
	
	
//	  public static ExtentTest startTest() {
//	         testnew = getExtent().createTest("Test Started on " +  browsername + " browser");
//	         test.set(testnew);
//	        return test.get();
//	    }
	  
	  
	  
	
}
