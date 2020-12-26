package com.pitchfinder.partita.dao;

import com.pitchfinder.partita.entity.Partita;

import java.util.List;

public interface PartitaDAO {
    /**
     * This method create an instance of partita in table Partita.
     * @param partita partita
     * @return boolean
     */
    boolean doSavePartita(Partita partita);

    /**
     * This method return all the partita instance from table Partita.
     * @return List<Partita>
     */
     List<Partita> doRetrieveAll();

    /**
     * This method create an instance of giocatore in table Giocatore.
     * @param idPartita idPartita
     * @param nome nome
     * @param cognome cognome
     * @return boolean
     */
     boolean doSaveGiocatore(int idPartita, String nome, String cognome);

    /**
     * This method return all the players from the table Giocatore.
     * @param idPartita
     * @return List<String>
     */
     List<String> doRetrieveAllGiocatori(int idPartita);
}
