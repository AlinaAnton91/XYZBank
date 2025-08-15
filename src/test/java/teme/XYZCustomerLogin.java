package teme;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.SharedData;

import java.util.List;

public class XYZCustomerLogin extends SharedData {

    @Test
    public void testMethod() {
        WebElement customerLoginElement = driver.findElement(By.cssSelector("button[ng-click='customer()']"));
        customerLoginElement.click();

        WebElement nameDropdownElement = driver.findElement(By.id("userSelect"));
        Select nameSelect = new Select(nameDropdownElement);
        nameSelect.selectByVisibleText("Ron Weasly");

        WebElement loginElement = driver.findElement(By.cssSelector("button[type='submit']"));
        loginElement.click();

        //Salvam suma initiala
        List<WebElement> accountDetailsList = driver.findElements(By.xpath("//div[@ng-hide='noAccount']/strong"));
        String currentAmountText = accountDetailsList.get(1).getText();
        System.out.println("Suma initiala este: " + currentAmountText);

        //Conversie la int
        //int currentAmount = Integer.parseInt(currentAmountText.replaceAll("[^0-9]", ""));
        int currentAmount = Integer.parseInt(currentAmountText);

        //Depunem bani
        WebElement depositElement = driver.findElement(By.cssSelector("button[ng-click='deposit()']"));
        depositElement.click();

        WebElement amountToBeDepositedElement = driver.findElement(By.cssSelector("input[placeholder='amount']"));
        String amountToDepositText = "50";
        amountToBeDepositedElement.sendKeys(amountToDepositText);

        // Conversie la int pentru depunere
        int amountToDeposit = Integer.parseInt(amountToDepositText);

        WebElement submitElement = driver.findElement(By.cssSelector("button[type='submit']"));
        submitElement.click();

        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Deposit Successful']"));
        String confirmationMessageText = confirmationMessage.getText();
        Assert.assertEquals(confirmationMessageText, "Deposit Successful");

        accountDetailsList = driver.findElements(By.xpath("//div[@ng-hide='noAccount']/strong"));
        String newAmountText = accountDetailsList.get(1).getText();
        System.out.println("Suma noua este: " + newAmountText);

        // Conversie la int pentru noua suma
        //int newAmount = Integer.parseInt(newAmountText.replaceAll("[^0-9]", ""));
        int newAmount = Integer.parseInt(newAmountText);

        Assert.assertEquals(newAmount, currentAmount + amountToDeposit,
                "Suma nouă nu este egală cu suma inițială + depunerea");

        //Selectam un alt cont
        //WebElement accountElement = driver.findElement(By.id("accountSelect"));
        //Select accountSelect = new Select(accountElement);
        //accountSelect.selectByVisibleText("1008");

    }
}
