package systemTesting.gestioneSport.rf_indicareDisponibilita;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_31_7 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @AfterAll
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @BeforeAll
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @Test
    public void TC317() throws Exception {

        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Meglio100");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();

        driver.findElement(By.cssSelector("svg.svg-inline--fa.fa-calendar-check.fa-w-14.fa-stack-1x.fa-inverse")).click();
        driver.findElement(By.id("data")).click();
        driver.findElement(By.id("data")).clear();
        driver.findElement(By.id("data")).sendKeys("25-02-2021");
        driver.findElement(By.id("inizio")).click();
        driver.findElement(By.id("inizio")).sendKeys("12:00");
        driver.findElement(By.id("fine")).click();
        driver.findElement(By.id("fine")).sendKeys("17:00");
        driver.findElement(By.id("idcampo")).click();
        driver.findElement(By.id("idcampo")).click();
        driver.findElement(By.id("Conferma")).click();


        driver.get("http://localhost:8080/PitchFinder_war_exploded/DareDispCampoController?data=2021-01-29&inizio=09%3A00&fine=12%3A00&idcampo=1002&Conferma=Conferma");

        assertEquals("La disponibilità viene memorizzata con successo.", closeAlertAndGetItsText());
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
            WebDriverWait wait = new WebDriverWait(driver, 15);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
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

