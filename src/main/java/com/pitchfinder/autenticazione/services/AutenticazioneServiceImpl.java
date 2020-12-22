package com.pitchfinder.autenticazione.services;

import com.pitchfinder.autenticazione.dao.AdminDAO;
import com.pitchfinder.autenticazione.dao.AdminDAOImpl;
import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;

import java.util.Date;

public class AutenticazioneServiceImpl implements AutenticazioneService {

    /**
     * Maximum length for email, username,
     * nome, cognome and password.
     */
    private static final int MAXLIMIT = 50;

    /**
     * Minimum length for email, username,
     * nome, cognome and password.
     */
    private static final int MINLIMIT = 1;


    /**
     * This methods manages the user login.
     * @param email
     * @param password
     * @return utente
     */
    public Utente loginUtente(final String email, final String password) {

        if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
            throw new IllegalArgumentException("Il login non va a buon fine "
                    + "perché l’e-mail non rispetta la "
                    + "lunghezza corretta");
        }

        if (!email.matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+")) {
            throw new IllegalArgumentException("Il login non va a buon fine "
                    + "perché il formato dell’e-mail non è corretto");
        }

        Utente u = new Utente();

        u.setEmail(email);
        u.setPassword(password);

        UtenteDAO ud = new UtenteDAOImpl();

        return ud.checkUtente(u);
    }

    /**
     * This method manages the admin login.
     * @param username
     * @param password
     * @return admin
     */
    public Admin loginAdmin(final String username, final String password) {

        if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
            throw new IllegalArgumentException("Il login non va a buon fine "
                    + "perché l’e-mail non rispetta la "
                    + "lunghezza corretta");
        }

        if (!username.matches("^\\S*$")) {
            throw new IllegalArgumentException("Il login non va a buon fine "
                    + "perché il formato dell’e-mail non è corretto");
        }

        Admin u = new Admin();

        u.setUsername(username);
        u.setPassword(password);

        AdminDAO ud = new AdminDAOImpl();

        return ud.checkAdmin(u);
    }

    /**
     * This method manages the registration of the user.
     * @param email
     * @param username
     * @param nome
     * @param cognome
     * @param password
     * @param dataDiNascita
     * @return boolean
     */
    public boolean registraUtente(final String email,
                                  final String username,
                                  final String nome,
                                  final String cognome,
                                  final String password,
                                  final Date dataDiNascita) {

        if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La registrazione "
                    + "non va a buon fine perché l’email inserita non "
                    + "rispetta la lunghezza corretta");
        }

        if (!email.matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+")) {
            throw new IllegalArgumentException("La registrazione non va a buon "
                    + "fine perché l’email inserita "
                    + "non rispetta il formato richiesto");
        }


        if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La registrazione "
                    + "non va a buon fine perché la username non "
                    + "rispetta la lunghezza corretta");
        }

        if (!username.matches("^\\S*$")) {
            throw new IllegalArgumentException("La registrazione non va a buon "
                    + "fine perché la username inserita "
                    + "non rispetta il formato richiesto");
        }

        if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La registrazione "
                    + "non va a buon fine "
                    + "perché il nome inserito non "
                    + "rispetta la lunghezza corretta");
        }

        if (!nome.matches("[A-Z][a-z]+")) {
            throw new IllegalArgumentException("La registrazione non va a "
                    + "buon fine perché il nome inserito non "
                    + "rispetta il formato richiesto");
        }

        if (cognome.length() < MINLIMIT || cognome.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La registrazione non "
                    + "va a buon fine perché il cognome "
                    + "inserito non rispetta "
                    + "la lunghezza corretta");
        }

        if (!cognome.matches("[A-Z][a-z]+")) {
            throw new IllegalArgumentException("La registrazione non va a "
                    + "buon fine perché il cognome inserito "
                    + "non rispetta il formato richiesto");
        }

        if (password.length() < MINLIMIT || password.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La registrazione "
                    + "non va a buon fine perché la password "
                    + "inserita non rispetta la "
                    + "lunghezza corretta");
        }

        if (!password.matches("^\\S*$")) {
            throw new IllegalArgumentException("La registrazione non "
                    + "va a buon fine perché la password inserita"
                    + " non rispetta il formato richiesto");
        }

        if (dataDiNascita == null) {
            throw new IllegalArgumentException("La registrazione "
                    + "non va a buon fine perché "
                    + "la data di nascita non è stata selezionata");
        }

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

    /**
     * This method checks weather the user is in the session.
     * @return boolean
     */
    public boolean controllaLoginUtente() {

        return true;
    }

}
