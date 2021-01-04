package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.apache.commons.mail.EmailException;

import java.net.MalformedURLException;

public interface PrenotazioneService {
    /**
     * Create booking.
     * @param utenteEmail - utenteEmail.
     * @param evento - evento.
     * @return Prenotazione
     */
    Prenotazione createPrenotazione(String utenteEmail, Evento evento) throws EmailException, MalformedURLException;

    /**
     * Return NumeroPrenotazione.
     * @param prenotazione - prenotazione.
     * @return int
     */
    int getNumeroPrenotazione(Prenotazione prenotazione);
}
