package com.pitchfinder.partita.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.services.PartitaService;
import com.pitchfinder.partita.services.PartitaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class CreazionePartitaController extends HttpServlet {
    /**
     *  doPost method.
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PartitaService service = new PartitaServiceImpl();

        String idCampoStr = request.getParameter("idCampo");
        int idCampo;
        Utente utente = (Utente) session.getAttribute("utente");
        String dateStr = request.getParameter("date");
        Date date;
        String startStr = request.getParameter("start");
        Time start;
        String endStr = request.getParameter("end");
        Time end;

        String maxGiocatoriStr = request.getParameter("maxGiocatori");
        int maxGiocatori;

        if (utente == null) {
            throw  new IllegalArgumentException("Utente non valido");
        }

        if (idCampoStr == null || idCampoStr.equals("")) {
            throw  new IllegalArgumentException("Campo non valido");
        }
        try {
            idCampo = Integer.parseInt(idCampoStr);
        } catch (NumberFormatException e) {
            throw  new IllegalArgumentException("Campo non valido");
        }

        Date currentDate = new Date(System.currentTimeMillis());
        try {
            date = Date.valueOf(dateStr);
            if (date.before(currentDate)) {
                throw new IllegalArgumentException("Formato a Data non valido");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato Data non valido");
        }

        if (!startStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("Formato Orario di Inizio non valido");
        }
        start = Time.valueOf(startStr.concat(":00"));

        if (!endStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("Formato Orario di Fine non valido");
        }
        end = Time.valueOf(endStr.concat(":00"));

        if (end.before(start) || start.after(end) || endStr.equals(startStr)) {
            throw new IllegalArgumentException("Formato Orario partita non valido");
        }
        //Check maximum match time
        int startIntHr = Integer.parseInt(startStr.substring(0, 2));
        int startIntMn = Integer.parseInt(startStr.substring(3));

        int endIntHr = Integer.parseInt(endStr.substring(0, 2));
        int endIntMn = Integer.parseInt(endStr.substring(3));


        if (endIntHr - startIntHr >= 2) {
            if (endIntHr - startIntHr == 2 && startIntMn - endIntMn < 0) {
                throw new IllegalArgumentException("Durata partita troppo lunga");
            } else if (endIntHr - startIntHr > 2) {
                throw new IllegalArgumentException("Durata partita troppo lunga");
            }
        }

        //Controllo sul numero dei giocatori
        if (maxGiocatoriStr.equals("") || maxGiocatoriStr == null) {
            throw  new IllegalArgumentException("Numero massimo giocatori non valido");
        }

        try {
            maxGiocatori = Integer.parseInt(maxGiocatoriStr);
        } catch (NumberFormatException e) {
            throw  new IllegalArgumentException("Numero massimo giocatori non valido");
        }

        //Inizia il controllo sui giocatori
        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<String> cognomi = new ArrayList<>();
        String nameStr = "nameG";
        String surnameStr = "surnameG";

        for (int i = 0; i < maxGiocatori; i++) {
            String currentName = request.getParameter(nameStr + i);
            if (currentName.equals("") || currentName == null) {
                throw  new IllegalArgumentException("Nome giocatore non valido");
            }

            if (!currentName.matches("^[a-zA-Z\\s]+$") || currentName.length()>12
                || currentName.length() < 2) {
                throw new IllegalArgumentException("Nome giocatore non valido");
            }
            nomi.add(currentName);

            String currentSurname = request.getParameter(surnameStr + i);
            if (currentSurname.equals("") || currentSurname == null) {
                throw  new IllegalArgumentException("Cognome giocatore non valido");
            }

            if (!currentSurname.matches("^[a-zA-Z\\s]+$") || currentSurname.length()>12
                    || currentSurname.length() < 2) {
                throw new IllegalArgumentException("Cognome giocatore non valido");
            }
            cognomi.add(currentSurname);
        }


        if (service.createPartita(idCampo, utente, date, start, end) != null) {
            response.setContentType("Creazione avvenuta!");
        } else {
            response.setContentType("Impossibile creare una partita!");
        }

    }

    /**
     * doGet method.
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
