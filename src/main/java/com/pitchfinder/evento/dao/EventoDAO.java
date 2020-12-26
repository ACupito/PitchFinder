package com.pitchfinder.evento.dao;

import com.pitchfinder.evento.entity.Evento;

import java.util.Date;
import java.util.List;

public interface EventoDAO {

    /**
     *
     * @param event object
     * @return boolean
     */
    boolean doSaveEvento(Evento event);

    /**
     *
     * @param event object
     * @return boolean
     */
    boolean doRemoveEvento(Evento event);

    /**
     *
     * @return list<Evento>
     */
    List<Evento> doRetrieveByAllEventi();

    /**
     * The key of event are name, date.
     * @param nome of the event
     * @param date of the event
     * @return Evento
     */
    Evento doRetrieveEvento(String nome, Date date);

    /**
     *
     * @param event looking for its prenotation.
     * @return int
     */
    int doRetrieveNPrenotazioniByEvento(Evento event);

}
