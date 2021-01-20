package com.pitchfinder.campo.services;

import com.pitchfinder.autenticazione.entity.Utente;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CampoService {
    /**
     *
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @param usernameAdmin is the username of the admin.
     * @return boolean
     */
    boolean createOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin);

    /**
     *
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    boolean deleteOccupazione(int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param id is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    boolean controllaOccupazione(int id, Date data, Time inizio, Time fine);

    /**
     *
     * @param emailUtente is the email of the user.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return boolean
     */
    boolean createDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine);

    /**
     *
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return List<Utente>
     */
    List<Utente> showAllDisponibilita(int idCampo, Date data, Time inizio, Time fine);
}
