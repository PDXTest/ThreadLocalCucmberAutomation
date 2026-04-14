package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import DFpages.LoginDFPage;
import DriverFactory.ThreadLocalDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {

	//ExtentTest test = ThreadLocalDriverFactory.startTest();
	private LoginDFPage loginpage = new LoginDFPage();

	public ExtentTest test() {
	    return ThreadLocalDriverFactory.getTest();
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {

		loginpage.launchURL();
		test().info("Launching application on Browser : " + ThreadLocalDriverFactory.getBrowser());
		test().info("Entering username");
		test().log(Status.PASS, "Test Passed");
		test().log(Status.PASS, "Test Case PASSED"); 

	}

    @When("User enter username as {string} and password as {string} and click on login button")
	public void user_enter_username_as_and_password_as_and_click_on_login_button(String username, String password) {

		loginpage.login(username, password);
		ThreadLocalDriverFactory.getTest().log(Status.PASS, "First User entered uID and PWD");

	}

	@Then("User navigate to the dashboard")
	public void user_navigate_to_the_dashboard() throws IOException {
		String Actualname = loginpage.verfyDashboard();

		try {

			Assert.assertEquals(Actualname, "Dashboard");
			ThreadLocalDriverFactory.getTest().log(Status.PASS, "First User is on Dashboard");

		}

		catch(AssertionError e)
		{

			ThreadLocalDriverFactory.getTest().log(Status.FAIL, "DashBoard Name not matching");
			throw e;

		}

	}

}
