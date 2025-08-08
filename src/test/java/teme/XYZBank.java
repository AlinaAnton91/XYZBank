package teme;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class XYZBank {

    WebDriver driver;

    @Test
    public void metodaTest() {
        //Deschidem un browser
        driver = new ChromeDriver();

        //Accesam un URL
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement bankManagerLoginElement = driver.findElement(By.cssSelector("button[ng-click='manager()']"));
        bankManagerLoginElement.click();

        WebElement addCustomerElement = driver.findElement(By.cssSelector("button[ng-click='addCust()']"));
        addCustomerElement.click();

        WebElement firstNameElement = driver.findElement(By.cssSelector("input[placeholder='First Name']"));
        String firstNameElementValue = "Eric";
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

        WebElement customersElement = driver.findElement(By.cssSelector("button[ng-click='showCust()']"));
        customersElement.click();


        WebElement tabelCustomers = driver.findElement(By.cssSelector("div[class='marTop ng-scope']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", tabelCustomers);


        //js.executeScript("window.scrollBy(0,250)", "");

    }

}
