# Star Furniture OMS Regression Suite

This is an automated regression suite built using Java, Selenium WebDriver, and TestNG to automate the Order Creation flow in the Star Furniture OMS system (Staging).

## Features
- **Page Object Model (POM)**: Ensures reusability and maintainability of element locators and logic.
- **Automated MFA Handling**: The authentication process has been configured to log in with Microsoft credentials and automatically wait up to 120 seconds for you to manually approve the prompt via the Microsoft Authenticator App on your phone.
- **End-to-End Order Creation**:
    1. Login & MFA Approval.
    2. Customer Creation (Mandatory fields & Texas/Austin zip code).
    3. Quote Creation for the saved customer.
    4. Adding an in-stock item to the quote.
    5. Placing the order.

## Prerequisites
- Java 23 installed.
- (Optional but recommended) Maven installed. Alternatively, you can run tests directly from IntelliJ IDEA/Eclipse without installing Maven in your terminal.
- Chrome Browser installed (the suite uses `WebDriverManager` to maintain driver compatibility automatically).

## Running the Suite Without Antigravity

This regression suite can be run completely independently via your terminal or IDE using **Maven** and **TestNG**.

### Method 1: Using the Pre-packaged Maven (Terminal) 
If you don't have Maven globally installed on your machine, I've downloaded a local standalone version for you inside this project. Simply run this exact command from within your `StarProject` folder:
```bash
.\apache-maven-3.9.6\bin\mvn.cmd clean test -Dtest=OrderCreationRegressionTest
```

### Method 2: Global Maven (Terminal)
If Maven is installed and configured in your system `PATH`:
```bash
mvn clean test -Dtest=OrderCreationRegressionTest
```

### Method 3: Via IntelliJ IDEA Setup
1. Open the `StarProjectNew` project in IntelliJ IDEA.
2. In the Project Explorer pane on the left, right-click the `pom.xml` -> **Maven** -> **Reload Project**. This ensures all dependencies (Selenium) are successfully downloaded.
3. Open `src/test/java/org/example/tests/OrderCreationRegressionTest.java`.
4. Click the green play button (`▶`) next to the `testOrderCreationFlow()` method.

## Important Considerations & Next Steps

### Updating Locators
The provided Page Objects (`LoginPage.java`, `DashboardPage.java`, `CustomerPage.java`, `QuotePage.java`, `OrderPage.java`) currently contain **placeholder locators** based on typical IDs. 
Since the actual internal DOM structure wasn't accessible at creation time, you must inspect the actual `stg-oms.starfurniture.com` portal and update the specific `By.id("...")` or `By.xpath("...")` references, particularly for:
- Quote / Customer Navigation links
- Dropdowns or Search auto-completes for Products and Customers.

### MFA Wait Behavior
When the script reaches the Microsoft Sign-in page, it inputs the provided email and password and hits next. 
At this exact moment, you need to check your phone to approve the MFA request. The framework is inherently programmed to "pause" using `WebDriverWait` (up to 120 secs) until the `Dashboard` element becomes visible.
