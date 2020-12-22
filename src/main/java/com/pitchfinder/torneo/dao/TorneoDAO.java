package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;

import java.util.Date;
import java.util.List;

/**
 * This interface manages the Torneo dao.
 */

public interface TorneoDAO {

    /**
     *
     * This method makes the Torneo object persist
     * in the database.
     * @param torneo object
     * @return boolean
     */
    boolean doSaveTorneo(Torneo torneo);

    /**
     * This method allows to delete a Torneo object
     * from the database.
     * @param torneo object
     * @return boolean
     */
    boolean doRemoveTorneo(Torneo torneo);

    /**
     * This method allows to get all Torneo items
     * from the database.
     * @return List<Torneo>
     */
    List<Torneo> doRetrieveAllTornei();

    /**
     * This method allows to get Torneo item
     * from the database.
     * @param nome name of the tournament.
     * @param dataInizio start date of the tournament.
     * @param idCampo pitch identifier.
     * @return Torneo
     */
    Torneo doRetrieveTorneo(String nome, Date dataInizio,
                            int idCampo);

    /**
     * This method allows to get Torneo items from filters.
     * @param dataInizio start date of the tournament.
     * @param dataFine  end date og the tournament.
     * @param tipo type of the tournament.
     * @return List<Torneo>
     */
    List<Torneo> doRetrieveTorneoByFilter(Date dataInizio, Date dataFine,
                                          String tipo);

    /**
     * This method allows to get Torneo items from through a certain sport.
     * @param sport sport of the tournament.
     * @return List<Torneo>
     */
    List<Torneo> doRetrieveTorneoBySport(String sport);

}
