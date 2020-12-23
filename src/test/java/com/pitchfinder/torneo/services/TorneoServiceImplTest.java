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
     * This method tests the method createTorneo.
     */
    @Test
    void createTorneoTest() {

        String usernameAdmin = "memex99";
        String nome = "Champions";
        int idCampo = 1001;
        String tipo = "Gironi";
        String struttura = "Partite singole";
        int maxSquadre = 12;
        int minPartecipanti = 1;
        int maxPartecipanti = 2;
        String giornoPartite = "Lunedì";
        Date dataInizio = new Date(2020 - 1900, 11, 24);
        Date dataFine = new Date(2020 - 1900, 11, 31);

        //check if the method return true
        assertTrue(tservice.createTorneo(usernameAdmin, idCampo, nome, tipo,
                struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                giornoPartite));

    }

}