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
import java.util.ArrayList;

@WebServlet("/EventoController")
public class EventoController  extends HttpServlet {

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

        EventoService service = new EventoServiceImpl();
        ArrayList<Evento> eventi = (ArrayList<Evento>) service.getAllEventi();

        request.getServletContext().setAttribute("eventiContext", eventi);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/view/evento/evento.jsp");
        dispatcher.forward(request, response);
    }
}
