package com.pitchfinder.partita.dao;

import com.pitchfinder.partita.entity.Partita;

import java.util.List;

public interface PartitaDAO {
    /**
     * This method create an instance of partita in table partita.
     * @param partita partita
     */
    void doSavePartita(Partita partita);

    /**
     * This method return all the partita instance from table partita.
     * @return List<Partita>
     */
     List<Partita> doRetrieveAll();
}
