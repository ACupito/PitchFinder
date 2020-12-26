package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.entity.Partita;

import java.sql.Date;
import java.sql.Time;

public interface PartitaService {
    /**
     *  This method is used by the user during a match booking.
     * @param idCampo idCampo
     * @param utente utente
     * @param date date
     * @param start start
     * @param end end
     * @return Partita
     */
    Partita createPartita(int idCampo, Utente utente, Date date, Time start, Time end);
}
