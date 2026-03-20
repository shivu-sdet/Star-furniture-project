package org.example.tests;

import org.example.pages.*;
import org.testng.annotations.Test;

public class OrderCreationRegressionTest extends BaseTest {

    // Initial setup data
    String url = "https://stg-oms.starfurniture.com:4849/";
    String email = "Akumar@starfurniture.com";
    String password = "Sourcemash@123!";
    
    // Global Customer Data for shared states
    String firstNamePrefix = "auto customer 1 ";
    static String firstName = "auto customer 1 " + (System.currentTimeMillis() % 100000); // Unique suffix
    static String lastName = "User_" + (System.currentTimeMillis() % 100000);
    String zipCode = "77808"; // Austin / TX
    String phoneNumber = "8555555555"; 
    String addressLine1 = "123 Star Street";
    
    // Item Data
    String stockItemToSearch = "Sofa"; // Example

    @Test(priority = 1, description = "Logs into the Staging OMS portal and waits for manual user MFA approval.")
    public void testLoginAndMFA() {
        LoginPage loginPage = new LoginPage(driver);
        
        System.out.println("Navigating to URL...");
        loginPage.navigateTo(url);
        
        System.out.println("Attempting to login...");
        loginPage.loginWithMFA(email, password);

        // EXTRA WAIT: Allow dashboard data to settle as requested by user
        try {
            System.out.println("Waiting 10 seconds for dashboard to fully settle...");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2, dependsOnMethods = "testLoginAndMFA", description = "Navigates to Dashboard tab and creates a new autoscrp customer.")
    public void testCreateCustomer() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        
        System.out.println("Navigating to Customers...");
        dashboardPage.navigateToCustomers();
        customerPage.createCustomer(firstName, lastName, phoneNumber, addressLine1, zipCode);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateCustomer", description = "Selects the previous customer, creates a quote, and adds in-stock items.")
    public void testCreateQuoteAndAddItems() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        QuotePage quotePage = new QuotePage(driver);
        
        System.out.println("Navigating to Quotes...");
        dashboardPage.navigateToQuotes();
        quotePage.createQuoteForCustomer(firstName + " " + lastName);
        quotePage.addInStockItem(stockItemToSearch);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateQuoteAndAddItems", description = "Finalizes the quote and confirms placement of the order.")
    public void testPlaceOrder() {
        OrderPage orderPage = new OrderPage(driver);
        
        orderPage.convertQuoteToOrder();
        orderPage.placeOrder();
    }
}
