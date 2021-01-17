package com.pitchfinder.evento.controller;

import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.prenotazione.services.PrenotazioneService;
import com.pitchfinder.prenotazione.services.PrenotazioneServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
@WebServlet("/ValidaEmail")
public class ValidaEmail extends HttpServlet {
    /**
     * doget.
     * @param req -req.
     * @param resp -resp.
     * @throws ServletException -.
     * @throws IOException -.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * doPost.
     * @param req -.
     * @param resp -.
     * @throws ServletException -.
     * @throws IOException -.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringa = req.getParameter("invio");
        String[] splitted = stringa.split(",");
        String email = splitted[0];
        Date dataEvento = Date.valueOf(splitted[2]);
        String nomeEvento = splitted[1];

        resp.setContentType("text/xml");
        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();
        Prenotazione prenotazione = prenotazioneService.getPrenotazione(email, dataEvento, nomeEvento);
        if (prenotazione == null) {
            resp.getWriter().append("<ok/>");
        } else {
            resp.getWriter().append("<no/>");
        }
    }
}
