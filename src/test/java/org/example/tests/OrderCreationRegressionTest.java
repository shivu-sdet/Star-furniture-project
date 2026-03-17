package org.example.tests;

import org.example.pages.*;
import org.testng.annotations.Test;

public class OrderCreationRegressionTest extends BaseTest {

    @Test
    public void testOrderCreationFlow() {
        // Initial setup data
        String url = "https://stg-oms.starfurniture.com:4849/";
        String email = "Akumar@starfurniture.com";
        String password = "Sourcemash@123!";
        
        // Customer Data
        String firstName = "Autoscrp";
        String lastName = "User_" + System.currentTimeMillis();
        String zipCode = "77808"; // Austin / TX
        
        // Item Data
        String stockItemToSearch = "Sofa"; // Example

        // 1. Initialize Pages
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        QuotePage quotePage = new QuotePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // 2. Log in and wait for MFA manually
        System.out.println("Navigating to URL...");
        loginPage.navigateTo(url);
        
        System.out.println("Attempting to login...");
        loginPage.loginWithMFA(email, password);

        // 3. Create Customer
        System.out.println("Navigating to Customers...");
        dashboardPage.navigateToCustomers();
        customerPage.createCustomer(firstName, lastName, zipCode);

        // 4. Create Quote & add In-Stock items
        System.out.println("Navigating to Quotes...");
        dashboardPage.navigateToQuotes();
        quotePage.createQuoteForCustomer(firstName + " " + lastName);
        quotePage.addInStockItem(stockItemToSearch);

        // 5. Place Order
        orderPage.convertQuoteToOrder();
        orderPage.placeOrder();
    }
}
