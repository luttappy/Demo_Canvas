package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Canvas_instructer_perform;

public class Canvasinstr_perform_file extends TestBase {

	@Test
	public void testNBC() throws Exception {
		try {
			Canvas_instructer_perform td = new Canvas_instructer_perform();
			String url=rds.getValue("DATA", this.getClass().getSimpleName(), "URL");
			System.out.println("url -->" + url);

			String uName = rds.getValue("DATA",
					this.getClass().getSimpleName(), "UserName");
			String pword = rds.getValue("DATA",
					this.getClass().getSimpleName(), "Password");
			// login
			td.login(url,uName,pword);

			// navigate to sher path
			td.navigateToSherPath();
			// select Course
			td.selectCourse();
			// See performance
			td.clickperformance();
			td.logout();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}