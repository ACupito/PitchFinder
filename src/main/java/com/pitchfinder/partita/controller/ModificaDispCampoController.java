package com.pitchfinder.partita.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.campo.services.CampoService;
import com.pitchfinder.campo.services.CampoServiceImpl;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class ModificaDispCampoController extends HttpServlet{
    /**
     * doPost() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) {

        HttpSession session = request.getSession();
        CampoService camp= new CampoServiceImpl();

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Campo campo= (Campo) request.getSession().getAttribute("campo");

        int idCampo;
        String dataStr = request.getParameter("data");
        Date data;
        String inizioStr = request.getParameter("inizio");
        Time inizio;
        String fineStr = request.getParameter("fine");
        Time fine;

        if (dataStr.equals("")) {
            throw new IllegalArgumentException("Errato: data non selezionata");
        }
        Date myDate = new Date(System.currentTimeMillis());
        try {
            data = Date.valueOf(dataStr);
            if (data.before(myDate)) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Errato: formato non valido");
        }
        if (inizioStr.matches("")) {
            throw new IllegalArgumentException("Errato: orario non selezionato");
        }
        if (!inizioStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("Formato Orario di Inizio non valido");
        }
        inizio = Time.valueOf(inizioStr.concat(":00"));

        if (fineStr.matches("")) {
            throw new IllegalArgumentException("Errato: orario non selezionato");
        }
        if (!fineStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("Formato Orario di Fine non valido");
        }
        fine = Time.valueOf(fineStr.concat(":00"));


        if (fine.before(inizio) || inizio.after(fine) || fineStr.equals(inizioStr)) {
            throw new IllegalArgumentException("Formato Orario non valido");
        }


        boolean creazione = camp.createOccupazione(campo.getIdentificativo(), data, inizio, fine, admin.getUsername());
        response.setContentType("Creazione avvenuta");
    }
    /**
     * doGet method.
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException  {
        this.doPost(request, response);

    }

}
