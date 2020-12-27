package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;

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
     * This method remove Torneo object
     * from database.
     * @param torneo object
     * @return boolean -> true: execute success / false: execute failed
     */
    boolean doRemoveTorneo(Torneo torneo);

    /**
     * This method allows to get all the tournaments
     * from the database.
     * @return A List of Torneo items.
     */
    List<Torneo> doRetrieveAllTornei();
}
