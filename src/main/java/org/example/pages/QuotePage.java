package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QuotePage {

    private WebDriverWait wait;

    // Locators for Quote creation (Placeholder)
    private By newQuoteBtn = By.id("btnCreateQuote");
    private By selectCustomerDropdown = By.id("selectCustomer"); // Assuming dropdown/search
    private By addStockItemsBtn = By.id("btnAddInStockItem");
    private By itemSearchField = By.id("itemSearchEntry");
    private By selectFirstItem = By.xpath("//ul[@id='itemList']//li[1]"); // Just picking the first
    private By saveQuoteBtn = By.id("btnSaveQuote");

    public QuotePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void createQuoteForCustomer(String customerName) {
        // Click New Quote
        wait.until(ExpectedConditions.elementToBeClickable(newQuoteBtn)).click();
        
        // Select the customer we just created
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCustomerDropdown)).sendKeys(customerName);
        // Add wait for auto-complete and selection if necessary, e.g., Keys.ENTER
        
        System.out.println("Started Quote for: " + customerName);
    }

    public void addInStockItem(String itemName) {
        // Click Add Item
        wait.until(ExpectedConditions.elementToBeClickable(addStockItemsBtn)).click();
        
        // Search and Add
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchField)).sendKeys(itemName);
        wait.until(ExpectedConditions.elementToBeClickable(selectFirstItem)).click();
        
        // Save
        wait.until(ExpectedConditions.elementToBeClickable(saveQuoteBtn)).click();
        
        System.out.println("Added In-Stock item: " + itemName + " to Quote.");
    }
}
