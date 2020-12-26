package com.pitchfinder.squadra.dao;


import com.pitchfinder.squadra.entity.Squadra;

public interface SquadraDAO {
    /**
     * If squadra is correctly SAVED in db return true else return false.
     * @param squadra - Squadra that wants to be saved in the database.
     * @return boolean
     */
    boolean doSaveSquadra(Squadra squadra);

    /**
     * If squadra is correctly REMOVED from database return true else return false.
     * @param squadra - Squadra that wants to be removed from database
     * @return boolean
     */
    boolean doRemoveSquadra(Squadra squadra);

    /**
     * Saves players.
     * @param nome - name player
     * @param cognome - surname player
     * @param squadra - squadra of player
     * @return boolean
     */
    boolean doSaveGiocatoreSquadra(String nome, String cognome, Squadra squadra);
}
