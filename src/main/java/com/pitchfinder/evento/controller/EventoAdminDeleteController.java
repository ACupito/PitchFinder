package com.pitchfinder.evento.controller;

import com.pitchfinder.autenticazione.entity.Admin;
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
import java.sql.Time;
import java.util.ArrayList;

@WebServlet("/EventoAdminDeleteController")
public class EventoAdminDeleteController extends HttpServlet {

    /** Minimum limit for name. */
    private static final int MINLIMIT = 1;
    /** Maximum limit for name. */
    private static final int MAXLIMIT = 50;
    /** Maximum limit for image. */
    private static final int MAXLIMITIMAGE = 2;
    /** Maximum limit for the Guest. */
    private static final int MAXGUESTLIMIT = 20;
    /** Maximum limit for the description. */
    private static final int MAXDESCRIPTIONLIMIT = 500;
    /** Maximum limit for the description. */
    private static final int MAXSITSLIMIT = 300;

    /**
     * doPost() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * doGet() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws ServletException, IOException {

        Admin admin = (Admin) request.getSession().getAttribute("admin"); //get admin from the session
        RequestDispatcher dispatcher;

        if (admin != null) {
            /* The EventoService object. */
            EventoService es = new EventoServiceImpl();
            /* The name of the Event (String). */
            String nome = request.getParameter("nome");
            /* The Event's date (String). */
            String dateStr = request.getParameter("data");
            /* The Event's date (Date)*/
            Date dataEvento;

            if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
                throw new IllegalArgumentException("Errato: lunghezza nome non valida");
            }
            if (!nome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }

            if (dateStr.equals("")) {
                throw new IllegalArgumentException("Errato: data non selezionata");
            }
            Date myDate = new Date(System.currentTimeMillis());
            try {
                dataEvento = Date.valueOf(dateStr); /* The Event's date (Date). */
                if (dataEvento.before(myDate)) {
                    throw new IllegalArgumentException("Errato: formato non valido");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }

            Evento temp = new Evento(nome, "default", new Time(12, 00, 00),
                    new Time(14, 00, 00), dataEvento, "temp", "temp",
                    0, admin.getUsername());
            if (temp == null) {
                response.setContentType("Impossibile eliminare l'evento");
            }

            response.setContentType("Eliminazione avvenuta");
            dispatcher = request.getServletContext().getRequestDispatcher("/view/autenticazione/admin.jsp");
            dispatcher.forward(request, response);
        }
    }


}
