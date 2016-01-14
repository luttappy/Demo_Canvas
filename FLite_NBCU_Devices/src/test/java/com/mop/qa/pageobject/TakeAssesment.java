package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase_Appium;

public class TakeAssesment extends PageBase_Appium {

	public TakeAssesment() {
		super();
	}

	@FindBy(name = "pseudonym_session[unique_id]")
	protected WebElement userName;

	@FindBy(name = "pseudonym_session[password]")
	protected WebElement passWord;

	@FindBy(xpath = ".//*[@id='login_form']/div[4]/div[2]/button")
	protected WebElement Login;

	@FindBy(xpath = "//*[@id='login_form']/button")
	protected WebElement mLogin;

	@FindBy(xpath = "//*[@id='DashboardCard_Container']/div/div/div/div/div[1]/div")
	protected WebElement dectest1;

	@FindBy(xpath = "//*[@id='wrapper']/div[1]/button/i")
	protected WebElement chooseMenu;
	
	@FindBy(className = "modules")
	protected WebElement moduletab;

	@FindBy(className = "title")
	protected WebElement sherpath;

	@FindBy(className = "btn")
	protected WebElement newWindow;
	
	@FindBy(xpath = "//*[@id='main']/main/article/h1/span[3]/span[3]")
	protected WebElement nextpage1;

	@FindBy(xpath = "//*[@id='main']/main/article/div[17]/div/div[2]/content-tile/div/div/div[1]/div[3]")
	protected WebElement nursingDiagnosis;

	@FindBy(xpath = "//*[@class='titleWrapper']/div")
	protected WebElement mnursingDiagnosis;		
	
	@FindBy(xpath = "//*[@id='main-learning-content']/div/article/div[3]/ul/li[2]/a/div")
	protected WebElement introduction;

	@FindBy(xpath = "//*[@id='main-learning-content']/div/article/div[3]/div[2]/button")
	protected WebElement resetButton;
	
	@FindBy(xpath = "//*[@id='confirmLessonReset']/div/button[1]")
	protected WebElement resetConfirm;

	@FindBy(xpath = ".//*[@id='readyButton']")
	protected WebElement Ready1;

	@FindBy(css = "article[id='question-container']>h2")
	protected WebElement question1;

	@FindBy(xpath = "//html/body/ui-view/div/div/div/div/div/div/div/div/form/article/h2")
	protected WebElement question2;

	@FindBy(xpath = ".//*[@id='question-container']/h2")
	protected WebElement question3;

	@FindBy(xpath = "//html/body/ui-view/div/div/div/div/div/div/div/div/form/article/h2")
	protected WebElement question4;

	@FindBy(xpath = "//html/body/ui-view/div/div/div/div/div/div/div/div/form/article/h2")
	protected WebElement question5;

	@FindBy(xpath = ".//*[@id='drag1']/span[1]")
	protected WebElement Dragele;

	@FindBy(xpath = ".//*[@id='question-container']/div/div[1]/div")
	protected WebElement Dropele;

	@FindBy(xpath = ".//*[@id='drag2']/span[1]")
	protected WebElement Dragele1;

	@FindBy(xpath = ".//*[@id='question-container']/div/div[2]/div")
	protected WebElement Dropele1;

	@FindBy(xpath = ".//*[@id='drag3']/span[1]")
	protected WebElement Dragele2;

	@FindBy(xpath = ".//*[@id='question-container']/div/div[3]/div")
	protected WebElement Dropele2;

	protected String tryAgain = "question-return";

	@FindBy(xpath = ".//*[@id='slide-panel']/div[2]/a")
	protected WebElement moretab;

	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[2]/div[3]/a")
	protected WebElement logout;

	protected String firstOption = "answerChoice0";

	protected String secondOption = "answerChoice1";

	protected String thirdOption = "answerChoice2";

	protected String fourthOption = "answerChoice3";

	protected String fifthOption = "answerChoice4";

	protected String firstanswer = "answerChoice[0]";

	protected String secondanswer = "answerChoice[1]";

	protected String thirdanswer = "answerChoice[2]";

	protected String fourthanswer = "answerChoice[3]";

	protected String fifthanswer = "answerChoice[4]";

	protected String submit = "submit-quiz-answer-high";

	protected String submit1 = "submit-quiz-answer";

	protected String nextpage = "next";

	protected String ready = "readyButton";

	protected String incorrect = "incorrectTab";

	protected	String correct = "correctTab";
	protected String parentWindow;
	
	@FindBy(id = "global_nav_profile_link")
	protected WebElement accounTab;
	
