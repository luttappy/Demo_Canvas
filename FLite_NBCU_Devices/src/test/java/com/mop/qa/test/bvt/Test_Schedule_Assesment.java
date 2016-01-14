package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Schedule_Assesment;

	
	public class Test_Schedule_Assesment extends TestBase {

		@Test
		public void testNBC() throws Exception {
			try {
				Schedule_Assesment sa = new Schedule_Assesment();
				String url=rds.getValue("DATA", this.getClass().getSimpleName(), "url");
				String uName = rds.getValue("DATA", this.getClass().getSimpleName() , "UserName");
				String pword = rds.getValue("DATA", this.getClass().getSimpleName(), "Password"); 
				
				//login
				sa.login(url,uName,pword);
				
			//navigate to sher path
				sa.navigateToSherPath();
				
   		        //schedule course
				sa.scheduleCourse();
				
				//logout
				sa.logout();
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		}
	}
