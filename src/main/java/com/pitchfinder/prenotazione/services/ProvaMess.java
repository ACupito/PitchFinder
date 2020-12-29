package com.pitchfinder.prenotazione.services;
import com.pitchfinder.evento.entity.Evento;
import org.apache.commons.mail.EmailException;

import java.sql.Date;
import java.sql.Time;

public class ProvaMess {

    public static void main(String[] args) throws EmailException {
        Evento evento = new Evento("NomeEvento", "immagine", Time.valueOf("13:00:00"),
                Time.valueOf("17:00:00"), Date.valueOf("2021-12-18"), "Lucia", "Descrizione",
                100, "memex99");

        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();
        prenotazioneService.createPrenotazione("Luca@gmail.com", evento);

    }
}
