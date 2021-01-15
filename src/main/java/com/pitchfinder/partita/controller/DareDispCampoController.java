package com.pitchfinder.partita.controller;


import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.services.CampoService;
import com.pitchfinder.campo.services.CampoServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
@WebServlet("/DareDispCampoController")
public class DareDispCampoController extends HttpServlet {

    /**
     * doPost() method.
     * @param request  is the servlet request.
     * @param response is the servlet response.
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");


        if (request.getParameter("Conferma") != null) {
            CampoService camp = new CampoServiceImpl();
            String campo = request.getParameter("idcampo");
            int idcampo = Integer.parseInt(campo);

            String email = utente.getEmail();
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

            camp.createDisponibilita(email, idcampo, data, inizio, fine);
            response.setContentType("La creazione va a buon fine");
            RequestDispatcher dispatcher =
                    request.getServletContext().getRequestDispatcher("/view/disponibilitaCampo/dareDisponibilita.jsp");
            dispatcher.forward(request, response);
        }
    }
    /**
     * doGet() method.
     * @param request  is the servlet request.
     * @param response is the servlet response.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
