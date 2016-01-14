package com.mop.qa.test.bvt;

import io.appium.java_client.AppiumDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.experitest.client.Client;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.Utilities.ReportGenerator;
import com.mop.qa.Utilities.TestReportGenerator;
import com.mop.qa.testbase.PageBase_Appium;
import com.mop.qa.testbase.PageBase_SeeTest;

public class TestBase {

	public static long startTime;
	public static String startTimeUpdate;
	public static long endTime;
	public static long totalTime;
	public static String totalTimeTaken;
	public static String osType = System.getProperty("os.name");
	static TestReportGenerator tr = new TestReportGenerator();

	public static AppiumDriver appiumDriver;
	public static RemoteWebDriver remoteDriver;
	public static Client client = null;
	ReadDataSheet rds = new ReadDataSheet();
	ReportGenerator reportgenerator = new ReportGenerator();

	@BeforeSuite
	public void executeSuite(ITestContext ctx) {
		try {
			// ReportGenerator reportgenerator = new ReportGenerator();
			reportgenerator.generateReport(ctx.getSuite().getName());
		String devicePlatform = null;
		if (rds.getAppProperties("tool").equalsIgnoreCase("Appium")) {
			PageBase_Appium pagebaseclass = new PageBase_Appium(appiumDriver);
			appiumDriver = pagebaseclass.launchApp();
			//appiumDriver.get("http://www.nbcnews.com");
		}
		if (rds.getAppProperties("tool").equalsIgnoreCase("Seetest")) {
			devicePlatform = getPropertyValue("device_Platform_SeeTest");
			PageBase_SeeTest pagebaseclass = new PageBase_SeeTest();
			client = pagebaseclass.launch();
			client.setDevice(getPropertyValue("deviceName_SeeTest"));
			if (devicePlatform.equalsIgnoreCase("Android")) {
				client.launch(getPropertyValue("appName_SeeTest_Android"), true, false);
			} else{
				client.launch(getPropertyValue("appName_SeeTest_iOS"), true, false);
			}
		} else if (rds.getAppProperties("tool").equalsIgnoreCase("selenium")) {
			PageBase_Appium pagebaseclass = new PageBase_Appium(remoteDriver);
			remoteDriver = pagebaseclass.launchSite();
			//remoteDriver.get(rds.getAppProperties("URL"));
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getSuiteName(ISuite ist) {

		try {
			System.out.println("" + ist.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeClass
	public void setUp() throws Exception {
	/*	String devicePlatform = null;
		if (rds.getAppProperties("tool").equalsIgnoreCase("Appium")) {
			PageBase_Appium pagebaseclass = new PageBase_Appium(appiumDriver);
			appiumDriver = pagebaseclass.launchApp();
			//appiumDriver.get("http://www.nbcnews.com");
		}
		if (rds.getAppProperties("tool").equalsIgnoreCase("Seetest")) {
			devicePlatform = getPropertyValue("device_Platform_SeeTest");
			PageBase_SeeTest pagebaseclass = new PageBase_SeeTest();
			client = pagebaseclass.launch();
			client.setDevice(getPropertyValue("deviceName_SeeTest"));
			if (devicePlatform.equalsIgnoreCase("Android")) {
				client.launch(getPropertyValue("appName_SeeTest_Android"), true, false);
			} else{
				client.launch(getPropertyValue("appName_SeeTest_iOS"), true, false);
			}
		} else if (rds.getAppProperties("tool").equalsIgnoreCase("selenium")) {
			PageBase_Appium pagebaseclass = new PageBase_Appium(remoteDriver);
			remoteDriver = pagebaseclass.launchSite();
			//remoteDriver.get(rds.getAppProperties("URL"));
		}*/
	}

	@BeforeTest
	public void startTestReport(ITestContext ctx) {

		try {
			System.out.println("test name is " + ctx.getName());
			reportgenerator.startTestCase(ctx.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void teardown() throws Exception {
		try {
			if (rds.getAppProperties("tool").equalsIgnoreCase("Appium")) {
				// appiumDriver.closeApp();
				//appiumDriver.quit();
			} else {
				//client.generateReport();
				// remoteDriver.quit();
			}

			// driver.quit();
			// System.out.println("@@@@@@@@@@@@@@@@@@       afterclass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void finishExecution() throws Exception {

		try {
			ReportGenerator reportgenerator = new ReportGenerator();
			reportgenerator.totalResult();
			if (remoteDriver != null) {
				remoteDriver.quit();
				// remoteDriver.close();
			}
			if (appiumDriver != null) {
				appiumDriver.quit();
				// appiumDriver.close();
			}
			if (client != null) {
				client.closeDevice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void startIOSServerForAndroidDevice() throws IOException,
			InterruptedException {
		System.out.println("generate report");
		File wd = new File(".");
		System.out.println(wd);
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("/bin/bash", null, wd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (proc != null) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(proc.getOutputStream())), true);
			out.println("pwd");
			String nodePathForAndroid = getPropertyValue("appium_ServerPath_Mac");
			// String nodePathForiOS = getPropertyValue("nodePathForiOSDevice");
			// out.println("./iOScmd.sh");
			out.println(nodePathForAndroid);
			out.println("exit");

			try {
				String line;
				while ((line = in.readLine()) != null) {
					if (line.contains("LogLevel: debug")) {
						System.out.println("process value is "
								+ proc.exitValue());
					}
				}

				proc.waitFor();
				in.close();
				out.close();
				proc.destroy();
			} catch (Exception e) {
				// e.printStackTrace();
				return;
			}
		}
	}

	public static void startIOSServerForiOSDevice() throws IOException,
			InterruptedException {

		try {
			System.out.println("inside  iosserver");
			Process p = Runtime
					.getRuntime()
					.exec("open -a /Applications/Utilities/Terminal.app ./startAppiumServer.sh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
	}

	public static void startWindowsServer() throws IOException,
			InterruptedException {
		try {
			String appiumPortVal = getPropertyValue("appiumPort");
			String nodePath_windows = getPropertyValue("nodePath_windows");
			String appiumJSPath_windows = getPropertyValue("appiumJSPath_windows");

			CommandLine command = new CommandLine(nodePath_windows);
			command.addArgument(appiumJSPath_windows, false);
			command.addArgument("--address", false);
			command.addArgument("127.0.0.1");
			command.addArgument("--port", false);
			command.addArgument(appiumPortVal);
			command.addArgument("--no-reset", false);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command, resultHandler);
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stopServer() {
		try {
			String filePath = "";
			if (System.getProperty("os.name").contains("Win")) {

				filePath = "cmd /c taskkill /F /IM node.exe";
				Runtime.getRuntime().exec(filePath);
			} else {

				Runtime.getRuntime().exec(
						new String[] { "bash", "-c", "killall node" });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) throws IOException {
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

	public static void updateProperty(String updateTime, String startTime) {
		try {

			FileInputStream in = new FileInputStream("report.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream("report.properties");
			props.setProperty("TOTAL_TIME", totalTimeTaken.toString());
			props.setProperty("RUN_STARTED", startTime.toString());
			props.store(out, null);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
