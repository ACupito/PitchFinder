
package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;

public class EventoController extends HttpServlet {

    /**
     * Minimum limit for name.
     */
    private static final int MINLIMIT = 1;
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
                       final HttpServletResponse response) throws IOException, ServletException {

        doGet(request, response);
    }


    /**
     * doGet() method.
     * @param request is the servlet request.
     * @param response is the servlet response.
     */
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException, ServletException {
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
        /* The Event's date (String). */
        String dateStr = request.getParameter("date");
        /* The Event's date (Date)*/
        new Date(1);
        Date dataEvento;
        try {
            /* The Event's date (Date). */
            dataEvento = Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato Data non valido");
        }
        /* The Event's guest (String). */
        String ospite = request.getParameter("ospite");
        /* The Event's description (String). */
        String descrizione = request.getParameter("descrizione");
        /* The Event's available sits (String). */
        String postiDisponibiliStr = request.getParameter("postiDisponibili");
        /* The Event's available sits (Integer). */
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
        /* The start time of the Event (Time). */
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
        /* The end time of the Event (Time). */
        Time orarioFine = new Time(oraFine, minutiFine, 0);

        Date myDate = new Date(System.currentTimeMillis());
        if (dataEvento.before(myDate)) {
            throw new IllegalArgumentException("La creazione "
                    + "non va a buon fine perché "
                    + "il giorno/mese/anno è antecedente a oggi");
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


        /* ***********************************
         *  Here begins the Image upload part *
         *  ***********************************
         */

        /* We take the parameter file (it's like "getParameter"). */
        Part filePart = request.getPart("fileAddProduct");
        /* We take the file's name. */
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        /* We take the servletContextRealPath + FileSeparator (/) + the folder's name (images). */
        String path = getServletContext().getRealPath("") + File.separator + "images";

        File uploads = new File(path);
        /* This is the name's length. */
        int lenght = fileName.length();

        File file = File.createTempFile(fileName.substring(0, lenght - 4), fileName.substring(lenght - 4, lenght), uploads);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Start of the file's name (the true one). */
        int lastIndex = file.getAbsoluteFile().toString().lastIndexOf("\\") + 1;
        /* Lenght of the absolute path. */
        int  totalLenght = file.getAbsoluteFile().toString().length();
        /* We get the true name's file. */
        String finalFileName = file.getAbsolutePath().toString().substring(lastIndex, totalLenght);
        /* We add the "images" folder to the name's file */
        String immagineStr = "images/" + finalFileName;

        /* *********************************
         *  Here ends the Image upload part *
         *  *********************************
         */

        /*
         *  We get the admin username from the session.
         *
         * HttpSession session = request.getSession();
         * Admin admin = (Admin) session.getAttribute("adminUsername");
         * String adminUsername = admin.getUsername();
         */

        String adminUsername = "memex99";

        es.createEvento(nome, immagineStr, orarioInizio, orarioFine, dataEvento, ospite, descrizione, postiDisponibili, adminUsername);

    }




}
