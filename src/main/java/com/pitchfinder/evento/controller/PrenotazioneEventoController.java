package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;
import com.pitchfinder.prenotazione.services.PrenotazioneService;
import com.pitchfinder.prenotazione.services.PrenotazioneServiceImpl;
import org.apache.commons.mail.EmailException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/PrenotazioneEventoController")
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

        if (req.getParameter("Conferma") != null) {
            HttpSession session = req.getSession();

            String email = req.getParameter("email");
            String nomeEvento = req.getParameter("eventName");
            String dataEvento = req.getParameter("eventDate");
            EventoService eventoService = new EventoServiceImpl();
            Evento evento = eventoService.getEvento(nomeEvento, Date.valueOf(dataEvento));

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
            try {
                prenotazioneService.createPrenotazione(email, evento);
            } catch (EmailException e) {
                e.printStackTrace();
            }

            req.setAttribute("evento", evento);
            //Se la prenotazione va a buon fine setto questo attributo che mi servirà per creare l'alert nella jsp
            req.setAttribute("prenotazione", "ok");
            resp.setContentType("La prenotazione all’evento va a buon fine.");

            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/view/evento/prenotazioneEvento.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("Indietro") != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/view/evento/evento.jsp");
            dispatcher.forward(req, resp);
        }
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
