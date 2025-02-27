package lms.LibraryManagementSystem.pageobjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import lms.LibraryManagementSystem.AbstractComponent.AbstractClass;

public class TaskPage extends AbstractClass{
	
	WebDriver driver;
	
	public TaskPage(WebDriver driver) {
		super(driver);
		//driver initialisation
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@type='submit']")
	WebElement startTestingButton;


	public void invokeLoginPageFromTaskPageAndConfirm() {
		driver.get("https://applicationforlibrarymanagementsystem.onrender.com/");
		startTestingButton.click();
		String actualURL = driver.getCurrentUrl();
		String exepectedURL = "https://applicationforlibrarymanagementsystem.onrender.com/login";
		System.out.println("Actual URL   : " + actualURL);
		System.out.println("Expected URL : " + exepectedURL);
		Assert.assertEquals(actualURL, exepectedURL);
	}

}
