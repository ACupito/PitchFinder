package com.pitchfinder.partita.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.services.CampoService;
import com.pitchfinder.campo.services.CampoServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
        String reqTosplit = req.getParameter("dataTime");
        String[] splitted = reqTosplit.split(",");

        Date data = Date.valueOf(splitted[0]);
        Time str = Time.valueOf(splitted[1].concat(":00"));
        Time end = Time.valueOf(splitted[2].concat(":00"));


        CampoService service = new CampoServiceImpl();
        List<Utente> utenti = service.showAllDisponibilita(1002, data, str, end);

        resp.setContentType("application/json");

        JSONObject pacchetto = new JSONObject();
        JSONArray nomi = new JSONArray();
        JSONArray cognomi = new JSONArray();

        for (int i = 0; i < utenti.size(); i++) {
            nomi.add(utenti.get(i).getNome());
            cognomi.add(utenti.get(i).getCognome());
        }

        pacchetto.put("nomi", nomi);
        pacchetto.put("cognomi", cognomi);

        //Files.write(Paths.get("response.json"), pacchetto.toJSONString().getBytes());
        resp.getWriter().print(pacchetto.toJSONString());
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
