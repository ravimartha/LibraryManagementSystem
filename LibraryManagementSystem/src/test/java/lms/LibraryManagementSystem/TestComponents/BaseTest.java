package lms.LibraryManagementSystem.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import lms.LibraryManagementSystem.pageobjects.LoginPage;


public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\lms\\LibraryManagementSystem\\Resources\\GlobalData.properties");
		//		\src\main\java\lms\LibraryManagementSystem\Resources\GlobalData.properties
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); // driver initialization
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(); // driver initialization
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(); // driver initialization	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	} 

	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		LoginPage logingpage = new LoginPage(driver);
		logingpage.loginInToApplication();
		return logingpage;
	}

	public WebDriver initializedriver() throws IOException {
		return driver = initializeDriver();
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
