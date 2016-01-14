package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Canvas_iOS;


public class TestCanvas_iOS extends TestBase {

	@Test
	public void testNBC() throws Exception {
		try {
			Canvas_iOS td = new Canvas_iOS(appiumDriver);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
