package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {

	WebDriver driver;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public static String username = "";

	By header = By.cssSelector("h6[class*='topbar-header-breadcrumb']");
	By tableRows = By.cssSelector(".oxd-table-body>.oxd-table-card>div");
	By rowDeleteButton = By.cssSelector("button:has(i[class*='trash'])");
//	By popupDelete = By.cssSelector(".orangehrm-modal-footer>button:has(i[class*='trash'])");
	By popup = By.cssSelector("div[class$='orangehrm-dialog-popup']");
	By popupDeleteButton = By.cssSelector("button:has(i[class*='trash'])");
	By userNameCell = By.cssSelector("div>div:nth-child(2)");

	public String getHeaderText() {
		String headerText = driver.findElement(header).getText().trim();
		return headerText;
	}

	/*
	 * Pass the record number you want to delete Eg: 1, 2, 3
	 */
	public void deleteRecord(int recordNumber) {
		int index = recordNumber - 1;
		List<WebElement> elements = driver.findElements(tableRows);
		
		WebElement record = elements.get(index);
		
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("arguments[0].scrollIntoViewIfNeeded();",record);
		
		username = record.findElement(userNameCell).getText();
		
		if (username.equals("Admin")) {
			index += 1;
			record = elements.get(index);
			username = record.findElement(userNameCell).getText();
			System.out.println("Deleting Username: " + username);
			record.findElement(rowDeleteButton).click();
		}else {
			record.findElement(rowDeleteButton).click();
			System.out.println("Deleting Username: " + username);
		}
		
		
	}
	
	public List<String> getTableUsernames() {
		List<WebElement> elements = driver.findElements(tableRows);
		List<String> names = new ArrayList<>();
		
		for (WebElement element : elements) {
			names.add(element.findElement(userNameCell).getText());
		}
		
		return names;
	}
	

	public void clickDeleteButtonOnPopup() {
		driver.findElement(popup).findElement(popupDeleteButton).click();
	}
}
