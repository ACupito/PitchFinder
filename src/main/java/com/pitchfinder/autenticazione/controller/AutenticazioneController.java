package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AutenticazioneController extends HttpServlet {

    /**
     * Maximum limit for username, name, surname and password.
     */
    private static final int MINLIMIT = 1;

    /**
     * Minimum limit for username, name, username and password.
     */
    private static final int MAXLIMIT = 50;

    /**
     * public constructor.
     */
    public AutenticazioneController() {
        super();
    }

    /**
     * doPost() method.
     * @param request is the servlet request
     * @param response is the servlet response
     */

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) {

        doGet(request, response);
    }

    /**
     * doGet() method.
     * @param request is the servlet request
     * @param response is the servlet response
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
        AutenticazioneService as = new AutenticazioneServiceImpl();

        int flag = Integer.parseInt(request.getParameter("flag"));

        if (flag == 1) {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String password = request.getParameter("password");
            String strData = request.getParameter("data");

            if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché l’email inserita non "
                        + "rispetta la lunghezza corretta");
            }
            if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]"
                    + "{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
                throw new IllegalArgumentException("La registrazione non va a buon "
                        + "fine perché l’email inserita "
                        + "non rispetta il formato richiesto");
            }
            if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché la username non "
                        + "rispetta la lunghezza corretta");
            }
            if (!username.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$")) {
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
            if (!nome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
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
            if (!cognome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
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
            if (!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$")) {
                throw new IllegalArgumentException("La registrazione non "
                        + "va a buon fine perché la password inserita"
                        + " non rispetta il formato richiesto");
            }
            if (strData == null) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché "
                        + "la data di nascita non è stata selezionata");
            }

            Date data;
            try {
                data = Date.valueOf(strData);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("La registrazione non va a buon fine "
                        + "perché la data di nascita "
                        + "non rispetta il formato richiesto");
            }

            boolean reg = as.registraUtente(email, username, nome, cognome, password, data);
            if (reg) {
                response.setContentType("La registrazione è avvenuta correttamente");
            }

        } else if (flag == 2) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
                throw new IllegalArgumentException("Il login non va a buon fine "
                        + "perché l’e-mail non rispetta la "
                        + "lunghezza corretta");
            }
            if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]"
                    + "{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
                throw new IllegalArgumentException("Il login non va a buon fine "
                        + "perché il formato dell’e-mail non è corretto");
            }
            if (password.length() < MINLIMIT || password.length() > MAXLIMIT) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché la password "
                        + "inserita non rispetta la "
                        + "lunghezza corretta");
            }
            if (!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$")) {
                throw new IllegalArgumentException("La registrazione non "
                        + "va a buon fine perché la password inserita"
                        + " non rispetta il formato richiesto");
            }

            Utente u = as.loginUtente(email, password);
            if (u != null) {
                response.setContentType("Il login è avvenuto correttamente");
            }

        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché la username non "
                        + "rispetta la lunghezza corretta");
            }
            if (!username.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$")) {
                throw new IllegalArgumentException("La registrazione non va a buon "
                        + "fine perché la username inserita "
                        + "non rispetta il formato richiesto");
            }
            if (password.length() < MINLIMIT || password.length() > MAXLIMIT) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché la password "
                        + "inserita non rispetta la "
                        + "lunghezza corretta");
            }
            if (!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$")) {
                throw new IllegalArgumentException("La registrazione non "
                        + "va a buon fine perché la password inserita"
                        + " non rispetta il formato richiesto");
            }

            as.loginAdmin(username, password);
        }
    }
}
