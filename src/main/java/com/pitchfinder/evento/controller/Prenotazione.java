package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/Prenotazione")
public class Prenotazione extends HttpServlet {
    /**
     *doGet.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * doPost.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomeEvento = req.getParameter("eventName");
        String dataEvento = req.getParameter("eventDate");
        Date dateEvento = Date.valueOf(dataEvento);

        EventoService eventoService = new EventoServiceImpl();
        Evento evento = eventoService.getEvento(nomeEvento, dateEvento);

        req.setAttribute("evento", evento);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/view/evento/prenotazioneEvento.jsp");
        dispatcher.forward(req, resp);
    }
}
