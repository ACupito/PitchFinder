package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class PartitaServiceImplTest {

    @Test
    void createPartitaTest() {
        Date date = new Date(2020-1900,0,7);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);
        Utente user = new Utente();
        user.setEmail("mario99@gmail.com");

        PartitaService service = new PartitaServiceImpl();
        
        assertNotNull(service.createPartita(1002,user,date,start,end));

    }
}