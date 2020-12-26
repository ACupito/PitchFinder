package com.pitchfinder.torneo.services;

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
     * This method setup the enviroment.
     */
    @BeforeAll
    public void setUp() {
        tservice = new TorneoServiceImpl();
    }

    /**
     * This method tests the method createTorneo in case it fails.
     */
    @Test
    void checkCreateTorneoTest_1() {

        String usernameAdmin = "";
        String nome = "Champions";
        int idCampo = 0;
        String tipo = "Gironi";
        String struttura = "Partite singole";
        int maxSquadre = 12;
        int minPartecipanti = 1;
        int maxPartecipanti = 2;
        String giornoPartite = "Lunedì";
        Date dataInizio = Date.valueOf("2020-12-24");
        Date dataFine = Date.valueOf("2020-12-31");

        //check if the method return true
        assertFalse(tservice.createTorneo(usernameAdmin, idCampo, nome, tipo,
                struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                giornoPartite));

    }

    /**
     * This method tests the method createTorneo in case it successful.
     */
    @Test
    void checkCreateTorneoTest_2() {

        String usernameAdmin = "memex99";
        String nome = "Champions";
        int idCampo = 1002;
        String tipo = "Gironi";
        String struttura = "Partite singole";
        int maxSquadre = 12;
        int minPartecipanti = 1;
        int maxPartecipanti = 2;
        String giornoPartite = "Lunedì";
        Date dataInizio = Date.valueOf("2020-12-24");
        Date dataFine = Date.valueOf("2020-12-31");

        //check if the method return true
        assertTrue(tservice.createTorneo(usernameAdmin, idCampo, nome, tipo,
                struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                giornoPartite));

    }

    /**
     * This method tests the method deleteTorneo in case it fails.
     */
    @Test
    void checkDeleteTorneoTest_3() {

        int idCampo = 0;
        String nome = "";
        Date dataInizio = Date.valueOf("2020-12-24");

        assertFalse(tservice.deleteTorneo(idCampo, nome, dataInizio));

    }

    /**
     * This method tests the method deleteTorneo in case it successful.
     */
    @Test
    void checkDeleteTorneoTest_4() {

        int idCampo = 1002;
        String nome = "Champions";
        Date dataInizio = Date.valueOf("2020-12-24");

        assertTrue(tservice.deleteTorneo(idCampo, nome, dataInizio));

    }

}