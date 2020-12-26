package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;

public class PrenotazioneServiceImpl implements PrenotazioneService {
    /**
     * Create Booking.
     * @param utenteEmail - utenteEmail.
     * @param evento - evento.
     * @return Prenotazione
     */
    @Override
    public Prenotazione createPrenotazione(String utenteEmail, Evento evento) {
        Prenotazione prenotazione = new Prenotazione(utenteEmail, evento.getName(), evento.getDate());
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

        if (!prenotazioneDAO.doSavePrenotazione(prenotazione)) {
           return null;
        }

        return prenotazione;
    }
}
