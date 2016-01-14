package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase_Appium;

public class Canvasapp_instructer_native extends PageBase_Appium
{
	
	public Canvasapp_instructer_native() {
		super();
	}

	// @FindBy
	@FindBy(id = "pseudonym_session_unique_id")
	protected WebElement userName;

	@FindBy(id = "pseudonym_session_password")
	protected WebElement passWord;

	@FindBy(xpath = ".//*[@id='login_form']/div[4]/div[2]/button")
	protected WebElement Login;
	
	
	
	
	@FindBy(xpath = ".//*[@id='DashboardCard_Container']/div/div/div/div/div[1]/div")
	protected WebElement Dectest1;

	

	@FindBy(className = "modules")
	protected WebElement moduletab;

	@FindBy(className = "title")
	protected WebElement sherpath;

	@FindBy(className = "btn")
	protected WebElement NewWindow;

	@FindBy(xpath = ".//*[@id='main']/main/article/h1/span[3]/span[3]")
	protected WebElement nextpage;

	
	@FindBy(xpath = ".//*[@id='main']/main/article/div[14]/div/div[5]/content-tile/div/div/div[1]/div[3]")
	protected WebElement NursingDiagnosis;
	
	

	@FindBy(xpath = ".//*[@id='main-learning-content']/div/article/div[3]/ul/li[2]/a/div")
	protected WebElement introduction;

	//@FindBy(id = "next")
	//protected WebElement nextpage;

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
	
	
	@FindBy(xpath = ".//*[@id='slide-panel']/div[2]/a")
	protected WebElement moretab;
	
		
	@FindBy(xpath = ".//*[@id='slide-panel']/navigation-list/div[2]/div[3]/a")
	protected WebElement logout;

		
	String firstOption = "answerChoice0";
	
	String secondOption = "answerChoice1";
	
	String thirdOption = "answerChoice2";
	
	String fourthOption = "answerChoice3";
	
	String fifthOption = "answerChoice4";
	
	String firstanswer = "answerChoice[0]";
	
	String secondanswer = "answerChoice[1]";
	
	String thirdanswer = "answerChoice[2]";
	
	String fourthanswer = "answerChoice[3]";
	
	String fifthanswer = "answerChoice[4]";
	
	
	
	String submit = "submit-quiz-answer-high";
	
	String submit1 = "submit-quiz-answer";
	
	String nextpage1 = "next";
	
	String Ready = "readyButton";
	
