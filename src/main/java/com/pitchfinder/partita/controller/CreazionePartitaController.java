package com.pitchfinder.partita.controller;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.partita.services.PartitaService;
import com.pitchfinder.partita.services.PartitaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Creation-Servlet")
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
        String btnMessage = request.getParameter("btnMatchCreation");
        if (btnMessage != null) {
            if (btnMessage.equals("Conferma")) {

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
                int maxGiocatori = 0;

                if (utente == null) {
                    throw new IllegalArgumentException("Utente non valido");
                }

                if (idCampoStr == null || idCampoStr.equals("")) {
                    throw new IllegalArgumentException("Campo non valido");
                }
                try {
                    idCampo = Integer.parseInt(idCampoStr);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Campo non valido");
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
                if (maxGiocatoriStr == null || maxGiocatoriStr.equals("")) {
                    throw new IllegalArgumentException("Numero massimo giocatori non valido");
                }

                try {
                    maxGiocatori = Integer.parseInt(maxGiocatoriStr);
                    if (maxGiocatori > 3) { //Giocatori massimi per una partita di tennis
                        throw new IllegalArgumentException("Numero massimo giocatori non valido");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Numero massimo giocatori non valido");
                }

                //Inizia il controllo sui giocatori
                ArrayList<String> nomi = new ArrayList<>();
                ArrayList<String> cognomi = new ArrayList<>();
                String nameStr = "nameG";
                String surnameStr = "surnameG";
                // Anche il for in js partirà da 1

                for (int i = 1; i <= maxGiocatori; i++) {
                    String currentName = request.getParameter(nameStr + i);
                    if (currentName == null || currentName.equals("")) {
                        throw new IllegalArgumentException("Nome giocatore non valido");
                    }

                    if (!currentName.matches("^[a-zA-Z\\s]+$") || currentName.length() > 12
                            || currentName.length() < 2) {
                        throw new IllegalArgumentException("Nome giocatore non valido");
                    }
                    nomi.add(currentName);

                    String currentSurname = request.getParameter(surnameStr + i);
                    if (currentSurname == null || currentSurname.equals("")) {
                        throw new IllegalArgumentException("Cognome giocatore non valido");
                    }

                    if (!currentSurname.matches("^[a-zA-Z\\s]+$") || currentSurname.length() > 12
                            || currentSurname.length() < 2) {
                        throw new IllegalArgumentException("Cognome giocatore non valido");
                    }
                    cognomi.add(currentSurname);
                }


                if (service.createPartita(idCampo, utente, date, start, end) != null) {
                    if (!nomi.isEmpty()) {
                        // Prendo la partita creata
                        List<Partita> partite = service.showPartite();
                        Partita nuova = new Partita();

                        for (Partita partita : partite) {
                            if (partita.getEmailUtente().equals(utente.getEmail())
                                    && partita.getData().equals(date) && partita.getOrarioInizio().equals(start)
                                    && partita.getOrarioFine().equals(end) && partita.getIdCampo() == idCampo) {
                                nuova = partita;
                            }
                        }
                        //Aggiungo i giocatori
                        for (int i = 0; i < nomi.size(); i++) {
                            service.createGiocatorePartita(nuova.getIdPartita(),
                                    nomi.get(i), cognomi.get(i));
                        }
                    }
                    response.setContentType("Creazione avvenuta!");
                    //tornare ad un'altra pagina
                } else {
                    response.setContentType("Impossibile creare una partita!");
                    //tornare alla pagina partite
                }
            } else {
                response.setContentType("Operazione annullata");
                //tornare alla pagina partite
            }
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
