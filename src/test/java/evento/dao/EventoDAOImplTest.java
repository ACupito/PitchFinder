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
         * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
         * All the parameters are correct.
         */
        @Test
        void checkdoSaveEventoTestTr() {

            Evento evento = new Evento();
            EventoDAO eventoDAO = new EventoDAOImpl();

            evento.setName("Evento di Prova 7");
            evento.setDate(new Date(2000 - 1900, 10 - 1, 3 ));
            evento.setImage("web/evento/immagini/eventoImage1");
            evento.setGuest("Giuseppe Verdi");
            evento.setDescription("Questo è un evento fittizio di prova");
            evento.setStartHour(new Time(16,00,00));
            evento.setEndHour(new Time(18,00,00));
            evento.setAvailableSits(150);
            evento.setAdmin("memex99");

            assertTrue(eventoDAO.doSaveEvento(evento));
        }

    /**
     * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
     * Start Hour is incorrect
     */
    @Test
    void checkdoSaveEventoTestFl() {

        Evento evento = new Evento();
        EventoDAO eventoDAO = new EventoDAOImpl();

        evento.setName("Evento di Prova 11");
        evento.setDate(new Date(2000 - 1900, 10 - 1, 3 ));
        evento.setImage("web/evento/immagini/eventoImage1");
        evento.setGuest("Giuseppe Verdi");
        evento.setDescription("Questo è un evento fittizio di prova");
        evento.setStartHour(new Time(04,00,00));
        evento.setEndHour(new Time(18,00,00));
        evento.setAvailableSits(150);
        evento.setAdmin("memex99");
        assertNotNull(eventoDAO.doSaveEvento(evento));

        assertThrows(RuntimeException.class, () ->{eventoDAO.doSaveEvento(evento);});
    }



}
