package lms.LibraryManagementSystem.pageobjects;

//import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import lms.LibraryManagementSystem.AbstractComponent.AbstractClass;

public class AddBookPage extends AbstractClass{

	WebDriver driver;

	public AddBookPage(WebDriver driver) {
		super(driver);
		//driver initialisation
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Field locators for adding a new book

	@FindBy(id="title")
	WebElement Title;

	@FindBy(id="author")
	WebElement Author;

	@FindBy(id="genre")
	WebElement Genre;

	@FindBy(id="isbn")
	WebElement ISBN;

	@FindBy(id="publicationDate")
	WebElement PublicationDate;

	@FindBy(id="price")
	WebElement Price;

	@FindBy(css="button.add-book-button")
	WebElement AddBookBtn;

	// banner - error message locators
	@FindBy(css="h3[class='font-bold']")
	WebElement errorHeader;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='Title is required.']")
	WebElement titleRequiredHeaderErrMsg;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='Author is required.']")
	WebElement authorRequiredHeaderErrMsg;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='Genre is required.']")
	WebElement genreRequiredHeaderErrMsg;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='ISBN is required.']")
	WebElement isbnRequiredHeaderErrMsg;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='Publication Date is required.']")
	WebElement dateRequiredHeaderErrMsg;

	@FindBy(xpath="//ul[@class='list-disc pl-5']/li[text()='Price is required.']")
	WebElement priceRequiredHeaderErrMsg;

	// filed - error message locators
	@FindBy(id="title-error")
	WebElement titleErrMsg;

	@FindBy(id="author-error")
	WebElement authorErrMsg;

	@FindBy(id="genre-error")
	WebElement genreErrMsg;

	@FindBy(id="isbn-error")
	WebElement isbnErrMsg;

	@FindBy(id="publicationDate-error")
	WebElement dateErrMsg;

	@FindBy(id="price-error")
	WebElement priceErrMsg;

	//Action methods for AddBookPage
	
	// to validate AddBookpage URL on clicking 'Add Book' button from BookList Page
	public void assertAddBookPageURL() {
		System.out.println("Checking Page URL after clicking on Add Book Page");
		// Page URL check
		String actualPageURL = driver.getCurrentUrl();
		System.out.println(actualPageURL);
		String expectedPageURL = "https://applicationforlibrarymanagementsystem.onrender.com/add-book"; 
		Assert.assertEquals(actualPageURL, expectedPageURL);
	}
	
	public void addNewBook(String title, String author, String genreText, String isbn, String date, String price) {
		System.out.println("Adding a new book to the catalog");
		Title.sendKeys(title);
		Author.sendKeys(author);
		selectBookGenre(genreText);
		ISBN.sendKeys(isbn);
		PublicationDate.sendKeys(date); // valid date format: DD/MM/YYYY
		Price.sendKeys(price);
		clicksubmitBtnForAddingNewBook();
	}

	public void selectBookGenre(String genreText) {

		WebElement dropdownElement = Genre;
		//		WebElement dropdownElement = driver.findElement(By.id("genre"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(genreText);
	}

	public void clicksubmitBtnForAddingNewBook() {
		AddBookBtn.click();
	}

	// to check banner and field error messages when submitted blank
	public void assertaddBookBlankSubmissionErrorMessages() {
		System.out.println("Checking blank submission error messages");

		assertAddBookHeaderErrMsg();
		assertaddBookTitleErrorMessages();
		assertaddBookAuthorErrorMessages();
		assertaddBookGenreErrorMessages();
		assertaddBookIsbnErrorMessages();
		assertaddBookDateErrorMessages();
		assertaddBookPriceErrorMessages();
	}

	// to check Banner Header error messages when submitted blank/or invalid data type
	public void assertAddBookHeaderErrMsg() {
		System.out.println("Checking Header error message");
		String ErrorHeader = errorHeader.getText();
		Assert.assertEquals(ErrorHeader, "Please correct the following errors:");
	}

	// to check BookTitle error messages when submitted blank
	public void assertaddBookTitleErrorMessages() {
		System.out.println("Checking Title header and field error messages");

		String TitleErrMsg = titleErrMsg.getText();
		Assert.assertEquals(TitleErrMsg, "Title is required.");

		String titleBannerErrMsg = titleRequiredHeaderErrMsg.getText();
		Assert.assertEquals(titleBannerErrMsg, "Title is required.");
	}

	// to check Author error messages when submitted blank
	public void assertaddBookAuthorErrorMessages() {
		System.out.println("Checking Author header and field error messages");

		String AuthorErrMsg = authorErrMsg.getText();
		Assert.assertEquals(AuthorErrMsg, "Author is required.");

		String authorBannerErrMsg = authorRequiredHeaderErrMsg.getText();
		Assert.assertEquals(authorBannerErrMsg, "Author is required.");
	}

	// to check Genre error messages when submitted blank
	public void assertaddBookGenreErrorMessages() {
		System.out.println("Checking Genre header and field error messages");

		String GenreErrMsg = genreErrMsg.getText();
		Assert.assertEquals(GenreErrMsg, "Genre is required.");

		String genreBannerErrMsg = genreRequiredHeaderErrMsg.getText();
		Assert.assertEquals(genreBannerErrMsg, "Genre is required.");
	}

	// to check ISBN error messages when submitted blank
	public void assertaddBookIsbnErrorMessages() {
		System.out.println("Checking ISBN header and field error messages");

		String IsbnErrMsg = isbnErrMsg.getText();
		Assert.assertEquals(IsbnErrMsg, "ISBN is required.");

		String isbnBannerErrMsg = isbnRequiredHeaderErrMsg.getText();
		Assert.assertEquals(isbnBannerErrMsg, "ISBN is required.");
	}

	// to check date error messages when submitted blank
	public void assertaddBookDateErrorMessages() {
		System.out.println("Checking Date header and field error messages");

		String DateErrMsg = dateErrMsg.getText();
		Assert.assertEquals(DateErrMsg, "Publication Date is required.");

		String dateBannerErrMsg = dateRequiredHeaderErrMsg.getText();
		Assert.assertEquals(dateBannerErrMsg, "Publication Date is required.");
	}

	// to check price error messages when submitted blank
	public void assertaddBookPriceErrorMessages() {
		System.out.println("Checking Price header and field error messages");

		String PriceErrMsg = priceErrMsg.getText();
		Assert.assertEquals(PriceErrMsg, "Price is required.");

		String priceBannerErrMsg = priceRequiredHeaderErrMsg.getText();
		Assert.assertEquals(priceBannerErrMsg, "Price is required.");
	}
	
	// to check for errors when invalid data submitted while adding a book
	public void assertAddNewBookInvalidDataSErrorMessages() {
		clicksubmitBtnForAddingNewBook();
		System.out.println("Checking error messages for invalid date submission");
		//		Date field accepting invalid data like text and allowing the form to submit instead of throwing error
	}
};