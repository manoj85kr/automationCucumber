package com.Project.Locators;

public class loginLocators {

	public static String userNameField = "//*[@name='email']";
	public static String passWordField = "//*[@id='ap_password']";
	public static String continueButton = "//input[@class='a-button-input']";
	public static String signInButton = "//*[@id='signInSubmit']";

	public static String navigationMenuForLogOut = "//*[@data-nav-ref='nav_youraccount_btn']";
	public static String signOutButton = "//*[@id='nav-item-signout']";

	// #---------------------------

	public static String loginNaukri = "//*[@id='login_Layer']";
	public static String userNameFieldNaukri = "//input[@placeholder='Enter your active Email ID / Username']";
	public static String passWordFieldNaukri = "//input[@placeholder='Enter your password']";
	public static String loginInButtonNaukri = "//button[@class='btn-primary loginButton']";

	public static String logOutButtonNaukri = "//*[@alt='naukri user profile img']";
	public static String logOutNaukri = "//*[@title='Logout']";

	public static String loginInvalidCredentials = "//*[contains(text(),'Invalid details. Please check the Email ID - Password combination.')]";

}
