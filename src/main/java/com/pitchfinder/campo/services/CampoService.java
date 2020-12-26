package com.pitchfinder.campo.services;

import java.sql.Date;
import java.sql.Time;

public interface CampoService {
    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @param usernameAdmin
     * @return boolean
     */
    boolean createOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin);

    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean deleteOccupazione(int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param id
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean checkOccupazione(int id, Date data, Time inizio, Time fine);

    /**
     *
     * @param emailUtente
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean createDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return List<Utente>
     */
    List<Utente> showAllDisponibilita(int idCampo, Date data, Time inizio, Time fine);
}
