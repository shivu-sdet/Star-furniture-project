package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriverWait wait;

    // Locators for placing order (Placeholder)
    private By convertToOrderBtn = By.id("btnConvertToOrder");
    private By placeOrderBtn = By.id("btnPlaceOrder");
    private By orderConfirmationMsg = By.id("orderSuccessMsg");

    public OrderPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void convertQuoteToOrder() {
        // Assume from the Quote screen there's a button to Convert to Order
        wait.until(ExpectedConditions.elementToBeClickable(convertToOrderBtn)).click();
        
        System.out.println("Converting Quote to Order...");
    }

    public void placeOrder() {
        // Confirm placement
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        
        // Wait for success
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMsg));
        
        System.out.println("Successfully Placed the Order!");
    }
}
