package com.mop.qa.test.bvt;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.mop.qa.Utilities.TestReportGenerator;
 
public class TestSuite implements IExecutionListener {
 
       public static long startTime;
       public static String startTimeUpdate;
       public static long endTime;
       public static long totalTime;
       public static String totalTimeTaken;
       public static String osType = System.getProperty("os.name");
       static TestReportGenerator tr = new TestReportGenerator();
       public static String reportFolder;
 
       public void onExecutionStart() {
              try {
 
                     File file = new File("ReportGenerator/Output_files");
                     if (file.exists()) {
                           try {
                                  org.apache.commons.io.FileUtils.cleanDirectory(file);
 
                           } catch (Exception e) {
                                  e.printStackTrace();
                           }
                     }
                     Logger.getRootLogger().setLevel(Level.OFF);
 
                     String tool = getPropertyValue("tool");
                     if (tool.equalsIgnoreCase("Appium")) {
                           // startServer();
                     }
 
                     String osType = System.getProperty("os.name");
                     System.out.println("osType" + osType);
                     System.out.println("setting up wß");
                     startTime = System.currentTimeMillis();
 
                     Calendar cal = Calendar.getInstance();
                     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                     startTimeUpdate = sdf.format(cal.getTime());
 
                     SimpleDateFormat sdfDateReport = new SimpleDateFormat(
                                  "yyyy-MM-dd-HH-mm-ss");// dd/MM/yyyy
                     Date now = new Date();
                     reportFolder = "HtmlReport_" + sdfDateReport.format(now);
 
                     File reportDir = new File("ReportGenerator/" + reportFolder);
                     reportDir.mkdir();
                     File source = new File("ReportGenerator/supportFiles");
                     // File dest = new File(reportDir);
                     try {
                           FileUtils.copyDirectory(source, reportDir);
                     } catch (IOException e) {
                           e.printStackTrace();
                     }
 
              } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              }
 
       }
 
       public void onExecutionFinish() {
              try {
                     String tool = getPropertyValue("tool");
                     endTime = System.currentTimeMillis();
                     NumberFormat formatter = new DecimalFormat("#0.00");
                     totalTime = (endTime - startTime);
                     System.out.println("totalTime" + totalTime);
                     if (totalTime <= 60000) {
                           totalTimeTaken = formatter.format(totalTime / 1000d)
                                         + " Seconds";
                     } else {
                           totalTimeTaken = formatter.format(totalTime / 60000d)
                                         + " minutes";
                     } // totalTimeTaken = totalTimeTaken.split(".")[0];
                           // updateProperty(totalTimeTaken, startTimeUpdate);
                     if (tool.equalsIgnoreCase("Appium")) {
                           // stopServer();
                     }
 
                     tr.generateConsolidatedReport(totalTimeTaken, startTimeUpdate);
                     // TODO Auto-generated method stub
              } catch (Exception e) {
                     // TODO Auto-generated catch block
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
 
       public static void startServer() throws IOException, InterruptedException {
              try {
                     System.out.println("before ending start11");
                     String appiumPortVal = getPropertyValue("appiumPort");
                     String udID = getPropertyValue("device_UDID");
                     String appiumNodePath = getPropertyValue("nodePath");
                     String appiumJSPath = getPropertyValue("appiumJSPath");
                     String platformName = getPropertyValue("platformName");
                     // Runtime.getRuntime().exec("/bin/sh export ANDROID_HOME=/Applications/android-sdk-macosx/");
 
                     CommandLine command = new CommandLine(appiumNodePath);
                     command.addArgument(appiumJSPath, false);
                     command.addArgument("--address", false);
                     command.addArgument("127.0.0.1");
                     command.addArgument("--port", false);
                     command.addArgument(appiumPortVal);
                     command.addArgument("--no-reset", false);
                     DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
                     DefaultExecutor executor = new DefaultExecutor();
                     System.out.println("before ending start22");
                     if (platformName.equalsIgnoreCase("android")) {
                           System.out.println("before ending start");
                           executor.setExitValue(1);
                           executor.execute(command, resultHandler);
                           System.out.println("before ending start255");
                     } else {
                           command.addArgument("--udid", false);
                           command.addArgument(udID);
                           ProcessBuilder pb = new ProcessBuilder();
                           Map<String, String> env = pb.environment();
 
                           env.put("ANDROID_HOME", "/Applications/android-sdk-macosx");
 
                           env.put("PATH",
                                         "/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/android-sdk-macosx:/usr/local/git/bin:/usr/local/Cellar/libimobiledevice/1.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/git/bin:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin:/Users/mspiosteam/libimobiledevice-macosx:/Applications/android-sdk-macosx:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin");
                           executor.setExitValue(1);
                            executor.execute(command, env, resultHandler);
                     }
                     Thread.sleep(10000);
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
 
}
 