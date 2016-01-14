package com.mop.qa.Utilities;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import junit.framework.AssertionFailedError;

import com.mop.qa.testbase.PageBase_Appium;
import com.mop.qa.testbase.PageBase_SeeTest;
import com.thoughtworks.selenium.webdriven.commands.GetValue;

public class ReportGenerator {

	AppiumDriver driver;
	public static PrintWriter pr = null;
	boolean bFailCase;
	public static File fwOutFile = null;
	public static int iTcCount = 0;
	public static int iPassCount = 0;
	public static int iFailCount = 0;
	public static String imgName = null;
	String strResultFile = null;
	public static String imgPath = null;

	// public static String strResultFile = "ResultFile_" + getCurrentDate()
	// + ".html";

	public void generateReport(String strStatusMessage) throws Exception {
		strResultFile = "ResultFile_" + getCurrentDate() + ".html";
		iTcCount = 0;
		iPassCount = 0;
		iFailCount = 0;
		bFailCase = false;

		createReportFile();
		pr.println("<html>");
		pr.println("<p>                                                           </p>");
		pr.println("<table width=\"600\" border=\"5\">");
		pr.println("<tr style=\"background-color:darkgoldenrod;\">");
		pr.println("<td width=\"40%\"><b>Module :  " + strStatusMessage
				+ "</b></td>");
		pr.println("<td width=\"30%\">Time :  " + getCurrentDate() + "</td>");
		pr.println("</tr>");
		pr.println("<tr>");
		pr.println("</table>");
		pr.flush();
	}

