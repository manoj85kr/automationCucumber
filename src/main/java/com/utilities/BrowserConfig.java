package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.utils.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfig {

	public static WebDriver createDriver(String browserName) {

		WebDriver driver;

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options); // ✔ apply options

		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		DriverManager.setDriver(driver); // ✔ assign driver once
		return driver;
	}
}
