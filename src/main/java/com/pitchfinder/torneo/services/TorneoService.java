package com.pitchfinder.torneo.services;


import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Date;
import java.util.List;

/**
 * This interface manages the TorneoService.
 */
public interface TorneoService {

    /**
     * This method allows to create a tournament and invoke daSaveTorneo.
     * @param usernameAdmin admin username
     * @param idCampo pitch identifier
     * @param nome name of the tournament
     * @param tipo type of the tournament
     * @param struttura structure of the tournament
     * @param maxSquadre maximum number of the teams
     * @param dataInizio start date of the tournament
     * @param dateFine end date of the tournament
     * @param minPartecipanti minimum number of players for the team
     * @param maxPartecipanti maximum number of players for the team
     * @param giornoPartite match day of the tournament
     * @return boolean -> true : created / false : failed creation
     */
    boolean createTorneo(String usernameAdmin, int idCampo, String nome, String tipo, String struttura, int maxSquadre,
                         Date dataInizio, Date dateFine, int minPartecipanti, int maxPartecipanti, String giornoPartite);

    /**
     * This method allows to remove a tournament.
     * @param idCampo pitch identifier
     * @param nome nome of the tournament
     * @param dataInizio dataInizio of the tournament
     * @return boolean -> true : created / false : failed creation
     */
    boolean deleteTorneo(int idCampo, String nome, Date dataInizio);

    /**
     * This method allows to get all tournaments.
     * @return A list of Torneo items
     */
    List<Torneo> getAllTornei();

    /**
     * This method call doCheckTorneo.
     * @see com.pitchfinder.torneo.dao.TorneoDAOImpl#doCheckTorneo(Date, Date, int)
     * @return boolean : true -> there are other tournaments / false -> empty at the time
     */
    boolean checkScheduledTorneo(Date dataInizio, Date dataFine, int IdCampo);
}
