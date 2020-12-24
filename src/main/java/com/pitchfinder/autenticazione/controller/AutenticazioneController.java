package com.pitchfinder.autenticazione.controller;

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

        //if (flag == 1) {

            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String password = request.getParameter("password");
            String giorno = request.getParameter("giorno");
            String mese = request.getParameter("mese");
            String anno = request.getParameter("anno");

            //Date dataDiNascita = new Date(anno, mese, giorno);

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

            /*if (dataDiNascita == null) {
                throw new IllegalArgumentException("La registrazione "
                        + "non va a buon fine perché "
                        + "la data di nascita non è stata selezionata");
            }*/



            as.registraUtente(email, username, nome, cognome, password, null);

       /* } else if (flag == 2) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
                throw new IllegalArgumentException("Il login non va a buon fine "
                        + "perché l’e-mail non rispetta la "
                        + "lunghezza corretta");
            }
            if (!email.matches("[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+")) {
                throw new IllegalArgumentException("Il login non va a buon fine "
                        + "perché il formato dell’e-mail non è corretto");
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

            as.loginUtente(email, password);

        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
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

            as.loginAdmin(username, password);
        }
        */
    }
}
