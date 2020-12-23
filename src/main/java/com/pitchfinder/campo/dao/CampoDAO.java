package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CampoDAO {
    /**
     *
     * @param id
     * @return Campo
     */
    Campo getCampo(int id);

    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @param usernameAdmin
     * @return boolean
     */
    boolean doSaveOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin);

    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean doRemoveOccupazione(int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param emailUtente
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean doSaveDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param emailUtente
     * @param idCampo
     * @return boolean
     */
    boolean doRemoveDisponibilita(String emailUtente,  int idCampo);

    /**
     *
     * @param idCampo
     * @param data
     * @param inizio
     * @param fine
     * @return List<String>
     */
    List<String> doRetriveEmailByDisponibilita(int idCampo, Date data, Time inizio, Time fine);
}