	public void createReportFile() throws Exception {
		if (pr == null) {

			File file = new File("ReportGenerator/Output_files");
			if (file.exists()) {
				try {
					// org.apache.commons.io.FileUtils.cleanDirectory(file);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				file.mkdir();
			}
			fwOutFile = new File("ReportGenerator//Output_files", strResultFile);

			pr = new PrintWriter(fwOutFile);
			File file1 = new File("ReportGenerator/Output_files");
			if (file1.exists()) {
				try {

				} catch (Exception e) {
					e.printStackTrace();
					;
				}
			}
		}
	}

	public static String getCurrentDate() {
		DateFormat dfDateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date dtDate = new Date();
		String strDateTime = dfDateFormat.format(dtDate);
		return strDateTime;
	}

	public long startTestCase(String strTestCaseName) throws Exception {
		long lnStartTimeTestCase;
		lnStartTimeTestCase = getStartTime();
		startTestCaseResultTable(strTestCaseName);
		return lnStartTimeTestCase;
	}

	public long getStartTime() throws Exception {
		Calendar calInstance1 = Calendar.getInstance();
		long lnMS1;
		lnMS1 = calInstance1.getTimeInMillis();
		return lnMS1;
	}

	public void startTestCaseResultTable(String strStatusMessage)
			throws Exception {
		bFailCase = false;
		iTcCount++;
		createReportFile();
		pr.println("<p>                                                           </p>");
		pr.println("<p>                                                           </p>");
		pr.println("<table width=\"600\" border=\"5\">");
		pr.println("<tr style=\"background-color:lightskyblue ;\">");
		pr.println("<td width=\"100%\"><b>TestCaseName :  " + strStatusMessage
				+ "</b></td>");
		pr.println("</tr>");
		pr.println("<tr>");
		pr.println("<td width=\"60%\"><b>Validations</b></td>");
		pr.println("<td width=\"40%\"><b>Result</b></td>");
		pr.println("<td width=\"40%\"><b>ScreenShots</b></td>");
		pr.println("</tr>");
		pr.flush();
	}

	public void passTestCase(String strStatusMessage) throws Exception {
		createReportFile();
		FileInputStream in = new FileInputStream("data.properties");
		Properties property = new Properties();
		property.load(in);
		String toolName = property.getProperty("tool");
		String screenshotReq = property.getProperty("screenShotRequiredForSuccess");
		if(screenshotReq.equalsIgnoreCase("Y")){
		if (toolName.equalsIgnoreCase("appium") || toolName.equalsIgnoreCase("selenium")) {
			imgPath = PageBase_Appium.screenshot();
			String[] imgPathSplit = imgPath.split("ReportGenerator");
			// String path1 = imgPathSplit[0];
			String path2 = imgPathSplit[1];
			imgName = ".." + path2;
		} else {
			imgName = PageBase_SeeTest.screenshot();

		}}

		// System.out.println("path1 *****" + path1);

		pr.println("<tr >");

		pr.println("<td width=\"40%\">" + strStatusMessage + "</td>");
		pr.println("<td width=\"30%\" style=\"background-color:green;\"><b>Pass</b></td>");
		// pr.println("<td width=\"30%\"></td>");
		pr.println("<td width=\"40%\"><img src=" + imgName
				+ " border=3 height=100 width=100></img></td>");
		pr.println("</tr>");
		pr.flush();

	}

	public void failTestCase(String strStatusMessage)
			throws Exception {

		iFailCount++;
		
		FileInputStream in = new FileInputStream("data.properties");
		Properties property = new Properties();
		property.load(in);
		String toolName = property.getProperty("tool");
		if (toolName.equalsIgnoreCase("SeeTest")) {

			PageBase_SeeTest.screenshot();
		} else {

			imgPath = PageBase_Appium.screenshot();
			String[] imgPathSplit = imgPath.split("ReportGenerator");
			// String path1 = imgPathSplit[0];
			String path2 = imgPathSplit[1];
			imgName = ".." + path2;
		}
		in.close();
		
		bFailCase = true;
		createReportFile();
		pr.println("<tr style=\"background-color:red;\">");
		pr.println("<td width=\"40%\">" + strStatusMessage + "</td>");
		pr.println("<td width=\"30%\"><b>Fail</b></td>");
		pr.println("</tr>");
		pr.println("<tr >");

		pr.println("</tr>");
		pr.flush();
		junit.framework.AssertionFailedError ASF = new junit.framework.AssertionFailedError(
				strStatusMessage);
		throw ASF;
	}

	public void logException(String strStatusMessage, Exception e)
			throws Exception {

		FileInputStream in = new FileInputStream("data.properties");
		Properties property = new Properties();
		property.load(in);
		String toolName = property.getProperty("tool");
		if (toolName.equalsIgnoreCase("SeeTest")) {

			PageBase_SeeTest.screenshot();
		} else {

			imgPath = PageBase_Appium.screenshot();
			String[] imgPathSplit = imgPath.split("ReportGenerator");
			// String path1 = imgPathSplit[0];
			String path2 = imgPathSplit[1];
			imgName = ".." + path2;
		}
		in.close();

		iFailCount++;
		/*
		 * imgName = "\"" + "./../" + "ScreenShots/images/" +
		 * Integer.toString(iFailCount) + ".png" + "\"";
		 */
		createReportFile();
		pr.println("<tr style=\"background-color:firebrick;\">");
		pr.println("<td width=\"40%\">An Exception: " + strStatusMessage + " "
				+ e.toString() + "</td>");
		pr.println("<td width=\"30%\"><b>Fail</b></td>");
		pr.println("<td width=\"40%\"><img src=" + imgName
				+ " border=3 height=100 width=100></img></td>");

		pr.println("</tr>");

		pr.flush();
		AssertionFailedError ASF = new AssertionFailedError(strStatusMessage
				+ e.toString());
		throw ASF;
	}

	public void totalResult() throws Exception {

		iPassCount = iTcCount - iFailCount;
		createReportFile();
		pr.println("</table>");
		pr.println("<h2>           Test Result Summary               </h2>");
		pr.println("<table width=\"600\" border=\"5\">");
		pr.println("<tr>");
		pr.println("<td width=\"75%\"><b>Total TestCases Executed:</b></td>");
		pr.println("<td width=\"25%\">" + iTcCount + "</td>");
		pr.println("</tr>");
		pr.println("<tr>");
		pr.println("<td width=\"75%\" style=\"background-color:green;\"><b>Total TestCases Passed:</b></td>");
		pr.println("<td width=\"25%\">" + iPassCount + "</td>");
		pr.println("</tr>");
		pr.println("<tr>");
		pr.println("<td width=\"75%\"  style=\"background-color:red;\"><b>Total TestCases Failed:</b></td>");
		pr.println("<td width=\"25%\">" + iFailCount + "</td>");
		pr.println("</tr>");
		pr.println("</table>");
		pieChart1();
		pr.println("</html>");

		pr.flush();
		pr = null;

	}

	public void endTestCase(String strTestCaseName) throws Exception {
		totalResultInaLine();
	}

	public void totalResultInaLine() throws Exception {

		iPassCount = iTcCount - iFailCount;
		createReportFile();

		pr.println("<table width=\"600\" border=\"5\">");
		pr.println("<tr>");
		pr.println("<td width=\"30%\">Total TestCases: </td>");
		pr.println("<td width=\"10%\">" + iTcCount + "</td>");
		pr.println("<td width=\"20%\"> Pass: </td>");
		pr.println("<td width=\"10%\">" + iPassCount + "</td>");
		pr.println("<td width=\"20%\"> Fail: </td>");
		pr.println("<td width=\"10%\">" + iFailCount + "</td>");
		pr.println("</tr>");
		pr.println("</table>");
		pieChart1();

		pr.flush();

	}

	public void pieChart1() {
		pr.println("<table>");
		pr.println("<head>");
		pr.println("<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
		pr.println("<script type='text/javascript'>");
		pr.println(" google.load('visualization', '1', {packages:['corechart']});");
		pr.println(" google.setOnLoadCallback(drawChart);");
		pr.println("function drawChart() {");

		pr.println(" var data = google.visualization.arrayToDataTable([");
		pr.println("['Task', '" + iTcCount + "'],");
		pr.println("['Pass',    " + iPassCount + "],");
		pr.println(" ['Fail',      " + iFailCount + "],");
		pr.println("['N/A',  " + iTcCount + " - (" + iPassCount + " + "
				+ iFailCount + ")],");

		pr.println(" ]);");

		pr.println(" var options = {");
		pr.println(" width: 400,");
		pr.println("height: 240,");
		pr.println(" title: 'Pie Chart',");
		pr.println("colors: ['green', 'red',  'blue']");
		pr.println(" };");

		pr.println("var chart = new google.visualization.PieChart(document.getElementById('piechart'));");

		pr.println("chart.draw(data, options);");
		pr.println("   }");
		pr.println("  </script>");
		pr.println("  </head>");
		pr.println(" <body>");
		pr.println(" <div id='piechart' style='width: 900px; height: 500px;'></div>");
		pr.println(" </body>");
		pr.println("</table>");

	}

}
