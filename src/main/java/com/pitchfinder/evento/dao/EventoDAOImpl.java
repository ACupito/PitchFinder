package com.pitchfinder.evento.dao;

import com.pitchfinder.evento.entity.Evento;

import java.util.Date;
import java.util.List;

/**
 * This interface manages the Evento dao.
 */
public class EventoDAOImpl {

        /**
         *
         * This method makes the Evento object persist
         * in the database.
         * @param evento object
         * @return boolean
         */
        public boolean doSaveEvento(final Evento evento) {
            return false;
        }

        /**
         * This method allows to delete a Evento object
         * from the database.
         * @param evento object
         * @return boolean
         */
        public boolean doRemoveEvento(final Evento evento) {
            return false;
        }

        /**
         * This method allows to get all Evento items
         * from the database.
         * @return List<Evento>
         */
        public List<Evento> doRetrieveByAllEventi() {
            return null;
        }

        /**
         * This method allows to get Evento item
         * from the database.
         * @param nome name of the event.
         * @param date date of the event.
         * @return Evento
         */
        public Evento doRetrieveEvento(final String nome, final Date date) {
            return null;
        }

        /**
         *
         * @param evento looking for its prenotation.
         * @return List<Evento>
         */
            public List<Evento> doRetrieveNPrenotazioniByEvento(final Evento evento) {
                return null;
            }
}
