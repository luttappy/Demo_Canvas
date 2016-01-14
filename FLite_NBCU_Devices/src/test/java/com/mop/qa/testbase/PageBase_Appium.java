package com.mop.qa.testbase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.ScrollsTo;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jboss.netty.util.Timeout;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import android.app.Activity;
import android.provider.Browser;
import android.view.WindowManager.LayoutParams;

import com.experitest.client.Client;
import com.mop.qa.Utilities.ReportGenerator;
import com.mop.qa.test.bvt.TestBase;
import com.mop.qa.test.bvt.TestSuite;

public class PageBase_Appium {

	protected static RemoteWebDriver remoteDriver;
	private static AppiumDriver appiumDriver;
	public static Client client = null;
	private static String toolName;

	public PageBase_Appium(AppiumDriver driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(appiumDriver, this);
		toolName = "Appium";
	}

	public PageBase_Appium(RemoteWebDriver driver) {
		this.remoteDriver = driver;
		PageFactory.initElements(remoteDriver, this);
		toolName = "Selenium";
	}

	public PageBase_Appium() {
		try {
			if (getAppProperties("tool").equalsIgnoreCase("selenium")) {
				this.remoteDriver = TestBase.remoteDriver;
				PageFactory.initElements(remoteDriver, this);
				toolName = "Selenium";

			} else if (getAppProperties("tool").equalsIgnoreCase("appium")) {
				this.appiumDriver = TestBase.appiumDriver;
				PageFactory.initElements(appiumDriver, this);
				toolName = "Appium";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int count3 = 1;
	public static int n = 0;
	public String text1 = null;
	ReportGenerator rg = new ReportGenerator();
	public Screen screen = new Screen();
	public String tool = null, appType = null, webBrowser = null,
			chromeDriverPath = null, fireFoxDriverPath = null,
			deviceName = null, appName = null, appiumPort = null,
			deviceVersion = null, appPackage = null, appActivity = null,
			Android_Appium_Server_Path = null, appiumPort_Ios = null,
			devicePlatformName_Ios = null, deviceVersion_Ios = null,
			device_UDID = null, platformName = null, applicationPath = null,
			appiumURL = null;

	public AppiumDriver launchApp() throws IOException, InterruptedException {
		tool = getAppProperties("tool");
		appType = getAppProperties("appType");
		platformName = getAppProperties("platformName");

		if (tool.equalsIgnoreCase("Appium")) {
			if ((platformName.equalsIgnoreCase("iOS"))
					&& (appType.equalsIgnoreCase("Native"))) {

				appiumPort_Ios = getAppProperties("appiumPort_Ios");
				deviceVersion_Ios = getAppProperties("deviceVersion_Ios");
				device_UDID = getAppProperties("device_UDID");
				applicationPath = getAppProperties("applicationPath");
				appiumURL = "http://127.0.0.1:" + appiumPort_Ios + "/wd/hub";

				DesiredCapabilities capabilities = new DesiredCapabilities();

				capabilities.setCapability("appium-version", "1.0");
				capabilities.setCapability("platformName", platformName);
				capabilities
						.setCapability("platformVersion", deviceVersion_Ios);
				capabilities.setCapability("deviceName", device_UDID);
				capabilities.setCapability("app", applicationPath);
				if (appiumDriver == null)
					appiumDriver = null;

				appiumDriver = new IOSDriver(new URL(appiumURL), capabilities);

			} else if ((platformName.equalsIgnoreCase("Android"))
					&& (appType.equalsIgnoreCase("Native"))) {

				System.out.println("Android Native");

				appName = getAppProperties("appName");
				deviceVersion = getAppProperties("deviceVersion");
				deviceName = getAppProperties("deviceName");
				appPackage = getAppProperties("appPackage");
				appActivity = getAppProperties("appActivity");
				appiumPort = getAppProperties("appiumPort");
				appiumURL = "http://127.0.0.1:" + appiumPort + "/wd/hub";

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("appium-version", "1.0");
				capabilities.setCapability("app", appName);
				capabilities.setCapability("platformName", platformName);
				capabilities.setCapability("platformVersion", deviceVersion);
				capabilities.setCapability("deviceName", deviceName);
				capabilities.setCapability("appPackage", appPackage);
				capabilities.setCapability("appActivity", appActivity);

				appiumDriver = new AndroidDriver(new URL(appiumURL),
						capabilities);
				// }
				appiumDriver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.SECONDS);
			}

			else if ((platformName.equalsIgnoreCase("Android"))
					&& (appType.equalsIgnoreCase("Web"))) {
				System.out.println("Android Browser");

				deviceVersion = getAppProperties("deviceVersion");
				deviceName = getAppProperties("deviceName");
				appiumPort = getAppProperties("appiumPort");
				appiumURL = "http://127.0.0.1:" + appiumPort + "/wd/hub";

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.BROWSER_NAME,
						"Chrome");
				capabilities.setCapability("newCommandTimeout", "300");
				capabilities.setCapability("appium-version", "1.0");
				capabilities.setCapability("platformName", platformName);
				capabilities.setCapability("deviceName", deviceName);
				capabilities.setCapability("platformVersion", deviceVersion);

				if (appiumDriver == null) {
					appiumDriver = new AndroidDriver(new URL(appiumURL),
							capabilities);
				}
				appiumDriver.manage().timeouts()
						.implicitlyWait(60, TimeUnit.SECONDS);
			} else if ((platformName.equalsIgnoreCase("iOS"))
					&& (appType.equalsIgnoreCase("Web"))) {
				System.out.println("iOS Browser");

				deviceVersion = getAppProperties("deviceVersion_Ios");
				deviceName = getAppProperties("device_UDID");
				appiumPort = getAppProperties("appiumPort");
				appiumURL = "http://127.0.0.1:" + appiumPort + "/wd/hub";

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("deviceName", "iPhone");
				cap.setCapability("browserName", "Safari");
				cap.setCapability("platformVersion", deviceVersion);
				cap.setCapability("platformName", platformName);
				cap.setCapability("udid",deviceName);

				if (appiumDriver == null) {
					System.out.println("before initializing mobile web");
					System.out.println("before appiumURL ---" + appiumURL);
					appiumDriver = new IOSDriver(new URL(appiumURL), cap);
					System.out.println("after initializing mobile web");
					appiumDriver.get("www.facebook.com");
					System.out.println("after initializing mobile web++");

				}
				System.out.println("after again");
				appiumDriver.manage().timeouts()
						.implicitlyWait(60, TimeUnit.SECONDS);
			}
		}

		return appiumDriver;
	}

	public RemoteWebDriver launchSite() throws Exception {
		webBrowser = getAppProperties("webBrowser");
		if (webBrowser.equalsIgnoreCase("chrome")) {
			chromeDriverPath = getAppProperties("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("start-maximized");
			remoteDriver = new ChromeDriver(options);

		} else if (webBrowser.equalsIgnoreCase("firefox")) {
			fireFoxDriverPath = getAppProperties("fireFoxDriverPath");
			File pathToFirefoxBinary = new File(fireFoxDriverPath);
			FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
			remoteDriver = new FirefoxDriver(firefoxbin, null);
			remoteDriver.manage().window().maximize();

		}
		// remoteDriver.manage().window().maximize();
		remoteDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return remoteDriver;

	}

	public String getAppProperties(String key) throws IOException {
		String value = "";
		try {

			FileInputStream fileInputStream = new FileInputStream(
					"data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public void enterUrl(String url) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				appiumDriver.get(url);
				break;
			case "Selenium":
				remoteDriver.get(url);
				break;
			}
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void getTitle() throws Exception {
		System.out.println("--------" + appiumDriver.getTitle());
	}

	public void clickPoint(WebElement e, String elementName) throws Exception {
		// Point point=e.getLocation();
		int xx = e.getLocation().x;
		int yy = e.getLocation().y;
		// e.getLocation().x;

		System.out.println("X Position : " + xx);
		System.out.println("Y Position : " + yy);
		clickCoordinates(xx, yy);
	}

	public void click(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			e.click();

			rg.passTestCase("click on element " + elementName + ""
					+ " successful");

		} catch (Exception exc) {
			exc.printStackTrace();
			rg.logException("Exception on clicking webelement" + elementName,
					exc);

		}

	}

	public void clickByCSS(String e, String text) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				// appiumDriver.findElementByCssSelector(e).click();
				
				List<WebElement> li = appiumDriver.findElementsByCssSelector(e);
				
				
				break;
			case "Selenium":
				remoteDriver.findElementByCssSelector(e).click();
				break;
			}
			rg.passTestCase("click on element " + e + "" + " successful");

		} catch (Exception exc) {
			exc.printStackTrace();
			rg.logException("Exception on clicking webelement" + e, exc);

		}

	}

