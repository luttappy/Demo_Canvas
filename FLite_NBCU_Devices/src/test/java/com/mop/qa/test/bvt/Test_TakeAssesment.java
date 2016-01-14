package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.TakeAssesment;


	public class Test_TakeAssesment extends TestBase {

		@Test
		public void testNBC() throws Exception {
			try {
				TakeAssesment ta = new TakeAssesment();

				String url=rds.getValue("DATA", this.getClass().getSimpleName(), "URL");
				String uName = rds.getValue("DATA", this.getClass().getSimpleName() , "UserName");
				String pword = rds.getValue("DATA", this.getClass().getSimpleName(), "Password"); 
				
				//Login
				ta.enterUrl(url);
				ta.login(url,uName,pword);
			
			/*	//navigate to share path
				ta.navigateSherPath();
				
				//Select Course in sher path
				ta.selectCourse();
		       
				//take quiz
				ta.takeQuiz();
				
				ta.lesson();
				ta.checkResult();
			
				//logout
				ta.logout();*/
				
								
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
}

	

