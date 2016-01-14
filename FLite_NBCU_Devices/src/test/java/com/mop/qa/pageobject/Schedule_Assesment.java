package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase_Appium;

public class Schedule_Assesment extends PageBase_Appium {

	public Schedule_Assesment() {
		super();
	}

	// @FindBy
	@FindBy(name = "pseudonym_session[unique_id]")
	protected WebElement userName;

	@FindBy(name = "pseudonym_session[password]")
	protected WebElement passWord;
//change
	@FindBy(xpath = ".//*[@id='login_form']/div[4]/div[2]/button")
	protected WebElement login;
	
	@FindBy(xpath = "//*[@id='login_form']/button")
	protected WebElement mLogin;

	//changed for desk too
	//@FindBy(xpath = "//html/body/div[2]/header/div[1]/div/div[1]/ul/li[2]/a")
	@FindBy(id = "global_nav_courses_link")
	protected WebElement courseTab;

	//*[@id='global_nav_profile_link']
	@FindBy(id = "global_nav_profile_link")
	protected WebElement accounTab;
	
	@FindBy(xpath = "//html/body/div[3]/div/div/div/div[1]/ul/li[6]/a")
	protected WebElement dectest1;

	@FindBy(className = "modules")
	protected WebElement moduletab;

	@FindBy(className = "title")
	protected WebElement sherpath;

	@FindBy(className = "btn")
	protected WebElement loadSherPath;
	
	//removed .
	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[1]/div[2]/a")
	protected WebElement syllabus;

	@FindBy(xpath = "//*[@id='slide-panel']/navigation-list/div[1]/div[2]/a")
	protected WebElement mSyl;
	
	String mCustom = "//*[@id='slide-panel']/navigation-list/div[1]/div[2]";
	
	//*[@id='contentSchedule.id']/div/div[1]/span
	@FindBy(xpath = "//html/body/ui-view/snap-content/div/div[4]/div/div/ul/li[2]/week-drag-panel/div[2]/ul/li[2]/module-drag-panel/div/div[1]/span")
	protected WebElement selectModule;
	
	@FindBy(xpath = ".//*[@id='contentSchedule.id']/div[3]/div[2]/ul/li[1]/lesson-drag-panel/ul/li/div[5]/span")
	protected WebElement assignbutton;

	@FindBy(xpath = "//html/body/ui-view/snap-drawers/snap-drawer/assign-lesson/div/main/section[2]/div/div/button")
	protected WebElement calbutton1;
	
	
	@FindBy(xpath = "/html/body/ui-view/snap-drawers/snap-drawer/assign-lesson/div/main/section[3]/div/div/button")
	protected WebElement calbutton2;

	@FindBy(xpath = "//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a")
	protected WebElement datebutton1;
	//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[4]/a
	@FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[4]/a")
	protected WebElement mDatebutton1;

	@FindBy(xpath = "//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a")
	protected WebElement datebutton2;
	
	@FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[7]/a")
	protected WebElement mDatebutton2;

	@FindBy(xpath = "//html/body/ui-view/snap-drawers/snap-drawer/assign-lesson/div/main/div/button[1]")
	
	protected WebElement assign;
	@FindBy(xpath = "(//*[@id='assignLessonScreen']/main/div/button[1])[2]")
	protected WebElement mAssign;

	@FindBy(xpath = "//html/body/ui-view/snap-drawers/side-panel/snap-drawer/div/div[2]/a")
	protected WebElement moretab;
	
	@FindBy(xpath = "//*[@id='slide-panel']/div[2]/a")
	protected WebElement mMoretab;

	@FindBy(xpath = "//*[@id='slide-panel']/navigation-list/div[2]/div[3]/a")
	protected WebElement logout;
	
	@FindBy(xpath = "//*[@class='ReactTray__link-list']/li[6]/form/a/span")
	protected WebElement parentLogout;
	////*[@class='ReactTray__link-list']/li[6]/form/a/span
	//html/body/div[3]/div/div/div/ul/li[6]/form/a/span
	
	@FindBy(css = "span[class='icon-assign ng-binding assigned-visible']")
	protected WebElement verifyCalenderBtn;

	@FindBy(css = "div[class='messages__message']>div[class='messages__message--title']")
	protected WebElement logoutMessage;

						
	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[1]/div[1]/a")
	protected WebElement home;

	@FindBy(xpath = "//*[@id='main']/header/div[2]/div")
	protected WebElement mHome;
	
	protected String parentWindow;
	

	public void enterUserName(String uname) throws Exception {

		enterText(userName, uname, "Entering UserName");
	}

