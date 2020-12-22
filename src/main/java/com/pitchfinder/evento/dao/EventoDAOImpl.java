package com.pitchfinder.evento.dao;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * This interface manages the Evento dao.
 */
public class EventoDAOImpl {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;


        /**
         *
         * This method makes the Evento object persist
         * in the database.
         * @param evento object
         * @return boolean
         */
        public boolean doSaveEvento(final Evento evento) {

            try (Connection con = ConPool.getInstance().getConnection()) {
                PreparedStatement ps =
                        con.prepareStatement("INSERT into evento(Nome,Data,Immagine,Ospite,Descrizione,OrarioInizio,OrarioFine,MaxPartecipanti,AdminUsername)"
                                +" values(?,?,?,?,?,?,?,?,?)");
                ps.setString(ONE,evento.getName());
                ps.setDate(TWO,evento.getDate());
                ps.setString(THREE,evento.getImage());
                ps.setString(FOUR,evento.getGuest());
                ps.setString(FIVE,evento.getDescription());
                ps.setString(SIX,evento.getStartHour());
                ps.setString(SEVEN,evento.getEndHour());
                ps.setInt(EIGHT,evento.getAvailableSits());
                ps.setString(NINE,evento.getAdmin());
                return 1 == ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
