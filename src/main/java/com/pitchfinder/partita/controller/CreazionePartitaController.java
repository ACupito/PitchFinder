package com.pitchfinder.partita.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class CreazionePartitaController extends HttpServlet {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCampo = request.getParameter("idCampo");
        String emailUtente = request.getParameter("emailUtente");
        String dateStr = request.getParameter("date");
        Date date;
        String startStr = request.getParameter("start");
        Time start;
        String endStr = request.getParameter("end");
        Time end;

        if (idCampo == null) {
            throw  new IllegalArgumentException("Campo non valido");
        }

        if (emailUtente == null) {
            throw  new IllegalArgumentException("Utente non valido");
        }

        try {
            date = Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato Data non valido");
        }



        // Attento il metodo: checkOccupazione
    }
}
