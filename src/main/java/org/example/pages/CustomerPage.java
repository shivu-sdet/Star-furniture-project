package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for customer creation from actual OMS DOM
    private By newCustomerBtn = By.xpath("//*[contains(text(), 'ADD NEW CUSTOMER')] | //button[contains(., 'ADD')] | //a[contains(., 'ADD')]");
    private By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private By zipCodeField = By.xpath("//input[@placeholder='Zip Code']");
    private By saveCustomerBtn = By.xpath("//button[contains(., 'CONTINUE')]");
    private By confirmationMsg = By.xpath("//div[contains(text(), 'successfully') or contains(@class, 'success')]");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void createCustomer(String fName, String lName, String zipCode) {
        // Wait for and click New Customer button
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
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
