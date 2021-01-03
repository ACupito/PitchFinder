package com.pitchfinder.torneo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IscrizioneTorneoController extends HttpServlet {

    /**
     * doPost.
     *@param req - request.
     *@param resp - response.
     *@throws ServletException - Exception.
     *@throws IOException - Exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * doGet.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - Exception.
     * @throws IOException - Exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

