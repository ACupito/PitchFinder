package com.pitchfinder.autenticazione.services;

import com.pitchfinder.autenticazione.dao.AdminDAO;
import com.pitchfinder.autenticazione.dao.AdminDAOImpl;
import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;

import java.sql.Date;

public class AutenticazioneServiceImpl implements AutenticazioneService {

    /**
     * This methods manages the user login.
     * @param username is the user username
     * @param password is the user password
     * @return utente
     */
    public Utente loginUtente(String username, String password) {

        Utente u = new Utente();

        u.setUsername(username);
        u.setPassword(password);

        UtenteDAO ud = new UtenteDAOImpl();

        Utente u1 = ud.checkUtente(u);

        if (u1 != null) {
            return u1;
        } else {
            return null;
        }
    }

    /**
     * This method manages the admin login.
     * @param username is the admin username
     * @param password is the admin password
     * @return admin
     */
    public Admin loginAdmin(String username, String password) {

        Admin u = new Admin();

        u.setUsername(username);
        u.setPassword(password);

        AdminDAO ud = new AdminDAOImpl();

        Admin a = ud.checkAdmin(u);

        if (a != null) {
            return a;
        } else {
            return null;
        }
    }

    /**
     * This method manages the registration of the user.
     * @param email is the user email
     * @param username is the user username
     * @param nome is the user name
     * @param cognome is the user surname
     * @param password is the user password
     * @param dataDiNascita is the user date of birth
     * @return boolean
     */
    public boolean registraUtente(String email, String username,
                                  String nome, String cognome,
                                  String password, Date dataDiNascita) {

        Utente u = new Utente();
        u.setEmail(email);
        u.setUsername(username);
        u.setNome(nome);
        u.setCognome(cognome);
        u.setPassword(password);
        u.setDataDiNascita(dataDiNascita);

        UtenteDAO ud = new UtenteDAOImpl();

        if (ud.doSaveUtente(u)) {
            return true;
        }

        return false;
    }

    /**
     * @param email is the user email
     * @return utente
     */
    public Utente prelevaUtenteByEmail(String email) {

        Utente u = new Utente();
        u.setEmail(email);

        UtenteDAO ud = new UtenteDAOImpl();

        Utente u1 = ud.doRetrieveUtenteByEmail(u);

        if (u1 != null) {
            return u1;
        } else {
            return null;
        }
    }

    /**
     * @param username is the user username
     * @return boolean
     */
    public boolean removeUtente(String username) {

        Utente u = new Utente();
        u.setUsername(username);

        UtenteDAO ud = new UtenteDAOImpl();

        if (ud.doRemoveUtente(u)) {
            return true;
        }

        return false;
    }
}
