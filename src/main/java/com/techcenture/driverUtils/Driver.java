package com.techcenture.driverUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
private static WebDriver driver;
	
	private Driver() {
		
	}
	
	public static WebDriver getDriver(String driverOption) {
		
		System.out.println("Instantiating the browser instance");
		switch (driverOption.toLowerCase()) {
			
		case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return driver;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return driver;
			
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return driver;
				
		}
		return null;
	}
	
	
	public static void quitBrowser() {
		System.out.println("quiting the browser");
		if (driver != null) {
			driver.quit();
		}
	}
}
