package systemTesting.gestioneUtente.rf_registrareUtente;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_4_2_3 {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @BeforeAll
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedrivermac");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTC423() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/autentication?flag=-1");
        driver.findElement(By.id("username_")).click();
        driver.findElement(By.id("username_")).clear();
        driver.findElement(By.id("username_")).sendKeys("Memex99999999999999999999999999999999999" +
                "999999999999");
        assertEquals("La username inserita non rispetta la lunghezza corretta",
                driver.findElement(By.id("div_username_")).getText());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
