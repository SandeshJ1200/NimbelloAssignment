package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By username = By.xpath("//p[starts-with(.,'Username :')]");
	By password = By.xpath("//p[starts-with(.,'Password :')]");

	By usernameField = By.cssSelector("[name='username']");
	By passwordField = By.cssSelector("[name='password']");
	By submitButton = By.cssSelector("[type='submit']");

	public void enterUsername() {
		String usernameToEnter = driver.findElement(username).getText().split(":")[1].trim();
		System.out.println(usernameToEnter);
		driver.findElement(usernameField).sendKeys(usernameToEnter);
	}

	public void enterPassword() {
		String passwordToEnter = driver.findElement(password).getText().split(":")[1].trim();
		System.out.println(passwordToEnter);
		driver.findElement(passwordField).sendKeys(passwordToEnter);
	}
	
	public DashboardPage clickSubmit() {
		driver.findElement(submitButton).click();
		return new DashboardPage(driver);
	}
	
	

}
