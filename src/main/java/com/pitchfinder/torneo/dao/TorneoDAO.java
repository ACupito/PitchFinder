package com.pitchfinder.torneo.dao;

import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Date;
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

    /**
     * This mehod allows to check if other tournaments have been scheduled in the same period.
     * @param dataInizio start date of the tournament
     * @param dataFine end date of the tournament
     * @param idCampo pitch identifier
     * @return boolean : true -> there are other tournaments / false -> empty at the time
     */
    boolean doCheckTorneo(Date dataInizio, Date dataFine, int idCampo);


    /**
     * This method allows to get a tournament
     * from the database.
     * @param nome name of the tournament
     * @param dataInizio start date of the tournament
     * @param idCampo end date of the tournament
     * @return Torneo item
     */
    Torneo doRetrieveTorneo(String nome, Date dataInizio, int idCampo);

}
