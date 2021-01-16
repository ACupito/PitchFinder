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

            if (!(nome.length() > MINLIMIT && nome.length() < MAXLIMIT)) {
                throw new IllegalArgumentException("La lunghezza del nome non è valida");
            }
            if (!nome.matches("^[a-zA-Z0-9\u00C0-\u00ff'\\s]+$")) {
                throw new IllegalArgumentException("Il formato nel nome non è valido");
            }

            if (dateStr.equals("")) {
                throw new IllegalArgumentException("Inserire la data");
            }

            try {
                /* The Event's date (Date). */
                dataEvento = Date.valueOf(dateStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("La data non rispetta il formato");
            }

            Evento temp = new Evento(nome, "default", new Time(12, 0, 0),
                    new Time(14, 0, 0), dataEvento, "temp", "temp",
                    0, admin.getUsername());

            es.removeEvento(temp);
            response.setContentType("Eliminazione avvenuta");
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/autentication?flag=5&result=1&message=Eliminazione avvenuta");
            requestDispatcher.forward(request, response);
        }
    }


}
