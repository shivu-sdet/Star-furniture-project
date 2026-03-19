package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait mfaWait; // Long wait for manual MFA approval

    // Adjust these locators based on the actual DOM of Star Furniture OMS
    private By emailField = By.name("loginfmt"); // Microsoft login
    private By nextButton = By.id("idSIButton9"); // Microsoft next button
    private By passwordField = By.name("passwd"); // Microsoft password
    private By signInButton = By.id("idSIButton9"); // Microsoft sign in
    private By mfaReminder = By.id("idSIButton9"); // Microsoft Keep me signed in prompt

    // Some element that uniquely identifies the actual OMS Dashboard after
    // Microsoft log in
    private By dashboardElement = By.xpath("//a[contains(@title, 'Customer')] | //p[contains(text(), 'Customer')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // MFA approval and slow dashboard redirect might take time, allowing 5 minutes
        this.mfaWait = new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void loginWithMFA(String email, String password) {
        // Enter Email
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.sendKeys(email);

        // Click Next
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        // Enter Password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password);

        // Click Sign In
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        // Inform user to approve on phone
        System.out.println("ACTION REQUIRED ");
        System.out.println("Please approve the MFA request on your phone.");
        System.out.println("Waiting up to 120 seconds...");
        System.out.println("=====================================");

        // Wait for the Dashboard or the 'Keep me signed in' page to appear.
        // Once approved, there might be a "Stay signed in?" screen.
        try {
            WebElement staySignedInBtn = mfaWait.until(ExpectedConditions.elementToBeClickable(mfaReminder));
            staySignedInBtn.click();
            System.out.println("Clicked 'Stay signed in' prompt.");
        } catch (Exception e) {
            System.out.println("'Stay signed in' prompt not seen, assuming direct redirect.");
        }

        // Wait for OMS dashboard to load
        mfaWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardElement));
        System.out.println("Successfully logged in and reached the Dashboard!");
    }
}
