package generic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pom.ActitimeHomePage;
import pom.ActitimeLoginPage;

public class BaseTest implements AutoConstant
{
	public WebDriver driver;
	
	@BeforeSuite
	public void executionstart()
	{
		Reporter.log("execution started" , true);
	}
	
	@BeforeClass
	public void setup()
	{
		/*
		 * ChromeOptions options=new ChromeOptions();
		 */		//options.addArguments("headless");
		System.setProperty(silent_output, "true");
		System.setProperty(chromelog_key, logfile_path);
		System.setProperty(chrome_key, chrome_value);
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
	}
	
	@BeforeMethod
	public void logintoApp() throws InterruptedException, IOException
	{
		ActitimeLoginPage loginPage=new ActitimeLoginPage(driver);
		loginPage.loginMethod();
	}
	
	@AfterMethod
	public void logoutfromApp()
	{
		ActitimeHomePage homePage=new ActitimeHomePage(driver);
		homePage.logoutMethod();
	}
	
	@AfterClass
	public void teardown()
	{
		//driver.quit();
	}
	
	@AfterSuite
	public void executioncomplete()
	{
		Reporter.log("execution completed" , true);
	}
}