package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
	
	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By header = By.cssSelector("h6[class*='topbar-header-breadcrumb']");
	By menuItemAdmin = By.cssSelector("li>a[href$='viewAdminModule']");
	
	public String getHeaderText() {
		String headerText = driver.findElement(header).getText().trim();
		return headerText;
	}
	
	public AdminPage clickMenuItemAdmin() {
		driver.findElement(menuItemAdmin).click();
		return new AdminPage(driver);
	}

}
