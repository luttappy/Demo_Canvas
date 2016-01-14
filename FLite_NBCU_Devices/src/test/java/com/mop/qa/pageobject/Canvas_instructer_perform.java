package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase_Appium;

public class Canvas_instructer_perform extends PageBase_Appium {

	@FindBy(id = "pseudonym_session_unique_id")
	protected WebElement userName;

	@FindBy(id = "pseudonym_session_password")
	protected WebElement passWord;

	@FindBy(xpath = ".//*[@id='login_form']/div[4]/div[2]/button")
	protected WebElement Login;

	@FindBy(xpath = "//html/body/div[2]/header/div[1]/div/div[1]/ul/li[2]/a")
	protected WebElement Coursetab;

	// @FindBy(xpath = "//html/body/div[3]/div/div/div/div[1]/ul/li[13]/a")
	// protected WebElement AllCourses;

	@FindBy(xpath = "//html/body/div[3]/div/div/div/div[1]/ul/li[6]/a")
	protected WebElement Dectest1;

	@FindBy(className = "modules")
	protected WebElement moduletab;

	@FindBy(className = "title")
	protected WebElement sherpath;

	@FindBy(className = "btn")
	protected WebElement NewWindow;

	@FindBy(xpath = ".//*[@id='main']/main/article/h1/span[3]/span[3]")
	protected WebElement nextpage;

	@FindBy(xpath = ".//*[@id='slide-panel']/div[2]/a")
	protected WebElement moretab;

	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[2]/div[3]/a")
	protected WebElement logout;

	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[1]/div[1]/a")
	protected WebElement Home;

	@FindBy(xpath = ".//*[@id='main']/main/article/div[14]/div/div[5]/content-tile/div/div/div[1]/div[2]")
	protected WebElement NursingDiagnosis;

	@FindBy(xpath = ".//*[@id='main-learning-content']/div/article/div[3]/ul/li[7]/a/div")
	protected WebElement performance;

	@FindBy(xpath = ".//*[@id='main']/header/div[2]")
	protected WebElement Test1;

	String incorrect = "incorrectTab";

	String correct = "correctTab";

	public void enterUserName(String uname) throws Exception {

		enterText(userName, uname, "Entering UserName");
	}

	public void enterPassword(String pword) throws Exception {
		enterText(passWord, pword, "Entering password");
	}

	public void clickLogin() throws Exception {
		click(Login, "Click Login");
	}

	public void clickCoursetab() throws Exception {
		click(Coursetab, "Click Coursetab");
	}

	public void login(String url, String uName, String pword) throws Exception {
		enterUrl(url);
		enterUserName(uName);
		enterPassword(pword);
		clickLogin();
	}

	public void navigateToSherPath() throws Exception {
		clickCoursetab();
		clickDectest1();
		clickmoduletab();
		clicksherpath();
		clickNewWindow();
		// clickalert();
		switchToWindowTitle();
	}

	public void selectCourse() throws Exception {

		verifyHomepagedefault();
		clickNursingDiagnosis();
		verifyDoneText();
	}

	// public void waitingforelementdispaled() throws Exception {
	// elementIsDisplayed(moduletab, "Click on All Courses");
	// }

	public void clickDectest1() throws Exception {
		click(Dectest1, "Click on Oct28Course");
	}

	public void clickmoduletab() throws Exception {
		click(moduletab, "Click on moduletab");
	}

	public void clicksherpath() throws Exception {
		click(sherpath, "Click on sherpath");
	}

	public void clickNewWindow() throws Exception {
		clickNewWindow(NewWindow, "Click on NewWindow");
	}

	public void clickalert() throws Exception {
		clickAlert();

	}

	public void clickNursingDiagnosis() throws Exception {
		click(NursingDiagnosis, "Click on Nursing Diagosis");
	}

	public void clickperformance() throws Exception {
		click(performance, "Click on performance button");
	}

	public void logout() throws Exception {
		checkResults();
		clickmoretab();
		clicklogout();
	}

	public void clicknextpage() throws Exception {
		click(nextpage, "Click on nextpage");
	}

	public void clickmoretab() throws Exception {

		click(moretab, "Click on more tab");
		System.out.println("selected more tab");
	}

	public void clicklogout() throws Exception {
		System.out.println("selected logout button");
		click(logout, "Click on logout button");
	}

	public void verifyHomepagedefault() throws Exception {

		elementIsDisplayed(Home, "Verifying the Home page is default");
		System.out.println("Home page is default in the screen");
	}

	// public void clickNursingDiagnosis() throws Exception {
	// click(NursingDiagnosis, "Click on courseOct28");
	// }

	public void verifyDoneText() throws Exception {
		verifyText(Test1, "Done");
		System.out.println("Verify Done button appear in the top of the page");
	}

	public void checkResults() throws Exception {
		clickbyid(incorrect, "click on incorrect tab");
		clickbyid(correct, "click on correct tab");
	}

}
