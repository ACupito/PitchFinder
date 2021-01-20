package com.pitchfinder.prenotazione.services;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.sql.Date;


public class PrenotazioneServiceImpl implements PrenotazioneService  {
    /**
     * Create Booking.
     * @param utenteEmail - utenteEmail.
     * @param evento - evento.
     * @return Prenotazione
     */
    @Override
    public Prenotazione createPrenotazione(String utenteEmail, Evento evento) throws EmailException {
        Prenotazione prenotazione = new Prenotazione(utenteEmail, evento.getName(), evento.getDate());
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

        String destinatario;
        String mittente;
        String messaggio;
        String oggetto;

        if (!prenotazioneDAO.doSavePrenotazione(prenotazione)) {
            return null;
        }

        destinatario = utenteEmail;
        mittente = "pitchfinder@gmail.com";
        oggetto = "Prenotazione";

        messaggio = "Prenotazione all'evento " + evento.getName() + " andata a buon fine, il codice della prenotazione è: "
                + prenotazioneDAO.doRetrievePrenotazione(utenteEmail, evento.getName(), evento.getDate()).getCodicePrenotazione()
                + ".";

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("utentepitchfinder@gmail.com", "utentePitch"));
        email.setSSLOnConnect(true);
        email.setFrom(mittente);
        email.setSubject(oggetto);
        email.setMsg(messaggio);
        email.addTo(destinatario);
        email.send();


        return prenotazione;
    }

    /**
     * Get BOOKING.
     * @param email - email.
     * @param eventoData - Date event.
     * @param eventoNome - Name event.
     * @return Prenotazione
     */
    @Override
    public Prenotazione getPrenotazione(String email, Date eventoData, String eventoNome) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
        Prenotazione prenotazione = prenotazioneDAO.doRetrievePrenotazione(email, eventoNome, eventoData);
        return prenotazione;
    }


    /**
     * Return NumeroPrenotazione.
     * @param prenotazione - prenotazione.
     * @return int
     */
    @Override
    public int getNumeroPrenotazione(Prenotazione prenotazione) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
        Prenotazione prenotazioneRicevuta = prenotazioneDAO.doRetrievePrenotazione(prenotazione.getUtenteEmail(),
                prenotazione.getEventoNome(), prenotazione.getEventoData());
        return prenotazioneRicevuta.getCodicePrenotazione();
    }
}
