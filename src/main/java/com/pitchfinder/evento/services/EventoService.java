package com.pitchfinder.evento.services;

import com.pitchfinder.evento.entity.Evento;

import java.sql.Date;
import java.util.List;

public interface EventoService {


    /**
     *
     * @param nome of the event
     * @param immagine of the event
     * @param oraInizio of the event
     * @param oraFine of the event
     * @param data of the event
     * @param ospite of the event
     * @param descrizione of the event
     * @param postiDisponibili of the event
     * @return Evento
     */
    Evento createEvento(String nome,
                        String immagine,
                        String oraInizio,
                        String oraFine,
                        Date data,
                        String ospite,
                        String descrizione,
                        int postiDisponibili);

    /**
     *
     * @param evento to be deleted
     * @return boolean
     */
    Boolean removeEvento(Evento evento);

    /**
     *
     * @return list<Evento>
     */
    List<Evento> getAllEventi();

    /**
     *
     * @param nome of the event
     * @param date of the event
     * @return Evento
     */
    Evento getEvento(String nome, Date date);

    /**
     *
     * @param evento for the number of participants
     * @return int
     */
    int findNumeroPartecipantiByEvento(Evento evento);

}
