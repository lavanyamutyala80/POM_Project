package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import CommonFunctions.AdminLoginPage;
import CommonFunctions.AdminLogoutPage;

public class AppUtils 
{
  public static WebDriver driver;
  public static Properties conpro;
  @BeforeTest
  public static void SetUp()throws Throwable
  {
	  conpro = new Properties();
	  conpro.load(new FileInputStream("./PropertyFiles/EnvironMent.Properties"));
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get(conpro.getProperty("Url"));
	  AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
	  login.adminLogin("admin", "master");
	  
  }
  @AfterTest
  public static void tearDown()
  {
	  AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
	  logout.adminLogout();
	  driver.quit();
	  
  }
  
  
  
}
