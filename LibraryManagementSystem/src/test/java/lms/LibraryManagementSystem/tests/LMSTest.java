package lms.LibraryManagementSystem.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lms.LibraryManagementSystem.TestComponents.BaseTest;
import lms.LibraryManagementSystem.pageobjects.AddBookPage;
import lms.LibraryManagementSystem.pageobjects.BookListPage;
import lms.LibraryManagementSystem.pageobjects.EditBookPage;
import lms.LibraryManagementSystem.pageobjects.LoginPage;
import lms.LibraryManagementSystem.pageobjects.TaskPage;

public class LMSTest extends BaseTest{

	//*************************************************************************
	// Test Cases for User login and CRED Operations 
	//*************************************************************************
	
	//---------------------------------------------------------------------------
	// Test Case 1: valid user login in for adding a new book and checking if the book is added to the list
	//---------------------------------------------------------------------------

	@Test	
	public void AddBookAndConfirm()  throws InterruptedException, IOException {
	 	System.out.println("Test Case 1: A valid user login in for adding a new book and checking if the book is added to the list");
//		initializedriver();
		LoginPage logingpage = launchApplication(); // login into application valid user and retruns loginpage object
		BookListPage bookListPage = new BookListPage(driver);
		bookListPage.assertLandingPageLoad(); // checks if header with welcome message and logout buttons are loaded
		AddBookPage addBookPage = new AddBookPage(driver);
		
		bookListPage.getTotalBookCount("Total Book Titles: 3");
		String bookTitle = "The Silent Patient";
		bookListPage.clickAddBookBtn();
		addBookPage.addNewBook(bookTitle, "Alex Michaelides", "Mystery", "9999999999001", "24/02/2025", "8.55");
		bookListPage.verifyNewBook(bookTitle); // to check if the new book added is displayed - bookTitle
		bookListPage.getTotalBookCount("Total Book Titles: 4");
		
//		Thread.sleep(5000);
//		driver.close();
	}
	
	//---------------------------------------------------------------------------
	// Test Case 2: Valid User adding a new book with blank details and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	
	@Test	
	public void AddBookWithInvalidDataAndConfirm()  throws InterruptedException, IOException {
	 	System.out.println("Test Case 2: Valid User adding a new book with blank details and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
//		bookListPage.assertLandingPageLoad();
		AddBookPage addBookPage = new AddBookPage(driver);
		
		bookListPage.clickAddBookBtn();
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertaddBookBlankSubmissionErrorMessages();
//		Thread.sleep(5000);
//		driver.close();
	}
	

	//---------------------------------------------------------------------------
	// Test Case 3: Valid User adding a new book with blank title and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------

	@Test	
	public void AddBookWithBlankTitleAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 3: Valid User adding a new book with blank title and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
//		bookListPage.assertLandingPageLoad();
		AddBookPage addBookPage = new AddBookPage(driver);
		
		bookListPage.clickAddBookBtn();
		
		addBookPage.addNewBook("", "Alex Michaelides", "Mystery", "9999999999001", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookTitleErrorMessages();
		
//		Thread.sleep(5000);
//		driver.close();
	}

	//---------------------------------------------------------------------------
	// Test Case 4: Valid User edits a book with valid data and checking if the changes are reflected
	//---------------------------------------------------------------------------

	@Test	
	public void editBookWithValidateAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 4: Valid User edits a book with valid data and checking if the changes are reflected");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		EditBookPage editBookPage = new EditBookPage(driver);
		
		String editbookTitle = "The Very Busy Spider";
//		bookListPage.printBookTitles();
		editBookPage.EditBookByTitle(editbookTitle);
		bookListPage.verifyNewBook(editbookTitle+"_updated");
		
//		Thread.sleep(5000);
//		driver.close();
	}

	//---------------------------------------------------------------------------
	// Test Case 5: Valid User edits a book with blank title and check if the appropriate error messages are displayed
	//---------------------------------------------------------------------------

	@Test	
	public void editBookWithBlankTitleAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 5: Valid User edits a book with blank title and check if the appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		EditBookPage editBookPage = new EditBookPage(driver);
		
		String editbookTitleToBlank = "The Cat in the Hat";
		String beforePageURL = editBookPage.clickOnEditBookBtnByTitle(editbookTitleToBlank);
//		String beforePageTitle = editBookPage.getPageTitle();
		System.out.println("beforePageURL "+ beforePageURL); 
		
		String afterPageURL = editBookPage.editBookWithBlankTitle();
		System.out.println("afterPageURL "+afterPageURL); 
		
//		String afterPageTitle = editBookPage.getPageTitle();
//		System.out.println("The title2 is "+afterPageTitle); 
		
