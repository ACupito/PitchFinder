package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.entity.Prenotazione;

public interface PrenotazioneService {
    /**
     * Create booking.
     * @param utenteEmail - utenteEmail.
     * @param evento - evento.
     * @return Prenotazione
     */
    Prenotazione createPrenotazione(String utenteEmail, Evento evento);
}
