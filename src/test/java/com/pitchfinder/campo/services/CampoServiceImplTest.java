package com.pitchfinder.campo.services;
import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.Time;



/**
 *  This class manages the test for CampoServiceImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CampoServiceImplTest {
    /**
     *  This is an instance of CampoService.
     */
    private CampoServiceImpl cs;

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
     * This is an instance of Campo entity and CampoDAOImpl
     */
    private Campo c;
    private CampoDAOImpl cdao;
    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    public void setUp() {
        cdao= new CampoDAOImpl();
        cs = new CampoServiceImpl();
        c = new Campo();
        Date d = new Date(1999 - 1900, 10, 4);

        AutenticazioneService as1 = new AutenticazioneServiceImpl();

        as1.registraUtente("mario129@gmail.com", "Mariox129",
                "Mario", "Rossi", "esse3", d);

        AutenticazioneService as2 = new AutenticazioneServiceImpl();

        as2.registraUtente("mario111@gmail.com", "Mariox111",
                "Mario", "Rossi", "esse3", d);

        cs.createOccupazione(ID_CAMPO,Date.valueOf("2010-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00")), USERNAME_ADMIN);

        cdao.doSaveDisponibilita("mario111@gmail.com", ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));
        cs.createDisponibilita("mario129@gmail.com", ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));

        Partita partita= new Partita(0011, 1002, "mario129@gmail.com", Date.valueOf("1999-11-11"), Time.valueOf("09:20".concat(":00")), Time.valueOf("10:20".concat(":00")));

        PartitaDAOImpl pp1= new PartitaDAOImpl();
        pp1.doSavePartita(partita);

        Partita partita1= new Partita(0012, 1002, "mario111@gmail.com", Date.valueOf("1999-11-11"), Time.valueOf("10:30".concat(":00")), Time.valueOf("11:30".concat(":00")));

        PartitaDAOImpl pp11= new PartitaDAOImpl();
        pp11.doSavePartita(partita1);

    }
    /**
     * This method tests the method createOccupazione.
     * creates an Occupazione.
     */

    @Test
    void createOccupazioneTest() {

        assertTrue(cs.createOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")), USERNAME_ADMIN));

    }
    /**
     * This method tests the method createOccupazione.
     * creates an Occupazione deleting first the Partita.
     */

    @Test
    void createOccupazioneDeletePartitaFirstTest() {

        assertTrue(cs.createOccupazione(ID_CAMPO, Date.valueOf("1999-11-11"), Time.valueOf("09:20".concat(":00")), Time.valueOf("11:30".concat(":00")), USERNAME_ADMIN));

    }


    /**
     * This method tests the method deleteOccupazione.
     * deletes an Occupazione.
     */
    @Test
    void deleteOccupazioneTest() {

        assertTrue(cs.deleteOccupazione(ID_CAMPO,Date.valueOf("2010-10-10"), Time.valueOf("20:20".concat(":00")), Time.valueOf("21:00".concat(":00"))));

    }

    /**
     * This method tests the method controllaOccupazione.
     * checks that the Occupazione exists.
     */

    @Test
    void controllaOccupazioneTest() {

        assertTrue(cs.controllaOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * This method tests the method controllaOccupazione.
     * error in checks that the Occupazione exists.
     */

    @Test
    void controllaOccupazioneTestError() {

        assertFalse(cs.controllaOccupazione(123000, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * This method tests the method createDisponibilita.
     * create a Disponibilita.
     */

    @Test
    void createDisponibilitaTest() {

        assertTrue(cs.createDisponibilita(EMAIL, ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }

    /**
     * This method tests the method showAllDisponibilita.
     * return a list of Utenti.
     */
    @Test
    void showAllDisponibilitaTest() {

        assertNotNull(cs.showAllDisponibilita(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00"))));

    }
    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {
        cdao.doRemoveOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")));
        cdao.doRemoveDisponibilita("mario111@gmail.com", ID_CAMPO);
        cdao.doRemoveDisponibilita(EMAIL, ID_CAMPO);
        AutenticazioneService as2 = new AutenticazioneServiceImpl();
        as2.removeUtente("Mariox129");
        cs.deleteOccupazione(ID_CAMPO, Date.valueOf("1999-11-11"), Time.valueOf("09:20".concat(":00")), Time.valueOf("11:30".concat(":00")));

    }

    }

