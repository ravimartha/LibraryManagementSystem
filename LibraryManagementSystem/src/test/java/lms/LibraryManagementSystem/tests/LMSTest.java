package lms.LibraryManagementSystem.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

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

	// Test cases for adding a book (CREATE) and checking (READ) if appropriate error messages are displayed
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

		bookListPage.assertTotalBookCount("Total Book Titles: 3");
		String bookTitle = "The Silent Patient";
		bookListPage.clickAddBookBtn();
		addBookPage.addNewBook(bookTitle, "Alex Michaelides", "Mystery", "9999999999001", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		bookListPage.verifyNewBook(bookTitle); // to check if the new book added is displayed - bookTitle
		bookListPage.assertTotalBookCount("Total Book Titles: 4");
	}

	//---------------------------------------------------------------------------
	// Test Case 2: Valid User adding a new book with blank details and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------

	@Test	
	public void AddBookWithInvalidDataOrBlankSubmissionAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 2: Valid User adding a new book with blank details and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		//		bookListPage.assertLandingPageLoad();
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertaddBookBlankSubmissionErrorMessages();
	}

	//---------------------------------------------------------------------------
	// Test Case 3: Valid User adding a new book with blank title and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------

	@Test	
	public void AddBookWithBlankTitleAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 3: Valid User adding a new book with blank title and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBook("", "Alex Michaelides", "Mystery", "9999999999001", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookTitleErrorMessages();// checking Title Banner and field error messages
	}

	//---------------------------------------------------------------------------
	// Test Case 4: Valid User adding a new book with blank Author and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithBlankAuthorAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 4: Valid User adding a new book with blank Author and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBook("The Silent Patient", "", "Mystery", "9999999999001", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookAuthorErrorMessages();// checking Author Banner and field error messages
	}

	//---------------------------------------------------------------------------
	// Test Case 5: Valid User adding a new book with blank Genre and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithBlankGenreAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 5: Valid User adding a new book with blank Genre and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBookWithoutGenreSelection("The Silent Patient", "Alex Michaelides", "Invalid Genre", "9999999999001", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookGenreErrorMessages();// checking Genre Banner and field error messages
	}


	//---------------------------------------------------------------------------
	// Test Case 6: Valid User adding a new book with blank ISBN and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithBlankISBNAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 6: Valid User adding a new book with blank ISBN and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBook("The Silent Patient", "Alex Michaelides", "Mystery", "", "24/02/2025", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookIsbnErrorMessages();// checking ISBN Banner and field error messages
	}

	//---------------------------------------------------------------------------
	// Test Case 7: Valid User adding a new book with blank Date and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithBlankDateAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 7: Valid User adding a new book with blank Date and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBook("The Silent Patient", "Alex Michaelides", "Mystery", "9999999999001", "", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookDateErrorMessages();// checking Publish Date Banner and field error messages
	}

	//---------------------------------------------------------------------------
	// Test Case 8: Valid User adding a new book with Invalid Date and checking if the page is accepting the information
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithInvalidDateAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 8: Valid User adding a new book with Invalid Date (text) and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();
		String beforeURL = driver.getCurrentUrl();
		System.out.println("Before URL " +beforeURL);
		addBookPage.addNewBook("The Silent Patient", "Alex Michaelides", "Mystery", "9999999999001", "TextDate", "8.55");
		addBookPage.clicksubmitBtnForAddingNewBook();
		String afterURL = driver.getCurrentUrl();
		System.out.println("After URL " +afterURL);
		//the page should display an error and stay on the same page as Invalid Date is passed
		// This test is failing as page is accepting invalid date and adding details to grid. Added it to bug list (Bug 2)
		Assert.assertEquals(afterURL,beforeURL); 
	}


	//---------------------------------------------------------------------------
	// Test Case 9: Valid User adding a new book with blank Price and checking if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	@Test	
	public void AddBookWithBlankPriceAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 9: Valid User adding a new book with blank Price and checking if appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);

		bookListPage.clickAddBookBtn();

		addBookPage.addNewBook("The Silent Patient", "Alex Michaelides", "Mystery", "9999999999001", "24/02/2025", "");
		addBookPage.clicksubmitBtnForAddingNewBook();
		addBookPage.assertAddBookHeaderErrMsg();
		addBookPage.assertaddBookPriceErrorMessages(); // checking Price Banner and field error messages
	}

	// Test cases for editing a book (UPDATE Operation)

	//---------------------------------------------------------------------------
	// Test Case 10: Valid User edits a book with valid data and checking if the changes are reflected
	//---------------------------------------------------------------------------

	@Test	
	public void editBookWithValidateAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 10: Valid User edits a book with valid data and checking if the changes are reflected");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);
		EditBookPage editBookPage = new EditBookPage(driver);

		String editbookTitle = "The Very Busy Spider";
		//		bookListPage.printBookTitles();
		editBookPage.EditBookByTitle(editbookTitle);
		bookListPage.verifyNewBook(editbookTitle+"_updated");
	}

	//---------------------------------------------------------------------------
	// Test Case 11: Valid User edits a book with blank title and check if the appropriate error messages are displayed
	//---------------------------------------------------------------------------

	@Test	
	public void editBookWithBlankTitleAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 11: Valid User edits a book with blank title and check if the appropriate error messages are displayed");
		LoginPage logingpage = launchApplication();
		EditBookPage editBookPage = new EditBookPage(driver);

		String editbookTitleToBlank = "The Cat in the Hat";
		String beforePageURL = editBookPage.clickOnEditBookBtnByTitle(editbookTitleToBlank);
		System.out.println("beforePageURL "+ beforePageURL); 

		String afterPageURL = editBookPage.editBookWithBlankTitle();// submitting edit book with blank title
		System.out.println("afterPageURL "+afterPageURL); 

		// to ensure that it has not redirected to next page as there is an error message displayed
		// Unable to find the locator for the alert/message "Please fill in this field" for validation, but tried below workaround
		// tried finding the locator by pausing the debugger selectors hub, but alert disappears as soon as debugger is paused
		// Work around assertion, if the page is accepting blank title then below assertion would fail.
		Assert.assertEquals(beforePageURL, afterPageURL); 
	}
	// Validations can be done for error messages of other fields when submitted blank


	// Test cases for deletion of books (DELETE Operation)

	//---------------------------------------------------------------------------
	// Test Case 12: Valid user login deleting a book and check if book is not displayed post deletion
	//---------------------------------------------------------------------------

	@Test	
	public void DeleteBookAndConfirm()  throws InterruptedException, IOException {
		System.out.println("Test Case 12: Valid user login deleting a book and check if book is not displayed post deletion");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);

		bookListPage.assertTotalBookCount("Total Book Titles: 3");
		String deleteBookName = "The Very Busy Spider";
		bookListPage.deleteBookByTitle(deleteBookName);
		bookListPage.verifyBookDeletion(deleteBookName);
		bookListPage.assertTotalBookCount("Total Book Titles: 2");
	}

	//------------------------------------------------------------------------------
	// Test Case 13: Delete all books (no books available scenario) and Check page info 
	//-------------------------------------------------------------------------------

	@Test	
	public void DeleteAllBooksAndConfirmPageInfo()  throws InterruptedException, IOException {
		System.out.println("Test Case 13: Delete all books (no books available scenario) and Check page info");
		LoginPage logingpage = launchApplication();
		BookListPage bookListPage = new BookListPage(driver);

		System.out.println(bookListPage.getBookTitles());

		for (String el : bookListPage.getBookTitles()) {
			bookListPage.deleteBookByTitle(el);
			Thread.sleep(1000);
		}
		bookListPage.assertTotalBookCount("Total Book Titles: 0");
		bookListPage.assertPageInfo("Page 1 of 1");
		// this test is failing as Page Info is incorrect "Page 1 of 0" - Added a Bug - (Bug 1)
	}

	//---------------------------------------------------------------------------
	// Login Page Test Cases to check if appropriate error messages are displayed
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	// Test Case 14: Invalid user should not login to application and should display appropriate error messages
	//---------------------------------------------------------------------------

	@Test	
	public void InvalidUserLoginFailErrorMessageValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 14:Invalid user should not login to application and should display appropriate error messages");
		driver = initializedriver();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginInToApplicationByInvalidUser();
		loginpage.assertLoginFailForInvalidUserErrorMessages();
	}

	//---------------------------------------------------------------------------
	// Test Case 15: Invalid User login with blank details and checking for appropriate error messages
	//---------------------------------------------------------------------------

	@Test	
	public void LoginWithBlankSubmissionErrorMessageValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 15:Invalid user login with blank details and checking for appropriate error messages");
		driver = initializedriver();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginInToApplicationWithBlankSubmission();
		loginpage.assertLoginFailErrorMessagesForBlankSubmission();
	}

	//---------------------------------------------------------------------------
	// Test Case 16: When LogOut Button is clicked it should redirect to login page
	//---------------------------------------------------------------------------

	// This test case is failing as Application is not redirecting to login page - Please find the details in in ReadMe.docx document
	@Test	
	public void LogOutPageRedirectionValidation()  throws InterruptedException, IOException {
		System.out.println("Test Case 16: When LogOut Button is clicked it should redirect to login page");
		LoginPage logingpage = launchApplication();
		String ExpectedLoginPageURL = "https://applicationforlibrarymanagementsystem.onrender.com/login";
		BookListPage bookListPage = new BookListPage(driver);
		String ActualLoginPageURL = bookListPage.LogOutFromApplicationAndGetURL();
		System.out.println("Acutal Page URL is " + ActualLoginPageURL);
		System.out.println("Expected page URL is " + ExpectedLoginPageURL);

		Assert.assertEquals(ActualLoginPageURL, ExpectedLoginPageURL);
		// this test is failing as the redirection to login page is not happening - Added a Bug - (Bug 3)
	}

	//*************************************************************************
	// Page URL/Titles validations
	//*************************************************************************
	//---------------------------------------------------------------------------
	// Test Case 17: BookListPage and AddBookPage URL validations 
	//---------------------------------------------------------------------------

	@Test	
	public void AddBookPageURLCheck()  throws InterruptedException, IOException {
		System.out.println("Test Case 17: BookListPage and AddBookPage URL validations");
		LoginPage logingpage = launchApplication(); // login into application valid user and retruns loginpage object
		BookListPage bookListPage = new BookListPage(driver);
		AddBookPage addBookPage = new AddBookPage(driver);
		bookListPage.assertLandingPageLoad(); // checks if header with welcome message and logout buttons are loaded
		bookListPage.clickAddBookBtn();
		addBookPage.assertAddBookPageURL();
	}

	//---------------------------------------------------------------------------
	// Test Case 18: EditBookPage Title validation 
	//---------------------------------------------------------------------------

	@Test	
	public void EditBookPageURLCheck()  throws InterruptedException, IOException {
		System.out.println("Test Case 18: EditBookPage Title validation");
		LoginPage logingpage = launchApplication();
		EditBookPage editBookPage = new EditBookPage(driver);
		editBookPage.clickOnEditBookBtnByTitle("The Very Busy Spider");
		editBookPage.assertEditBookPageURL();
	}

	//---------------------------------------------------------------------------
	// Test Case 19: Invoking Login Screen from Task Page 
	//---------------------------------------------------------------------------

	@Test	
	public void InvokeLoginPageFromTaskPageLink()  throws InterruptedException, IOException {
		System.out.println("Test Case 19: Invoking Login Screen from Task Page");
		initializedriver();
		TaskPage taskpage = new TaskPage(driver);
		taskpage.invokeLoginPageFromTaskPageAndConfirm();
	}


}



