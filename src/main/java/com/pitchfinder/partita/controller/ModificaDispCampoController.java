package com.pitchfinder.partita.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.services.CampoService;
import com.pitchfinder.campo.services.CampoServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
@WebServlet("/ModificaDispCampoController")
public class ModificaDispCampoController extends HttpServlet {
    /**
     * doPost() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {


        CampoService camp = new CampoServiceImpl();

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        String campo = request.getParameter("idcampo");
        int idcampo = Integer.parseInt(campo);

        String dataStr = request.getParameter("data");
        Date data;
        String inizioStr = request.getParameter("inizio");
        Time inizio;
        String fineStr = request.getParameter("fine");
        Time fine;

        if (dataStr.equals("")) {
            throw new IllegalArgumentException("La modifica fallisce perché la data non è selezionata");
        }

        try {
            data = Date.valueOf(dataStr);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La modifica fallisce perché la data non rispetta il formato");
        }
        if (inizioStr.matches("")) {
            throw new IllegalArgumentException("La modifica fallisce perché l’orario di inizio non è stato selezionato");
        }
        if (!inizioStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("La modifica fallisce perché l’orario di inizio non rispetta il formato");
        }
        inizio = Time.valueOf(inizioStr.concat(":00"));

        if (fineStr.matches("")) {
            throw new IllegalArgumentException("La modifica fallisce perché l’orario di fine non è stato selezionato");
        }
        if (!fineStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("La modifica fallisce perché l’orario di fine non rispetta il formato");
        }
        fine = Time.valueOf(fineStr.concat(":00"));

        if (request.getParameter("Occupa") != null) {
            camp.createOccupazione(idcampo, data, inizio, fine, admin.getUsername());
            response.setContentType("La modifica va a buon fine");
            request.setAttribute("risultatoOccupa", "1");
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/autentication?flag=5");
            requestDispatcher.forward(request, response);
        } else if (request.getParameter("Libera") != null) {
            camp.deleteOccupazione(idcampo, data, inizio, fine);
            response.setContentType("La modifica va a buon fine.");
            request.setAttribute("risultatoLibera", "1");
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/autentication?flag=5");
            requestDispatcher.forward(request, response);
        }
    }
    /**
     * doGet method.
     * @param request request
     * @param response response

     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