	@FindBy(xpath = "//*[@class='ReactTray__link-list']/li[6]/form/a/span")
	protected WebElement parentLogout;
	

	public void login(String url, String uName, String pword) throws Exception {
		enterUrl(url);
		enterUserName(uName);
		enterPassword(pword);
		clickLogin();
		
		parentWindow = getParentWindow();
		System.out.println("Login Successful");
	}

	public void navigateSherPath() throws Exception {
		clickDectest1();
		clickmoduletab();
		clicksherpath();
		clickNewWindow();
		// clickalert();
		switchToWindowTitle();
		System.out.println("Navigation to sher Path successful");
	}

	public void enterUserName(String uname) throws Exception {

		enterText(userName, uname, "Entering UserName");

	}

	public void enterPassword(String pword) throws Exception {

		enterText(passWord, pword, "Entering password");

	}

	public void clickLogin() throws Exception {
		String toolName  = getAppProperties("tool");
		
		if (toolName.equalsIgnoreCase("Selenium")) {
			click(Login, "Click Login");
		} else if (toolName.equalsIgnoreCase("Appium")) {
			click(mLogin, "Click Login");
		}

	}

	public void clickDectest1() throws Exception {

		click(dectest1, "Click on Oct28Course");

	}
	
	

	public void clickmoduletab() throws Exception {
		String toolName  = getAppProperties("tool");
		if (toolName.equalsIgnoreCase("Selenium")) {
			click(moduletab, "Click on module tab");
		} else if (toolName.equalsIgnoreCase("Appium")) {
			Thread.sleep(10000);
			clickPoint(chooseMenu, "click menu");
			click(moduletab, "Click on module tab");
		}
	}


	public void clicksherpath() throws Exception {
		click(sherpath, "Click on sherpath");
	}

	public void clickNewWindow() throws Exception {
		clickNewWindow(newWindow, "Click on Load Window");
	}

	public void clickalert() throws Exception {
		clickAlert();

	}
	
	
	public void clickNursingDiagnosis() throws Exception {
		String toolName  = getAppProperties("tool");
		click(nursingDiagnosis, "Click on Course");
		/*if(toolName.equalsIgnoreCase("Selenium")){
		click(nursingDiagnosis, "Click on Course");
		}
		else if(toolName.equalsIgnoreCase("Appium")){
			Thread.sleep(30000);
			click(nursingDiagnosis, "Click on Course");
			
		}*/
	}
	public void resetCourse() throws Exception {
		Thread.sleep(30000);
		Boolean res = elementIsEnabled(resetButton);
		if(res){
			click(resetButton, "Reset button");
			click(resetConfirm, "Reset Confirm");
			
		}
		
	}
	
