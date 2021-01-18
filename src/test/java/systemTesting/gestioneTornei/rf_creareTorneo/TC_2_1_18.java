package systemTesting.gestioneTornei.rf_creareTorneo;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_2_1_18 {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeAll
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTC2118() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_torneo")).click();
        driver.findElement(By.id("uname")).click();
        driver.findElement(By.id("uname")).clear();
        driver.findElement(By.id("uname")).sendKeys("Champions Five");
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("2021-01-18");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("2021-01-20");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("sport")).click();
        new Select(driver.findElement(By.id("sport"))).selectByVisibleText("Tennis");
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("tipo")).click();
        new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Gironi");
        driver.findElement(By.id("tipo")).click();
        driver.findElement(By.id("struttura")).click();
        new Select(driver.findElement(By.id("struttura"))).selectByVisibleText("Partite singole");
        driver.findElement(By.id("struttura")).click();
        driver.findElement(By.id("maxSquadre")).click();
        driver.findElement(By.id("maxSquadre")).clear();
        driver.findElement(By.id("maxSquadre")).sendKeys("20");
        driver.findElement(By.id("minPartecipanti")).click();
        driver.findElement(By.id("minPartecipanti")).clear();
        driver.findElement(By.id("minPartecipanti")).sendKeys("60");
        driver.findElement(By.id("minPartecipanti")).click();
        driver.findElement(By.id("minPartecipanti")).clear();
        driver.findElement(By.id("minPartecipanti")).sendKeys("4");
        driver.findElement(By.id("maxPartecipanti")).click();
        driver.findElement(By.id("maxPartecipanti")).clear();
        driver.findElement(By.id("maxPartecipanti")).sendKeys("6");
        driver.findElement(By.id("createTorneo")).click();
        assertEquals("La creazione del torneo � avvenuta correttamente", closeAlertAndGetItsText());
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
