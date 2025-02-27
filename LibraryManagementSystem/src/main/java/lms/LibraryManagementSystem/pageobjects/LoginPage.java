package lms.LibraryManagementSystem.pageobjects;

import org.jspecify.annotations.Nullable;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import lms.LibraryManagementSystem.AbstractComponent.AbstractClass;

public class LoginPage  extends AbstractClass{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		//driver initialisation
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="username")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement logInBtn;

	
	// Banner Login error messages locators
	@FindBy(css="h3.font-bold")
	WebElement loginErrorHeader;
	
	@FindBy(xpath="//li[normalize-space()='Invalid username or password. Please try again.']")
	WebElement loginBannerErrMsg;
	
	@FindBy(xpath="//li[normalize-space()='Please enter your username']")
	WebElement userNameBannerErrMsg;
	
	@FindBy(xpath="//li[normalize-space()='Please enter your password']")
	WebElement passwordBannerErrMsg;
	
	// field Login error messages locators
	@FindBy(id="username-error")
	WebElement userNameError;
	
	@FindBy(id="password-error")
	WebElement passwordError;
	
	
	
	
	

//	WebElement startTestingButton = driver.findElement(By.xpath("//button[@type='submit']"));

	
	
	public void loginInToApplication() {
		driver.get("https://applicationforlibrarymanagementsystem.onrender.com/login");
		userName.sendKeys("admin1");
		password.sendKeys("securePassword");
		logInBtn.click();
		driver.getTitle();
	}
	
	public @Nullable String getLoginPageTitle() {
		driver.get("https://applicationforlibrarymanagementsystem.onrender.com/login");
		return driver.getTitle();
	}

	
	public void loginInToApplicationByInvalidUser() {
		driver.get("https://applicationforlibrarymanagementsystem.onrender.com/login");
		userName.sendKeys("user1");
		password.sendKeys("password1");
		logInBtn.click();
	}
	
	public void loginInToApplicationWithBlankSubmission() {
		driver.get("https://applicationforlibrarymanagementsystem.onrender.com/login");
		logInBtn.click();
	}
	
	public void assertLoginFailForInvalidUserErrorMessages() {
		System.out.println("Checking Login Banner error messages for Invalid user credentials");
		
		String LoginErrorHeader = loginErrorHeader.getText();
		Assert.assertEquals(LoginErrorHeader, "There is a problem with your submission");
		
		String LoginBannerErrMsg = loginBannerErrMsg.getText();
		Assert.assertEquals(LoginBannerErrMsg, "Invalid username or password. Please try again.");
	}
	
	public void assertLoginFailErrorMessagesForBlankSubmission() {
		System.out.println("Checking Login Banner error messages for Invalid user credentials");
		
		String LoginErrorHeader = loginErrorHeader.getText();
		Assert.assertEquals(LoginErrorHeader, "There is a problem with your submission");
		
		//assert for username and password banner error messages
		String UserNameBannerErrMsg = userNameBannerErrMsg.getText();
		Assert.assertEquals(UserNameBannerErrMsg, "Please enter your username");
		String PasswordBannerErrMsg = passwordBannerErrMsg.getText();
		Assert.assertEquals(PasswordBannerErrMsg, "Please enter your password");
		
		//assert for username and password field error messages
		String UserNameError = userNameError.getText();
		Assert.assertEquals(UserNameError, "Please enter your username");
		System.out.println(userNameError.getText());
		String PasswordError = passwordError.getText();
		Assert.assertEquals(PasswordError, "Please enter your password");
		System.out.println(passwordError.getText());
		
	}
	
	// method to create valid credentials
	// method to create invalid credentials

}

// error validation for incorrect login details


// error validation for Blank login submission
//driver.findElement(By.id("login-button")).click();
//
//driver.findElement(By.id("username")).sendKeys("username1");
//driver.findElement(By.id("password")).sendKeys("password1");
