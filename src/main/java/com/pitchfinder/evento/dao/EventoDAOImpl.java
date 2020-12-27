package com.pitchfinder.evento.dao;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This interface manages the Evento dao.
 */
public class EventoDAOImpl implements EventoDAO {

    /** Macro variable for number 1. */
    private static final int ONE = 1;
    /** Macro variable for number 2. */
    private static final int TWO = 2;
    /** Macro variable for number 3. */
    private static final int THREE = 3;
    /** Macro variable for number 4. */
    private static final int FOUR = 4;
    /** Macro variable for number 5. */
    private static final int FIVE = 5;
    /** Macro variable for number 6. */
    private static final int SIX = 6;
    /** Macro variable for number 7. */
    private static final int SEVEN = 7;
    /** Macro variable for number 8. */
    private static final int EIGHT = 8;
    /** Macro variable for number 9. */
    private static final int NINE = 9;


        /**
         *
         * This method makes the Evento object persist
         * in the database.
         * @param event object
         * @return boolean
         */
        public boolean doSaveEvento(final Evento event) {

            String query = "INSERT into evento(Nome,Data,Immagine,Ospite,Descrizione,OrarioInizio,OrarioFine,MaxPartecipanti,AdminUsername)"
                           + " values(?,?,?,?,?,?,?,?,?)";

            try (Connection con = ConPool.getInstance().getConnection()) {
                PreparedStatement ps =
                        con.prepareStatement(query);
                ps.setString(ONE, event.getName());
                ps.setDate(TWO, event.getDate());
                ps.setString(THREE, event.getImage());
                ps.setString(FOUR, event.getGuest());
                ps.setString(FIVE, event.getDescription());
                ps.setTime(SIX, event.getStartHour());
                ps.setTime(SEVEN, event.getEndHour());
                ps.setInt(EIGHT, event.getAvailableSits());
                ps.setString(NINE, event.getAdmin());
                return 1 == ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * This method allows to delete a Evento object
         * from the database.
         * @param event object
         * @return boolean
         */
        public boolean doRemoveEvento(final Evento event) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps =
                        con.prepareStatement("DELETE FROM evento WHERE Nome=? and Data=?");
                ps.setString(ONE, event.getName());
                ps.setDate(TWO, event.getDate());
                return 1 == ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * This method allows to get all Evento items
         * from the database.
         * @return List<Evento>
         */
        public List<Evento> doRetrieveByAllEventi() {
            try (Connection con = ConPool.getConnection()) {

                String query = "SELECT Nome,Immagine,OrarioInizio,OrarioFine,Data,Ospite,Descrizione,MaxPartecipanti,AdminUsername"
                               + " FROM evento";
                PreparedStatement ps =
                        con.prepareStatement(query);
                ResultSet rs =  ps.executeQuery();
                List<Evento> allEvents = new ArrayList<>();
                while (rs.next()) {
                    Evento eventoAdd = new Evento(rs.getString(ONE),
                            rs.getString(TWO), rs.getTime(THREE),
                            rs.getTime(FOUR), rs.getDate(FIVE),
                            rs.getString(SIX), rs.getString(SEVEN),
                            rs.getInt(EIGHT), rs.getString(NINE));
                    allEvents.add(eventoAdd);
                }
                return allEvents;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * This method allows to get Evento item
         * from the database.
         * @param nome name of the event.
         * @param date date of the event.
         * @return Evento
         */
        public Evento doRetrieveEvento(final String nome, final Date date) {


            String query = "SELECT Nome,Immagine,OrarioInizio,OrarioFine,Data,Ospite,Descrizione,MaxPartecipanti,AdminUsername"
                    + " FROM evento where Nome=? AND Data=?;";

            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps =
                        con.prepareStatement(query);
                ps.setString(ONE, nome);
                ps.setDate(TWO, (java.sql.Date) date);
                ResultSet rs =  ps.executeQuery();
                Evento event = new Evento();
                if (rs.next()) {
                    event = new Evento(rs.getString(ONE),
                            rs.getString(TWO), rs.getTime(THREE),
                            rs.getTime(FOUR), rs.getDate(FIVE),
                            rs.getString(SIX), rs.getString(SEVEN),
                            rs.getInt(EIGHT), rs.getString(NINE));
                }
                return event;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         *
         * @param event looking for its prenotation.
         * @return List<Evento>
         */
            public int doRetrieveNPrenotazioniByEvento(final Evento event) {


                String query = "SELECT count(CodicePrenotazione) as nPrenotazioni"
                        + " FROM prenotazione WHERE EventoNome = ?";


                try (Connection con = ConPool.getConnection()) {
                    PreparedStatement ps =
                            con.prepareStatement(query);
                    ps.setString(ONE, event.getName());
                    ResultSet rs =  ps.executeQuery();
                    int numberPrenotation = 0;
                    if (rs.next()) {
                        numberPrenotation = rs.getInt("nPrenotazioni");
                    }

                    return numberPrenotation;

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
}
