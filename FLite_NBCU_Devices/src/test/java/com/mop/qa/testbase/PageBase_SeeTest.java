package com.mop.qa.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.experitest.client.Client;
import com.mop.qa.Utilities.ReportGenerator;



public class PageBase_SeeTest {

	public static Client client = null;

	public int count3 = 1;

	public static int n = 0;
	public String text1 = null;
	ReportGenerator rg = new ReportGenerator();
	public String appName = null, devicePlatform = null, deviceName = null,
			platformName = null, platformVersion = null;

	private String host = "localhost";
	private int port = 8889;
	private String projectBaseDirectory = null;

	public Client launch() throws IOException {
		projectBaseDirectory = getValue("seetest_workspace");
		devicePlatform = getValue("device_Platform_SeeTest");
		if (devicePlatform.equalsIgnoreCase("Android")) {
			appName = getValue("appName_SeeTest_Android");
		} else{
			appName = getValue("appName_SeeTest_iOS");
		}
		deviceName = getValue("deviceName_SeeTest");
		client = new Client(host, port, true);
		client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "reports", "Untitled");
		// client.setDevice(deviceName);
		// client.uninstall(appName);
		// client.install(appName, true, false);
		// client.launch(appName, true, false);

		return client;
	}

	public String getValue(String key) throws IOException {
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

	public void click(String zone, String key, int index, int timeOut, 
			String element) throws Exception {

		try {
			// screenshot();
			client.waitForElement(zone, key, index, timeOut);
			client.click(zone, key, 0, 1);

			rg.passTestCase("click on element " + "" + element + ""
					+ " successful");

		} catch (Exception exc) {
			exc.printStackTrace();

			rg.logException("Exception on clicking " + element, exc);

		}

	}

	public void clickByCoordinates(int x, int y, int clickCount)
			throws Exception {

		try {
			// screenshot();

			client.clickCoordinate(x, y, clickCount);

			rg.passTestCase("click on coordinates successful");

		} catch (Exception exc) {
			exc.printStackTrace();

			rg.logException("Exception on clicking ", exc);

		}

	}
	public void clickMultipleButtons(String button1, String button2 , String message)
			throws Exception {

		try {
			client.click("NATIVE", button1, 0, 1);
			client.click("NATIVE", button2, 0, 1);
			rg.passTestCase(message);
		} catch (Exception exc) {
			exc.printStackTrace();
			rg.failTestCase("Failed to click on both the elements");
		}

	}
	public void clickMultipleByCoordinates(int x, int y, int clickCount)
			throws Exception {

		try {
			client.click("NATIVE",
					"xpath=//*[@accessibilityLabel='PauseButton']", 0, 1);
			client.click("NATIVE",
					"xpath=//*[@accessibilityLabel='PauseButton']", 0, 1);
			// client.clickCoordinate(x, y, clickCount);
			// client.clickCoordinate(x, y, clickCount);
			rg.passTestCase("click on coordinates successful");
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public void longClick(String zone, String key, int index, int clickcount,
			int x, int y) {
		client.longClick(zone, key, index, clickcount, x, y);
	}

	public static String screenshot() throws IOException, InterruptedException {
		n = n + 1;
	String imgPath = client.capture("image");
	//client.sleep(10000);

	return imgPath;
	}

	public boolean isElementFound(String zone, String Element, int index)
			throws Exception {
			client.waitForElement(zone, Element, index, 3000);
			boolean res = client.isElementFound(zone, Element);
			return res;		

	}

	public void elementSendText(String zone, String Element, int index,
			String text) throws Exception {

		try {
			// screenshot();
			client.waitForElement(zone, Element, index, 3000);
			client.elementSendText(zone, Element, index, text);
			client.click(zone, Element, index, 1);
			if (getValue("platformName").equalsIgnoreCase("iOS")) {
				client.click("NATIVE", "xpath=//*[@text='search']", 0, 1);
			} else {
				client.sendText("{ENTER}");

				/*
				 * client.click( "NATIVE",
				 * "xpath=//*[@class='android.view.View' and @width>0 and @height>0 and ./following-sibling::*[./*[@class='android.widget.FrameLayout']] and ./parent::*[@class='android.widget.FrameLayout']]"
				 * , 0, 1);
				 */
			}

			// client.click(zone, "xpath=//*[@text='search']", index, 1);
			// client.sendText("{ENTER}");

			rg.passTestCase("Enter text in Element " + "" + Element + ""
					+ " is Succesful");

		} catch (Exception exc) {
			rg.logException("Exception in entering text in element " + Element,
					exc);
		}

	}
	
	public void elementSendTextOnly(String text) throws Exception {
		try {
				client.sendText(text);
    			rg.passTestCase("Enter text in is Succesful");
		} catch (Exception exc) {
			rg.logException("Exception in entering text in element " ,
					exc);
		}

	}

	public String elementGetText(String zone, String element, int index, String property,
			String text) throws Exception {

		String resultText = null;

		try {
			//resultText = client.elementGetText(zone, element, index);
			//System.out.println("accessablity label is 111111111111111" + resultText);
			String[] values = client.getAllValues(zone , element, property);
			for(String str:values){
				resultText = str;
			}
			rg.passTestCase("Get text from Element " + "" + text + ""
					+ " is Succesful");

		} catch (Exception exc) {
			rg.logException(
					"Exception in getting text from element " + text, exc);
		}
		return resultText;

	}
	public void verifyIfPresent( String zone ,String element, String property,  String savedTitle , String message) throws Exception {
		
		String[] values = client.getAllValues(zone, element, property);
		String isPresent = "false";
		for(String label:values){
			if(label.contains(savedTitle)){
				isPresent = "true";
				rg.passTestCase(message + "is successful");
				break;
			}
			else{
				isPresent = "false";
			}
		}
		if(isPresent.equals("false")){
			rg.passTestCase(message+ "is failed");
		}
		
	}
	public void verifySearchElement( String zone ,String element, String property,  String searchInput, String message) throws Exception {
		String[] values = client.getAllValues(zone, element, property);
		String isPresent = "false";
		for(String label:values){
			if(label.contains(searchInput)){
				isPresent = "true";
				rg.passTestCase(message +  "is successful");
				//break;
			}
			else{
				isPresent = "false";
			}
		}
		if(isPresent.equals("false")){
			rg.passTestCase(message + "is failed");
		}
		
	}

}