		// to ensure that it has not redirected to next page as there is an error message displayed
		// Unable to find the locator for the alert/message "Please fill in this field" for validation, but tried below workaround
		// tried finding the locator by pausing the debugger selectors hub, but alert disappears as soon as debugger is paused
		Assert.assertEquals(beforePageURL, afterPageURL); 
		
//		Thread.sleep(5000);
//		driver.close();
	}
	
	//---------------------------------------------------------------------------
	// Test Case 6: Valid user login deleting a book and check if book is not displayed post deletion
	//---------------------------------------------------------------------------

	@Test	
	public void DeleteBookAndConfirm()  throws InterruptedException, IOException {
	 	System.out.println("Test Case 6: Valid user login deleting a book and check if book is not displayed post deletion");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		
		bookListPage.getTotalBookCount("Total Book Titles: 3");
		String deleteBookName = "The Very Busy Spider";
		Thread.sleep(1000);
		bookListPage.deleteBookByTitle(deleteBookName);
		Thread.sleep(1000);
		bookListPage.verifyBookDeletion(deleteBookName);
		bookListPage.getTotalBookCount("Total Book Titles: 2");
//		Thread.sleep(1000);
//		driver.close();
	}
	
	//---------------------------------------------------------------------------
	// Login Page Test Cases to check if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	// Test Case 7: Invalid user should not login to application and should display appropriate error messages
	//---------------------------------------------------------------------------

	@Test	
	public void InvalidUserLoginFailErrorMessageValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 7:Invalid user should not login to application and should display appropriate error messages");
	 	// 6. ******************* Delete a book and check if its deleted *******************
		driver = initializedriver();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginInToApplicationByInvalidUser();
		loginpage.assertLoginFailForInvalidUserErrorMessages();
//		Thread.sleep(1000);
//		driver.close();
	}

	//---------------------------------------------------------------------------
	// Test Case 8: Invalid User login with blank details and checking for appropriate error messages
	//---------------------------------------------------------------------------
	
	@Test	
	public void LoginWithBlankSubmissionErrorMessageValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 8:Invalid user login with blank details and checking for appropriate error messages");
	 	driver = initializedriver();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginInToApplicationWithBlankSubmission();
		loginpage.assertLoginFailErrorMessagesForBlankSubmission();
	
//		Thread.sleep(1000);
//		driver.close();
	}
	
	//---------------------------------------------------------------------------
	// Test Case 9: When LogOut Button is clicked it should redirect to login page
	//---------------------------------------------------------------------------
	
	// This test case is failing as Application is not redirecting to login page - Please find the details in in ReadMe.docx document
	@Test	
	public void LogOutPageRedirectionValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 9: When LogOut Button is clicked it should redirect to login page");
		LoginPage logingpage = launchApplication();
		String ActualLoginPageURL = "https://applicationforlibrarymanagementsystem.onrender.com/login";
		BookListPage bookListPage = new BookListPage(driver);
		String ExepectedLoginPageURL = bookListPage.LogOutFromApplicationAndGetURL();
		System.out.println("Acutal Page URL is " + ActualLoginPageURL);
		System.out.println("Expected page URL is " + ExepectedLoginPageURL);
		Assert.assertEquals(ActualLoginPageURL, ExepectedLoginPageURL);
	
//		Thread.sleep(3000);
//		driver.quit();
	}

	//*************************************************************************
	// Page URL/Titles validations
	//*************************************************************************
	//---------------------------------------------------------------------------
	// Test Case 10: BookListPage and AddBookPage URL validations 
	//---------------------------------------------------------------------------
	
	@Test	
	public void AddBookPageURLCheck()  throws InterruptedException, IOException {
		System.out.println("Test Case 10: BookListPage and AddBookPage URL validations");
		LoginPage logingpage = launchApplication(); // login into application valid user and retruns loginpage object
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);
		bookListPage.assertLandingPageLoad(); // checks if header with welcome message and logout buttons are loaded
		bookListPage.clickAddBookBtn();
		addBookPage.assertAddBookPageURL();
//		driver.close();
	}
	
	//---------------------------------------------------------------------------
	// Test Case 11: EditBookPage Title validation 
	//---------------------------------------------------------------------------

	@Test	
	public void EditBookPageURLCheck()  throws InterruptedException, IOException {
		System.out.println("Test Case 11: EditBookPage Title validation");
		LoginPage logingpage = launchApplication(); // login into application valid user and retruns loginpage object
		EditBookPage editBookPage = new EditBookPage(driver);
		editBookPage.clickOnEditBookBtnByTitle("The Very Busy Spider");
		editBookPage.assertEditBookPageURL();
//		driver.close();
	}
	
	
	//---------------------------------------------------------------------------
	// Test Case 12: Invoking Login Screen from Task Page 
	//---------------------------------------------------------------------------

	@Test	
	public void InvokeLoginPageFromTaskPageLink()  throws InterruptedException, IOException {
		System.out.println("Test Case 12: Invoking Login Screen from Task Page ");
//		LoginPage logingpage = launchApplication(); // login into application valid user and retruns loginpage object
		initializedriver();
		TaskPage taskpage = new TaskPage(driver);
		taskpage.invokeLoginPageFromTaskPageAndConfirm();
		Thread.sleep(2000);
//		driver.close();
	}

}



