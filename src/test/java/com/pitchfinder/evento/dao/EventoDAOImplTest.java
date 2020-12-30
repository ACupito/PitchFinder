package com.pitchfinder.evento.dao;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.evento.services.EventoServiceImpl;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class EventoDAOImplTest {


        @BeforeAll
        public static void setUp() {

                Admin admin = new Admin();
                admin.setNome("Paolo");
                admin.setCognome("DB");
                admin.setUsername("testAdmin05");
                admin.setPassword("password");

                try (Connection con = ConPool.getInstance().getConnection()) {
                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO Admin (Nome, Cognome, Username, Password) VALUES(?,?,?,?)");
                        ps.setString(1, admin.getNome());
                        ps.setString(2, admin.getCognome());
                        ps.setString(3, admin.getUsername());
                        ps.setString(4, admin.getPasswordHash());
                        ps.executeUpdate();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        /**
         *  This Method tests the doSaveEvento method of the EventoDAOImpl class.
         */
        @Test
        void check1() {
                Evento evento = new Evento();
                EventoDAO eventoDAO = new EventoDAOImpl();

                evento.setName("Evento di Prova 700");
                evento.setDate(new Date(2000 - 1900, 10 - 1, 3 ));
                evento.setImage("web/evento/immagini/eventoImage1");
                evento.setGuest("Giuseppe Verdi");
                evento.setDescription("Questo è un evento fittizio di prova");
                evento.setStartHour(new Time(16,0,0));
                evento.setEndHour(new Time(18,0,0));
                evento.setAvailableSits(150);
                evento.setAdmin("testAdmin05");

                assertTrue(eventoDAO.doSaveEvento(evento));
        }

        /**
         *  This Method tests the doRetrieveEventoTest method of the EventoDAOImpl class.
         */
        @Test
        void check2(){
            EventoDAO eventoDAO = new EventoDAOImpl();

            assertNotNull(eventoDAO.doRetrieveEvento("Evento di Prova 700",new Date(2000 - 1900, 10 - 1, 3 )));


        }

        /**
         *  This Method tests the doRetrieveNPrenotazioniByEventoTest method of the EventoDAOImpl class.
         */
        @Test
        void check3(){
            EventoDAO eventoDAO = new EventoDAOImpl();
            Evento evento = eventoDAO.doRetrieveEvento("Evento di Prova 700",new Date(2000 - 1900, 10 - 1, 3 ));
            assertNotNull(eventoDAO.doRetrieveNPrenotazioniByEvento(evento));
        }

        /**
         *  This Method tests the doRetrieveByAllEventiTest method of the EventoDAOImpl class.
         */
        @Test
        void check4(){
            EventoDAO eventoDAO = new EventoDAOImpl();
            assertNotNull(eventoDAO.doRetrieveByAllEventi());
        }

        /**
         *  This Method tests the doRemoveEventoTest method of the EventoDAOImpl class.
         */
        @Test
        void check5(){
                Evento evento = new Evento();
                EventoDAO eventoDAO = new EventoDAOImpl();

                evento.setName("Evento di Prova 700");
                evento.setDate(new Date(2000 - 1900, 10 - 1, 3 ));
                evento.setImage("web/evento/immagini/eventoImage1");
                evento.setGuest("Giuseppe Verdi");
                evento.setDescription("Questo è un evento fittizio di prova");
                evento.setStartHour(new Time(16,0,0));
                evento.setEndHour(new Time(18,0,0));
                evento.setAvailableSits(150);
                evento.setAdmin("testAdmin05");

                assertTrue(eventoDAO.doRemoveEvento(evento));
        }

        @AfterAll
        public static void cleanUp(){

                Admin admin = new Admin();
                admin.setNome("Paolo");
                admin.setCognome("DB");
                admin.setUsername("testAdmin05");
                admin.setPassword("password");

                try (Connection con = ConPool.getInstance().getConnection()) {
                        PreparedStatement ps =
                                con.prepareStatement("delete from Admin where username=? && password=?");

                        ps.setString(1, admin.getUsername());
                        ps.setString(2, admin.getPasswordHash());

                        ps.executeUpdate();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

        }

}
