package com.Step.Definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.time.Duration;
import com.Project.Locators.loginLocators;
import com.helperUtilities.helperUtils;
import com.helperUtilities.timeOutsForWaits;
import com.utils.DriverManager;
import io.cucumber.java.en.*;

public class LoginSteps extends helperUtils {

	@Given("User is on login page")
	public void user_is_on_login_page() {
		if (isClickable(loginLocators.loginNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts)) {
			clickFunction(loginLocators.loginNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
		}
		System.out.println("Navigated to login page");
	}

	@When("User enters username {string} and password {string}")
	public void user_enters_credentials(String username, String password) {
		sendKeysFunction(loginLocators.userNameFieldNaukri, username, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
		sendKeysFunction(loginLocators.passWordFieldNaukri, password, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
		clickFunction(loginLocators.loginInButtonNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
	}

	@And("User should be redirected to dashboard")
	public void user_redirected_to_dashboard() {
		// Assert.assertTrue(driver().getCurrentUrl().contains("dashboard"));
	}

	@And("User should be able to view the error message as {string}")
	public void user_able_to_view_error_message(String expected) {
		String errorText = getText(loginLocators.loginInvalidCredentials, timeOutsForWaits.TimeOuts,
				timeOutsForWaits.PollingTimeOuts);
		Assert.assertTrue(errorText.contains(expected));
	}

	@Then("User should be logout")
	public void user_should_be_logout() {
		clickFunction(loginLocators.logOutButtonNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
		clickFunction(loginLocators.logOutNaukri, timeOutsForWaits.TimeOuts, timeOutsForWaits.PollingTimeOuts);
		System.out.println("User logged out successfully");
	}
}
