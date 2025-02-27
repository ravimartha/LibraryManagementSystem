package lms.LibraryManagementSystem.pageobjects;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import lms.LibraryManagementSystem.AbstractComponent.AbstractClass;

public class BookListPage extends AbstractClass{

	WebDriver driver;

	public BookListPage(WebDriver driver) {
		super(driver);
		//driver initialisation
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="h2.text-lg.font-bold")
	WebElement welcomeMessage;

	@FindBy(css="button.logout-button")
	WebElement logOutBtn;

	@FindBy(css="button.add-book-button")
	WebElement AddBookBtn;

	@FindBy(css="h3.total-book-title")
	WebElement TotalBooksTitles;

	// optimised this to a method to click on delete button based on Book title
	//	@FindBy(xpath="//table/tbody/tr/td[contains(text(),'Charlotte')]//following-sibling::td/button[1]")
	//	WebElement DeleteBtn;

	@FindBy(css="table.min-w-full tr th")
	List<WebElement> Headers;

	@FindBy(css="table.min-w-full td")
	List<WebElement> TableData;

	@FindBy(css="table.min-w-full tbody tr")
	List<WebElement> TotalRows; //table.min-w-full tr

	@FindBy(xpath="//table/tbody/tr/td[1]")
	List<WebElement> BookTitles;

	//Action Methods for BookListPage

	public void verifyNewBook(String bookTitle) {
		String actualTitle = driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+bookTitle+"']")).getText();
		Assert.assertEquals(actualTitle, bookTitle);
	}


	public void assertLandingPageLoad() {
		//welcome message check
		String actualWelcomeText = welcomeMessage.getText();
		//		System.out.println(actualWelcomeText);
		String expectedWelcomeText = "Welcome, Admin!";
		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);

		//Log Out button check
		String actualLogOutText = logOutBtn.getText();
		//		System.out.println(actualLogOutText);
		String expectedLogOutText = "Log Out";
		Assert.assertEquals(actualLogOutText, expectedLogOutText);

		// Page URL check
		String actualPageURL = driver.getCurrentUrl();
		System.out.println(actualPageURL);
		String expectedPageURL = "https://applicationforlibrarymanagementsystem.onrender.com/books"; 
		System.out.println(expectedPageURL);
		Assert.assertEquals(actualPageURL, expectedPageURL);
	}

	public void clickAddBookBtn() {
		AddBookBtn.click();
	}

	public void getTotalBookCount(String expectedTotalBooksTitles) {
		System.out.println(TotalBooksTitles.getText());
		String ExpectedTotalBooksTitles = expectedTotalBooksTitles;

		String ActualTotalBooksTitles = TotalBooksTitles.getText();
		Assert.assertEquals(ActualTotalBooksTitles, ExpectedTotalBooksTitles);
	}


	public void printBookTitles() {
		for(WebElement el : BookTitles) {
			System.out.println(el.getText());
		}}

	public void deleteBookByTitle(String bookTitle) {
		//table/tbody/tr/td[contains(text(),'Charlotte')]//following-sibling::td/button[1]
		WebElement deleteBtn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'"+bookTitle+"')]//following-sibling::td/button[2]"));
		deleteBtn.click();
	}

	public void verifyBookDeletion(String bookTitle) {
		// row count changed
		boolean deleteflag = true;
		for(WebElement el : BookTitles) {
			if(el.getText().equalsIgnoreCase(bookTitle)) {
				deleteflag=false;
				Assert.assertTrue(deleteflag, "Book successfully deleted");
			}
			else {
				Assert.assertTrue(deleteflag, "Book failed to delete");
			}
		}
	}


	public @Nullable String LogOutFromApplicationAndGetURL() throws InterruptedException {
		Thread.sleep(2000);
		logOutBtn.click();
		return driver.getCurrentUrl();
	}



}