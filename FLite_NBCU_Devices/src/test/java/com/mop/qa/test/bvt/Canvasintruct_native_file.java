package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Canvasapp_instructer_native;

public class Canvasintruct_native_file extends TestBase {
	
	@Test
	public void testNBC() throws Exception {
		try {
			Canvasapp_instructer_native td = new Canvasapp_instructer_native();
			//Thread.sleep(20000);
			//String url=rds.getValue("DATA", this.getClass().getSimpleName(), "URL");
			//td.enterURL(url);
			
			String uName = rds.getValue("DATA", this.getClass().getSimpleName() , "UserName");
			td.enterUserName(uName);
			// navigation btw sections
			String pword = rds.getValue("DATA", this.getClass().getSimpleName(), "Password"); 
			td.enterPassword(pword);
			
			td.clickLogin();
			td.clickDectest1();
			td.clickmoduletab();
			td.clicksherpath();
			td.clickNewWindow();
			td.clickalert();
			td.switchToWindowTitle();
	       	td.clickNursingDiagnosis();
			td.clickintroduction();
			td.switchToWindowTitle();
			td.getText1();
			td.switchToWindowTitle();
			td.getText2();
			td.switchToWindowTitle();
			td.lesson();
			td.logout();
			td.clickmoretab();
			td.clicklogout();
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
}

	
		
	}



