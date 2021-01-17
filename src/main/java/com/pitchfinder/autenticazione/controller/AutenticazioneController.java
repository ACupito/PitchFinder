package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.partita.services.PartitaService;
import com.pitchfinder.partita.services.PartitaServiceImpl;
import com.pitchfinder.torneo.entity.Torneo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


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
        HttpSession session = request.getSession(true);

        AutenticazioneService as = new AutenticazioneServiceImpl();

        String messaggio;
        int flag = Integer.parseInt(request.getParameter("flag"));
        Admin aCheck;
        Utente uCheck;

        if (flag == -1) {

            dispatcher = getServletContext().getRequestDispatcher("/view/autenticazione/registrazione.jsp");
            dispatcher.forward(request, response);
        }

        if (flag == 1) {

            uCheck = (Utente) session.getAttribute("utente");

            if (uCheck != null) {
                throw new IllegalArgumentException("Sei loggato, non puoi registrati !");
            }

            String email = request.getParameter("email");
            String username = request.getParameter("username_");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String password = request.getParameter("password_");
            String strData = request.getParameter("data");

            if (email.length() < MINLIMIT || email.length() > MAXLIMIT) {
                messaggio = "L'email inserita "
                        + "non rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]"
                    + "{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
                messaggio = "L’email inserita "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                messaggio = "La username non "
                        + "rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!username.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")
                    || username.substring(0, 5).equalsIgnoreCase("admin")) {
                messaggio = "La username inserita "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
                messaggio = "Il nome inserito non "
                        + "rispetta la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!nome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                messaggio = "Il nome inserito non "
                        + "rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (cognome.length() < MINLIMIT || cognome.length() > MAXLIMIT) {
                messaggio = "Il cognome "
                        + "inserito non rispetta "
                        + "la lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!cognome.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                messaggio = "Il cognome inserito "
                        + "non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (password.length() < MINLIMIT || password.length() > MAXLIMIT) {
                messaggio = "La password "
                        + "inserita non rispetta la "
                        + "lunghezza corretta";
                throw new IllegalArgumentException(messaggio);
            }

            if (!password.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")) {
                messaggio = "La password inserita"
                        + " non rispetta il formato richiesto";
                throw new IllegalArgumentException(messaggio);
            }

            if (strData == null) {
                messaggio = "Data di nascita non selezionata";
                throw new IllegalArgumentException(messaggio);
            }

            Date data;

            try {
                data = Date.valueOf(strData);
            } catch (IllegalArgumentException e) {
                messaggio = "La data di nascita "
                        + "non rispetta il formato corretto";
                throw new IllegalArgumentException(messaggio);
            }

            boolean reg = as.registraUtente(email, username, nome, cognome, password, data);
            if (reg) {

                messaggio = "La registrazione è avvenuta con successo";

                response.setContentType("La registrazione è avvenuta con successo");
                request.setAttribute("messaggio", messaggio);
                dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/avvenutaRegistrazione.jsp");
                dispatcher.forward(request, response);
            }

        } else if (flag == 2) {

            aCheck = (Admin) session.getAttribute("admin");
            uCheck = (Utente) session.getAttribute("utente");

            if (aCheck != null) {

                dispatcher = getServletContext().getRequestDispatcher("/view/autenticazione/admin.jsp");
                dispatcher.forward(request, response);

            } else if (uCheck != null) {

                dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);

            } else {

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (username.length() < MINLIMIT || username.length() > MAXLIMIT) {
                    messaggio = "La username inserita non rispetta la "
                            + "lunghezza corretta";
                    throw new IllegalArgumentException(messaggio);
                }

                if (!username.matches("^((?!.*[\\s])(?=.*[A-Z])(?=.*\\d).{1,50})")) {
                    messaggio = "La username inserita non rispetta il formato corretto";
                    throw new IllegalArgumentException(messaggio);
                }

                if (username.substring(0, 5).equalsIgnoreCase("admin")) {

                    try {

                        Admin a = as.loginAdmin(username, password);

                        if (a != null) {

                            session.setAttribute("admin", a);

                            TorneoService torneoService = new TorneoServiceImpl();
                            List<Torneo> tornei = torneoService.getAllTornei();
                            EventoService eventoService = new EventoServiceImpl();
                            List<Evento> eventi = eventoService.getAllEventi();
                            PartitaService partitaService = new PartitaServiceImpl();
                            List<Partita> partite = partitaService.showPartite();
                            request.getServletContext().setAttribute("tornei", tornei);
                            request.getServletContext().setAttribute("eventi", eventi);
                            request.getServletContext().setAttribute("partite", partite);

                            response.setContentType("Il login admin è avvenuto correttamente");

                            dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/admin.jsp");
                            dispatcher.forward(request, response);
                        }

                    } catch (IllegalArgumentException e) {

                        messaggio = "Login non avvenuto perchè la password è scorretta";
                        request.setAttribute("messaggio", messaggio);
                        dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/loginResult.jsp");
                        dispatcher.forward(request, response);
                    }

                } else {

                    try {

                        Utente u = as.loginUtente(username, password);

                        if (u != null) {

                            response.setContentType("Benvenuto");
                            session.setAttribute("utente", u);
                            dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
                            dispatcher.forward(request, response);

                        } else {

                            messaggio = "Login non avvenuto perchè la username non esiste";
                            request.setAttribute("messaggio", messaggio);
                            dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/loginResult.jsp");
                            dispatcher.forward(request, response);
                        }

                    } catch (IllegalArgumentException e) {

                        messaggio = "Login non avvenuto perchè la password è scorretta";
                        request.setAttribute("messaggio", messaggio);
                        dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/loginResult.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            }

        } else if (flag == 3) {

            dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/utente.jsp");
            dispatcher.forward(request, response);

        } else if (flag == 4) {

            session.setAttribute("utente", null);
            session.setAttribute("admin", null);
            dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

        } else if (flag == 5) {

            TorneoService torneoService = new TorneoServiceImpl();
            List<Torneo> tornei = torneoService.getAllTornei();
            EventoService eventoService = new EventoServiceImpl();
            List<Evento> eventi = eventoService.getAllEventi();
            PartitaService partitaService = new PartitaServiceImpl();
            List<Partita> partite = partitaService.showPartite();
            request.getServletContext().setAttribute("tornei", tornei);
            request.getServletContext().setAttribute("eventi", eventi);
            request.getServletContext().setAttribute("partite", partite);

            dispatcher = getServletContext().getRequestDispatcher("/view/autenticazione/admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}
