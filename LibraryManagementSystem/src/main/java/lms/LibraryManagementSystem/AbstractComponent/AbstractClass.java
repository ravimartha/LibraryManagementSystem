package lms.LibraryManagementSystem.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	
	WebDriver driver;
	
	public AbstractClass(WebDriver driver) {
		this.driver = driver;
	}
	
	// generic method to wait for element to appear
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// By.cssSelector("test") --> not webelement --> its a By Locator -- 161
}
