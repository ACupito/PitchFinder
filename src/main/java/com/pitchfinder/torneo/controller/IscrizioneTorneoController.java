package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.squadra.services.SquadraService;
import com.pitchfinder.squadra.services.SquadraServiceImpl;
import com.pitchfinder.torneo.entity.Torneo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IscrizioneTorneoController extends HttpServlet {

    /**
     * doPost.
     *@param req - request.
     *@param resp - response.
     *@throws ServletException - Exception.
     *@throws IOException - Exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Controll of button
        if (req.getParameter("conferma") != null) {

            HttpSession session = req.getSession();
            Torneo torneo = (Torneo) session.getAttribute("torneo");
            Utente utente = (Utente) session.getAttribute("utente");
            String nomeSquadra = req.getParameter("nomeSquadra");
            String numeroGiocatori = req.getParameter("numeroGiocatori");
            int nGiocatori;
            String nomeCapitano = req.getParameter("nomeCapitano");
            String cognomeCapitano = req.getParameter("cognomeCapitano");
            String capitano; //variabile da passare al metodo createSquadra

            if (nomeSquadra == null) {
                throw new IllegalArgumentException("Nome squadra non valido");
            }
            //check name's team
            if (nomeSquadra.length() < 1 || nomeSquadra.length() > 50) {
                throw new IllegalArgumentException("Lunghezza nome squadra non valida");
            }

            if (!nomeSquadra.matches("^[a-zA-Z_ ]*$")) {
                throw new IllegalArgumentException("Formato nome squadra non valido");
            }

            //Check torneo
            if (torneo == null) {
                throw new IllegalArgumentException("Torneo non valido");
            }

            //Check utente
            if (utente == null) {
                throw new IllegalArgumentException("Utente non valido");
            }

            //check number of players
            if (numeroGiocatori == null) {
                throw new IllegalArgumentException("Numero dei giocatori non valido");
            }

            if (numeroGiocatori.length() < 1 || numeroGiocatori.length() > 2) {
                throw new IllegalArgumentException("Lunghezza numero dei giocatori non valida");
            }

            try {
                nGiocatori = Integer.parseInt(numeroGiocatori);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Formato numero giocatori non valido");
            }


            if (nGiocatori < torneo.getMinNumeroPartecipantiPerSquadra() || nGiocatori > torneo.getMaxNumeroPartecipantiPerSquadra()) {
                throw new IllegalArgumentException("Il numero dei giocatori no rispetta le direttive del torneo");
            }



            //check nome e cognome
            if (nomeCapitano == null) {
                throw new IllegalArgumentException("Nome capitano non valido");
            }

            if (nomeCapitano.length() < 1 || nomeCapitano.length() > 10) {
                throw new IllegalArgumentException("Lunghezza nome capitano non valido");
            }

            if (cognomeCapitano == null) {
                throw new IllegalArgumentException("Cognome capitano non valido");
            }

            if (cognomeCapitano.length() < 1 || cognomeCapitano.length() > 20) {
                throw new IllegalArgumentException("Lunghezza cognome capitano non valida");
            }

            if (!cognomeCapitano.matches("^[a-zA-Z_ ]*$")) {
                throw new IllegalArgumentException("Formato cognome capitano non valido");
            }

            if (!nomeCapitano.matches("^[a-zA-Z_ ]*$")) {
                throw new IllegalArgumentException("Formato nome capitano non valido");
            }

            //controllo squadre iscritte al torneo
            TorneoService torneoService = new TorneoServiceImpl();
            if (torneoService.nIscritti(torneo) >= torneo.getNumeroSquadre()){
                throw new IllegalArgumentException("Non è possibile iscriversi, troppe squadre iscritte");
            }

            //capitano
            capitano = nomeCapitano + "" + cognomeCapitano;


            //check giocatori
            List<String> giocatori = new ArrayList<>();

            for (int i = 0; i < nGiocatori; i++) {
                String nomePlayer = "nomePlayer" + (i + 1);
                String cognomePlayer = "cognomePlayer" + (i + 1);

                if (req.getParameter(nomePlayer) == null) {
                    throw new IllegalArgumentException("Nome giocatore non valido");
                }

                if (req.getParameter(cognomePlayer) == null) {
                    throw new IllegalArgumentException("Cognome giocatore non valido");
                }

                if (req.getParameter(nomePlayer).length() < 1 || req.getParameter(nomePlayer).length() > 10) {
                    throw new IllegalArgumentException("Lunghezza nome giocatore non valida");
                }

                if (req.getParameter(cognomePlayer).length() < 1 || req.getParameter(cognomePlayer).length() > 10) {
                    throw new IllegalArgumentException("Lunghezza cognome giocatore non valida");
                }

                if (!req.getParameter(nomePlayer).matches("^[a-zA-Z\\\\s]+$")) {
                    throw new IllegalArgumentException("Formato Nome giocatore non valido");
                }

                if (!req.getParameter(cognomePlayer).matches("^[a-zA-Z\\\\s]+$")) {
                    throw new IllegalArgumentException("Formato cognome giocatore non valido");
                }

                giocatori.add(req.getParameter(nomePlayer));
                giocatori.add(req.getParameter(cognomePlayer));

            }

            //Subscription of team to the tournament
            SquadraService squadraService = new SquadraServiceImpl();
            squadraService.createSquadra(nomeSquadra, torneo, nGiocatori, capitano, utente);

            //Creation of players
            Squadra squadra = new Squadra(nomeSquadra, torneo.getNome(), torneo.getDataInizio(),
                    torneo.getCampoIdentificativo(), nGiocatori, capitano, utente.getEmail());
            for (int i = 0; i < giocatori.size() / 2; i = i + 2) {
                squadraService.createGiocatoreSquadra(giocatori.get(i), giocatori.get(i + 1), squadra);
            }

            resp.setContentType("Iscrizione avvenuta con successo");

        } else {
            resp.setContentType("Indietro");
        }
    }

    /**
     * doGet.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - Exception.
     * @throws IOException - Exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

