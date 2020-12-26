
package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Date;
import java.sql.Time;

public class EventoController extends HttpServlet {

    /**
     * Maximum limit for name.
     */
    private static final int MINLIMIT = 1;

    /**
     * Minimum limit for name.
     */
    private static final int MAXLIMIT = 50;

    /**
     * Minimum limit for image.
     */
    private static final int MAXLIMITIMAGE = 2;

    /**
     * Minimum limit for hours.
     */
    private static final int MAXHOUR = 24;

    /**
     * Maximum limit for the minutes.
     */
    private static final int MAXMINUTE = 60;

    /**
     * Maximum limit for the Month.
     */
    private static final int MAXMONTH = 12;

    /**
     * Maximum limit for the day.
     */
    private static final int MAXDAY = 31;

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
        /* The EventoService object. */
        EventoService es = new EventoServiceImpl();
        /* The name of the Event (String). */
        String nome = request.getParameter("nome");
        /* The image's file of the event (File). */
        File immagine = new File(request.getParameter("immagine"));
        /* The start of the Event's hour (String). */
        String oraInizioStr = request.getParameter("orarioInizio").substring(0, 2);
        /* The start of the Event's minute (String). */
        String minutiInizioStr = request.getParameter("orarioInizio").substring(2);
        /* The start of the Event's hour (Integer). */
        int oraInizio = Integer.parseInt(oraInizioStr);
        /* The start of the Event's minute (Integer). */
        int minutiInizio = Integer.parseInt(minutiInizioStr);
        /* The end of the Event's hour (String). */
        String oraFineStr = request.getParameter("orarioInizio").substring(0, 2);
        /* The end of the Event's minute (String). */
        String minutiFineStr = request.getParameter("orarioInizio").substring(2);
        /* The end of the Event's hour (Integer). */
        int oraFine = Integer.parseInt(oraFineStr);
        /* The end of the Event's minute (Integer). */
        int minutiFine = Integer.parseInt(minutiFineStr);
        /* The Event's day (String). */
        String giornoStr = request.getParameter("giorno");
        /* The Event's month (String). */
        String meseStr = request.getParameter("mese");
        /* The Event's year (String). */
        String annoStr = request.getParameter("anno");
        /* The Event's day (Integer). */
        int giorno = Integer.parseInt(giornoStr);
        /* The Event's month (Integer). */
        int mese = Integer.parseInt(meseStr);
        /* The Event's year (Integer). */
        int anno = Integer.parseInt(annoStr);
        /* The Event's date (Date). */
        Date dataEvento = new Date(anno - 1900, mese - 1, giorno);
        /* The Event's guest (String). */
        String ospite = request.getParameter("ospite");
        /* The Event's description (String). */
        String descrizione = request.getParameter("descrizione");
        /* The Event's available sits (Integer). */
        int postiDisponibili = Integer.parseInt(request.getParameter("postiDisponibili"));

        if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché il nome inserito non "
                    + "rispetta la lunghezza corretta [1 - 50]");
        }
        if (nome.matches("^\\S*$")) {
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
        /* The start time of the Event (Time). */
        Time orarioInizio = new Time(oraInizio, minutiInizio, 0);

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
        /* The end time of the Event (Time). */
        Time orarioFine = new Time(oraFine, minutiFine, 0);

        if (giorno > MAXDAY || mese > MAXMONTH) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché "
                    + "il mese o il giorno dell'evento è errato");
        }
        if (!ospite.matches("[A-Z][a-z]+")) {
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
        if (descrizione.length() < MINLIMIT || descrizione.length() > MAXDESCRIPTIONLIMIT) {
            throw new IllegalArgumentException("La creazione non "
                    + "va a buon fine perché la descrizione "
                    + "inserita non rispetta "
                    + "la lunghezza corretta [1 - 500]");
        }
        if (postiDisponibili < MINLIMIT || postiDisponibili > MAXSITSLIMIT) {
            throw new IllegalArgumentException("La creazione non va a "
                    + "buon fine perché il numero dei posti inseriti "
                    + "non rispetta la grandezza richiesta [1 - 300]");
        }

        /*
            Qui va inserito l'immagine e il percorso dell'immagine

         */
        String immagineStr = "null";

        /*
            Qui prendiamo l'adminUsername dalla sessione
         */
        //Work in progress
        String adminUsername = "null";

        es.createEvento(nome, immagineStr, orarioInizio, orarioFine, dataEvento, ospite, descrizione, postiDisponibili, adminUsername);

    }




}
