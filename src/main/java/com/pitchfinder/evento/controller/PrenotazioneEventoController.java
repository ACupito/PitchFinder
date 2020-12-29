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
        HttpSession httpSession = req.getSession();

        String email = req.getParameter("utenteEmail");
        Evento evento = (Evento) httpSession.getAttribute("evento");

        if (email == null) {
            throw new IllegalArgumentException("Email non valida");
        }

        if ((email. length() < 1) || (email.length() > 50)) {
            throw new IllegalArgumentException("Lunghezza email non corretta");
        }

        if (email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")) {
            throw new IllegalArgumentException("Formato email non valido");
        }

        if (evento == null) {
            throw new IllegalArgumentException("Evento non valido");
        }

        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();
        prenotazioneService.createPrenotazione(email, evento);

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
        super.doGet(req, resp);
    }
}
