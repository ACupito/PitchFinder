
package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class EventoController extends HttpServlet {

    /**
     * Minimum limit for name.
     */
    private static final int MINLIMIT = 1;
    /**
     * Minimum limit for year.
     */
    private static final int MINLIMITYEAR = new Date(Calendar.getInstance().getWeekYear()).getYear();
    /**
     * Maximum limit for name.
     */
    private static final int MAXLIMIT = 50;

    /**
     * Maximum limit for image.
     */
    private static final int MAXLIMITIMAGE = 2;

    /**
     * Maximum limit for hours.
     */
    private static final int MAXHOUR = 24;

    /**
     * Maximum limit for the minutes.
     */
    private static final int MAXMINUTE = 60;

    /**
     * Maximum limit for the Guest.
     */
    private static final int MAXGUESTLIMIT = 20;

    /**
     * Maximum limit for the description.
     */
    private static final int MAXDESCRIPTIONLIMIT = 500;

    /**
     * Maximum limit for the description.
     */
    private static final int MAXSITSLIMIT = 300;


    /**
     * doPost() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) {

        doGet(request, response);
    }


    /**
     * doGet() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) {

        EventoService es = new EventoServiceImpl();
        String nome = request.getParameter("nome");
        File immagine = new File(request.getParameter("immagine"));
        String oraInizioStr = request.getParameter("orarioInizio").substring(0, 2);
        String minutiInizioStr = request.getParameter("orarioInizio").substring(2);
        int oraInizio = Integer.parseInt(oraInizioStr);
        int minutiInizio = Integer.parseInt(minutiInizioStr);
        String oraFineStr = request.getParameter("orarioInizio").substring(0, 2);
        String minutiFineStr = request.getParameter("orarioInizio").substring(2);
        int oraFine = Integer.parseInt(oraFineStr);
        int minutiFine = Integer.parseInt(minutiFineStr);
        String dateStr = request.getParameter("date");
        Date date = new Date(1);
        try {
            date = Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato Data non valido");
        }
        int giorno = date.getDay();
        int mese = date.getMonth();
        int anno = date.getYear();
        Date dataEvento = new Date(anno - 1900, mese - 1, giorno);
        String ospite = request.getParameter("ospite");
        String descrizione = request.getParameter("descrizione");
        String postiDisponibiliStr = request.getParameter("postiDisponibili");
        int postiDisponibili = Integer.parseInt(postiDisponibiliStr);
        if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché il nome inserito non "
                    + "rispetta la lunghezza corretta [1 - 50]");
        }
        if (!nome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")) {
            throw new IllegalArgumentException("La registrazione non va a buon "
                    + "fine perché il nome inserito "
                    + "non rispetta il formato richiesto");
        }
        if ((immagine.length() / (1024 * 1024)) > MAXLIMITIMAGE) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché l'immagine inserita non "
                    + "rispetta la grandezza corretta [<= 2MB]");
        }
        if (!immagine.toString().matches(".jpg") || !immagine.toString().matches(".png")) {
            throw new IllegalArgumentException("La creazione non va a buon "
                    + "fine perché l'immagine inserita "
                    + "non rispetta il formato richiesto [jpg o png]");
        }
        if (!oraInizioStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché l'orario di inizio "
                    + "non ha un formato valido");
        }
        if (oraInizio <= 0 || minutiInizio < 0) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché non è stato inserito "
                    + "l'orario di inizio");
        }
        if (oraInizio > MAXHOUR || minutiInizio > MAXMINUTE) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché l'orario inserito "
                    + "non rispetta il formato corretto");
        }
        Time orarioInizio = new Time(oraInizio, minutiInizio, 0);
        if (!oraFineStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché l'orario di fine "
                    + "non ha un formato valido");
        }
        if (oraFine <= 0 || minutiFine < 0) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché non è stato inserito "
                    + "l'orario di fine");
        }
        if (oraFine > MAXHOUR || minutiFine > MAXMINUTE) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché l'orario inserito "
                    + "non rispetta il formato corretto");
        }
        Time orarioFine = new Time(oraFine, minutiFine, 0);
        if (anno < MINLIMITYEAR) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché "
                    + "l'anno dell'evento è errato");
        }
        if (!ospite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
            throw new IllegalArgumentException("La creazione non va a "
                    + "buon fine perché il nome dell'ospite inserito non "
                    + "rispetta il formato richiesto");
        }
        if (ospite.length() < MINLIMIT || ospite.length() > MAXGUESTLIMIT) {
            throw new IllegalArgumentException("La creazione non "
                    + "va a buon fine perché il nome dell'ospite "
                    + "inserito non rispetta "
                    + "la lunghezza corretta [1 - 20]");
        }
        if (ospite.equals("")) {
            throw new IllegalArgumentException("La creazione non "
                    + "va a buon fine perché il nome dell'ospite "
                    + "inserito non è presente.");
        }
        if (descrizione.length() < MINLIMIT || descrizione.length() > MAXDESCRIPTIONLIMIT) {
            throw new IllegalArgumentException("La creazione non "
                    + "va a buon fine perché la descrizione "
                    + "inserita non rispetta "
                    + "la lunghezza corretta [1 - 500]");
        }
        if (!postiDisponibiliStr.matches("[0-9]+$")) {
            throw new IllegalArgumentException("La creazione non va a "
                    + "buon fine poiché i posti disponibili non "
                    + "rispettano il formato richiesto");
        }
        if (postiDisponibiliStr.equals("")) {
            throw new IllegalArgumentException("La creazione non va a buon "
                    + "fine poiché non sono stati inseriti i posti disponibili.");
        }
        if (postiDisponibili < MINLIMIT || postiDisponibili > MAXSITSLIMIT) {
            throw new IllegalArgumentException("La creazione non va a "
                    + "buon fine perché il numero dei posti inseriti "
                    + "non rispetta la grandezza richiesta [1 - 300]");
        }

        String immagineStr = "null";
        String adminUsername = "null";
        es.createEvento(nome, immagineStr, orarioInizio, orarioFine, dataEvento, ospite, descrizione, postiDisponibili, adminUsername);

    }




}
