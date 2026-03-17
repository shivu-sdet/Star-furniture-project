package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriverWait wait;

    private By customerTab = By.id("customerTabMenu"); // Placeholder locator
    private By quoteTab = By.id("quoteTabMenu");       // Placeholder locator

    public DashboardPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToCustomers() {
        wait.until(ExpectedConditions.elementToBeClickable(customerTab)).click();
    }

    public void navigateToQuotes() {
        wait.until(ExpectedConditions.elementToBeClickable(quoteTab)).click();
    }
}
