package com.pitchfinder.partita.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AvailabilityAj")
public class AvailabilityAjaxServlet extends HttpServlet {

    /**
     * doGet method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* String reqTosplit = req.getParameter("dataTime");
        String[] splitted = reqTosplit.split(",");
        System.out.println("Elemento 1"+ splitted[0]); */
    }

    /**
     * doPosth method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
