package com.techcenture.orders;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.techcenture.driverUtils.Driver;
import com.techcenture.pages.HomePage;
import com.techcenture.pages.OrderPage;
import com.techcenture.pages.ProductPage;

public class PlaceAnOrderTest {

	
	@Test
	public void placeOrderEndToEnd() throws InterruptedException {
		WebDriver driver = Driver.getDriver("chrome");
		
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
        OrderPage orderPage=new OrderPage(driver);
		
		homePage.goToHomePage();
		homePage.selectBestSellersElement();
		homePage.verifyBestSellerProducts();
		homePage.clickProduct("Blouse");
		
		productPage.verifyProductInfo();
		productPage.verifySocialMediaButtons();
	    productPage.productSelection(2, "White", "M");
	    
		
	   	productPage.verifyAndclickAddToCartBtn();
		productPage.verifyPopUpWindow();
		productPage.verifyProductAdded();
		productPage.verifyPriceAfterChoosingQuantity();
		productPage.verifyPceAfterShippingCost();
	
		productPage.clickProceedToCheckOutBtn();
		
//		orderPage.verifyCartSummaryText();
		orderPage.verifySummaryTab();
		orderPage.verifyDeleteOption();
		orderPage.proceedToCheckoutBtnClick();
		
		Thread.sleep(5000);
		
		driver.quit();
		
	}
}
