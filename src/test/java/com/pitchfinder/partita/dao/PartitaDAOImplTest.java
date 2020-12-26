package com.pitchfinder.partita.dao;

import com.pitchfinder.partita.entity.Partita;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PartitaDAOImplTest {
    /**
     * This method check correct behaviour of doSavePartita.
     */
    @Test
    public void checkdoSavePartita() {
        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita pTest = new Partita(1002,"mario96@gmail.com",
                date,start,end);

        PartitaDAO daoTest = new PartitaDAOImpl();

        assertTrue(daoTest.doSavePartita(pTest));
    }

    /**
     * This method check correct behaviour of doRetrieveAll.
     */
    @Test
    public void checkdoRetrieveAll() {
        PartitaDAO daoTest = new PartitaDAOImpl();
        List<Partita> partite = daoTest.doRetrieveAll();

        assertNotNull(partite);
    }
}