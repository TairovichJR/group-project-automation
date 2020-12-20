package com.techcenture.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	WebDriver driver;
	
	@FindBy(xpath = "//h1[text()='Blouse']")
	private WebElement productNameElement;
	
	@FindBy(xpath = "(//span[@class='editable'])[2]")
	private WebElement productConditionElement;
	
	@FindBy(xpath = "(//p[text()='Short sleeved blouse with feminine draped sleeve detail.'])[1]")
	private WebElement productDescriptionElement;
	
	@FindBy(xpath = "//button[@type='button']")
	private List<WebElement> socialMediaButtonsList;
	
	@FindBy(xpath = "//i[@class='icon-plus']")
	private WebElement quantityPlusButtonElement;
	
	@FindBy(id = "group_1")
	private WebElement sizeDropDownElement;

	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyProductInfo() {
		
		assertEquals("Blouse", productNameElement.getText());
		assertEquals("New", productConditionElement.getText());
		assertEquals("Short sleeved blouse with feminine draped sleeve detail.", productDescriptionElement.getText());
		
	}
	
	public void verifySocialMediaButtons() {
		
		String[] expectedSocialMediaButtonsNameStrings = {"Tweet","Share","Google+","Pinterest"};
		
		for (int i = 0; i < socialMediaButtonsList.size(); i++) {
			String actualSocialMediaButtonsName = socialMediaButtonsList.get(i).getText().trim();
			
			assertEquals(expectedSocialMediaButtonsNameStrings[i], actualSocialMediaButtonsName);
			
		}
		
	}
	
	
	public void productSelection(int quantity, String color, String size) throws InterruptedException {
		selectProductQuantity(quantity);
		selectProductSize(size);
		selectProductColor(color);
	}
	
	private void selectProductQuantity(int quantity) throws InterruptedException {
		for(int i = 1; i < quantity; i++) {
			quantityPlusButtonElement.click();
			Thread.sleep(500);
		}
	}
	
	private void selectProductColor(String color) throws InterruptedException {
		driver.findElement(By.xpath("//a[@name='"+color+"']")).click();
		Thread.sleep(500);
	}
	
	private void selectProductSize(String size) throws InterruptedException {
		//M, S, L for size
		Select select = new Select(sizeDropDownElement);
		select.selectByVisibleText(size);
		Thread.sleep(500);
	}
	
	
}
