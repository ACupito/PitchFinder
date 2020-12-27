package com.pitchfinder.prenotazione.dao;


import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrenotazioneDAOImplTest {

    private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

    /**
     * Inserts the booking.
     */
    @BeforeAll
    public void save(){
        Date eventoData = new Date(2021-1900, 12-1, 18);
        Prenotazione prenotazione = new Prenotazione("manuzzi97@gmail.com", "NomeEvento", eventoData);

        prenotazioneDAO.doSavePrenotazione(prenotazione);
    }

    /**
     * Booking correctly saved
     */
    @Test
    public void checkDoSave() {
        Date eventoData = new Date(2021-1900, 12-1, 18);
        Prenotazione prenotazione = new Prenotazione("manuzzi98@gmail.com", "NomeEvento", eventoData);

        assertTrue(prenotazioneDAO.doSavePrenotazione(prenotazione));

    }

    /**
     * Success, the booking correctly removed
     */
    @Test
    public void checkDoRemove() {

        Date eventoData = new Date(2021-1900, 12-1, 18);
        Prenotazione prenotazione = new Prenotazione("manuzzi97@gmail.com","NomeEvento", eventoData);

        assertTrue(prenotazioneDAO.doRemovePrenotazione(prenotazione));
    }

    /**
     * Deletes the booking created after doSave.
     */
    @AfterAll
    public void clean (){
        Date eventoData = new Date(2021-1900, 12-1, 18);
        Prenotazione prenotazione = new Prenotazione("manuzzi98@gmail.com", "NomeEvento", eventoData);

        prenotazioneDAO.doRemovePrenotazione(prenotazione);

    }



}
