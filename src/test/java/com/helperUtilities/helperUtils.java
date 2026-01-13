package com.helperUtilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Project.Locators.searchLocator;
import com.utils.DriverManager;

public class helperUtils implements DriverManager {

	private static WebElement fluentWait(By locator, int pollingTimeOuts, int timeOuts) {
		try {
			Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver()).withTimeout(Duration.ofSeconds(timeOuts))
					.pollingEvery(Duration.ofSeconds(pollingTimeOuts)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Element not found: " + locator);
			return null;
		}
	}

	public boolean isDisplayed(String xpath, int polling, int timeout) {
		WebElement element = fluentWait(By.xpath(xpath), polling, timeout);
		return element != null && element.isDisplayed();
	}

	public boolean isClickable(String xpath, int polling, int timeout) {
		try {
			Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver()).withTimeout(Duration.ofSeconds(timeout))
					.pollingEvery(Duration.ofMillis(polling)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class);

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			return (element != null && element.isEnabled());

		} catch (Exception e) {
			System.out.println("❌ Not clickable: " + xpath + " | Reason: " + e.getMessage());
			return false;
		}
	}

	public void clickFunction(String xpath, int polling, int timeout) {
		WebElement element = fluentWait(By.xpath(xpath), polling, timeout);
		if (element != null) {
			element.click();
		} else {
			throw new RuntimeException("Failed to click. Element not found: " + xpath);
		}
	}

	public String getText(String xpath, int polling, int timeout) {
		String text;
		WebElement element = fluentWait(By.xpath(xpath), polling, timeout);
		if (element != null) {
			text = element.getText();
		} else {
			throw new RuntimeException("Failed to click. Element not found: " + xpath);
		}
		return text;
	}

	public void sendKeysFunction(String xpath, String text, int polling, int timeout) {
		WebElement element = fluentWait(By.xpath(xpath), polling, timeout);
		if (element != null) {
			element.clear();
			element.sendKeys(text);
		} else {
			throw new RuntimeException("Failed to send keys. Element not found: " + xpath);
		}
	}

	public static boolean Waits(int timeUnits) {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeUnits));
		return true;
	}

	public boolean Waits(String xpathLocator, int timeUnits) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeUnits));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println("Element not found: " + xpathLocator);
			return false;
		}
	}

	public void scrollTheDropDown() {
		WebElement dropdown = DriverManager.getDriver().findElement(By.xpath(searchLocator.searchExperienceDropDown));
		dropdown.click();
		WebElement targetOption = DriverManager.getDriver()
				.findElement(By.xpath(searchLocator.searchExperienceDropDownValues));
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				targetOption);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void commonScrollTheDropDown(String xpath) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
				Duration.ofSeconds(timeOutsForWaits.TimeOuts));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		WebElement targetOption = DriverManager.getDriver().findElement(By.xpath(xpath));
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				targetOption);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mouseHover(String xpath, int polling, int timeout) {
		WebElement element = fluentWait(By.xpath(xpath), polling, timeout);
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).perform();
	}

}
