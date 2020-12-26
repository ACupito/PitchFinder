package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;

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
    boolean doRemoveTorneo(Torneo torneo);

}
