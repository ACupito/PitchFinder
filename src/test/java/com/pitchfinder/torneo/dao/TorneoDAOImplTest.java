package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class manages the test for TorneoDAOImpl.
 */

class TorneoDAOImplTest {

    /**
     *  This is an instance of TorneoDAO.
     */
    private static TorneoDAO tdao;

    /**
     * This is an instance of Torneo entity.
     */
    private static Torneo t;

    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    static void setUp() {
        tdao = new TorneoDAOImpl();
        t = new Torneo();
    }

    /**
     * This method tests the method doSaveTorneo.
     */
    @Test
    void doSaveTorneoTest() {

        t.setAdminUsername("memex99");
        t.setNome("Champions");
        t.setCampoIdentificativo(1001);
        t.setTipo("Gironi");
        t.setStruttura("Partite singole");
        t.setNumeroSquadre(12);
        t.setMinNumeroPartecipantiPerSquadra(1);
        t.setMaxNumeroPartecipantiPerSquadra(2);
        t.setGiornoPartite("Lunedì");
        Date dataInizio = new Date(2020 - 1900, 11, 24);
        t.setDataInizio(dataInizio);
        Date dataFine = new Date(2020 - 1900, 11, 31);
        t.setDataFine(dataFine);

        assertTrue(tdao.doSaveTorneo(t));

    }

}