package com.pitchfinder.torneo.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

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
                GIORNO_PARTITE));
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
                GIORNO_PARTITE));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method createTorneo in case it successful.
     */
    @Test
    void check_3() {

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
     * This method tests the method deleteTorneo in case it fails.
     */
    @Test
    void check_5() {

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
    void check_6() {

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