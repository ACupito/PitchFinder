package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.apache.commons.mail.EmailException;

import java.net.MalformedURLException;
import java.sql.Date;

public interface PrenotazioneService {
    /**
     * Create booking.
     * @param utenteEmail - utenteEmail.
     * @param evento - evento.
     * @return Prenotazione
     */
    Prenotazione createPrenotazione(String utenteEmail, Evento evento) throws EmailException, MalformedURLException;

    /**
     * Return Prenotazione.
     * @param email - email.
     * @param eventoData - Date event.
     * @param eventoNome - Name event.
     * @return Prenotazione.
     */
    Prenotazione getPrenotazione(String email, Date eventoData, String eventoNome);

    /**
     * Return NumeroPrenotazione.
     * @param prenotazione - prenotazione.
     * @return int
     */
    int getNumeroPrenotazione(Prenotazione prenotazione);
}
