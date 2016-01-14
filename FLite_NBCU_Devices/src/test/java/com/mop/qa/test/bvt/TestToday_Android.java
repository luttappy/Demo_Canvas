package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.TodayNews_Android;

//import com.mop.qa.Utilities.ReadDataSheet;

public class TestToday_Android extends TestBase {

	@Test
	public void testNBC() throws Exception {
		try {
			TodayNews_Android td = new TodayNews_Android(appiumDriver);
			Thread.sleep(20000);
			//navigating between home menus 
			td.navigateHomeMenus();
			//navigation btw sections
			td.navigateSections();
			//save and verify 
		     td.saveAndVerify();
			//verify if its present in history
			td.validateHistory();
			//play with video
			td.playVideo();
			td.pauseVideo();
			td.closeVideo();
			
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
