package systemTesting.gestioneEventi.RF_GE_1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_RF_CreareEventi {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();



    @Test
    public void testTC111() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("");
        assertEquals("La lunghezza del nome non è valida",
                driver.findElement(By.id("name-evento-invalid")).getText());
    }

    @Test
    public void testTC112() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Evento%%&&&£");
        assertEquals("Il formato del nome non è valido",
                driver.findElement(By.id("name-evento-valid")).getText());
    }

    @Test
    public void testTC113() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("");
        assertEquals("Inserire l’orario di inizio",
                driver.findElement(By.id("time-evento-str-invalid")).getText());
    }

    @Test
    public void testTC114() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("?!:78");
        assertEquals("L'orario di inizio non rispetta il formato",
                driver.findElement(By.id("time-evento-str-valid")).getText());
    }

    @Test
    public void testTC115() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("");
        assertEquals("Inserisci l’orario di fine",
                driver.findElement(By.id("time-evento-end-invalid")).getText());
    }

    @Test
    public void testTC116() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("40:'?");
        assertEquals("L'orario di fine non rispetta il formato",
                driver.findElement(By.id("time-evento-end-valid")).getText());
    }

    @Test
    public void testTC117() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("");
        assertEquals("Inserire la data",
                driver.findElement(By.id("date-evento-invalid")).getText());
    }

    @Test
    public void testTC118() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("Aw/s1/??");
        assertEquals("La data non rispetta il formato",
                driver.findElement(By.id("date-evento-valid")).getText());
    }

    @Test
    public void testTC119() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("");
        assertEquals("La lunghezza del nome dell’ospite non è valida",
                driver.findElement(By.id("guest-evento-invalid")).getText());
    }

    @Test
    public void testTC1110() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("!€Giu€PP£!12");
        assertEquals("Il formato dell'ospite non è valido",
                driver.findElement(By.id("guest-evento-valid")).getText());
    }

    @Test
    public void testTC1111() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("Test Ospite");
        driver.findElement(By.id("creation-description-Evento")).clear();
        driver.findElement(By.id("creation-description-Evento")).sendKeys("");
        assertEquals("La lunghezza della descrizione non è valida",
                driver.findElement(By.id("description-evento-invalid")).getText());
    }

    @Test
    public void testTC1112() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("Test Ospite");
        driver.findElement(By.id("creation-description-Evento")).clear();
        driver.findElement(By.id("creation-description-Evento")).sendKeys("#@Descrizione <> Evento");
        assertEquals("Il formato della descrizione non è valido",
                driver.findElement(By.id("description-evento-valid")).getText());
    }

    @Test
    public void testTC1113() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("Test Ospite");
        driver.findElement(By.id("creation-description-Evento")).clear();
        driver.findElement(By.id("creation-description-Evento")).sendKeys("Descrizione di prova");
        driver.findElement(By.id("creation-player-Evento")).clear();
        driver.findElement(By.id("creation-player-Evento")).sendKeys("");
        assertEquals("Il numero di posti disponibili non è valido",
                driver.findElement(By.id("sits-evento-invalid")).getText());
    }

    @Test
    public void testTC1114() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("Test Ospite");
        driver.findElement(By.id("creation-description-Evento")).clear();
        driver.findElement(By.id("creation-description-Evento")).sendKeys("Descrizione di prova");
        driver.findElement(By.id("creation-player-Evento")).clear();
        driver.findElement(By.id("creation-player-Evento")).sendKeys("@");
        assertEquals("Il numero dei posti disponibili non rispetta il formato",
                driver.findElement(By.id("sits-evento-valid")).getText());
    }

    @Test
    public void testTC1115() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("AdminEmanuele99");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("crea_evento")).click();
        driver.findElement(By.id("creation-name-Evento")).clear();
        driver.findElement(By.id("creation-name-Evento")).sendKeys("Test");
        driver.findElement(By.id("creation-timestr-Evento")).clear();
        driver.findElement(By.id("creation-timestr-Evento")).sendKeys("10:00");
        driver.findElement(By.id("creation-timeend-Evento")).clear();
        driver.findElement(By.id("creation-timeend-Evento")).sendKeys("11:00");
        driver.findElement(By.id("creation-data-Evento")).clear();
        driver.findElement(By.id("creation-data-Evento")).sendKeys("2021-12-31");
        driver.findElement(By.id("creation-guest-Evento")).clear();
        driver.findElement(By.id("creation-guest-Evento")).sendKeys("Test Ospite");
        driver.findElement(By.id("creation-description-Evento")).clear();
        driver.findElement(By.id("creation-description-Evento")).sendKeys("Descrizione di prova");
        driver.findElement(By.id("creation-player-Evento")).clear();
        driver.findElement(By.id("creation-player-Evento")).sendKeys("150");
        driver.findElement(By.id("submit")).click();
        while(isAlertPresent());
        assertEquals("La creazione dell’evento è andata a buon fine",
                driver.findElement(By.id("flagMessage")).getAttribute("value"));
    }

    @AfterAll
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


    @BeforeAll
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
}