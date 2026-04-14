package TestRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import DriverFactory.ThreadLocalDriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= "src/test/resources/features/Login.feature",
		glue={"stepDefinitions","Hooks"},
		monochrome=true,
		plugin = { "pretty", "html:target/cucumber-reports.html"},
		publish=true
		)


public class Runner extends AbstractTestNGCucumberTests {
	
	@BeforeTest
	@Parameters({"browser"})
	public void setup(String browser) {
	    ThreadLocalDriverFactory.setBrowser(browser); // ✅ only set browser
	}
	
//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] scenarios() {
//	    return super.scenarios();
//	}
	 // static thread-safe container to keep the browser value
   // public final static ThreadLocal<String> BROWSER = new ThreadLocal<>();
	
//	@BeforeTest
//    @Parameters({"browser"})
//    public void setup(String browser) {
//		ThreadLocalDriverFactory.setDriver(browser);
//       // DriverManager.setBrowserType(browser); // A method in your DriverManager to set the browser type
//    }
	
	
//	   @Override
//	    @DataProvider(parallel = true)
//	    public Object[][] scenarios() {
//	        return super.scenarios();
//	    }
	
	

//    @BeforeTest
//	@Parameters("browser")
//	public void setBrowser(String browser) {
//	    System.setProperty("browser", browser);
//	}
//	



	
	//to run the cucumber scenrios in parallel
	
//	@Override
//    @DataProvider(parallel = true) // Crucial for parallel execution
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

}