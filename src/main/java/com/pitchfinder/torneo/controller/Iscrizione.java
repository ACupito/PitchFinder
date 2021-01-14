package com.pitchfinder.torneo.controller;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String nomeTorneo = req.getParameter("nomeTorneo");
       int campo = Integer.parseInt(req.getParameter("campo"));
       Date dataTorneo = Date.valueOf(req.getParameter("dataTorneo"));

        TorneoService torneoService = new TorneoServiceImpl();
        Torneo torneo = torneoService.getTorneo(nomeTorneo, dataTorneo, campo);
        req.setAttribute("torneo", torneo);

        RequestDispatcher dispatcher =
                req.getServletContext().getRequestDispatcher("/view/torneo/iscrizioneTorneo.jsp");
        dispatcher.forward(req, resp);


    }
}
