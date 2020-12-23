package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TorneoDAOImplTest {

    private static TorneoDAO tdao;
    private static Torneo t;

    @BeforeAll
    static void setUp() {
        tdao = new TorneoDAOImpl();
        t = new Torneo();
    }

    @Test
    void doSaveTorneo() {

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