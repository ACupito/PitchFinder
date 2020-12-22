package com.pitchfinder.autenticazione.services;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import java.util.Date;

public interface AutenticazioneService {

    /**
     * This methods manages the user login.
     * @param email
     * @param password
     * @return Utente
     */
    Utente loginUtente(String email, String password);

    /**
     * This method manages the admin login.
     * @param username
     * @param password
     * @return Admin
     */
    Admin loginAdmin(String username, String password);

    /**
     * This method manages the registration of the user.
     * @param email
     * @param username
     * @param nome
     * @param cognome
     * @param password
     * @param dataDiNascita
     * @return Utente
     */
    boolean registraUtente(String email, String username,
                           String nome, String cognome,
                           String password, Date dataDiNascita);

    /**
     * This method checks weather the user is in the session.
     * @return boolean
     */
    boolean controllaLoginUtente();
}
