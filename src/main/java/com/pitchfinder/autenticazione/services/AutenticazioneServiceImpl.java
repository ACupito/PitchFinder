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

        return ud.checkUtente(u);
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

        return ud.checkAdmin(u);
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

        return ud.doSaveUtente(u);
    }
}
