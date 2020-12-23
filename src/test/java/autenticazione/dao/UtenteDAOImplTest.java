package autenticazione.dao;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class UtenteDAOImplTest {

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * First case: the email and password are correct.
     * Second case: the email is wrong.
     */
    @Test
    void checkUtenteTest() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setEmail("mario97@gmail.com");
        a.setPassword("esse3");
        assertNotNull(ad.checkUtente(a));

        a = new Utente();

        a.setEmail("mario1300@gmail.com");
        a.setPassword("esse3");
        assertNull(ad.checkUtente(a));
    }

    /**
     * Method to test the doSaveUtente method offered by UtenteDAO interface.
     * First case: the e-mail already exists.
     * Second case: the username already exists.
     */
    @Test
    void doSaveUtenteTest() {

        Utente a = new Utente();
        Date d = new Date(1999 - 1900, 10, 4);
        UtenteDAO ad = new UtenteDAOImpl();

        a.setNome("Mario");
        a.setCognome("Rossi");
        a.setPassword("esse4");
        a.setDataDiNascita(d);

        a.setEmail("mario98@gmail.com");
        a.setUsername("Mario1200");
        assertFalse(ad.doSaveUtente(a));

        a.setEmail("mario1330@gmail.com");
        a.setUsername("Mario99");
        assertFalse(ad.doSaveUtente(a));
    }
}
