package com.techcenture.pages;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

	@FindBy(xpath = "//p//span[@id='our_price_display'][text()='$27.00']")
	private WebElement priceForOneItem;

	@FindBy(xpath = "//button[@type='submit']/span[text()='Add to cart']")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//div[@id='layer_cart']")
	private WebElement popUpWindow;

	@FindBy(xpath = "//h2[contains(.,'Product successfully added to your shopping cart')]")
	private WebElement productAddedText;

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	private WebElement proceedToCkeckOutBtn;

	@FindBy(xpath = "//div[@class='layer_cart_row']//span[@class='ajax_cart_shipping_cost']\n" + 
			"")
	private WebElement shippingCost;
	
	@FindBy(xpath = "//div[@class='layer_cart_row']//span[@class='ajax_block_cart_total']\n" + 
			"")
	private WebElement totalCost;
	
	
	
	
	
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

		String[] expectedSocialMediaButtonsNameStrings = { "Tweet", "Share", "Google+", "Pinterest" };

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

	int productQuantity = 1;

	private void selectProductQuantity(int quantity) throws InterruptedException {
		for (int i = 1; i < quantity; i++) {
			quantityPlusButtonElement.click();
			productQuantity++;
			Thread.sleep(500);
		}
	}

	private void selectProductColor(String color) throws InterruptedException {
		driver.findElement(By.xpath("//a[@name='" + color + "']")).click();
		Thread.sleep(500);
	}

	private void selectProductSize(String size) throws InterruptedException {
		// M, S, L for size
		Select select = new Select(sizeDropDownElement);
		select.selectByVisibleText(size);
		Thread.sleep(500);

	}

	public double getPricePerItem() {
		String priceText = priceForOneItem.getText().trim().replace("$", "");
		double price = Double.parseDouble(priceText);
		return price;
	}

	public void verifyAndclickAddToCartBtn() {
		addToCartBtn.isDisplayed();
		addToCartBtn.click();

	}

	public void verifyPopUpWindow() throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(popUpWindow.isDisplayed());
	}

	public void verifyProductAdded() {
		assertEquals("Product successfully added to your shopping cart", productAddedText.getText().trim());

	}

	private double getTotalPrice() {
		double price = getPricePerItem();
		double totalPrice = productQuantity * price;
		return totalPrice;
	}

	public void verifyPriceAfterChoosingQuantity() {
		double totalPrice = getTotalPrice();
		WebElement totalPriceForAllItems = driver.findElement(
				By.xpath("//div//span[@class='ajax_block_products_total']"));
		double totalPriceOnCartPopUp = Double.parseDouble(totalPriceForAllItems.getText().trim()
				.replace("$", ""));
		assertEquals(totalPrice, totalPriceOnCartPopUp);
	}

	 public double findShippingCost() {
		 double shipCost=Double.parseDouble(shippingCost.getText().replace("$", ""));
		 return shipCost;
	 }
	 
	 public void verifyPceAfterShippingCost() {
		 double totalPriceAfterShipping=getTotalPrice() + findShippingCost();
		 double totalCostInCart=Double.parseDouble(totalCost.getText().replace("$", ""));
		 assertEquals(totalCostInCart, totalPriceAfterShipping);
		 
	 }
	public void clickProceedToCheckOutBtn() {
		proceedToCkeckOutBtn.click();
	}
//	
//	
}