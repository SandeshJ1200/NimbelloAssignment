package com.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.AdminPage;
import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.utils.AppConstants;

public class LoginPageTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	AdminPage adminPage;

	@Test
	public void completeFlow() {
		loginPage = new LoginPage(BaseTest.getDriver());
		loginPage.enterUsername();
		loginPage.enterPassword();
		dashboardPage = loginPage.clickSubmit();

		String dashboardPageActualHeader = dashboardPage.getHeaderText();
		Assert.assertEquals(dashboardPageActualHeader, AppConstants.DASHBOARD_PAGE_HEADER);

		adminPage = dashboardPage.clickMenuItemAdmin();
		String adminPageActualHeader = adminPage.getHeaderText();

		Assert.assertEquals(adminPageActualHeader, AppConstants.ADMIN_PAGE_HEADER);

		adminPage.deleteRecord(2);
		adminPage.clickDeleteButtonOnPopup();

		List<String> usernames = adminPage.getTableUsernames();

//		Check If List doesn't contain the deleted Username
		Assert.assertTrue(!usernames.contains(AdminPage.username));

	}

}
