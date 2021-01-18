package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.partita.services.PartitaService;
import com.pitchfinder.partita.services.PartitaServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FilterJSON")
public class ShowMatchFilterAjax extends HttpServlet {

    /**
     * doGet method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = null;

        String dateStr = req.getParameter("data");
        if (!dateStr.equals("null")) {
            date = Date.valueOf(dateStr);
        }

        Time time = null;

        String timeStr = req.getParameter("time");
        if (!timeStr.equals("null")) {
            timeStr = timeStr.concat(":00");
            time = Time.valueOf(timeStr);
        }

        PartitaService service = new PartitaServiceImpl();
        List<Partita> partite = service.showPartite();
        List<Partita> nuove = new ArrayList<Partita>();

        for (Partita current : partite) {
            if (date == null && time == null) {
                nuove.addAll(partite);
                break;
            } else if (time == null && date != null && date.getTime() == current.getData().getTime()) {
                nuove.add(current);
            } else if (date == null && time != null && time.getTime() == current.getOrarioInizio().getTime()) {
                nuove.add(current);
            } else if (current.getOrarioInizio().getTime() == time.getTime()
                    && current.getData().getTime() == date.getTime()) {
                nuove.add(current);
            }
        }
        JSONObject partita;
        JSONArray arrayPartite = new JSONArray();

        for (Partita game : nuove) {
            partita = new JSONObject();
            partita.put("idPartita", String.valueOf(game.getIdPartita()));
            partita.put("idCampo", String.valueOf(game.getIdCampo()));
            partita.put("utenteEmail", game.getEmailUtente());
            partita.put("date", game.getData().toString());
            partita.put("orarioInizio", game.getOrarioInizio().toString());
            partita.put("orarioFine", game.getOrarioFine().toString());

            arrayPartite.add(partita);
        }

        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().append(JSONValue.toJSONString(arrayPartite));
    }

    /**
     * doPost method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