	public void enterPassword(String pword) throws Exception {
		enterText(passWord, pword, "Entering password");
	}

	public void clickLogin() throws Exception {	
		String toolName  = getAppProperties("tool");
		if (toolName.equalsIgnoreCase("Selenium")) {
			click(login, "Click Login");
			} else if (toolName.equalsIgnoreCase("Appium")) {
				click(mLogin, "Click Login");
				}
		}

	public void clickCoursetab() throws Exception {
			click(courseTab, "Click Coursetab");
		
	}

	public void login(String url, String uName, String pword) throws Exception {
		
		enterUrl(url);
		enterUserName(uName);
		enterPassword(pword);
		clickLogin();
		System.out.println("Login Successful");
		parentWindow = getParentWindow();
	//	logoutParentWindow();
	}

	public void navigateToSherPath() throws Exception {
		clickCoursetab();
		selectDec();
		clickmoduletab();
		clicksherpath();
		laodShareWindow();
		Thread.sleep(30000);
		switchToWindowTitle();
		System.out.println("Loading Sher Path Successful");
	}


	public void scheduleCourse() throws Exception {
		clickSyllabus();
		selectModule();
		clickAssignButton();
		clickCalbutton1();
		selectAssignDate();
		clickCalbutton2();
		selectDueDate();
		clickassign();
		System.out.println("Scheduling course Successful");

	}

	public void logout() throws Exception {
		clickmoretab();
		clicklogout();
		logoutParentWindow();
		System.out.println("Logout Successful");
	}

	public void selectDec() throws Exception {
		click(dectest1, "Click on Course");
	}

	public void clickmoduletab() throws Exception {
		click(moduletab, "Click on moduletab");
	}

	public void clicksherpath() throws Exception {
		click(sherpath, "Click on sherpath");
	}

	public void laodShareWindow() throws Exception {
		String browser  = getAppProperties("webBrowser");
		clickNewWindow(loadSherPath, "Click on Load SherPath");
		if(browser.equalsIgnoreCase("firefox"))
		{
			clickAlert();
		}
	}


	public void clickSyllabus() throws Exception {
		
		String toolName  = getAppProperties("tool");
		Thread.sleep(5000);
		click(syllabus, "Click on Syllabus tab");
	}

	public void selectModule() throws Exception {

		click(selectModule, "Click on Belief, Values and Caring");
	}

	public void clickAssignButton() throws Exception {

		click(assignbutton, "Click on assign button");
	}

	public void clickCalbutton1() throws Exception {

		click(calbutton1, "Select Assign date from calender");
	}

	public void clickCalbutton2() throws Exception {

		click(calbutton2, "Select due date from calender");
	}

	public void selectAssignDate() throws Exception {

		click(mDatebutton1, "Select assign date");
	}

	public void selectDueDate() throws Exception {

		click(mDatebutton2, "Select due date");
	}

	public void clickassign() throws Exception {
		String toolName = getAppProperties("tool");
		if (toolName.equalsIgnoreCase("Selenium")) {
			click(assign, "Click on assign button");
		} else {
			click(mAssign, "Click on assign button");
		}
	}

	public void clickmoretab() throws Exception {
		String toolName  = getAppProperties("tool");
		if (toolName.equalsIgnoreCase("Selenium")) {
			click(moretab, "Click on more tab");
		}
		else{
			click(mMoretab, "Click on more tab");
		}
	}

	public void clicklogout() throws Exception {

		click(logout, "Click on logout button");
	}

	public void logoutParentWindow() throws Exception {
		switchToParentWindow(parentWindow);
		Thread.sleep(20000);
		System.out.println("123");
		clickNewWindow(accounTab,"Click on Account");
		System.out.println("123456");
		Thread.sleep(20000);
		click(parentLogout, "Logout Parent window");
		
	}
	
	public void verifyCalenderIconColor() throws Exception {

		elementIsDisplayed(verifyCalenderBtn,
				"Verifying the calender icon color");
		System.out.println("Calender colour is orange");
	}

	public void verifyLogoutText() throws Exception {
		verifyText(logoutMessage, "Signed Out");
		System.out.println("Veridy the logout successfully");
	}

	public void verifyHomepagedefault() throws Exception {
		String toolName  = getAppProperties("tool");
		if (toolName.equalsIgnoreCase("Selenium")) {
			elementIsDisplayed(home, "Verifying the Home page is default");
		}
		else{
			boolean val = elementIsDisplayed(mHome, "Verifying the Home page is default");
		}
		
	}

}
