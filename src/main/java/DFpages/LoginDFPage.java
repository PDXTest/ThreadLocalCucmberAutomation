package DFpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.ThreadLocalDriverFactory;


public class LoginDFPage {

	//private static WebDriver driver;

	public WebDriver driver() {
	    return ThreadLocalDriverFactory.getDriver();
	}
	
	private By username = By.name("username");
	private By password = By.name("password");
	private By LoginButton = By.xpath("//button[@type='submit']");
	private By Dashboardname = By.cssSelector("h6[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");


    public void launchURL() {
    	driver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login(String uid, String pwd) {
    	driver().findElement(username).sendKeys(uid);
    	driver().findElement(password).sendKeys(pwd);
    	driver().findElement(LoginButton).click();
    }

    public String verfyDashboard() {
        return driver().findElement(Dashboardname).getText();
    }

}
