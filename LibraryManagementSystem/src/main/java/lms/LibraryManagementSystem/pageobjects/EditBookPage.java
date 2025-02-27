package lms.LibraryManagementSystem.pageobjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import lms.LibraryManagementSystem.AbstractComponent.AbstractClass;

public class EditBookPage extends AbstractClass{

	WebDriver driver;

	public EditBookPage(WebDriver driver) {
		super(driver);
		//driver initialisation
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//locators for edit book page
	@FindBy(id="save-changes")
	WebElement SaveChangesBtn;
	
	@FindBy(id="edit-title")
	WebElement EditTitle;
	
	//EditBook Page action methods
	
	// to validate EditBookpage URL on clicking 'Add Book' button from BookList Page
	public void assertEditBookPageURL() {
		System.out.println("Checking Page URL after clicking on Edit button");
		// Page URL check
		String actualPageURL = driver.getCurrentUrl();
		System.out.println(actualPageURL);
		String expectedPageURL = "https://applicationforlibrarymanagementsystem.onrender.com/edit-book"; 
//		Assert.assertEquals(actualPageURL, expectedPageURL);
		Assert.assertTrue(actualPageURL.contains(expectedPageURL), "Edit URL is incorrect");
	}
	

	public void EditBookByTitle(String bookTitle) {
		WebElement editBtn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'"+bookTitle+"')]//following-sibling::td/button[1]"));
		editBtn.click();
		
		EditTitle.clear();
		EditTitle.sendKeys(bookTitle+"_updated");
		// other fields can be updated in the same fashion as above step
		
		SaveChangesBtn.click();
	}
	
//	public void clickOnEditBookBtnByTitle(String bookTitle) {
//		WebElement editBtn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'"+bookTitle+"')]//following-sibling::td/button[1]"));
//		editBtn.click();
//	}
	
	public @Nullable String clickOnEditBookBtnByTitle(String bookTitle) {
		WebElement editBtn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'"+bookTitle+"')]//following-sibling::td/button[1]"));
		editBtn.click();
		return driver.getCurrentUrl();
	}
	
	public @Nullable String getPageTitle() {
		return driver.getTitle();
	}
	
//	public void editBookWithBlankTitle() {
//		EditTitle.clear();
//		EditTitle.sendKeys("");
//		// other fields can be updated in the same fashion as above step
//		
//		SaveChangesBtn.click();
//	}
	
	public @Nullable String editBookWithBlankTitle() {
		EditTitle.clear();
		EditTitle.sendKeys("");
		// other fields can be updated in the same fashion as above step
		
		SaveChangesBtn.click();
		return driver.getCurrentUrl();
	}



};