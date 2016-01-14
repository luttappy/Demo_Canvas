package com.mop.qa.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mop.qa.test.bvt.TestSuite;

public class TestReportGenerator {

	int passCounter = 0;
	int failCounter = 0;

	public TestReportGenerator() {

	}

	public void generateConsolidatedReport(String totalTimeTaken,
			String startTimeUpdate) {
		System.out.println("Starting the Report Consolidator");
		try {

			// get property fr success case
			FileInputStream inputStream = new FileInputStream("data.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			String scForPass = prop.getProperty("screenShotRequiredForSuccess");

			// for mac

			File f = new File("ReportGenerator/Output_files");
			File[] fList = f.listFiles();
			List<String> fileList = new ArrayList<String>();
			for (int i = 0; i < fList.length; i++) {
				if (fList[i].isFile()) {
					fileList.add(fList[i].getAbsolutePath());

				} else if (fList[i].isDirectory()) {
					System.out.println("Directory " + fList[i].getName());
				}
			}

			StringBuilder root = new StringBuilder();
			int totalTestCases = 0;
			int totalPassedCases = 0;
			int totalFailedCases = 0;
			StringBuilder popupDiv = new StringBuilder();

			root.append("<ol class='tree'><li><label for='subfolder1'>Test Report</label><input type='checkbox' id='subfolder1'/><ol>");
			for (String fileName : fileList) {
				StringBuilder level1 = new StringBuilder();
				StringBuilder level2 = new StringBuilder();
				StringBuilder testCaseSteps = new StringBuilder();
				File input = new File(fileName);
				Document doc = Jsoup.parse(input, "UTF-8",
						"http://example.com/");
				Elements table = doc.select("table");

				String testCaseName = "";
				//
				//
				String imgName = null;
				for (int i = 0; i < table.size(); i++) {

					Element tab = table.get(i);
					for (Element row : tab.select("tr")) {
						Elements columns = row.select("td");

						// Elements imgNameVal =
						// row.getElementsByAttribute("img");
						// System.out.println("imgNameVal ---****" +imgNameVal
						// );

						for (Element column : columns) {
							Elements screenshotPath = columns.select("img");
							for (Element scrP : screenshotPath) {

							}
						}

						Iterator<Element> ite1 = columns.iterator();
						String testTree = "";
						while (ite1.hasNext()) {
							String content = ite1.next().text();
							if (content.startsWith("Module")) {
								level1.append("<li><label for='subfolder1'>"
										+ content.split(":")[1]
										+ "</label><input type='checkbox' id='subfolder1'/><ol>");
							} else if (content.startsWith("TestCaseName")) {

								if ((testCaseSteps.toString().length()) != 0) {

									if (failCounter > 0) {
										level2.append(testTree
												+ " - <font style='color:red;'>Fail</font></a></li>");
									} else {
										level2.append(testTree
												+ " - <font style='color:green;'>Pass</font></a></li>");
									}
									 passCounter = 0;
									 failCounter = 0;
									testCaseSteps
											.append("</table></div></div>");

									String finalDiv = "<div id='"
											+ testCaseName
											+ "' class='modalDialog'><div><a href='#close' title='Close' class='close'>X</a><table class='tableClass'><tr><td style='background:#9192C2'>Validation</td><td style='background:#9192C2'>Results</td><td style='background:#9192C2'>ScreenShots</td></tr>"
											+ testCaseSteps.toString();

									popupDiv.append(finalDiv);
									testCaseSteps = new StringBuilder();
								}
								testCaseName = content.split(":")[1].trim();
								testCaseName = testCaseName.replace(" ", "_");
								level2.append("<li class='file'><a href='#"
										+ testCaseName + "'>"
										+ content.split(":")[1]);
							} else if (content
									.startsWith("Total TestCases Executed")) {
								totalTestCases = totalTestCases
										+ Integer.parseInt(ite1.next().text()
												.trim());
								if ((testCaseSteps.toString().length()) != 0) {
									if (failCounter > 0) {
										level2.append(testTree
												+ " - <font style='color:red;'>Fail</font></a></li>");
									} else {
										level2.append(testTree
												+ " - <font style='color:green;'>Pass</font></a></li>");
									}
									 passCounter = 0;
									 failCounter = 0;
									 System.out.println("123456++++");
									testCaseSteps
											.append("</table></div></div>");

									String finalDiv = "<div id='"
											+ testCaseName
											+ "' class='modalDialog'><div><a href='#close' title='Close' class='close'>X</a><table class='tableClass'><tr><td style='background:#9192C2'>Validation</td><td style='background:#9192C2'>Results</td><td style='background:#9192C2'>ScreenShots</td></tr>"
											+ testCaseSteps.toString();

									popupDiv.append(finalDiv);
									testCaseSteps = new StringBuilder();
								}
							} else if (content
									.startsWith("Total TestCases Passed")) {
								totalPassedCases = totalPassedCases
										+ Integer.parseInt(ite1.next().text()
												.trim());
							} else if (content
									.startsWith("Total TestCases Failed")) {
								totalFailedCases = totalFailedCases
										+ Integer.parseInt(ite1.next().text()
												.trim());
							} else if (((content.length()) != 0)
									&& !(content
											.equalsIgnoreCase("Validations"))
									&& !(content.equalsIgnoreCase("Result"))
									&& !(content
											.equalsIgnoreCase("ScreenShots"))
									&& !(content.startsWith("Time :"))) {

								testCaseSteps.append("<tr><td>"
										+ content.trim() + "</td>");
								String passOrFail = ite1.next().text().trim();
								Element screenShotElement = (Element) ite1
										.next();
								String ssPath = screenShotElement
										.getElementsByTag("img").get(0)
										.attr("src");

								// String[] ssPathSplit = ssPath.split("..");
								String screenPath = ssPath.replace("..//",
										"..//");

								imgName = ssPath;

								if (passOrFail.toLowerCase().equalsIgnoreCase(
										"pass")) {

									passCounter = passCounter + 1;

									/*
									 * imgName = "\"" + "./ScreenShots/images/"
									 * + passCounter + ".png" + "\"";
									 */

								} else {
									failCounter = failCounter + 1;
									/*
									 * imgName = "\"" + "./ScreenShots/images/"
									 * + failCounter + ".png" + "\"";
									 */
								}
								// testCaseSteps.append("<td>"+passOrFail+"</td></tr>");

								/*
								 * System.out.println("passOrFail Value ---->" +
								 * passOrFail);
								 */
								/*
								 * if (passOrFail.equalsIgnoreCase("Pass")) {
								 * testCaseSteps
								 * .append("<td style='background:#00F541'>" +
								 * passOrFail + "</td></tr>");
								 */
								if (passOrFail.equalsIgnoreCase("Pass")) {
									testCaseSteps
											.append("<td style='background:#00F541'>"
													+ passOrFail + "</td>");
									// testCaseSteps.append("<td></td>");
									if (scForPass.equalsIgnoreCase("Y")) {
										testCaseSteps
												.append("<td><img src="
														+ imgName
														+ " border=3 height=300 width=300></img></td></tr>");
									} else {
										testCaseSteps.append("<td></td></tr>");
									}

								} else if (passOrFail.equalsIgnoreCase("Fail")) {

									testCaseSteps
											.append("<td style='background:#FF0000'>"
													+ passOrFail + "</td>");
									testCaseSteps
											.append("<td><img src="
													+ imgName
													+ " border=3 height=300 width=300></img></td></tr>");
								} else {
									testCaseSteps.append("<td>" + passOrFail
											+ "</td></tr>");
								}

								// String screenshotPath =
								// ite1.next().text().trim();
								// System.out.println("screenshot path is "+screenshotPath);
							}
						}

					}

				}
				level1.append(level2.toString());
				level1.append("</ol></li>");
				root.append(level1);
			}
			root.append("</ol></li></ol>");

			/*
			 * System.out.println("Total Test Cases->" + totalTestCases +
			 * "-----------------------Passed-->" + totalPassedCases +
			 * "--------------------Failed->" + totalFailedCases);
			 */

			File file = new File("ReportGenerator/SummaryReport_Template.html");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while ((line = reader.readLine()) != null) {
				oldtext += line + "\r\n";

			}
			reader.close();

			String newtext = oldtext.replace("[PASSED_VALUE]", ""
					+ totalPassedCases);
			newtext = newtext.replace("[FAILED_VALUE]", "" + totalFailedCases);
			newtext = newtext.replace("[NORUN_VALUE]", "" + totalTestCases);
			newtext = newtext.replace("[TEST CASE HIERARCHY]", root.toString());

			newtext = newtext.replace("[TOTAL_TIME]", "" + totalTimeTaken);
			newtext = newtext.replace("[RUN_STARTED]", "" + startTimeUpdate);

			newtext = newtext.replace("[TIME_ZONE]",
					prop.getProperty("TIME_ZONE"));
			newtext = newtext.replace("[DEVICE_NAME]",
					prop.getProperty("DEVICE_DETAILS"));
			newtext = newtext.replace("[OS_DETAILS]",
					prop.getProperty("OS_DETAILS"));
			newtext = newtext.replace("[SCREEN_SIZE]",
					prop.getProperty("SCREEN_SIZE"));
			newtext = newtext.replace("[USER]", prop.getProperty("USER"));
			newtext = newtext.replace("[MACHINE_NAME]",
					prop.getProperty("MACHINE_NAME"));
			newtext = newtext.replace("[POP_UP_DIV]", popupDiv.toString());
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String consolidatedReportPath = "ReportGenerator/"
					+ TestSuite.reportFolder + "/ConsolidatedSummary_"
					+ sdf.format(d) + ".html";
			FileWriter writer = new FileWriter(consolidatedReportPath);
			writer.write(newtext);
			writer.close();
			// org.apache.commons.io.FileUtils.cleanDirectory(f);
			System.out.println("Completed the Generation");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}