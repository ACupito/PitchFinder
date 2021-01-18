package systemTesting.gestioneSport.rf_prenotareUnCampo;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class TC_3_2_2 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTC322() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("login")).click();
        driver.findElement(By.cssSelector("svg.svg-inline--fa.fa-running.fa-w-13.fa-stack-1x.fa-inverse")).click();
        driver.findElement(By.id("creation-data")).click();
        driver.findElement(By.id("creation-data")).clear();
        driver.findElement(By.id("creation-data")).sendKeys("20210-02-21");
        driver.findElement(By.id("form-creation")).click();
        assertEquals("La data non rispetta il formato", driver.findElement(By.id("small-creation-data")).getText());
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
