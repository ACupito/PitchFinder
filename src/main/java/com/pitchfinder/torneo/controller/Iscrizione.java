package com.pitchfinder.torneo.controller;

import com.mysql.cj.Session;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.torneo.entity.Torneo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/Iscrizione")
public class Iscrizione extends HttpServlet {
    /**
     * DoGet.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException -Servlet Exception.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * DoPost.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Utente utente = (Utente) req.getSession().getAttribute("utente");

        if (utente != null) {

            String nomeTorneo = req.getParameter("nomeTorneo");
            int campo = Integer.parseInt(req.getParameter("campo"));
            Date dataTorneo = Date.valueOf(req.getParameter("dataTorneo"));

            TorneoService torneoService = new TorneoServiceImpl();
            Torneo torneo = torneoService.getTorneo(nomeTorneo, dataTorneo, campo);
            req.setAttribute("torneo", torneo);

            RequestDispatcher dispatcher =
                    req.getServletContext().getRequestDispatcher("/view/torneo/iscrizioneTorneo.jsp");
            dispatcher.forward(req, resp);

        } else {
            req.setAttribute("nonRegistrato", "no");
            RequestDispatcher dispatcher =
                    req.getServletContext().getRequestDispatcher("/view/torneo/dettagliTorneo.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
