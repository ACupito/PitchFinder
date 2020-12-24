package prenotazione.dao;

import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class PrenotazioneDAOImplTest {

    Prenotazione prenotazione = new Prenotazione();
    PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
    /**
     * Booking correctly saved
     */
    @Test
    public void checkDoSaveTr() {

        prenotazione.setUtenteEmail("manuzzi97@gmail.com");
        prenotazione.setCodicePrenotazione(100);
        prenotazione.setEventoNome("NomeEvento");
        Date eventoData = new Date(2021-1900, 12-1, 18);
        prenotazione.setEventoData(eventoData);
        System.out.println(prenotazione);
        prenotazioneDAO.doRemovePrenotazione(prenotazione);
        assertTrue(prenotazioneDAO.doSavePrenotazione(prenotazione));

    }

    /**
     * Failure, the booking is duplicated
     */
    @Test
    public void checkDoSaveFailure(){
        prenotazione.setUtenteEmail("manuzzi97@gmail.com");
        prenotazione.setCodicePrenotazione(100);
        prenotazione.setEventoNome("NomeEvento");
        Date eventoData = new Date(2021-1900, 12-1, 18);
        prenotazione.setEventoData(eventoData);
        assertThrows(RuntimeException.class ,() -> {prenotazioneDAO.doSavePrenotazione(prenotazione);});

    }

    /**
     * Success, the booking correctly removed
     */
    @Test
    public void checkDoRemoveTr() {

        prenotazione.setUtenteEmail("manuzzi97@gmail.com");
        prenotazione.setCodicePrenotazione(100);
        prenotazione.setEventoNome("NomeEvento");
        Date eventoData = new Date(2021-1900, 12-1, 18);
        prenotazione.setEventoData(eventoData);

        assertTrue(prenotazioneDAO.doRemovePrenotazione(prenotazione));
        prenotazioneDAO.doSavePrenotazione(prenotazione);

    }

    /**
     * There isn't a booking with CodicePrenotazione=135600987
     */
    @Test
    public void checkDoRemoveFailure(){
        prenotazione.setUtenteEmail("manuzzi97@gmail.com");
        prenotazione.setCodicePrenotazione(135600987);
        prenotazione.setEventoNome("NomeEvento");
        Date eventoData = new Date(2021-1900, 12-1, 18);
        prenotazione.setEventoData(eventoData);

        assertFalse(prenotazioneDAO.doRemovePrenotazione(prenotazione));

    }


}
