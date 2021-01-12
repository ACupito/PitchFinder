package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/autentication")
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

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    /**
     * doGet() method.
     * @param request is the servlet request
     * @param response is the servlet response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        AutenticazioneService as = new AutenticazioneServiceImpl();

        String messaggio = "";
        int flag = Integer.parseInt(request.getParameter("flag"));

        if (flag == -1) {

            dispatcher = getServletContext().getRequestDispatcher("/view/autenticazione/registrazione.jsp");
            dispatcher.forward(request, response);
        }


        if (flag == 1) {
            String email = request.getParameter("email");
            String username = request.getParameter("username_");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String password = request.getParameter("password_");
            String strData = request.getParameter("data");

            if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
                messaggio = "La registrazione non va a buon fine perchè l'email inserita "
                        + "non rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]"
                    + "{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
                messaggio = "La registrazione non va a buon "
                        + "fine perché l’email inserita "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                messaggio = "La registrazione "
                        + "non va a buon fine perché la username non "
                        + "rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!username.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")
                    || username.substring(0, 5).equalsIgnoreCase("admin")) {
                messaggio = "La registrazione non va a buon "
                        + "fine perché la username inserita "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
                messaggio = "La registrazione "
                        + "non va a buon fine "
                        + "perché il nome inserito non "
                        + "rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!nome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                messaggio = "La registrazione non va a "
                        + "buon fine perché il nome inserito non "
                        + "rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (cognome.length() < MINLIMIT || cognome.length() > MAXLIMIT) {
                messaggio = "La registrazione non "
                        + "va a buon fine perché il cognome "
                        + "inserito non rispetta "
                        + "la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!cognome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                messaggio = "La registrazione non va a "
                        + "buon fine perché il cognome inserito "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (password.length() < MINLIMIT || password.length() > MAXLIMIT) {
                messaggio = "La registrazione "
                        + "non va a buon fine perché la password "
                        + "inserita non rispetta la "
                        + "lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!password.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")) {
                messaggio = "La registrazione non "
                        + "va a buon fine perché la password inserita"
                        + " non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (strData == null) {
                messaggio = "La registrazione "
                        + "non va a buon fine perché "
                        + "la data di nascita non è stata selezionata";
                throw new IllegalArgumentException(messaggio);
            }

            Date data;

            try {
                data = Date.valueOf(strData);
            } catch (IllegalArgumentException e) {
                messaggio = "La registrazione non va a buon fine "
                        + "perché la data di nascita "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            boolean reg = as.registraUtente(email, username, nome, cognome, password, data);
            if (reg) {

                response.setContentType("La registrazione è avvenuta correttamente");
                request.setAttribute("messaggio", messaggio);
                dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/avvenutaRegistrazione.jsp");
                dispatcher.forward(request, response);
            }

        } else if (flag == 2) {

            System.out.println("Sei nel login");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                messaggio = "Il login non va a buon fine "
                        + "perché la username non rispetta la "
                        + "lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!username.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")) {
                messaggio = "Il login non va a buon fine "
                        + "perché il formato della username non è corretto";
                throw new IllegalArgumentException(messaggio);
            }

            if (username.substring(0, 5).equalsIgnoreCase("admin")) {

                Admin a = as.loginAdmin(username, password);

                if (a != null) {
                    request.setAttribute("admin", a);
                    dispatcher = getServletContext().getRequestDispatcher("/view/autenticazione/admin.jsp");
                    dispatcher.forward(request, response);
                }

            } else {

                System.out.println("Sei nel login utente");

                Utente u = as.loginUtente(username, password);
                if (u != null) {

                    System.out.println("Sei entrato");

                    response.setContentType("Il login è avvenuto correttamente");
                    request.setAttribute("utente", u);
                    dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/utente.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
