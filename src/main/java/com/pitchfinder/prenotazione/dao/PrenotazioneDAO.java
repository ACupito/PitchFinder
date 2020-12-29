package com.pitchfinder.prenotazione.dao;

import com.pitchfinder.prenotazione.entity.Prenotazione;

import java.sql.Date;

public interface PrenotazioneDAO {
    /**
     * If prenotazione is correctly SAVED in database return true else return false.
     * @param prenotazione - prenotazione.
     * @return boolean
     */
    boolean doSavePrenotazione(Prenotazione prenotazione);

    /**
     * If prenotazione is correctly REMOVED from database return true else return false.
     * @param prenotazione - prenotazione.
     * @return boolean
     */
    boolean doRemovePrenotazione(Prenotazione prenotazione);

    /**
     * Do retrieve prenotazione.
     * @param email - email.
     * @param nomeEvento - nomeEvento.
     * @param dataEvento - dataEvento.
     * @return Prenotazione.
     */
    Prenotazione doRetrievePrenotazione(String email, String nomeEvento, Date dataEvento);
}
