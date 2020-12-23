package com.pitchfinder.evento.services;

import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;

import java.sql.Date;
import java.util.List;

public class EventoServiceImpl implements EventoService {


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
     * @param adminUsername of the event
     * @return Evento
     */
    public Evento createEvento(final String nome,
                               final String immagine,
                               final String oraInizio,
                               final String oraFine,
                               final Date data,
                               final String ospite,
                               final String descrizione,
                               final int postiDisponibili,
                               final String adminUsername) {

        Evento evento = new Evento(nome,
                                   immagine,
                                   oraInizio,
                                   oraFine,
                                   data,
                                   ospite,
                                   descrizione,
                                   postiDisponibili,
                                   adminUsername);
        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        if (eventoDAO.doSaveEvento(evento)) {
            return evento;
        }
        return null;
    }

    /**
     *
     * @param evento to be deleted
     * @return boolean
     */
    public Boolean removeEvento(final Evento evento) {
        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        return eventoDAO.doRemoveEvento(evento);
    }

    /**
     *
     * @return list<Evento>
     */
    public List<Evento> getAllEventi() {
        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        return eventoDAO.doRetrieveByAllEventi();
    }

    /**
     *
     * @param nome of the event
     * @param date of the event
     * @return Evento
     */
    public Evento getEvento(final String nome, final Date date) {
        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        return eventoDAO.doRetrieveEvento(nome, date);
    }

    /**
     *
     * @param evento for the number of participants
     * @return int
     */
    public int findNumeroPartecipantiByEvento(final Evento evento) {
        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        return eventoDAO.doRetrieveNPrenotazioniByEvento(evento);
    }
}
