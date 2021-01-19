package systemTesting.gestioneTornei.IscrizioneTorneo;

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
public class TC_2_2_11 {
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
    public void TC2211() throws Exception {

        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Meglio100");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();



        driver.get("http://localhost:8080/PitchFinder_war_exploded/IscrizioneTorneoController?nomeTorneo=Champions+FIVE&dataTorneo=2021-01-18&campoTorneo=1002&nomeSquadra=I+Magnific&minP=4&maxP=6&nGiocatori=4&nome0=antonia&nome1=marco&nome2=paolo&nome3=peppe&nome4=&nome5=&cognome0=gaeta&cognome1=bruno&cognome2=buono&cognome3=ok&cognome4=&cognome5=&nomeCapitano=lucia&cognomeCapitano=gaeta&nomeTorneo=Champions+FIVE&campo=1002&dataTorneo=2021-01-18&conferma=conferma");
        assertEquals("Iscrizione avvenuta con Successo!", closeAlertAndGetItsText());

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
            WebDriverWait wait = new WebDriverWait(driver, 30);
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
