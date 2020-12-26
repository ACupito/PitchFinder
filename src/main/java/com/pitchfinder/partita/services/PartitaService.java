package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.entity.Partita;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

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

    /**
     * This method is used to view the players participating in a match
     * @return List<String>
     */
    List<String> showGiocatori(Partita partita);

    /**
     * This method is used to add a player to the players of a match
     * @param idPartita idPartita
     * @param nome nome
     * @param cognome cognome
     */
    void createGiocatorePartita(int idPartita, String nome, String cognome);
}
