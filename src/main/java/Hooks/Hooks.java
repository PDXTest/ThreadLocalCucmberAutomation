package Hooks;

import java.io.IOException;
import java.security.PublicKey;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import DriverFactory.ThreadLocalDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	

	@Before
	public void setup(Scenario scenario) {
	   // ThreadLocalDriverFactory.setDriver("chrome"); // ✅ per scenario
	    String browser = ThreadLocalDriverFactory.getBrowser();
	    ThreadLocalDriverFactory.setDriver(browser); // ✅ dynamic browser
	    
	    ExtentTest extentTest = ThreadLocalDriverFactory.getExtent().createTest(scenario.getName());
	    ThreadLocalDriverFactory.setTest(extentTest);
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {

	    if (scenario.isFailed()) {
	        ThreadLocalDriverFactory.addScreenShottoReport(); // ✅ before quit
	    }

	    ThreadLocalDriverFactory.quitDriver(); // ✅ after everything
	}
    			
    
	
}     
