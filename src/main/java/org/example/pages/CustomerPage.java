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
    private By newCustomerBtn = By.xpath("//button[contains(., 'Add') and contains(., 'Customer')] | //button[contains(@class, 'button-primary')] | //a[contains(@class, 'button-primary')]");
    private By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private By phoneField = By.xpath("//input[contains(@placeholder, 'Phone')]");
    private By addressField = By.xpath("//input[contains(@placeholder, 'Address Line 1')]");
    private By zipCodeField = By.xpath("//input[@placeholder='Zip Code']");
    private By saveCustomerBtn = By.xpath("//*[contains(text(), 'CONTINUE')] | //button[contains(., 'CONTINUE')] | //a[contains(., 'CONTINUE')]");
    private By confirmationMsg = By.xpath("//div[contains(text(), 'successfully') or contains(@class, 'success')]");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void createCustomer(String fName, String lName, String phone, String address, String zipCode) {
        // Wait for and click New Customer button
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        wait.until(ExpectedConditions.elementToBeClickable(newCustomerBtn)).click();
        
        // Enter mandatory details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fName);
        driver.findElement(lastNameField).sendKeys(lName);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(zipCodeField).sendKeys(zipCode); // For Texas/Austin
        
        // Save (Click CONTINUE)
        wait.until(ExpectedConditions.elementToBeClickable(saveCustomerBtn)).click();
        
        // Verify success
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
        System.out.println("Customer saved successfully!");
    }
}
