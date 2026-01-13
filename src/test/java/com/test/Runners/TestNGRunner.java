package com.test.Runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/com/features", glue = { "com.Step.Definitions",
		"com.hooks" }, plugin = { "pretty", "html:target/cucumber-report.html", "json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, tags = "@search", monochrome = true)
public class TestNGRunner extends AbstractTestNGCucumberTests {
	@DataProvider(parallel = false)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}

/*
 * 🚀 Parallel Execution Requirements Checklist Component Status
 * parallel="classes" in TestNG XML ✅ Required
 * 
 * @DataProvider(parallel=true) in runner ✅ Required ThreadLocal WebDriver ✅
 * Required No static global driver ❌ Not allowed No shared state across
 * scenarios ❗ Must avoid POM + wait utilities fetch driver fresh ✔ Recommended
 */