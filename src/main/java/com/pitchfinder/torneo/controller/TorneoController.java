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
        if(session == null){
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

        }

        int campo = Integer.parseInt(request.getParameter("idCampo"));
        String nome = request.getParameter("nome");
        String startDate = request.getParameter("dataInizio");

        if (nome == null) {
            throw new IllegalArgumentException("Nome non inserito");
        }

        if (flag == 4) { //get a specific tournament

            if (nome.length() < 1 || nome.length() > 50) {
                throw new IllegalArgumentException("Lunghezza nome non valida");
            }
            if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                throw new IllegalArgumentException("Formato nome non valido");
            }

            Date dataInizio;
            if (startDate == null) {
                throw new IllegalArgumentException("Data inizio non selezionata");
            }
            try {
                dataInizio = Date.valueOf(startDate);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Formato data inizio non valido");
            }

            Torneo t = ts.getTorneo(nome, dataInizio, campo);
            response.setContentType("Torneo ottenuto");

            session.setAttribute("torneo", t);
            request.setAttribute("torneo", t);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/torneo/dettagliTorneo.jsp");
            dispatcher.forward(request, response);
        }

        String endDate = request.getParameter("dataFine");

        String giornoPartite = request.getParameter("giornoPartite");
        if (giornoPartite == null) {
            throw new IllegalArgumentException("Giorno partite non inserito");
        }

        if (admin != null) {

            if (flag == 1) { //tournament creation

                if (nome.length() < 1 || nome.length() > 50) {
                    throw new IllegalArgumentException("Lunghezza nome non valida");
                }
                if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                    throw new IllegalArgumentException("Formato nome non valido");
                }

                String sport = request.getParameter("sport");
                if (sport == null) {
                    throw new IllegalArgumentException("Sport non selezionato");
                }

                String tipo = request.getParameter("tipo");
                if (tipo == null) {
                    throw new IllegalArgumentException("Tipo non selezionato");
                }

                String struttura = request.getParameter("struttura");
                if (struttura == null) {
                    throw new IllegalArgumentException("Struttura non selezionata");
                }

                Date dataInizio;
                if (startDate == null) {
                    throw new IllegalArgumentException("Data inizio non selezionata");
                }
                try {
                    dataInizio = Date.valueOf(startDate);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Formato data inizio non valido");
                }

                Date dataFine;
                if (endDate == null) {
                    throw new IllegalArgumentException("Data fine non selezionata");
                }
                try {
                    dataFine = Date.valueOf(endDate);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Formato data fine non valido");
                }

                if (ts.checkScheduledTorneo(dataInizio, dataFine, campo)) {
                   throw new IllegalArgumentException("Tornei già schedulati in quel periodo");
                }

                if (giornoPartite.length() < 1 || giornoPartite.length() > 20) {
                    throw new IllegalArgumentException("Lunghezza giorno partite non valida");
                }
                if (!giornoPartite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                    throw new IllegalArgumentException("Formato giorno partite non valido");
                }

                int maxSquadre;
                try {
                    maxSquadre = Integer.parseInt(request.getParameter("maxSquadre"));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Formato numero squadre non valido");
                }
                if (maxSquadre < 1 || maxSquadre > 20) {
                    throw new IllegalArgumentException("Numero di squadre non valido");
                }

                int minPartecipanti;
                try {
                    minPartecipanti = Integer.parseInt(request.getParameter("minPartecipanti"));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Formato minimo partecipanti non valido");
                }
                if (minPartecipanti < 1 || minPartecipanti > 5) {
                    throw new IllegalArgumentException("Numero minimo di partecipanti non valido");
                }

                int maxPartecipanti;
                try {
                    maxPartecipanti = Integer.parseInt(request.getParameter("maxPartecipanti"));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Numero massimo di partecipanti non valido");
                }
                if (maxPartecipanti < 5 || maxPartecipanti > 12) {
                    throw new IllegalArgumentException("Numero massimo di partecipanti non valido");
                }

                //creation tournament
                boolean creationResult = ts.createTorneo(admin.getUsername(), campo, nome, tipo,
                        struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                        giornoPartite);

                if (creationResult) { //creation occurred
                    response.setContentType("Creazione avvenuta");
                }

            } else if (flag == 2) { //tournament elimination
                // when remove button clicked.

                if (nome.length() < 1 || nome.length() > 50) {
                    throw new IllegalArgumentException("Lunghezza nome non valida");
                }
                if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                    throw new IllegalArgumentException("Formato nome non valido");
                }

                Date dataInizio;
                if (startDate == null) {
                    throw new IllegalArgumentException("Data inizio non selezionata");
                }
                try {
                    dataInizio = Date.valueOf(startDate);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Formato data inizio non valido");
                }

                Date dataFine;
                if (endDate == null) {
                    throw new IllegalArgumentException("Data fine non selezionata");
                }
                try {
                    dataFine = Date.valueOf(endDate);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Formato data fine non valido");
                }


                if (giornoPartite.length() < 1 || giornoPartite.length() > 20) {
                    throw new IllegalArgumentException("Lunghezza giorno partite non valida");
                }
                if (!giornoPartite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                    throw new IllegalArgumentException("Formato giorno partite non valido");
                }

                boolean removeResult = ts.deleteTorneo(campo, nome, dataInizio, dataFine, giornoPartite);
                if (removeResult) response.setContentType("Eliminazione avvenuta");
            }
        }
    }
}