	// appiumDriver.findElementByCssSelector(using);
	public void clickWithoutWait(WebElement e, String elementName)
			throws Exception {
		try {
			// System.out.println("11111222" + toolName);
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				Thread.sleep(10000);
				// WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
				// 60, 500);
				// waitSelenium.until(ExpectedConditions.visibilityOf(e));
				// waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			e.click();
			rg.passTestCase("click on element " + elementName + ""
					+ " successful");

		} catch (Exception exc) {
			exc.printStackTrace();
			rg.logException("Exception on clicking webelement" + elementName,
					exc);

		}

	}

	public void clickNewWindow(WebElement e, String elementName)
			throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;
			}
			e.click();
		} catch (Exception exc) {
			exc.printStackTrace();
			rg.logException("Exception on clicking webelement" + elementName,exc);
		}

	}

	public void click(String xpath, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				/*
				 * waitSelenium.until(ExpectedConditions
				 * .visibilityOfElementLocated(By.xpath(xpath)));
				 */
				remoteDriver.findElementByXPath(xpath).click();
				break;
			}

			rg.passTestCase("Click on Element " + elementName + " successful");

		} catch (Exception exc) {
			rg.logException("Exception in clicking " + elementName, exc);
		}
	}

	public void clickbyid(String id, String elementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
				appiumDriver.findElementById(id).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						100, 1000);
				/*
				 * waitSelenium.until(ExpectedConditions
				 * .visibilityOfElementLocated(By.id(id)));
				 */
				remoteDriver.findElementById(id).click();
				break;
			}
			rg.passTestCase("Click on Element " + elementName + " successful");

		} catch (Exception exc) {

			exc.printStackTrace();
			rg.logException("Exception in clicking " + elementName, exc);

		}
	}

	public void clickbyClassName(String className, String elementName)
			throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.className(className)));
				appiumDriver.findElementByClassName(className).click();

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						100, 1000);
				remoteDriver.findElementByClassName(className).click();
				break;
			}
			rg.passTestCase("Click on Element " + elementName + " successful");

		} catch (Exception exc) {

			exc.printStackTrace();
			rg.logException("Exception in clicking " + elementName, exc);

		}
	}

	public void clickByElementName(String name, String elementName)
			throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.name(name)));
				appiumDriver.findElementByName(name).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(By
						.name(name)));
				remoteDriver.findElementByName(name).click();
				break;
			}

			rg.passTestCase("Click on Element " + elementName + " successful");

		} catch (Exception exc) {

			rg.logException("Exception in clicking " + elementName, exc);

		}
	}

	public void clickAlert() throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				Alert a2 = remoteDriver.switchTo().alert();
				a2.accept();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);

				Alert a1 = remoteDriver.switchTo().alert();
				a1.accept();

				break;
			}

			// rg.passTestCase("Click on Alert Successful");

		} catch (Exception exc) {
			exc.printStackTrace();

			rg.logException("Exception in clicking ", exc);

		}
	}

	public void dragAndDrop(WebElement e1, WebElement e2) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				Actions action = new Actions(appiumDriver);
				action.dragAndDrop(e1, e2).perform();

				break;
			case "Selenium":
				Actions action1 = new Actions(remoteDriver);
				action1.dragAndDrop(e1, e2).perform();

				break;
			}

			// rg.passTestCase("Click on Alert Successful");

		} catch (Exception exc) {
			exc.printStackTrace();

			rg.logException("Exception in clicking ", exc);

		}
	}

	public void MicroQuiz() throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				System.out.println("Navigated to the page micro-quiz!");

				break;
			case "Selenium":
				System.out.println("Navigated to the page micro-quiz!");
				break;
			}

			// rg.passTestCase("Click on Alert Successful");

		} catch (Exception exc) {
			exc.printStackTrace();

			rg.logException("Exception in clicking ", exc);

		}
	}

	public String getText(WebElement e, String elementName) throws Exception {

		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60,
					500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getText();
		rg.passTestCase("get text from " + elementName + " successful");
		return text;

	}

	public String getText(WebElement e) throws Exception {
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60,
					500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getText();
		return text;
	}

	public String driverSwitching(String articleTitlePath, String articleTitle)
			throws Exception {
		String storyTitle = "";
		try {
			Set<String> contextNames = (appiumDriver).getContextHandles();
			System.out.println("total contexts is 00000000000000000 "
					+ contextNames.size());

			for (String contextName : contextNames) {
				System.out.println(contextNames); // prints out something like

				// Thread.sleep(10000);
				if (contextName.contains("WEBVIEW")) {
					(appiumDriver).context(contextName);
					System.out.println("current context is "+ (appiumDriver).getContext());
					Set<String> windowhandles = (appiumDriver).getWindowHandles();
					System.out.println("window handles are ++++++++++++++++ "+ windowhandles);
					for (String s : windowhandles) {
						System.out.println("current window handle is ------------"+ s);
						(appiumDriver).switchTo().window(s);

						try {
							System.out.println("current window handle articleTitle is ------------"+ articleTitlePath);
							if (elementIsDisplayed(articleTitlePath)) {
								storyTitle = appiumDriver.findElementByXPath(articleTitlePath).getText();
								System.out.println("storyTitle///" + storyTitle);
								if (storyTitle.equalsIgnoreCase(articleTitle)) {
									rg.passTestCase("Proper Article opened");
									System.out.println("Proper Article opened");
									break;
								}

							}
						} catch (Exception e) { // TODO
							e.printStackTrace();
							(appiumDriver).context("NATIVE_APP");
							// break;
						}
					}
				}
			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("storyTitle ---" + storyTitle);

		return storyTitle;
	}

	public void verifyIfPresent(String savedTitle, String path1, String path2)
			throws Exception {
		int res = 0;
		List<WebElement> totalList = appiumDriver
				.findElementsByXPath("//android.widget.ListView[@resource-id='com.nbcnews.today.beta:id/secondaryCoverList']/android.widget.LinearLayout");
		int listSize = totalList.size();
		for (int i = 1; i <= listSize; i++) {
			String saveList = path1 + i + path2;
			WebElement we = appiumDriver.findElement(By.xpath(saveList));
			String titlePresent = getText(we);
			if (savedTitle.equalsIgnoreCase(titlePresent)) {
				rg.passTestCase("Article is present");
				res = 1;
				break;
			}
		}
		if (res == 0) {
			System.out.println("Article is not present");
			rg.failTestCase("Article is not present");
		}

	}

	public void verifyIfPresentiOS(String savedTitle, String path1, String path2)
			throws Exception {
		List totalList = appiumDriver
				.findElementsByClassName("UIACollectionCell");
		int res = 0;
		int listSize = totalList.size();
		for (int i = 1; i <= listSize; i++) {
			String saveList = path1 + i + path2;
			WebElement we = appiumDriver.findElement(By.xpath(saveList));
			String titlePresent = getText(we);
			if (savedTitle.equalsIgnoreCase(titlePresent)) {
				System.out.println("Article is present");
				rg.passTestCase("Article is present");
				res = 1;
				break;
			}
		}
		if (res == 0) {
			System.out.println("Article is not present");
			rg.failTestCase("Article is not present");
		}
	}

	public void verifySearchKey(String key, String path1, String path2)
			throws Exception {
		int res = 0;
		List totalList = appiumDriver
				.findElementsByClassName("UIACollectionCell");
		int listSize = totalList.size();
		for (int i = 1; i <= listSize; i++) {
			String saveList = path1 + i + path2;
			WebElement we = appiumDriver.findElement(By.xpath(saveList));
			String artTitle = getText(we);
			if (artTitle.contains(key)) {
				res = 1;
			} else {
				res = 0;
			}
		}
		if (res == 1) {
			rg.passTestCase(" Verify search key Success");
		} else if (res == 0) {
			rg.failTestCase("Problem with search key");
		}
		if (listSize == 0) {
			System.out.println("No Article for the search key");
		}
	}

	public void webView() throws InterruptedException {
		System.out.println("qwretrnghkjmdlf;kgz;gh");

		System.out.println("clvjadsgkl'jadfsgdfklskhjdfkljn");
	}

	public void clickArticle(String latestArtTitle, String path1, String path2)
			throws Exception {

		for (int i = 1; i > 0; i++) {
			// android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ListView[1]/android.widget.LinearLayout[4]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]
			String xpathArt = path1 + i + path2;
			System.out.println("xpath-->" + xpathArt);
			System.out.println("title requested -->" + latestArtTitle);
			String title = getText(remoteDriver.findElement(By.xpath(xpathArt)));
			System.out.println("title present -->" + title);
			if (latestArtTitle.equalsIgnoreCase(title)) {
				click(xpathArt, "Click on Article");
				break;
			}
		}
	}

	public void clickMultipleButtons(WebElement tab, WebElement pause,
			String elementName) throws Exception {

		try {
			Thread.sleep(20000);
			if (elementIsDisplayed(pause, "pausebutton")) {
				pause.click();
			} else {
				tab.click();
				pause.click();
			}
			Thread.sleep(10000);

			rg.passTestCase("click on element " + elementName + ""
					+ " successful");

		} catch (Exception exc) {
			exc.printStackTrace();
			rg.logException("Exception on clicking webelement" + elementName,
					exc);
		}

	}

	public void selectArticle(String articleName, String classText)
			throws Exception {
		List<WebElement> webElementList = remoteDriver.findElements(By
				.className(classText));

		for (WebElement we : webElementList) {
			if (we.getText().equalsIgnoreCase(articleName)) {
				try {
					appiumDriver.manage().timeouts()
							.implicitlyWait(10, TimeUnit.SECONDS);
					we.click();
					System.out.println("Click Article Success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void switchToWindowTitle() throws Exception {

		try {
			
			switch (toolName) {
			case "Appium":
				Thread.sleep(10000);
			int size =	appiumDriver.getWindowHandles().size();
				for (String winHandle : appiumDriver.getWindowHandles()) {
					appiumDriver.switchTo().window(winHandle);
					//if(Sherpath)
				}
				
				break;
			case "Selenium":
				Thread.sleep(10000);
				for (String winHandle : remoteDriver.getWindowHandles()) {
					remoteDriver.switchTo().window(winHandle);
				}
				
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}
	
	public String getParentWindow() throws Exception {
		String parentWindow = null;
		if(toolName.equalsIgnoreCase("Appium")){
			parentWindow = appiumDriver.getWindowHandle();
		}
		else if(toolName.equalsIgnoreCase("Selenium")){
			parentWindow = remoteDriver.getWindowHandle();
		}
		return parentWindow;
	}
	
	public void  switchToParentWindow(String parentWindow) throws Exception {
		
		if(toolName.equalsIgnoreCase("Appium")){
			appiumDriver.close();
			appiumDriver.switchTo().window(parentWindow);
		}
		else if(toolName.equalsIgnoreCase("Selenium")){
			remoteDriver.close();
			remoteDriver.switchTo().window(parentWindow);
		}
	}
	
	public WebElement getElement(String xpath) throws Exception {
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(appiumDriver
					.findElementByXPath(xpath)));
			WebElement we = appiumDriver.findElementByXPath(xpath);
			return we;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60,
					500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver
					.findElementByXPath(xpath)));
			WebElement weSelenium = remoteDriver.findElementByXPath(xpath);
			return weSelenium;
		}

		rg.passTestCase("get text on webelement successful");
		return null;

	}

	public boolean verifyText(WebElement e, String value) {
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60,
					500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
		}
		if (e.getText().equalsIgnoreCase(value))
			return true;
		else
			return false;

	}

	public void enterText(WebElement url, String cred, String elementName)
			throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(url));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(url));
				break;
			}

			url.clear();
			url.sendKeys(cred);
			rg.passTestCase("enter text in " + elementName + " successful");
		} catch (Exception exc) {

			rg.logException("Enter text failed", exc);
		}

	}

	public boolean elementIsDisplayed(WebElement e, String ElementName)
			throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;

			}

		} catch (Exception exc) {

		}
		try {
			if (e.isDisplayed()) {
				rg.passTestCase(ElementName + " is displayed");

				return true;
			} else
				return false;
		} catch (Exception exc) {

			return false;

		}

	}

	public boolean elementIsEnabled(WebElement e) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
			}
		} catch (Exception exc) {

			rg.logException("Get element visibilty failed", exc);
		}
		try {
			if (e.isEnabled()) {

				return true;
			} else
				return false;
		} catch (Exception exc) {

			rg.logException("Get element visibilty failed", exc);
			return false;

		}

	}

	public boolean elementIsDisplayed(String xpath) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver
						.findElementByXPath(xpath)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver
						.findElementByXPath(xpath)));
				break;
			}

		} catch (Exception exc) {

			return false;

		}
		return true;

	}

	public boolean elementIsDisplayedByName(String name) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver
						.findElementByName(name)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver,
						60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver
						.findElementByName(name)));
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;

	}

	public void scroll(String key) {
		switch (toolName) {
		case "Appium":
			for (int i = 0;; i++) {
				boolean shouldBreak = false;

				List<WebElement> listObject = appiumDriver.findElements(By
						.name("dropdownViewCell_" + i + ""));
				while ((listObject.size()) == 0)
					break;

				for (WebElement wb : listObject) {

					if ((wb.getText().equalsIgnoreCase(key))) {
						wb.click();
						shouldBreak = true;
						break;
					}

				}
				if (shouldBreak)
					break;

			}

		case "Selenium":
			for (int i = 0;; i++) {
				boolean shouldBreak = false;

				List<WebElement> listObject = remoteDriver.findElements(By
						.name("dropdownViewCell_" + i + ""));
				while ((listObject.size()) == 0)
					break;

				for (WebElement wb : listObject) {

					if ((wb.getText().equalsIgnoreCase(key))) {
						wb.click();
						shouldBreak = true;
						break;
					}

				}
				if (shouldBreak)
					break;

			}

		}

	}

	public void clickCoordinates(final int x, final int y) {
		switch (toolName) {
		case "Appium":
			appiumDriver.executeScript("mobile: tap",
					new HashMap<String, Integer>() {
						{
							put("tapCount", (int) 1);
							put("touchCount", (int) 1);
							put("duration", (int) 0.5);
							put("x", x);
							put("y", y);
						}
					});
			break;
		case "Selenium":
			remoteDriver.executeScript("mobile: tap",
					new HashMap<String, Integer>() {
						{
							put("tapCount", (int) 1);
							put("touchCount", (int) 1);
							put("duration", (int) 0.5);
							put("x", x);
							put("y", y);
						}
					});
			break;
		}
	}

	public void keyBoardActions(String text) {
		switch (toolName) {
		case "Appium":
			if (text.equalsIgnoreCase("return"))
				appiumDriver.findElementByName(text).click();
			else {
				for (int i = 0; i < text.length(); i++) {
					String alp = text.substring(i, i + 1);
					appiumDriver.findElementByName(alp).click();
				}
			}
		case "Selenium":
			if (text.equalsIgnoreCase("return"))
				remoteDriver.findElementByName(text).click();
			else {
				for (int i = 0; i < text.length(); i++) {
					String alp = text.substring(i, i + 1);
					remoteDriver.findElementByName(alp).click();
				}
			}
		}

	}

	public void scrollToExact(String key) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				((ScrollsTo) appiumDriver).scrollToExact(key);
			case "Selenium":
				((ScrollsTo) remoteDriver).scrollToExact(key);
			}
			rg.passTestCase("scroll to element " + "" + key + ""
					+ " successful");
		} catch (Exception exc) {
			rg.logException("Exception on scroll to element" + key, exc);

		}

	}

	public void navToHomePage() throws Exception {

		while (elementIsDisplayedByName("OK")) {

			clickByElementName("OK", "pop up");

		}

		while (!elementIsDisplayedByName("Logout")) {

			clickByElementName("Back", "Back Button");

		}
		click("//UIAApplication[1]/UIAWindow[1]/UIASegmentedControl[1]/UIAButton[1]",
				"Arrivals");

	}

	public void backButton() throws Exception {

		clickByElementName("Back", "Back Button");
	}

	/*
	 * public static void launchAppiumiOS() { System.out.println("open appium");
	 * File wd = new File(".");
	 * 
	 * Process proc = null; try { proc = Runtime.getRuntime().exec("/bin/bash",
	 * null, wd); } catch (IOException e) { e.printStackTrace(); } if (proc !=
	 * null) { BufferedReader in = new BufferedReader(new InputStreamReader(
	 * proc.getInputStream())); PrintWriter out = new PrintWriter(new
	 * BufferedWriter( new OutputStreamWriter(proc.getOutputStream())), true);
	 * out.println("pwd"); // out.println("cd ReportGenerator");
	 * out.println("./iOScmd.sh"); out.println("exit");
	 * System.out.println("launched"); try { String line; while ((line =
	 * in.readLine()) != null) { System.out.println(line);
	 * System.out.println("in while"); } // proc.waitFor(); in.close();
	 * out.close(); proc.destroy(); } catch (Exception e) { e.printStackTrace();
	 * } } }
	 */
	public static String screenshot() throws IOException, InterruptedException {
		String imgPath = null;
		Thread.sleep(2000);
		n = n + 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		switch (toolName) {
		case "Appium":
			File scrFile = ((TakesScreenshot) appiumDriver)
					.getScreenshotAs(OutputType.FILE);

			imgPath = ".//ReportGenerator//" + TestSuite.reportFolder
					+ "//screenshots" + "/" + "/" + timeStamp + ".png";
			FileUtils.copyFile(scrFile, new File(imgPath));
			break;

		case "Selenium":
			File scrFileSelenium = ((TakesScreenshot) remoteDriver)
					.getScreenshotAs(OutputType.FILE);

			imgPath = ".//ReportGenerator//" + TestSuite.reportFolder
					+ "//screenshots" + "/" + "/" + timeStamp + ".png";
			FileUtils.copyFile(scrFileSelenium, new File(imgPath));
			break;

		}
		return imgPath;
	}

	// nfr scenarios
	public void accessNotification() {
		AndroidDriver android = (AndroidDriver) this.appiumDriver;
		android.openNotifications();

	}

	public void brightness(Activity act) {

		LayoutParams br = act.getWindow().getAttributes();
		br.screenBrightness = (250);
		act.getWindow().setAttributes(br);

	}

	public void setDataConnection(boolean enable) {
		// TODO Auto-generated method stub
		if (this.appiumDriver instanceof AndroidDriver) {
			AndroidDriver android = (AndroidDriver) this.appiumDriver;
			NetworkConnectionSetting setting = android.getNetworkConnection();
			setting.dataEnabled();
			android.setNetworkConnection(setting);
			String mode = enable ? "ON" : "OFF";
			System.out.println("Current Status of data network:"
					+ setting.dataEnabled());
		}
	}

	public void setAirplaneConnection(boolean enable) {
		// TODO Auto-generated method stub
		if (this.appiumDriver instanceof AndroidDriver) {
			AndroidDriver android = (AndroidDriver) this.appiumDriver;
			NetworkConnectionSetting setting = android.getNetworkConnection();

			setting.setAirplaneMode(true);

			android.setNetworkConnection(setting);
			String mode = enable ? "ON" : "OFF";
		}
	}

	public void setWifiConnection(boolean enable) {
		// TODO Auto-generated method stub
		System.out.println("before +++++");
		if (this.appiumDriver instanceof AndroidDriver) {
			System.out.println("inside +++++");
			AndroidDriver android = (AndroidDriver) this.appiumDriver;
			NetworkConnectionSetting setting = android.getNetworkConnection();
			setting.setWifi(enable);
			android.setNetworkConnection(setting);
			System.out.println("Current Status of wifi:"
					+ setting.wifiEnabled());
			String mode = enable ? "ON" : "OFF";
		}
	}

	public void findScreen(String image) throws FindFailed {

		screen.find(image);

	}

	public void clickScreen(String image) throws FindFailed {

		screen.click(image);

	}

	public void pauseVid() throws FindFailed, InterruptedException {
		// TODO Auto-generated method stub
		// WebElement element =
		// remoteDriver.findElement(By.xpath("//*[starts-with(@id,'ssn_display'))]"));
		/*
		 * ((JavascriptExecutor) remoteDriver) .executeScript(
		 * "var elems = document.querySelectorAll('div[id^=\"npn\"]'); " +
		 * "elems.pause()");
		 */
		Screen screen = new Screen();
		/*
		 * remoteDriver.findElement(
		 * By.xpath("//object[@type=\"application/x-shockwave-flash\"]"))
		 * .click();
		 */
		/*
		 * System.out.println("Coordinates: " + listFlash.getLocation().x + ", "
		 * + listFlash.getLocation().y); System.out.println("Size: " +
		 * listFlash.getSize().width + ", " + listFlash.getSize().height);
		 */

		// Thread.sleep(5000);
		// WebElement flash = remoteDriver.findElement(
		// By.xpath("//object[@type=\"application/x-shockwave-flash\"]"));

		// Actions act = new Actions(remoteDriver);
		// /act.moveToElement(flash, 110, 677).click().build().perform();
		/*
		 * act.moveByOffset(110,677).build().perform();
		 * act.click().build().perform();
		 */

		// screen.waitVanish(target);
		// screen.find("D:\\selenium demo\\FLite\\pause.PNG");
		screen.click("/Users/mspiosteam/Documents/FLite_v1/pause.PNG");
		// Thread.sleep(15000);

	}

	public void keyboardActions(WebElement e, Keys key) {

		WebDriverWait wait = new WebDriverWait(remoteDriver, 60, 500);
		wait.until(ExpectedConditions.visibilityOf(e));
		e.sendKeys(key);

	}

}
