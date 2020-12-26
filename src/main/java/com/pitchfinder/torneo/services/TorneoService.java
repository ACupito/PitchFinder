package com.pitchfinder.torneo.services;


import java.sql.Date;

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
}
