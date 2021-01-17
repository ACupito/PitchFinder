package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
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

@WebServlet("/torneoServlet")
public class TorneoController extends HttpServlet {

    /**
     * Constructor.
     */
    public TorneoController() {
        super();
    }

    /**
     * doPost() method.
     * @param request is the servlet request
     * @param response is the servlet response
     * @throws ServletException is the ServletException
     * @throws IOException is the I/O Exception
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    /**
     * doGet() method.
     * @param request is the servlet request
     * @param response is the servlet response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TorneoService ts = new TorneoServiceImpl();
        HttpSession session = request.getSession();
        if (session == null) {
            session = request.getSession(true);
        }
        int flag = Integer.parseInt(request.getParameter("flag"));

        Admin admin = (Admin) request.getSession().getAttribute("admin"); //get admin from the session



        if (flag == 3) { //get all tornei

            List<Torneo> tornei = ts.getAllTornei();
            response.setContentType("Tornei ottenuti");
            request.getServletContext().setAttribute("tornei", tornei);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/torneo/visualizzaTornei.jsp");
            dispatcher.forward(request, response);

        } else {

            int campo = Integer.parseInt(request.getParameter("idCampo"));
            String nome = request.getParameter("nome");
            String startDate = request.getParameter("dataInizio");

            if (nome == null) {
                throw new IllegalArgumentException("Nome non inserito");
            }

            if (flag == 4) { //get a specific tournament

                if (nome.length() < 1 || nome.length() > 50) {
                    throw new IllegalArgumentException("L'ottenimento del torneo fallisce a causa"
                            + " della lunghezza del nome.");
                }
                if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                    throw new IllegalArgumentException("L'ottenimento del torneo fallisce a causa"
                            + " del formato errato del nome.");
                }

                Date dataInizio;
                if (startDate == null) {
                    throw new IllegalArgumentException("L'ottenimento del torneo fallisce a causa del "
                            + "mancata selezione della data di inizio.");
                }
                try {
                    dataInizio = Date.valueOf(startDate);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("L'ottenimento del torneo fallisce a causa del"
                            + " formato errato della data di inizio.");
                }

                Torneo t = ts.getTorneo(nome, dataInizio, campo);
                response.setContentType("Torneo ottenuto");

                session.setAttribute("torneo", t);
                request.getServletContext().setAttribute("torneo", t);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/torneo/dettagliTorneo.jsp");
                dispatcher.forward(request, response);
            } else {

                String endDate = request.getParameter("dataFine");

                String giornoPartite = request.getParameter("giornoPartite");
                if (giornoPartite == null) {
                    throw new IllegalArgumentException("La creazione del torneo fallisce a causa della"
                            + " mancata selezione del Giorno partite.");
                }

                if (admin != null) {

                    if (flag == 1) { //tournament creation

                        if (nome.length() < 1 || nome.length() > 50) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa della lunghezza del nome.");
                        }
                        if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato del nome.");
                        }

                        String sport = request.getParameter("sport");
                        if (sport == null) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce per"
                                    + " la mancata selezione di uno sport.");
                        }

                        String tipo = request.getParameter("tipo");
                        if (tipo == null) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce"
                                    + " per la mancata selezione della tipologia.");
                        }

                        String struttura = request.getParameter("struttura");
                        if (struttura == null) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce per"
                                    + " la mancata selezione della struttura.");
                        }

                        Date dataInizio;
                        if (startDate == null) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce "
                                    + "per la mancata selezione della data di inizio.");
                        }
                        try {
                            dataInizio = Date.valueOf(startDate);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato della data di inizio.");
                        }

                        Date dataFine;
                        if (endDate == null) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce"
                                    + " per mancata selezione della data di fine.");
                        }
                        try {
                            dataFine = Date.valueOf(endDate);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce"
                                    + " a causa del formato errato della data di fine.");
                        }

                        if (ts.checkScheduledTorneo(dataInizio, dataFine, campo)) {
                            throw new IllegalArgumentException("Tornei già schedulati in quel periodo");
                        }

                        if (giornoPartite.length() < 1 || giornoPartite.length() > 20) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa della lunghezza del Giorno Partite.");
                        }
                        if (!giornoPartite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato del Giorno partite.");
                        }

                        int maxSquadre;
                        try {
                            maxSquadre = Integer.parseInt(request.getParameter("maxSquadre"));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato del Numero squadre.");
                        }
                        if (maxSquadre < 1 || maxSquadre > 20) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del valore errato del Numero squadre.");
                        }

                        int minPartecipanti;
                        try {
                            minPartecipanti = Integer.parseInt(request.getParameter("minPartecipanti"));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato del Minimo Giocatori.");
                        }
                        if (minPartecipanti < 1 || minPartecipanti > 5) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del valore errato del Minimo Giocatori.");
                        }

                        int maxPartecipanti;
                        try {
                            maxPartecipanti = Integer.parseInt(request.getParameter("maxPartecipanti"));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del formato errato del Massimo Giocatori.");
                        }
                        if (maxPartecipanti < 5 || maxPartecipanti > 12) {
                            throw new IllegalArgumentException("La creazione del torneo fallisce a"
                                    + " causa del valore errato del Massimo Giocatori.");
                        }

                        //creation tournament
                        boolean creationResult = ts.createTorneo(admin.getUsername(), campo, nome, tipo,
                                struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                                giornoPartite);

                        if (creationResult) { //creation occurred
                            response.setContentType("La creazione del torneo è avvenuta correttamente.");
                            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/autentication?flag=5");
                            requestDispatcher.forward(request, response);
                        }

                    } else if (flag == 2) { //tournament elimination
                        // when remove button clicked.

                        if (nome.length() < 1 || nome.length() > 50) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa della lunghezza del nome.");
                        }
                        if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa del formato errato del nome.");
                        }

                        Date dataInizio;
                        if (startDate == null) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa della mancata selezione della data di inizio.");
                        }
                        try {
                            dataInizio = Date.valueOf(startDate);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa del formato errato della data di inizio.");
                        }

                        Date dataFine;
                        if (endDate == null) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa della mancata selezione della data di fine.");
                        }
                        try {
                            dataFine = Date.valueOf(endDate);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa del formato errato della data di fine.");
                        }


                        if (giornoPartite.length() < 1 || giornoPartite.length() > 20) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa della lunghezza errata del giorno partite.");
                        }
                        if (!giornoPartite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                            throw new IllegalArgumentException("L'eliminazione del torneo fallisce a"
                                    + " causa del formato errato del giorno partite.");
                        }

                        boolean removeResult = ts.deleteTorneo(campo, nome, dataInizio, dataFine, giornoPartite);
                        if (removeResult) response.setContentType("Eliminazione del torneo avvenuta correttamente.");

                        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/autentication?flag=5");
                        requestDispatcher.forward(request, response);
                    }

                }
            }
        }
    }
}
