package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.services.PrenotazioneService;
import com.pitchfinder.prenotazione.services.PrenotazioneServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PrenotazioneEventoController extends HttpServlet {
    /**
     * doPost.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String email = req.getParameter("email");

        Evento evento = (Evento) session.getAttribute("evento");

        if (email == null) {
            throw new IllegalArgumentException("Email non valida.");
        }

        if ((email.length() < 1) || (email.length() > 50)) {
            throw new IllegalArgumentException("La prenotazione all’evento non va a buon fine la lunghezza dell’email non è valida.");
        }

        if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")) {
            throw new IllegalArgumentException("La prenotazione all’evento non va a buon fine il formato dell’email non è valido.");
        }

        if (evento == null) {
            throw new IllegalArgumentException("Evento non valido");
        }

        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();
        prenotazioneService.createPrenotazione(email, evento);
        resp.setContentType("La prenotazione all’evento va a buon fine.");
    }

    /**
     * doGet.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
