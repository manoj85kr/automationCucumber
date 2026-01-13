package com.Step.Definitions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import com.Project.Locators.searchLocator;
import com.helperUtilities.helperUtils;
import com.helperUtilities.timeOutsForWaits;
import com.utils.DriverManager;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps extends helperUtils {

	@Given("user clicks on the search bar")
	public void user_clicks_on_the_search_bar() {
		clickFunction(searchLocator.searchBoxNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@And("wait for the search banner is opened")
	public void wait_for_the_search_banner_is_opened() {
		isDisplayed(searchLocator.searchBoxEnterText, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@Then("enter the keyword as {string}")
	public void enter_the_keyword(String values) {
		sendKeysFunction(searchLocator.searchBoxEnterText, values, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
	}

	@And("select the experience dropdown with value as {string}")
	public void select_the_experience_dropdown_with_value(String values) {
		scrollTheDropDown();
		String finalXPath = String.format(searchLocator.searchExperienceDropDownValue, values);
		clickFunction(finalXPath, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@And("enter the location as {string}")
	public void enter_the_location(String values) {
		sendKeysFunction(searchLocator.searchBoxEnterLocation, values, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
	}

	@Then("click on the search button")
	public void click_on_the_search_button() {
		clickFunction(searchLocator.searchButton, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@When("user enters job search details")
	public void user_enters_job_search_details(io.cucumber.datatable.DataTable table) {
		Map<String, String> data = table.asMap(String.class, String.class);
		enter_the_keyword(data.get("keyword"));
		select_the_experience_dropdown_with_value(data.get("experience"));
		enter_the_location(data.get("location"));
	}

	@And("user gets the search results")
	public void user_gets_the_search_results() throws IOException {
		com.pages.searchPage.getJobListingCounts();
	}

	@Given("user navigates to the recommended jobs section")
	public void user_navigates_to_the_recommended_jobs_section() {
		mouseHover(searchLocator.recommendedJobs, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
		clickFunction(searchLocator.recommendedJobsSubMenu, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
	}

	@When("user clicks on the {string} tab in recommended jobs for you")
	public void user_clicks_on_the_preferences_tab_in_recommended_jobs_for_you(String recommendedTab) {
		String finalXPath = String.format(searchLocator.recommendedJobsSubTabs, recommendedTab);
		clickFunction(finalXPath, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@And("user gets the recommended jobs search results")
	public void user_gets_the_recommended_jobs_search_results() throws IOException {
		com.pages.searchPage.getJobListingCountsRecommendedJobs();
	}
}
