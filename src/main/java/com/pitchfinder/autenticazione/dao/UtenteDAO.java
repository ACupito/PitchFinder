package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Utente;


public interface UtenteDAO {

    /**
     * This method memorizes a user into the database.
     * @param utente is the user who wants to join the platform
     * @return boolean
     */
    boolean doSaveUtente(Utente utente);

    /**
     * This method checks weather the subject who is trying to
     * access is a registered user o not.
     * @param utente is the user who wants to log in
     * @return boolean
     */
    Utente checkUtente(Utente utente);

    /**
     * This method returns a list of users using input emails.
     * @param u is the user
     * @return utente
     */
    Utente doRetrieveUtenteByEmail(Utente u);


    /**
     * This method removes a user.
     * @param u is the user
     * @return boolean
     */
    boolean doRemoveUtente(Utente u);
}
