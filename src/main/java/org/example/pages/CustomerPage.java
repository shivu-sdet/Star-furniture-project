package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for customer creation (Placeholder)
    private By newCustomerBtn = By.id("btnCreateCustomer");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By zipCodeField = By.id("zipCode");   // Mandatory for Austin/Texas e.g., 78701
    private By saveCustomerBtn = By.id("btnSaveCustomer");
    private By confirmationMsg = By.id("customerSuccessMsg");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void createCustomer(String fName, String lName, String zipCode) {
        // Wait for and click New Customer button
        wait.until(ExpectedConditions.elementToBeClickable(newCustomerBtn)).click();
        
        // Enter mandatory details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fName);
        driver.findElement(lastNameField).sendKeys(lName);
        driver.findElement(zipCodeField).sendKeys(zipCode); // For Texas/Austin
        
        // Save
        wait.until(ExpectedConditions.elementToBeClickable(saveCustomerBtn)).click();
        
        // Verify success
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
        System.out.println("Customer saved successfully!");
    }
}
