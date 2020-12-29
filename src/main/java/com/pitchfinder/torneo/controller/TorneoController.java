package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.torneo.entity.Torneo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        TorneoService ts = new TorneoServiceImpl();

        int flag = Integer.parseInt(request.getParameter("flag"));
        Admin admin = (Admin) request.getSession().getAttribute("admin"); //get admin from the session
        Campo campo = (Campo) request.getSession().getAttribute("campo"); //get campo from the session

        if (admin != null && campo != null) {

            String nome = request.getParameter("nome");
            if (nome == null) {
                throw new IllegalArgumentException("Nome non inserito");
            }

            String startDate = request.getParameter("dataInizio");
            String endDate = request.getParameter("dataFine");

            String giornoPartite = request.getParameter("giornoPartite");
            if (giornoPartite == null) {
                throw new IllegalArgumentException("Giorno partite non inserito");
            }

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

                if (dataInizio.getTime() > dataFine.getTime()) {
                   throw new IllegalArgumentException("Data inizio successiva alla data di fine");
                }
                if (!ts.checkScheduledTorneo(dataInizio, dataFine, campo.getIdentificativo())) {
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
                if (maxSquadre < 1 || maxSquadre > 50) {
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
                boolean creationResult = ts.createTorneo(admin.getUsername(), campo.getIdentificativo(), nome, tipo,
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

                boolean removeResult = ts.deleteTorneo(campo.getIdentificativo(), nome, dataInizio, dataFine, giornoPartite);
                if (removeResult) response.setContentType("Eliminazione avvenuta");
            } else if (flag == 3) { //get all tornei

                List<Torneo> tornei = ts.getAllTornei();
                request.setAttribute("tornei", tornei);

            }
        }
    }
}