	String Return = "question-return";
	
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
		click(NursingDiagnosis, "Click on courseOct28");
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

	
	
	
	public void getText1() throws Exception
	{
		clickbyid(nextpage1, "click on next button");
		clickbyid(nextpage1, "click on next button");
		clickbyid(nextpage1, "click on next button");
		clickbyid(nextpage1, "click on next button");
		clickbyid(Ready, "click on Ready button");
		String questionTxt=getText(question1, "Get the text of first question");
		System.out.println("questionTxt: "+questionTxt);
		if(questionTxt.equals("The nursing diagnosis is formed based on information obtained during which part of the nursing process?"))
		{
			
			clickbyid(secondOption, "click on secondoption");
			clickbyid(submit, "click on submit button");
			clickbyid(Return, "click on return button");    
			clickbyid(thirdOption, "click on firstoption");
			clickbyid(submit1, "click on submit button");
			clickbyid(Return, "click on return button");
			clickbyid(fourthOption, "click on firstoption");
			clickbyid(submit1, "click on submit button");
			System.out.println("System Selected right answer");
			Thread.sleep(3000);
			clickbyid(submit1, "click on submit button");
			clickbyid(nextpage1, "click on next button");
		}
			String questionTxt1=getText(question2, "Get the text of second question");
			System.out.println("questionTxt: "+questionTxt1);
			if(questionTxt1.equals("What are the benefits of using nursing diagnoses?"))
			{   
				
						
				clickbyid(thirdanswer, "Click on secondoption");
				clickbyid(fourthanswer, "Click on fourthoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");
				clickbyid(thirdanswer, "Click on secondoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(fifthanswer, "Click on fourthoption");
				clickbyid(secondanswer, "Click on fourthoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on next button");
			}
			String questionTxt2=getText(question3, "Get the text of third question");
			System.out.println("questionTxt: "+questionTxt2);
			if(questionTxt2.equals("What does the nurse do with the nursing diagnosis during the diagnosis step of the nursing process?"))
			{   
				
				clickbyid(firstanswer, "Click on secondoption");
				clickbyid(fifthanswer, "Click on fifthoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");
				clickbyid(thirdanswer, "Click on secondoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(fifthanswer, "Click on fourthoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on next button");							
			}
			String questionTxt3=getText(question4, "Get the text of fourth question");
			System.out.println("questionTxt: "+questionTxt3);
			if(questionTxt3.equals("Which statement about the diagnosis step of the nursing process is true?"))
			{   
				
				clickbyid(secondOption, "Click on firstoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");    
				clickbyid(thirdOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(fourthOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on next button");
			}
			String questionTxt4=getText(question5, "Get the text of fifth question");
			System.out.println("questionTxt: "+questionTxt4);
			if(questionTxt4.equals("What is the purpose of the nursing diagnosis?"))
			{   
				
				clickbyid(secondOption, "Click on fourthoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");    
				clickbyid(thirdOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(firstOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on next button");
			}
	}

	


	public void getText2() throws Exception
	{
		clickbyid(nextpage1, "click on next button");
		clickbyid(nextpage1, "click on next button");
		clickbyid(Ready, "click on Ready button");
		String questionTxt=getText(question1, "Get the text of first question");
		System.out.println("questionTxt: "+questionTxt);
		if(questionTxt.equals("What was the purpose of the first unofficial nursing diagnosis conference in 1973?"))
		{
			
			clickbyid(secondOption, "click on firstoption");
			clickbyid(submit, "click on submit button");
			clickbyid(Return, "click on return button");    
			clickbyid(thirdOption, "click on firstoption");
			clickbyid(submit1, "click on submit button");
			clickbyid(Return, "click on return button");
			clickbyid(fourthOption, "click on firstoption");
			clickbyid(submit1, "click on submit button");
			System.out.println("System Selected right answer");
			Thread.sleep(3000);
			clickbyid(submit1, "click on submit button");
			clickbyid(nextpage1, "click on next button");
		}
			String questionTxt1=getText(question2, "Get the text of second question");
			System.out.println("questionTxt: "+questionTxt1);
			if(questionTxt1.equals("What are the goals of NANDA?"))
			{   
				clickbyid(thirdanswer, "Click on secondoption");
				clickbyid(fourthanswer, "Click on fourthoption");
				clickbyid(fifthanswer, "Click on fourthoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");
				clickbyid(thirdanswer, "Click on secondoption");
				clickbyid(secondanswer, "Click on secondoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(fifthanswer, "Click on fourthoption");
				clickbyid(fourthanswer, "Click on fourthoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on nextpage");
			}
			String questionTxt2=getText(question3, "Get the text of third question");
			System.out.println("questionTxt: "+questionTxt2);
			if(questionTxt2.equals("What is the purpose for NANDA-I to continue to meet every two years?"))
			{   
				System.out.println("select first options");
				clickbyid(secondOption, "click on firstoption");
				clickbyid(submit, "Click on submit answer");
				clickbyid(Return, "click on return button");    
				clickbyid(thirdOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				clickbyid(Return, "click on return button");
				clickbyid(fourthOption, "click on firstoption");
				clickbyid(submit1, "click on submit button");
				System.out.println("System Selected right answer");
				Thread.sleep(3000);
				clickbyid(submit1, "click on submit button");
				clickbyid(nextpage1, "Click on nextpage");							
			}
	}
	

	
	public void lesson() throws Exception
	{
		clickbyid(nextpage1, "click on next button");
		clickbyid(Ready, "click on Ready button");
		clickbyid(firstOption, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected first question answer");
		
		clickbyid(fourthOption, "Click on fourthoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected second question answer");
		
		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected third question answer");
		
		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected fourth question answer");
		
		clickbyid(fifthanswer, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected fifth question answer");
		
		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected six question answer");
		
		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected seventh question answer");
		
		clickbyid(firstOption, "Click on firstoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected eighth question answer");
		
		clickbyid(thirdOption, "Click on thirdoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected ninth question answers");
		
		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected tenth question answer");
		
		clickbyid(firstanswer, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected levelnth question answer");
		
		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected twelth question answer");
		
		clickbyid(fourthOption, "Click on fifthoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected thirteen question answer");
		
		clickbyid(fifthanswer, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected fourteen question answer");
		
		clickbyid(secondOption, "Click on secondoption");
		clickbyid(submit1, "Click on submit answer");
		System.out.println("selected fiftheen question answer");
		
	}

	public void logout() throws Exception
	{
		clickbyid(incorrect, "click on incorrect tab");
		clickbyid(correct, "click on correct tab");
	}
}
