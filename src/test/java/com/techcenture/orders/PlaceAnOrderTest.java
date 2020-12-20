package com.techcenture.orders;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.techcenture.driverUtils.Driver;
import com.techcenture.pages.HomePage;
import com.techcenture.pages.ProductPage;

public class PlaceAnOrderTest {

	
	@Test
	public void placeOrderEndToEnd() throws InterruptedException {
		WebDriver driver = Driver.getDriver("chrome");
		
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
 
		
		homePage.goToHomePage();
		homePage.selectBestSellersElement();
		homePage.verifyBestSellerProducts();
		homePage.clickProduct("Blouse");
		
		productPage.verifyProductInfo();
		productPage.verifySocialMediaButtons();
	
		productPage.productSelection(2, "White", "M");
		
		Thread.sleep(5000);
		
		driver.quit();
		
	}
}
