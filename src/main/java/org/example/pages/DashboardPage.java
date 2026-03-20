package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriverWait wait;

    private By customerTab = By.xpath("//a[@title='Customer']");
    private By quoteTab = By.xpath("//a[@title='Quotes']");
    private By orderTab = By.xpath("//a[@title='Orders']");

    public DashboardPage(WebDriver driver) {
        // Dashboard data takes a significant amount of time to load initially
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void navigateToCustomers() {
        System.out.println("Navigating to Customers...");
        wait.until(ExpectedConditions.elementToBeClickable(customerTab)).click();
        wait.until(ExpectedConditions.urlContains("/customer"));
    }

    public void navigateToQuotes() {
        wait.until(ExpectedConditions.elementToBeClickable(quoteTab)).click();
    }
}
