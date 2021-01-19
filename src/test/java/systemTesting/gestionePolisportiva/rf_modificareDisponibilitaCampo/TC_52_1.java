package systemTesting.gestionePolisportiva.rf_modificareDisponibilitaCampo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;



import static org.junit.jupiter.api.Assertions.fail;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_52_1 {
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
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @Test
    public void TC521(){
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.get("http://localhost:8080/PitchFinder_war_exploded/ModificaDispCampoController?data=&inizio=03%3A16&fine=20%3A16&idcampo=1002&Occupa=Occupa");
        assertEquals("La modifica fallisce perché la data non è selezionata",
                driver.findElement(By.id("error")).getText());
    }
}
