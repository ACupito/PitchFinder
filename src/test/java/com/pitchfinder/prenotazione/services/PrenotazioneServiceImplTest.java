package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;

import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrenotazioneServiceImplTest {

    @Test
    public void checkCreatePrenotazione(){
        Date data = new Date(2021-1900, 12-1, 18);
        Time inizio = new Time(13,00,00);
        Time fine = new Time(15, 00, 00);

        Evento evento = new Evento("NomeEvento","Immaginet",inizio,fine,data,"Ospitee", "Descrizionee",100,"memex99");
        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();

        assertNotNull(prenotazioneService.createPrenotazione("AndreSquillante@gmail.com", evento));

    }

    @AfterAll
    void clean(){
        Date data = new Date(2021-1900, 12-1, 18);
        Time inizio = new Time(13,00,00);
        Time fine = new Time(15, 00, 00);
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
        Evento evento = new Evento("NomeEvento","Immaginet",inizio,fine,data,"Ospitee", "Descrizionee",100,"memex99");
        Prenotazione prenotazione = new Prenotazione("AndreSquillante@gmail.com", evento.getName(), evento.getDate());
        prenotazioneDAO.doRemovePrenotazione(prenotazione);
    }


}