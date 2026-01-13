package com.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import com.Project.DataSet.DataWriterUtils;
import com.Project.Locators.searchLocator;
import com.helperUtilities.helperUtils;
import com.helperUtilities.timeOutsForWaits;
import com.utils.DriverManager;

public class searchPage implements DriverManager {

	public static Map<String, Map<String, String>> getTheJobListing() {
		Map<String, String> job = new HashMap<String, String>();
		Map<String, Map<String, String>> Details = new HashMap<String, Map<String, String>>();
		helperUtils.commonScrollTheDropDown(searchLocator.paginationBanner);
		List<WebElement> ls = DriverManager.getDriver().findElements(By.xpath(searchLocator.jobHeaderTitle));
		List<WebElement> lsDate = DriverManager.getDriver().findElements(By.xpath(searchLocator.jobHeaderPostedDate));
		helperUtils.commonScrollTheDropDown(searchLocator.jobHeader);
		for (int i = 0; i <= ls.size() - 1; i++) {
			job.put(ls.get(i).getText(), ls.get(i).getAttribute("href"));
			Details.put(lsDate.get(i).getText(), job);
		}
		return Details;
	}

	public static Map<String, Map<String, String>> getTheJobListingRecommendedJobs() {
		Map<String, String> job = new HashMap<String, String>();
		Map<String, Map<String, String>> Details = new HashMap<String, Map<String, String>>();
		List<WebElement> ls = DriverManager.getDriver().findElements(By.xpath(searchLocator.recommendedJobHeaderTitle));
		List<WebElement> lsDate = DriverManager.getDriver()
				.findElements(By.xpath(searchLocator.recommendedJobPostedDate));
		List<String> links = new ArrayList<String>();
		helperUtils.Waits(timeOutsForWaits.TimeOuts);
		String parentTab = DriverManager.getDriver().getWindowHandle();
		String childUrl;
		Set<String> windowHandles;
		for (WebElement wes : ls) {
			wes.click();
			windowHandles = DriverManager.getDriver().getWindowHandles();
			for (String tab : windowHandles) {
				if (!tab.equals(parentTab)) {
					DriverManager.getDriver().switchTo().window(tab);
					childUrl = DriverManager.getDriver().getCurrentUrl();
					links.add(childUrl);
					DriverManager.getDriver().close();
				}
			}
			DriverManager.getDriver().switchTo().window(parentTab);
		}
		for (int i = 0; i <= ls.size() - 1; i++) {
			job.put(ls.get(i).getText(), links.get(i));
			Details.put(lsDate.get(i).getText(), job);
		}
		return Details;
	}

	public static void getJobListingCountsRecommendedJobs() throws IOException {
		// ✅ File details
		String[] title = new String[] { "S.No", "Title", "Link", "DateOfPosted", "Timestamp" };
		String filePath = "E:\\Automation\\automationCucumber\\testresult.xlsx";
		String sheetName = "Naukri_Data_File";
		int count = 1;
		boolean Status = false;
		String buttonType = "";
		File file = new File(filePath);
		if (!file.exists()) {
			DataWriterUtils.createExcelFileForNaukri(filePath, sheetName, title);
		}
		Map<String, Map<String, String>> data = searchPage.getTheJobListingRecommendedJobs();
		for (Entry<String, Map<String, String>> ent : data.entrySet()) {
			String postedDate = ent.getKey();
			for (Entry<String, String> val : ent.getValue().entrySet()) {
				String jobTitle = val.getKey();
				String jobLink = val.getValue();
				System.out.println("Opening URL: " + jobLink);
				DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
				DriverManager.getDriver().get(jobLink);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Duration originalWait = DriverManager.getDriver().manage().timeouts().getImplicitWaitTimeout();
				try {
					DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
					boolean hasCompanySiteButton = !DriverManager.getDriver()
							.findElements(By.xpath(searchLocator.applyOnCompanySiteButton)).isEmpty();
					boolean hasApplyButton = !DriverManager.getDriver()
							.findElements(By.xpath(searchLocator.applyButton)).isEmpty();
					if (hasCompanySiteButton) {
						Status = false;
					} else if (hasApplyButton) {
						Status = true;
					} else {
						Status = false;
					}
				} finally {
					DriverManager.getDriver().manage().timeouts().implicitlyWait(originalWait);
				}
				if (Status == true) {
					buttonType = "Apply";
				} else {
					buttonType = "ApplyOnCompanySite";
				}
				DriverManager.getDriver().close();
				for (String handle : DriverManager.getDriver().getWindowHandles()) {
					DriverManager.getDriver().switchTo().window(handle);
					break;
				}
				DataWriterUtils.appendResultNaukri(filePath, sheetName, count++, jobTitle, jobLink, postedDate,
						buttonType);
			}
		}
		System.out.println("✅ All job listings appended successfully to sheet: " + sheetName);
	}

	public static void getJobListingCounts() throws IOException {
		// ✅ File details
		String[] title = new String[] { "S.No", "Title", "Link", "DateOfPosted", "Timestamp" };
		String filePath = "E:\\Automation\\automationCucumber\\testresult.xlsx";
		String sheetName = "Naukri_Data_File";
		int count = 1;
		boolean Status = false;
		String buttonType = "";
		File file = new File(filePath);
		if (!file.exists()) {
			DataWriterUtils.createExcelFileForNaukri(filePath, sheetName, title);
		}
		Map<String, Map<String, String>> data = searchPage.getTheJobListing();
		// ✅ Loop through job listings and append each record
		for (Entry<String, Map<String, String>> ent : data.entrySet()) {
			String postedDate = ent.getKey(); // Example: "3 days ago"
			for (Entry<String, String> val : ent.getValue().entrySet()) {
				String jobTitle = val.getKey();
				String jobLink = val.getValue();
				System.out.println("Opening URL: " + jobLink);
				DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
				DriverManager.getDriver().get(jobLink);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Duration originalWait = DriverManager.getDriver().manage().timeouts().getImplicitWaitTimeout();
				try {
					// Temporarily disable waiting
					DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
					boolean hasCompanySiteButton = !DriverManager.getDriver()
							.findElements(By.xpath(searchLocator.applyOnCompanySiteButton)).isEmpty();
					boolean hasApplyButton = !DriverManager.getDriver()
							.findElements(By.xpath(searchLocator.applyButton)).isEmpty();
					if (hasCompanySiteButton) {
						Status = false;
					} else if (hasApplyButton) {
						Status = true;
					} else {
						Status = false; // Default or handle differently if needed
					}
				} finally {
					// Restore original wait
					DriverManager.getDriver().manage().timeouts().implicitlyWait(originalWait);
				}
				if (Status == true) {
					buttonType = "Apply";
				} else {
					buttonType = "ApplyOnCompanySite";
				}
				DriverManager.getDriver().close();
				for (String handle : DriverManager.getDriver().getWindowHandles()) {
					DriverManager.getDriver().switchTo().window(handle);
					break;
				}
				DataWriterUtils.appendResultNaukri(filePath, sheetName, count++, jobTitle, jobLink, postedDate,
						buttonType);
			}
		}
		System.out.println("✅ All job listings appended successfully to sheet: " + sheetName);
	}
}
