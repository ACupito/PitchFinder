package com.pitchfinder.torneo.services;

import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class manages the tests for TorneoServiceImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoServiceImplTest {

    /**
     *  This is an instance of TorneoService.
     */
    private TorneoService tservice;

    /**
     * Parameters declaration.
     */
    private static final String USERNAME_ADMIN = "memex99";
    private static final String NOME = "Champions";
    private static final int ID_CAMPO = 1002;
    private static final String TIPO = "Gironi";
    private static final String STRUTTURA = "Partite singole";
    private static final int MAX_SQUADRE = 12;
    private static final int MIN_PARTECIPANTI = 1;
    private static final int MAX_PARTECIPANTI = 5;
    private static final String GIORNO_PARTITE = "Lunedì";
    private static final String DATA_INIZIO = "2020-12-24";
    private static final String DATA_FINE = "2020-12-31";

    /**
     * This method setup the enviroment.
     */
    @BeforeAll
    public void setUp() {
        tservice = new TorneoServiceImpl();
    }

    /**
     * This method tests the method createTorneo in case fails doSaveTorneo.
     */
    @Test
    void check_1() {

        String usernameAdmin = "memex99";
        int idCampo = 1002;
        Date dataInizio = Date.valueOf(DATA_INIZIO);
        Date dataFine = Date.valueOf(DATA_FINE);

        String message = "Creazione fallita: Save Torneo fallito";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tservice.createTorneo(usernameAdmin, idCampo, null, null,
                 null, MAX_SQUADRE, dataInizio, dataFine, MIN_PARTECIPANTI, MAX_PARTECIPANTI,
                "Mercoledì"));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method createTorneo in case fails insertOccupazione.
     */
    @Test
    void check_2() {

        String usernameAdmin = "";
        int idCampo = 1002;
        Date dataInizio = Date.valueOf(DATA_INIZIO);
        Date dataFine = Date.valueOf(DATA_FINE);

        String message = "Creazione fallita: Insert Occupazione fallita";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tservice.createTorneo(usernameAdmin, idCampo, null, null,
                null, MAX_SQUADRE, dataInizio, dataFine, MIN_PARTECIPANTI, MAX_PARTECIPANTI,
                "Martedì"));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method createTorneo in case it successful.
     */
    @Test
    void check_3() {

        //creation of a match to test the correct occupation entry, eliminating the scheduled match
        PartitaDAO partitaDAO = new PartitaDAOImpl();
        Partita p = new Partita(1000, ID_CAMPO, "manuzzi99@gmail.com", Date.valueOf("2020-12-28"),
                Time.valueOf("17:00:00"), Time.valueOf("18:00:00"));
        partitaDAO.doSavePartita(p);

        Date dataInizio = Date.valueOf(DATA_INIZIO);
        Date dataFine = Date.valueOf(DATA_FINE);

        //check if the method return true
        assertTrue(tservice.createTorneo(USERNAME_ADMIN, ID_CAMPO, NOME, TIPO,
                STRUTTURA, MAX_SQUADRE, dataInizio, dataFine, MIN_PARTECIPANTI, MAX_PARTECIPANTI,
                GIORNO_PARTITE));

    }

    /**
     * This method tests the method getAllTornei.
     */
    @Test
    void check_4() {

        assertNotNull(tservice.getAllTornei());

    }

    /**
     * This method tests checkScheduledTorneo in case there are any tournaments on that date.
     */
    @Test
    void check_5() {

        assertTrue(tservice.checkScheduledTorneo(Date.valueOf(DATA_INIZIO), Date.valueOf(DATA_FINE),
                ID_CAMPO));

    }

    /**
     * This method tests checkScheduledTorneo in case there are no tournaments on that date.
     */
    @Test
    void check_6() {
        assertFalse(tservice.checkScheduledTorneo(Date.valueOf("2021-12-20"), Date.valueOf("2021-12-25"),
                ID_CAMPO));
    }

    /**
     * This method tests the method getTorneo in case it fails.
     */
    @Test
    void check_7() {
        String message = "Get Torneo fallito";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tservice.getTorneo(null, Date.valueOf(DATA_INIZIO),
                0));
        assertEquals(message, exception.getMessage());
    }

    /**
     * This method tests the method getTorneo in case it successful.
     */
    @Test
    void check_8() {
        assertNotNull(tservice.getTorneo(NOME, Date.valueOf(DATA_INIZIO), ID_CAMPO));
    }

    /**
     * This method tests the method deleteTorneo in case it fails.
     */
    @Test
    void check_9() {

        int idCampo = 0;
        String nome = "";
        Date dataInizio = Date.valueOf(DATA_INIZIO);
        Date dataFine = Date.valueOf(DATA_FINE);

        String message = "Eliminazione fallita";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tservice.deleteTorneo(idCampo, nome, dataInizio, dataFine, GIORNO_PARTITE));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method deleteTorneo in case it successful.
     */
    @Test
    void check_10() {

        Date dataInizio = Date.valueOf(DATA_INIZIO);
        Date dataFine = Date.valueOf(DATA_FINE);
        assertTrue(tservice.deleteTorneo(ID_CAMPO, NOME, dataInizio, dataFine, GIORNO_PARTITE));

    }

    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {

        tservice = null;

    }

}