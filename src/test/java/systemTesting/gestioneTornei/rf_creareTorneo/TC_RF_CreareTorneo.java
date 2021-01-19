package systemTesting.gestioneTornei.rf_creareTorneo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_RF_CreareTorneo {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    @Test
    public void testTC211() throws Exception {
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
        driver.findElement(By.id("uname")).sendKeys("C");
        driver.findElement(By.xpath("//div/div")).click();
        assertEquals("La lunghezza del nome non è valida!", driver.findElement(By.id("valid_nome")).getText());
    }

    @Test
    public void testTC212() throws Exception {
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
        driver.findElement(By.id("uname")).sendKeys("???");
        driver.findElement(By.xpath("//div[2]")).click();
        assertEquals("Il formato del nome non è valido!", driver.findElement(By.id("valid_nome")).getText());
    }

    @Test
    public void testTC213() throws Exception {
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
        driver.findElement(By.id("sport")).click();
        new Select(driver.findElement(By.id("sport"))).selectByVisibleText("Tennis");
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("sport")).click();
        new Select(driver.findElement(By.id("sport"))).selectByVisibleText("");
        driver.findElement(By.id("sport")).click();
        assertEquals("Lo sport non è stato selezionato!", driver.findElement(By.id("valid_sport")).getText());
    }

    @Test
    public void testTC214() throws Exception {
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
        driver.findElement(By.id("sport")).click();
        new Select(driver.findElement(By.id("sport"))).selectByVisibleText("Tennis");
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("tipo")).click();
        new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Gironi");
        driver.findElement(By.id("tipo")).click();
        driver.findElement(By.id("tipo")).click();
        new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("");
        driver.findElement(By.id("tipo")).click();
        assertEquals("Il tipo non è stato selezionato!", driver.findElement(By.id("valid_tipo")).getText());
    }

    @Test
    public void testTC215() throws Exception {
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
        driver.findElement(By.id("sport")).click();
        new Select(driver.findElement(By.id("sport"))).selectByVisibleText("Tennis");
        driver.findElement(By.id("sport")).click();
        driver.findElement(By.id("tipo")).click();
        new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Gironi");
        driver.findElement(By.id("tipo")).click();
        driver.findElement(By.id("struttura")).click();
        new Select(driver.findElement(By.id("struttura"))).selectByVisibleText("Partite singole");
        driver.findElement(By.id("struttura")).click();
        driver.findElement(By.id("struttura")).click();
        new Select(driver.findElement(By.id("struttura"))).selectByVisibleText("");
        driver.findElement(By.id("struttura")).click();
        assertEquals("La struttura non è stata selezionata!", driver.findElement(By.id("valid_struttura")).getText());
    }


    @Test
    public void testTC216() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("10-02-0001");
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("10-02-0011");
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("10-02-0111");
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("10-02-1111");
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("");
        driver.findElement(By.xpath("//div/div")).click();
        assertEquals("La data di inizio non è selezionata!", driver.findElement(By.id("valid_dataInizio")).getText());
    }

    @Test
    public void testTC217() throws Exception {
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
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("10-10-0222");
        assertEquals("Il formato della data di inizio è sbagliato!", driver.findElement(By.id("valid_dataInizio")).getText());
    }

    @Test
    public void testTC218() throws Exception {
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
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("10-02-3332");
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("");
        driver.findElement(By.xpath("//body")).click();
        assertEquals("La data di fine non è selezionata!", driver.findElement(By.id("valid_dataFine")).getText());
    }

    @Test
    public void testTC219() throws Exception {
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
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-0221");
        assertEquals("Il formato della data di fine è sbagliato!", driver.findElement(By.id("valid_dataFine")).getText());
    }

    @Test
    public void testTC2110() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("p");
        driver.findElement(By.id("valid_giornoPartite")).click();
        driver.findElement(By.xpath("//div/div/div")).click();
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("La lunghezza del giorno delle partite non è valida!", driver.findElement(By.id("valid_giornoPartite")).getText());
    }

    @Test
    public void testTC2111() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("??JJ");
        driver.findElement(By.id("valid_sport")).click();
        driver.findElement(By.xpath("//div[@id='creaTorneo']/div[2]/div/form/div[6]")).click();
        driver.findElement(By.xpath("//div[@id='creaTorneo']/div[2]/div/form/div[6]")).click();
        assertEquals("Il formato del giorno delle partite non è valido!", driver.findElement(By.id("valid_giornoPartite")).getText());
    }




    @Test
    public void testTC2112() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
        driver.findElement(By.id("maxSquadre")).click();
        driver.findElement(By.id("maxSquadre")).clear();
        driver.findElement(By.id("maxSquadre")).sendKeys("n");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Il formato del numero di squadre non è valido!", driver.findElement(By.id("valid_squadra")).getText());
    }

    @Test
    public void testTC2113() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
        driver.findElement(By.id("maxSquadre")).click();
        driver.findElement(By.id("maxSquadre")).clear();
        driver.findElement(By.id("maxSquadre")).sendKeys("1000");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Numero di squadre non valido!", driver.findElement(By.id("valid_squadra")).getText());
    }

    @Test
    public void testTC2114() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
        driver.findElement(By.id("maxSquadre")).click();
        driver.findElement(By.id("maxSquadre")).clear();
        driver.findElement(By.id("maxSquadre")).sendKeys("20");
        driver.findElement(By.id("minPartecipanti")).click();
        driver.findElement(By.id("minPartecipanti")).clear();
        driver.findElement(By.id("minPartecipanti")).sendKeys("a");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Il formato del numero minimo di partecipanti non è valido!", driver.findElement(By.id("valid_minParteci")).getText());
    }

    @Test
    public void testTC2115() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
        driver.findElement(By.id("maxSquadre")).click();
        driver.findElement(By.id("maxSquadre")).clear();
        driver.findElement(By.id("maxSquadre")).sendKeys("20");
        driver.findElement(By.id("minPartecipanti")).click();
        driver.findElement(By.id("minPartecipanti")).clear();
        driver.findElement(By.id("minPartecipanti")).sendKeys("60");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Numero minimo di partecipanti non valido!", driver.findElement(By.id("valid_minParteci")).getText());
    }

    @Test
    public void testTC2116() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
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
        driver.findElement(By.id("maxPartecipanti")).sendKeys("a");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Il formato del numero massimo di partecipanti non è valido!", driver.findElement(By.id("valid_maxParteci")).getText());
    }

    @Test
    public void testTC2117() throws Exception {
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
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
        driver.findElement(By.id("maxPartecipanti")).sendKeys("60");
        driver.findElement(By.xpath("//div/div/div")).click();
        assertEquals("Numero massimo di partecipanti non valido!", driver.findElement(By.id("valid_maxParteci")).getText());
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
        driver.findElement(By.id("data_inizio")).click();
        driver.findElement(By.id("data_inizio")).clear();
        driver.findElement(By.id("data_inizio")).sendKeys("18-01-2022");
        driver.findElement(By.id("data_fine")).click();
        driver.findElement(By.id("data_fine")).clear();
        driver.findElement(By.id("data_fine")).sendKeys("25-01-2022");
        driver.findElement(By.id("giornoPartite")).click();
        driver.findElement(By.id("giornoPartite")).clear();
        driver.findElement(By.id("giornoPartite")).sendKeys("Lunedì");
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


    @BeforeAll
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
}