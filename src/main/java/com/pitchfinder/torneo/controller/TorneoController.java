package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

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

        if (flag == 1) { //tournament creation

            Campo campo = (Campo) request.getSession().getAttribute("campo"); //get campo from the context

            String nome = request.getParameter("nome");
            if (nome == null) {
                throw new IllegalArgumentException("Nome non inserito");
            }
            if (nome.length() < 1 || nome.length() > 50) {
                throw new IllegalArgumentException("Lunghezza nome non valida");
            }
            if (!nome.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                throw new IllegalArgumentException("Formato nome non valido");
            }

            String tipo = request.getParameter("tipo");
            if (tipo == null) {
                throw new IllegalArgumentException("Tipo non selezionato");
            }

            String struttura = request.getParameter("struttura");
            if (struttura == null) {
                throw new IllegalArgumentException("Struttura non selezionata");
            }

            int maxSquadre = Integer.parseInt(request.getParameter("maxSquadre"));
            if (maxSquadre < 1 || maxSquadre > 50) {
                throw new IllegalArgumentException("Numero di squadre non valido");
            }

            int minPartecipanti = Integer.parseInt(request.getParameter("minPartecipanti"));
            if (minPartecipanti < 1 || minPartecipanti > 5) {
                throw new IllegalArgumentException("Numero minimo di partecipanti non valido");
            }

            int maxPartecipanti = Integer.parseInt(request.getParameter("maxPartecipanti"));
            if (maxPartecipanti < 5 || maxPartecipanti > 12) {
                throw new IllegalArgumentException("Numero massi di partecipanti non valido");
            }

            String giornoPartite = request.getParameter("giornoPartite");
            if (giornoPartite == null) {
                throw new IllegalArgumentException("Giorno partite non inserito");
            }
            if (giornoPartite.length() < 1 || giornoPartite.length() > 20) {
                throw new IllegalArgumentException("Lunghezza giorno partite non valida");
            }
            if (!giornoPartite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                throw new IllegalArgumentException("Formato giorno partite non valido");
            }

            Date dataInizio = Date.valueOf(request.getParameter("dataInizio"));
            Date dataFine = Date.valueOf(request.getParameter("dataFine"));

            ts.createTorneo(admin.getUsername(), campo.getIdentificativo(), nome, tipo,
                    struttura, maxSquadre, dataInizio, dataFine, minPartecipanti, maxPartecipanti,
                    giornoPartite);
        }


    }
}
