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
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @param username_admin
     * @return boolean
     */
    boolean doSaveOccupazione(int id_campo, Date data, Time inizio, Time fine, String username_admin);

    /**
     *
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean doRemoveOccupazione(int id_campo, Date data, Time inizio, Time fine);

    /**
     *
     * @param email_utente
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    boolean doSaveDisponibilita(String email_utente, int id_campo, Date data, Time inizio, Time fine);

    /**
     *
     * @param email_utente
     * @param id_campo
     * @return boolean
     */
    boolean doRemoveDisponibilita(String email_utente,  int id_campo);

    /**
     *
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return List<String>
     */
    List<String> doRetriveEmailByDisponibilita(int id_campo, Date data, Time inizio, Time fine);
}
