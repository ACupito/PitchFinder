package com.pitchfinder.prenotazione.dao;


import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrenotazioneDAOImplTest {
    private Utente utente;
    private Evento evento;
    private EventoDAO eventoDAO = new EventoDAOImpl();
    private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

    /**
     * Inserts the booking.
     */
    @BeforeAll
    public void save(){
        /**create a user.*/
        Date dataDiNascita = new Date(1998-1900, 3-1, 6);
        utente = new Utente("lucia.gaeta.98@gmail.com", "lGaeta", "Lucia", "Gaeta", "ciao", dataDiNascita);
        utente.setPassword("ciao");
        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.doSaveUtente(utente);

        /**Create a event.*/
        Date eventoData = new Date(2021-1900, 12-1, 18);
        Time oraInizio = new Time(13, 00, 00);
        Time oraFine = new Time(17, 00,00);
        evento = new Evento("EventoTest", "path", oraInizio, oraFine, eventoData, "lucia", "Descrizione", 100,"memex99");
        eventoDAO.doSaveEvento(evento);

        /**Create a booking.*/

        Prenotazione prenotazione = new Prenotazione(utente.getEmail(), evento.getName(), eventoData);
        prenotazioneDAO.doSavePrenotazione(prenotazione);


    }

    /**
     * Booking correctly saved.
     */
    @Test
    public void checkDoSave() {
        Date dataDiNascita = new Date(1998-1900, 3-1, 6);
        Utente utenteDue = new Utente("lucia.ercole.98@gmail.com", "lErcole", "Lucia", "Ercole", "ciao", dataDiNascita);
        utenteDue.setPassword("ciao");
        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.doSaveUtente(utenteDue);
        Prenotazione prenotazione = new Prenotazione(utenteDue.getEmail(), evento.getName(), evento.getDate());

        assertTrue(prenotazioneDAO.doSavePrenotazione(prenotazione));

    }

    /**
     * Success, the booking correctly removed
     */
    @Test
    public void checkDoRemove() {


        Prenotazione prenotazione = new Prenotazione(utente.getEmail(), evento.getName(), evento.getDate());

        assertTrue(prenotazioneDAO.doRemovePrenotazione(prenotazione));
    }

    /**
     * Deletes the booking created after doSave.
     */
    @AfterAll
    public void clean (){
        //Remove booking
        Prenotazione prenotazione = new Prenotazione(utente.getEmail(), evento.getName(), evento.getDate());
        prenotazioneDAO.doRemovePrenotazione(prenotazione);

        //remove users
        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.doRemoveUtente(utente);
        Date dataDiNascita = new Date(1998-1900, 3-1, 6);
        Utente utenteDue = new Utente("lucia.ercole.98@gmail.com", "lErcole", "Lucia", "Ercole", "ciao", dataDiNascita);
        utenteDAO.doRemoveUtente(utenteDue);
        //remove event
        eventoDAO.doRemoveEvento(evento);
    }



}