	public void clickintroduction() throws Exception {
		click(introduction, "Click on introduction");
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

	public void selectCourse() throws Exception {
		clickNursingDiagnosis();
		String toolName  = getAppProperties("tool");
		if(toolName.equalsIgnoreCase("Selenium")){
			resetCourse();
		}
		System.out.println("Selecting course successful");
	}

	public void takeQuiz() throws Exception {
		quiz1();
		quiz2();
	}

	public void quiz1() throws Exception {
		clickintroduction();
		String toolName = getAppProperties("tool");
		clickbyid(nextpage, "click on next button");
		Thread.sleep(5000);
		clickbyid(nextpage, "click on next button");
		Thread.sleep(5000);
		clickbyid(nextpage, "click on next button");
		Thread.sleep(5000);
		clickbyid(nextpage, "click on next button");
		
		clickbyid(ready, "click on Ready button for quiz");
		String question1Txt = getText(question1, "Get the text of  first question");
	//	System.out.println("questionTxt: " + question1Txt);
		if (question1Txt.equals("The nursing diagnosis is formed based on information obtained during which part of the nursing process?")) {

			//System.out.println("select first option");
			clickbyid(firstOption, "click on firstoption");
			Thread.sleep(3000);
			clickbyid(submit, "click on submit button");
			clickbyid(nextpage, "click on next button");
		}
		
		String question2Txt = getText(question2,"Get the text of second question");
	//	System.out.println("questionTxt: " + question2Txt);
		if (question2Txt.equals("What are the benefits of using nursing diagnoses?")) {
			clickbyid(thirdanswer, "Click on secondoption");
			clickbyid(fourthanswer, "Click on fourthoption");
			clickbyid(submit, "Click on submit answer");
			Thread.sleep(3000);
			clickbyid(tryAgain, "click on return button");
			clickbyid(thirdanswer, "Click on secondoption");
			clickbyid(submit1, "click on submit button");
			Thread.sleep(3000);
			clickbyid(tryAgain, "click on return button");
			clickbyid(fifthanswer, "Click on fourthoption");
			clickbyid(secondanswer, "Click on fourthoption");
			clickbyid(submit1, "click on submit button");
			System.out.println("System Selected right answer");
			Thread.sleep(3000);
			clickbyid(submit1, "click on submit button");
			clickbyid(nextpage, "Click on next button");
		}
		
		String question3Txt = getText(question3,
				"Get the text of  third question");
	//	System.out.println("questionTxt: " + question3Txt);
		if (question3Txt.equals("What does the nurse do with the nursing diagnosis during the diagnosis step of the nursing process?")) {
	//		System.out.println("select second, fifth options");
			clickbyid(secondanswer, "Click on secondoption");
			clickbyid(fifthanswer, "Click on fifthoption");
			clickbyid(submit, "Click on submit answer");
			clickbyid(nextpage, "Click on next button");
		}
		String question4Txt = getText(question4,
				"Get the text of  fourth question");
	//	System.out.println("questionTxt: " + question4Txt);
		if (question4Txt.equals("Which statement about the diagnosis step of the nursing process is true?")) {
	//		System.out.println("select first options");
			clickbyid(firstOption, "Click on firstoption");
			clickbyid(submit, "Click on submit answer");
			clickbyid(nextpage, "Click on next button");
		}
		String question5Txt = getText(question5,"Get the text of  fifth question");
	//	System.out.println("questionTxt: " + question5Txt);
		if (question5Txt.equals("What is the purpose of the nursing diagnosis?")) {
		//	System.out.println("select fourth options");
			clickbyid(fourthOption, "Click on fourthoption");
			clickbyid(submit, "Click on submit answer");
			clickbyid(nextpage, "Click on next button");
		}
		
		System.out.println("Quiz1 successfuly completed");
	}

	public void quiz2() throws Exception {
		clickbyid(nextpage, "click on next button");
		clickbyid(nextpage, "click on next button");
		clickbyid(ready, "click on Ready button");
		String question1Txt = getText(question1, "Get the text of  first question");
	//	System.out.println("questionTxt: " + question1Txt);
		if (question1Txt.equals("What was the purpose of the first unofficial nursing diagnosis conference in 1973?")) {
	//		System.out.println("select first option");
			clickbyid(firstOption, "click on firstoption");
			clickbyid(submit, "click on submit button");
			clickbyid(nextpage, "click on next button");
		}
		String question2Txt = getText(question2, "Get the text of  second question");
	//	System.out.println("questionTxt: " + question2Txt);
		if (question2Txt.equals("What are the goals of NANDA?")) {
	//		System.out.println("select first, third and fourth and fifth options");
			clickbyid(firstanswer, "Click on secondoption");
			clickbyid(thirdanswer, "Click on secondoption");
			clickbyid(fourthanswer, "Click on fourthoption");
			clickbyid(fifthanswer, "Click on fourthoption");
			clickbyid(submit, "Click on submit answer");
			clickbyid(nextpage, "Click on nextpage");
		}
		String question3Txt = getText(question3, "Get the text of third question");
	//	System.out.println("questionTxt: " + question3Txt);
		if (question3Txt.equals("What is the purpose for NANDA-I to continue to meet every two years?")) {
	//		System.out.println("select first options");
			clickbyid(firstOption,"Click First Option");
			clickbyid(submit, "Click on submit answer");
			Thread.sleep(3000);
			clickbyid(nextpage, "Click on nextpage");
		}
		System.out.println("Quiz2 successfuly completed");
	}

	public void lesson() throws Exception {
		clickbyid(nextpage, "click on next button");
		clickbyid(ready, "click on Ready button");
		clickbyid(firstOption, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(fourthOption, "Click on fourthoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(fifthanswer, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(firstOption, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(firstanswer, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(fourthOption, "Click on fifthoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(fifthanswer, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");

		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		
		System.out.println("Completed Lesson");
	}

	public void checkResult() throws Exception {
		clickbyid(incorrect, "click on incorrect tab");
		clickbyid(correct, "click on correct tab");
		System.out.println("Checked Results");

	}
	
	public void logoutParentWindow() throws Exception {
		switchToParentWindow(parentWindow);
		Thread.sleep(20000);
		click(accounTab,"Click on Account");
		Thread.sleep(20000);
		click(parentLogout, "Logout Parent window");
		
	}

	public void logout() throws Exception {
		clickmoretab();
		clicklogout();
		logoutParentWindow();
		System.out.println("Logout Successful");
	}
}