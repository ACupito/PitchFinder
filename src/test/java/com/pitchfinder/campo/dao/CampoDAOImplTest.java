package com.pitchfinder.campo.dao;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.campo.entity.Campo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.Time;



/**
 *  This class manages the test for CampoDAOImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CampoDAOImplTest {

    /**
     *  This is an instance of CampoDAO.
     */
    private CampoDAO cdao;

    /**
     * Parameters declaration.
     */
    private static final String USERNAME_ADMIN = "memex99";
    private static final int ID_CAMPO = 1002;
    private static final String DATA = "2020-10-10";
    private static final String TEMPO_INIZIO = "10:00";
    private static final String TEMPO_FINE = "11:00";
    private static final String EMAIL = "manuzzi99@gmail.com";

    /**
     * This is an instance of Campo entity.
     */
    private Campo c;

    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    public void setUp() {

        cdao = new CampoDAOImpl();
        c = new Campo();
        cdao.doSaveOccupazione(ID_CAMPO,Date.valueOf("2010-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00")), USERNAME_ADMIN);

        Date d = new Date(1999 - 1900, 10, 4);

        AutenticazioneService as1 = new AutenticazioneServiceImpl();

        as1.registraUtente("mario129@gmail.com", "Mariox129",
                "Mario", "Rossi", "esse3", d);
        AutenticazioneService as2 = new AutenticazioneServiceImpl();

        as2.registraUtente("mario111@gmail.com", "Mariox111",
                "Mario", "Rossi", "esse3", d);

        cdao.doSaveDisponibilita("mario129@gmail.com", ID_CAMPO, Date.valueOf("2010-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00")));
        cdao.doSaveDisponibilita("mario111@gmail.com", ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));
    }
    /**
     * This method tests the method doRetriveCampo.
     * get the Campo.
     */
    @Test
    void doRetriveCampoTest(){
        assertNotNull(cdao.doRetriveCampo(1002));
    }
    /**
     * This method tests the method doRetriveCampo.
     * not get the Campo.
     */
    @Test
    void doRetriveCampoTestNull(){
        assertNull(cdao.doRetriveCampo(1000));
    }

    /**
     * This method tests the method doSaveOccupazione.
     * saves the Occupazione.
     */

    @Test
    void doSaveOccupazioneTest() {

        assertTrue(cdao.doSaveOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")), USERNAME_ADMIN));

    }

    /**
     * This method tests the method doSaveOccupazione.
     * error in saves the Occupazione.
     */

    @Test
    void doSaveOccupazioneTestError() {

        assertThrows(RuntimeException.class ,() -> {cdao.doSaveOccupazione(ID_CAMPO,Date.valueOf("2010-10-10") , Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00")), USERNAME_ADMIN);});

    }
    /**
     * This method tests the method doRemoveOccupazione.
     * deletes the Occupazione.
     */
    @Test
    void doRemoveOccupazioneTest() {

        assertTrue(cdao.doRemoveOccupazione(ID_CAMPO,Date.valueOf("2010-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00"))));

    }

    /**
     * This method tests the method doRemoveOccupazione.
     * deletes the Occupazione.
     */
    @Test
    void doRemoveOccupazioneTestError() {

        assertThrows(RuntimeException.class ,() -> {cdao.doRemoveOccupazione(00000000,Date.valueOf("00-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00")));});

    }
    /**
     * This method tests the method checkOccupazioneExistence.
     * checks that the Occupazione exists.
     */

    @Test
    void checkOccupazioneExistenceTest() {

        assertTrue(cdao.checkOccupazioneExistence(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * This method tests the method checkOccupazioneExistence.
     * checks that the Occupazione exists.
     */

    @Test
    void checkOccupazioneExistenceTestFalse() {

        assertFalse(cdao.checkOccupazioneExistence(1000, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * This method tests the method doSaveDisponibilita.
     * saves the Disponibilita.
     */

    @Test
    void doSaveDisponibilitaTestError() {

        assertThrows(RuntimeException.class ,() -> {cdao.doSaveDisponibilita(EMAIL, ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));});

    }
    /**
     * This method tests the method doSaveDisponibilita.
     * error in saves the Disponibilita.
     */

    @Test
    void doSaveDisponibilitaTest() {

        assertTrue(cdao.doSaveDisponibilita(EMAIL, ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * This method tests the method doRemoveDisponibilita.
     * removes the Disponibilita.
     */

    @Test
    void doRemoveDisponibilitaTest() {

        assertTrue(cdao.doRemoveDisponibilita("mario129@gmail.com", ID_CAMPO,Date.valueOf("2010-10-10")));

    }
    /**
     * This method tests the method doRetriveEmailByDisponibilita.
     * return a list of emails.
     */

    @Test
    void doRetriveEmailByDisponibilitaTest() {

        assertNotNull(cdao.doRetriveEmailByDisponibilita(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }

    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {
        cdao.doRemoveOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));

        cdao.doRemoveDisponibilita(EMAIL, ID_CAMPO,Date.valueOf(DATA));
        cdao.doRemoveDisponibilita("mario111@gmail.com", ID_CAMPO,Date.valueOf(DATA));
        AutenticazioneService as1 = new AutenticazioneServiceImpl();
        AutenticazioneService as2 = new AutenticazioneServiceImpl();
        as1.removeUtente("Mariox129");
        as2.removeUtente("Mariox111");
    }
}
