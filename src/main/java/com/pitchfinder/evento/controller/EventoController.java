
package com.pitchfinder.evento.controller;

import com.pitchfinder.autenticazione.entity.Admin;
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

    /** Minimum limit for name. */
    private static final int MINLIMIT = 1;
    /** Maximum limit for name. */
    private static final int MAXLIMIT = 50;
    /** Maximum limit for image. */
    private static final int MAXLIMITIMAGE = 2;
    /** Maximum limit for hours. */
    private static final int MAXHOUR = 24;
    /** Maximum limit for the minutes. */
    private static final int MAXMINUTE = 60;
    /** Maximum limit for the Guest. */
    private static final int MAXGUESTLIMIT = 20;
    /** Maximum limit for the description. */
    private static final int MAXDESCRIPTIONLIMIT = 500;
    /** Maximum limit for the description. */
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
        Admin admin = (Admin) request.getSession().getAttribute("admin"); //get admin from the session

        if (admin != null) {
            /* The EventoService object. */
            EventoService es = new EventoServiceImpl();
            /* The name of the Event (String). */
            String nome = request.getParameter("nome");
            /* The image's file of the event (File). */
            String immagine = request.getParameter("immagine");
            /* The start of the Event's hour (String). */
            String orarioInizioStr = request.getParameter("orarioInizio");
            /* The end of the Event's hour (String). */
            String orarioFineStr = request.getParameter("orarioFine");
            /* The Event's date (String). */
            String dateStr = request.getParameter("data");
            /* The Event's date (Date)*/
            Date dataEvento;
            /* The Event's guest (String). */
            String ospite = request.getParameter("ospite");
            /* The Event's description (String). */
            String descrizione = request.getParameter("descrizione");
            /* The Event's available sits (String). */
            String postiDisponibiliStr = request.getParameter("postiDisponibili");

            if (nome.length() < MINLIMIT || nome.length() > MAXLIMIT) {
                throw new IllegalArgumentException("Errato: lunghezza nome non valida");
            }
            if (!nome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            if (!immagine.equals("")) {
                if (!immagine.matches(".*\\.(jpg|png)$")) {
                    throw new IllegalArgumentException("Errato: Formato non valido");
                }
                File file = new File(immagine);
                // Get length of file in bytes
                long fileSizeInBytes = file.length();
                // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                long fileSizeInKB = fileSizeInBytes / 1024;
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                long fileSizeInMB = fileSizeInKB / 1024;

                if (fileSizeInKB > MAXLIMITIMAGE) {
                    throw new IllegalArgumentException("Errato: dimensione non valida");
                }
            } else {
                //Default image
                File defaultImage = new File("defaultDirectory");
            }

            if (orarioInizioStr.matches("")) {
                throw new IllegalArgumentException("Errato: orario non selezionato");
            }
            if (!orarioInizioStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            /* The start time of the Event (Time). */
            Time orarioInizio = Time.valueOf(orarioInizioStr.concat(":00"));

            if (orarioFineStr.matches("")) {
                throw new IllegalArgumentException("Errato: orario non selezionato");
            }
            if (!orarioFineStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            /* The end time of the Event (Time). */
            Time orarioFine = Time.valueOf(orarioFineStr.concat(":00"));

            if (dateStr.equals("")) {
                throw new IllegalArgumentException("Errato: data non selezionata");
            }
            try {
                /* The Event's date (Date). */
                dataEvento = Date.valueOf(dateStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            Date myDate = new Date(System.currentTimeMillis());
            if (dataEvento.before(myDate)) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            if (ospite.length() < MINLIMIT || ospite.length() > MAXGUESTLIMIT) {
                throw new IllegalArgumentException("Errato: lunghezza non valida");
            }
            if (!ospite.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            if (descrizione.length() < MINLIMIT || descrizione.length() > MAXDESCRIPTIONLIMIT) {
                throw new IllegalArgumentException("Errato: lunghezza non valida");
            }
            if (!descrizione.matches("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            if (postiDisponibiliStr.equals("")) {
                throw new IllegalArgumentException("Errato: lunghezza non valida");
            }
            if (!postiDisponibiliStr.matches("[0-9]+$")) {
                throw new IllegalArgumentException("Errato: formato non valido");
            }
            /* The Event's available sits (Integer). */
            int postiDisponibili = Integer.parseInt(postiDisponibiliStr);
            if (postiDisponibili < MINLIMIT || postiDisponibili > MAXSITSLIMIT) {
                throw new IllegalArgumentException("Errato: lunghezza non valida");
            }
//            /* ***********************************
//             *  Here begins the Image upload part *
//             *  ***********************************
//             */
//            /* We take the parameter file (it's like "getParameter"). */
//            Part filePart = request.getPart("fileAddProduct");
//            /* We take the file's name. */
//            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            /* We take the servletContextRealPath + FileSeparator (/) + the folder's name (images). */
//            String path = getServletContext().getRealPath("") + File.separator + "images";
//            File uploads = new File(path);
//            /* This is the name's length. */
//            int lenght = fileName.length();
//            File file = File.createTempFile(fileName.substring(0, lenght - 4), fileName.substring(lenght - 4, lenght), uploads);
//            try (InputStream input = filePart.getInputStream()) {
//            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            /* Start of the file's name (the true one). */
//            int lastIndex = file.getAbsoluteFile().toString().lastIndexOf("\\") + 1;
//            /* Lenght of the absolute path. */
//            int  totalLenght = file.getAbsoluteFile().toString().length();
//            /* We get the true name's file. */
//            String finalFileName = file.getAbsolutePath().toString().substring(lastIndex, totalLenght);
//            /* We add the "images" folder to the name's file */
//            String immagineStr = "images/events" + finalFileName;
//            /* *********************************
//             *  Here ends the Image upload part *
//             *  *********************************
//             */
            es.createEvento(nome, "immagineStr", orarioInizio, orarioFine, dataEvento,
                            ospite, descrizione, postiDisponibili, admin.getUsername());
            response.setContentType("Creazione avvenuta");
        }

    }




}
