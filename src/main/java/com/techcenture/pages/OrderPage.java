package com.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OrderPage {
     WebDriver driver;

     @FindBy(xpath="//h1[@id='cart_title'][contains(text(),'Shopping-cart')]")
     private WebElement pageHeading;
     
     @FindBy(xpath="//li[@class='step_current  first']//span[text()=' Summary']")
     private WebElement summaryTab;
     
     @FindBy(className = "icon-trash")
     private WebElement deleteIcon;
     
     @FindBy(xpath ="//a//span[text()='Proceed to checkout']")
     private WebElement proceedToCheckoutBtn;
     
	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//      
//	public void verifyCartSummaryText() {
//		pageHeading.getText();
//		assertEquals("SHOPPING-CART SUMMARY", pageHeading.getText().toUpperCase());
//	}
	
	public void verifySummaryTab() {
		assertEquals("01. Summary", summaryTab.getText().trim());
	}
	
	public void verifyDeleteOption() {
		
		assertTrue(deleteIcon.isDisplayed());
		
	}
	public void  proceedToCheckoutBtnClick() {
		proceedToCheckoutBtn.click();
	}
}
