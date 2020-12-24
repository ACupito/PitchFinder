package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;

public class PrenotazioneServiceImpl implements PrenotazioneService {
    /**
     * Create Booking.
     * @param utenteEmail
     * @param evento
     * @param codicePrenotazione
     * @return
     */
    @Override
    public Prenotazione createPrenotazione(String utenteEmail, Evento evento, int codicePrenotazione) {
        Prenotazione prenotazione = new Prenotazione(utenteEmail, evento.getName(), evento.getDate(), codicePrenotazione);
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

        if (!prenotazioneDAO.doSavePrenotazione(prenotazione)) {
           return null;
        }

        return prenotazione;
    }
}
