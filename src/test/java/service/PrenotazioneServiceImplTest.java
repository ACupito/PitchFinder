package service;

import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertTrue;


public class PrenotazioneServiceImplTest {

    @Test
    public void checkCreatePrenotazione(){
        Date data = new Date(2000-1900, 10-1, 3);

        Evento evento = new Evento("Evento di Prova 11","web/evento/immagini/eventoImage1","04:00:00","Giuseppe Verdi",data,"Giuseppe Verdi","Questo è un evento fittizio di prova",150,"memex99");

        Prenotazione prenotazione = new Prenotazione("lucia.gaeta.98@gmail.com", evento.getName(), evento.getDate());
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
        assertTrue(prenotazioneDAO.doSavePrenotazione(prenotazione));
    }
}
