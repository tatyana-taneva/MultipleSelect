import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultipleSelectTest {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    @Test
    public void multipleSelectTest() {
        Select allColors = new Select(driver.findElement(By.name("color")));

        Actions builder = new Actions(driver);

        builder
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.xpath("//option[@value='rd']")))
                .click(driver.findElement(By.xpath("//option[@value='sl']")))
                .keyUp(Keys.CONTROL)
                .perform();

        List<WebElement> allSelectedOptions = allColors.getAllSelectedOptions();
        List<String> actualSelectedOptions = new ArrayList<>();

        for (WebElement curSelectedOption : allSelectedOptions) {
            actualSelectedOptions.add(curSelectedOption.getText());
        }


        String[] expectedSelectedOptions = {"Red", "Silver"};

        Assert.assertEquals(expectedSelectedOptions, actualSelectedOptions.toArray());

    }
    @AfterMethod
    public  void closeBrowser() {
        driver.close();
    }


}








