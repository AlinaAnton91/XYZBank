package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class SharedData {
    public WebDriver driver;

    @BeforeMethod
    public void prepareEnvironment() {
        //Deschidem un browser
        driver = new ChromeDriver();

        //Accesam un URL
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        //Maximizam fereastra
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void clearEnvironment() {
        driver.quit();
    }

}
