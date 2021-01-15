package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CampoDAO {
    /**
     *
     * @param id is the id of the pitch.
     * @return Campo
     */
    Campo doRetriveCampo(int id);

    /**
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @param usernameAdmin is the admin username.
     * @return boolean
     */
    boolean doSaveOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin);

    /**
     *
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    boolean doRemoveOccupazione(int idCampo, Date data, Time inizio, Time fine);

    /**
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    boolean checkOccupazioneExistence(int idCampo, Date data, Time inizio, Time fine);
    /**
     *
     * @param emailUtente is the email of the user.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return boolean
     */
    boolean doSaveDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param emailUtente is the email of the user.
     * @param idCampo is the id of the pitch.
     * @param data is the data of the Disponibilita.
     * @return boolean
     */
    boolean doRemoveDisponibilita(String emailUtente,  int idCampo, Date data);

    /**
     *
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return List<String>
     */
    List<String> doRetriveEmailByDisponibilita(int idCampo, Date data, Time inizio, Time fine);


}
