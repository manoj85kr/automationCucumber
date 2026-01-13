package com.Project.Locators;

public class searchLocator {

	public static final String searchBox = "//*[@id='twotabsearchtextbox']";
	public static final String searchResultProductDescription = "//*[@role='listitem']/following::h2/parent::div/following-sibling::a/h2/span";
	public static final String searchResultProductTitle = "//*[@role='listitem']/following::h2/span[@class='a-size-base-plus a-color-base']";
	public static final String searchResultProductImage = "//*[@role='listitem']/following::h2/parent::div/following-sibling::a/following::img";

	// *************************************************************************************************//
	public static final String searchBoxNaukri = "//*[@class='ni-gnb-icn ni-gnb-icn-search']";
	public static final String searchBoxEnterText = "//input[@placeholder='Enter keyword / designation / companies']";
	public static final String searchExperienceDropDown = "//input[@id='experienceDD']";
	public static final String searchExperienceDropDownValue = "//ul/li/div/span[contains(text(),'%s')]";
	public static final String searchExperienceDropDownValues = "//ul/li/div/span[contains(text(),'13 years')]";
	public static final String searchBoxEnterLocation = "//input[@placeholder='Enter location']";
	public static final String searchButton = "//button/span[contains(text(),'Search')]";

	// *************************************************************************************************//
	public static final String jobLists = "//*[@id='listContainer']/div[2]/div/div";
	public static final String paginationBanner = "//*[@id='lastCompMark']";
	public static final String jobHeader = "//*[@id='jobs-list-header']";
	public static final String jobHeaderTitle = "//*[@id='listContainer']/div[2]/div/div/following::h2/a[@title]";
	public static final String jobHeaderPostedDate = "//*[@id='listContainer']/div[2]/div/div/div/div[6]/span[@class='job-post-day ']";

	// ************************************************************************************************//
	public static final String applyButton = "(//button[contains(text(),'Apply')])[1]";
	public static final String applyOnCompanySiteButton = "(//button[@id='company-site-button'])[1]";

	// ************************************************************************************************//
	public static final String recommendedJobs = "//*[@title='Recommended Jobs']";
	public static final String recommendedJobsSubMenu = "//div[contains(text(),'Recommended jobs')]";
	public static final String recommendedJobsSubTabs = "//div[contains(text(),'%s')]";
	public static final String recommendedJobHeaderTitle = "//*[starts-with(@class, 'title ellipsis')]";
	public static final String recommendedJobPostedDate = "//*[starts-with(@class,'jobTupleFooter')]";
}
