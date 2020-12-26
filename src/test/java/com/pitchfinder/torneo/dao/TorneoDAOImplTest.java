package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class manages the test for TorneoDAOImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoDAOImplTest {

    /**
     *  This is an instance of TorneoDAO.
     */
    private TorneoDAO tdao;

    /**
     * This is an instance of Torneo entity.
     */
    private Torneo t;

    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    public void setUp() {
        tdao = new TorneoDAOImpl();
        t = new Torneo();
    }

    /**
     * This method tests the method doSaveTorneo in case it fails.
     */
    @Test
    void checkSaveTorneoTest1() {

        t.setAdminUsername("");
        t.setNome("");
        t.setCampoIdentificativo(0);
        t.setTipo("Gironi");
        t.setStruttura("Partite singole");
        t.setNumeroSquadre(12);
        t.setMinNumeroPartecipantiPerSquadra(1);
        t.setMaxNumeroPartecipantiPerSquadra(2);
        t.setGiornoPartite("Lunedì");
        t.setDataInizio(Date.valueOf("2000-12-24"));
        t.setDataFine(Date.valueOf("2000-12-31"));

        assertFalse(tdao.doSaveTorneo(t));

    }

    /**
     * This method tests the method doSaveTorneo in case it successful.
     */
    @Test
    void checkSaveTorneoTest2() {

        t.setAdminUsername("memex99");
        t.setNome("Champions");
        t.setCampoIdentificativo(1002);
        t.setTipo("Gironi");
        t.setStruttura("Partite singole");
        t.setNumeroSquadre(12);
        t.setMinNumeroPartecipantiPerSquadra(1);
        t.setMaxNumeroPartecipantiPerSquadra(2);
        t.setGiornoPartite("Lunedì");
        t.setDataInizio(Date.valueOf("2020-12-24"));
        t.setDataFine(Date.valueOf("2020-12-31"));

        assertTrue(tdao.doSaveTorneo(t));

    }

    /**
     * This method tests the method doRemoveTorneo in case it fails.
     */
    @Test
    void checkRemoveTorneoTest3() {

        t.setAdminUsername("");
        t.setNome("");
        t.setCampoIdentificativo(0);
        t.setTipo("Gironi");
        t.setStruttura("Partite singole");
        t.setNumeroSquadre(12);
        t.setMinNumeroPartecipantiPerSquadra(1);
        t.setMaxNumeroPartecipantiPerSquadra(2);
        t.setGiornoPartite("Lunedì");
        t.setDataInizio(Date.valueOf("2000-12-24"));
        t.setDataFine(Date.valueOf("2000-12-31"));


        assertFalse(tdao.doRemoveTorneo(t));

    }

    /**
     * This method tests the method doRemoveTorneo in case it successful.
     */
    @Test
    void checkRemoveTorneoTest4() {

        t.setAdminUsername("memex99");
        t.setNome("Champions");
        t.setCampoIdentificativo(1002);
        t.setTipo("Gironi");
        t.setStruttura("Partite singole");
        t.setNumeroSquadre(12);
        t.setMinNumeroPartecipantiPerSquadra(1);
        t.setMaxNumeroPartecipantiPerSquadra(2);
        t.setGiornoPartite("Lunedì");
        t.setDataInizio(Date.valueOf("2020-12-24"));
        t.setDataFine(Date.valueOf("2020-12-24"));

        assertTrue(tdao.doRemoveTorneo(t));

    }



}