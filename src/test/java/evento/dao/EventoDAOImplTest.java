package evento.dao;

import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

public class EventoDAOImplTest {

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
                evento.setAdmin("memex99");

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
                evento.setAdmin("memex99");

                assertTrue(eventoDAO.doRemoveEvento(evento));
        }

}
