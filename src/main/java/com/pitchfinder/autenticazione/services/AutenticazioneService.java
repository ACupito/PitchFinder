package com.pitchfinder.autenticazione.services;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import java.sql.Date;

public interface AutenticazioneService {

    /**
     * This methods manages the user login.
     * @param username is the user username
     * @param password is the user password
     * @return Utente
     */
    Utente loginUtente(String username, String password);

    /**
     * This method manages the admin login.
     * @param username is the admin username
     * @param password is the admin password
     * @return Admin
     */
    Admin loginAdmin(String username, String password);

    /**
     * This method manages the registration of the user.
     * @param email is the user email
     * @param username is the user username
     * @param nome is the user name
     * @param cognome is the user surname
     * @param password is the  user password
     * @param dataDiNascita is the user date of birth
     * @return Utente
     */
    boolean registraUtente(String email, String username,
                           String nome, String cognome,
                           String password, Date dataDiNascita);

    /**
     * @param email is the user email.
     * @return utente
     */
    Utente prelevaUtenteByEmail(String email);

    /**
     * @param username is the user username
     * @return boolean
     */
    boolean removeUtente(String username);
}
