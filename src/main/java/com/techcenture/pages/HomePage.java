package com.techcenture.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import org.hamcrest.DiagnosingMatcher;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.techcenture.driverUtils.Driver;

public class HomePage {

	WebDriver driver;
	
	
	@FindBy(xpath = "//a[text()='Best Sellers']")
	private WebElement bestSellersLinkElement;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void goToHomePage() {
		
		driver.get("http://automationpractice.com/index.php");
	}

	public void selectBestSellersElement() {
		bestSellersLinkElement.click();
		
	}
	
	public void verifyBestSellerProducts() {
			
		List<WebElement> bestSellerProductsList = driver.findElements(By.xpath("//ul[@id='homefeatured']/li//h5/a"));
		
		int bestSellerProductsNumber = bestSellerProductsList.size();
		
		assertEquals(7, bestSellerProductsNumber);
		
		String[] expectedProductsName = {"Faded Short Sleeve T-shirts","Blouse","Printed Dress", "Printed Dress",
				"Printed Summer Dress", "Printed Summer Dress", "Printed Chiffon Dress"};
		
		for (int i = 0; i < bestSellerProductsList.size(); i++) {
			String actualProductsName = bestSellerProductsList.get(i).getAttribute("title").trim();
		
			assertEquals(expectedProductsName[i], actualProductsName);
		}
	}

	public void clickProduct(String productName) {
		
		Actions actions = new Actions(driver);
		WebElement blouse = driver.findElement(By.xpath("//ul[@id='blockbestsellers']/li//h5/a[@title='Blouse']"));
		
		actions.moveToElement(blouse).build().perform();
		
		WebElement morElement = driver.findElement(By.xpath("//ul[@id='blockbestsellers']/li//h5/a[@title='Blouse']/../../div[@class='button-container']/a/span[text()='More']"));
		morElement.click();
	
	}
	
}





