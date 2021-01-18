package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.partita.services.PartitaService;
import com.pitchfinder.partita.services.PartitaServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAj")
public class ShowMatchAdminAj extends HttpServlet {
    /**
     * doPost method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * doGet method.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codiceStr = req.getParameter("codice");
        int idPartita = Integer.parseInt(codiceStr);
        PartitaService service = new PartitaServiceImpl();

        Partita current = new Partita();

        List<Partita> partite = service.showPartite();
        for (Partita partita: partite) {
            if (partita.getIdPartita() == idPartita) {
                current = partita;
                break;
            }
        }
        List<String> giocatore = service.showGiocatori(current);

        JSONObject pacchetto = new JSONObject();
        JSONArray nomi = new JSONArray();
        JSONArray cognomi = new JSONArray();

        for (int i = 0; i < giocatore.size(); i = i + 2) {
            nomi.add(giocatore.get(i));
            cognomi.add(giocatore.get(i + 1));
        }

        AutenticazioneService autService = new AutenticazioneServiceImpl();
        Utente user = autService.prelevaUtenteByEmail(current.getEmailUtente());

        pacchetto.put("userName", user.getNome());
        pacchetto.put("userSurname", user.getCognome());
        pacchetto.put("nomi", nomi);
        pacchetto.put("cognomi", cognomi);

        resp.setContentType("application/json");
        resp.getWriter().print(pacchetto.toJSONString());
    }
}
