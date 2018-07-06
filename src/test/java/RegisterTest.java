import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterTest {

    private WebDriver driver;
    private static String FIRSTNAME = "firstName";
    private static String LASTNAME = "lastName";
    private static String ADDRESS = "address";
    private static String CITY = "city";
    private static String ZIPCODE = "zipCode";
    private static String COUNTRY = "country";
    private static String PHONENUMBER = "phoneNumber";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";

    @BeforeTest
    public void setup() {
        driver = createDriver();
        driver.get("http://35.154.181.189/register.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registrationTest() {
        enterText(driver.findElement(By.name("fname")), FIRSTNAME);
        enterText(driver.findElement(By.name("lname")), LASTNAME);
        enterText(driver.findElement(By.name("address")), ADDRESS);
        enterText(driver.findElement(By.name("city")), CITY);
        enterText(driver.findElement(By.name("zip")), ZIPCODE);
        enterText(driver.findElement(By.name("country")), COUNTRY);
        enterText(driver.findElement(By.name("phone")), PHONENUMBER);
        enterText(driver.findElement(By.name("email")), EMAIL);
        enterText(driver.findElement(By.name("password")), PASSWORD);

        clickOn(driver.findElement(By.name("aggree")));
        clickOn(driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")));
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("allow-running-insecure-content");
        options.addArguments("--disable-extensions");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void enterText(WebElement webElement, String value) {
        waitForElementToBeVisible(webElement);
        webElement.sendKeys(value);
    }

    private void clickOn(WebElement webElement) {
        waitForElementToBeVisible(webElement);
        webElement.click();
    }

}