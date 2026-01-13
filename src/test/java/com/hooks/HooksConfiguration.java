package com.hooks;

import com.Configuration.ConfigReader;
import com.Project.Locators.loginLocators;
import com.Step.Definitions.LoginSteps;
import com.helperUtilities.JsonDataReader;
import com.helperUtilities.timeOutsForWaits;
import com.utilities.BrowserConfig;
import com.utils.DriverManager;

import java.io.IOException;

import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;

public class HooksConfiguration {

	LoginSteps ls = new LoginSteps();

	@Before(order = 0)
	public void setup() throws IOException {
		ConfigReader.load();
		BrowserConfig.createDriver(ConfigReader.getProperty("browser")); // create driver
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().get(ConfigReader.getProperty("url"));
	}

//	@Before(order = 1)
//	public void login() throws IOException {
//		JSONObject data = JsonDataReader.getData("valid");
//		System.out.println(data.getString("username"));
//		System.out.println(data.getString("password"));
//		ls.user_is_on_login_page();
//		ls.user_enters_credentials("manojranganathanofficial@gmail.com", "Madan123$");
//	}
//
//	@After(order = 1)
//	public void logOut() {
//		ls.user_should_be_logout();
//	}

	@After(order = 0)
	public void tearDown() {
		WebDriver driver = DriverManager.getDriver();
		if (driver != null) {
			driver.quit();
			DriverManager.unload();
		}
	}

	@AfterStep
	public void takeScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "failure screenshot");
		}
	}

}
