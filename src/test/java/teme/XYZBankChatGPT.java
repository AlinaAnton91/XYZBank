package teme;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class XYZBankChatGPT {

    WebDriver driver;

    @Test
    public void metodaTest() {
        //Deschidem un browser
        driver = new ChromeDriver();
        //Accesam un URL
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement bankManagerLoginElement = driver.findElement(By.cssSelector("button[ng-click='manager()']"));
        bankManagerLoginElement.click();

        WebElement addCustomerElement = driver.findElement(By.cssSelector("button[ng-click='addCust()']"));
        addCustomerElement.click();

        WebElement firstNameElement = driver.findElement(By.cssSelector("input[placeholder='First Name']"));
        String firstNameElementValue = "Eric" + System.currentTimeMillis();
        firstNameElement.sendKeys(firstNameElementValue);

        WebElement lastNameElement = driver.findElement(By.cssSelector("input[placeholder='Last Name']"));
        String lastNameElementValue = "Stone";
        lastNameElement.sendKeys(lastNameElementValue);

        WebElement postCodeElement = driver.findElement(By.cssSelector("input[placeholder='Post Code']"));
        String postCodeElementValue = "SW3 4JU";
        postCodeElement.sendKeys(postCodeElementValue);

        WebElement submitCustomerElement = driver.findElement(By.cssSelector("button[type='Submit']"));
        submitCustomerElement.click();
        Alert confirmationAlert = driver.switchTo().alert();
        //String confirmationAlertText = confirmationAlert.getText();
        Assert.assertTrue(confirmationAlert.getText().contains("Customer added successfully with customer id"));
        confirmationAlert.accept();

        WebElement openAccountElement = driver.findElement(By.xpath("//button[@ng-click='openAccount()']"));
        openAccountElement.click();

        WebElement customerDropdownElement = driver.findElement(By.id("userSelect"));
        Select customerSelect = new Select(customerDropdownElement);
        customerSelect.selectByVisibleText(firstNameElementValue + " " + lastNameElementValue);

        WebElement currencyDropdownElement = driver.findElement(By.id("currency"));
        Select currencySelect = new Select(currencyDropdownElement);
        currencySelect.selectByVisibleText("Dollar");

        WebElement processElement = driver.findElement(By.cssSelector("button[type='submit']"));
        processElement.click();
        confirmationAlert = driver.switchTo().alert();
        String alertText = confirmationAlert.getText();
        String[] alertSplit = alertText.split(":");
        String accountID = alertSplit[1];
        confirmationAlert.accept();

        WebElement customersElement = driver.findElement(By.cssSelector("button[ng-click='showCust()']"));
        customersElement.click();


        WebElement searchElement = driver.findElement(By.cssSelector("input[placeholder='Search Customer']"));
        searchElement.sendKeys(firstNameElementValue);

        List<WebElement> tableResultsList = driver.findElements(By.xpath("//table/tbody/tr"));
        Assert.assertTrue(tableResultsList.get(0).getText().contains(firstNameElementValue));
        Assert.assertTrue(tableResultsList.get(0).getText().contains(lastNameElementValue));
        Assert.assertTrue(tableResultsList.get(0).getText().contains(postCodeElementValue));
        Assert.assertTrue(tableResultsList.get(0).getText().contains(accountID));


        WebElement deleteElement = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteElement.click();

        tableResultsList = driver.findElements(By.xpath("//table/tbody/tr"));
        Assert.assertEquals(tableResultsList.size(), 0);


//
//        driver.quit();

        // Luăm toate rândurile din tabel
//        List<WebElement> randuri = driver.findElements(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr"));
//
//        WebElement tabelCustomers = driver.findElement(By.cssSelector("div[class='marTop ng-scope']"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", tabelCustomers);

       }
    }

