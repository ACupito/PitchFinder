package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class UtenteDAOImplTest {

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * First case: the username and password are correct.
     */
    @Test
    void checkUtenteTest1() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setUsername("Mariox97");
        a.setPassword("esse3");
        assertNotNull(ad.checkUtente(a));
    }

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * Second case: the username is wrong.
     */
    @Test
    void checkUtenteTest2() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setUsername("Mariox29");
        a.setPassword("esse3");
        assertNull(ad.checkUtente(a));
    }

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * Third case: the password is wrong.
     */
    @Test
    void checkUtenteTest3() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setUsername("Mario99");
        a.setPassword("PitchFinder 57");

        String message = "Il login non va a buon " +
                "fine perché la password inserita non " +
                "è corretta";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    ad.checkUtente(a);
                });

        assertEquals(message, exception.getMessage());

    }

    /**
     * Method to test the doSaveUtente method offered by UtenteDAO interface.
     * First case: the e-mail already exists.
     */
    @Test
    void doSaveUtenteTest1() {

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
    }


    /**
     * Method to test the doSaveUtente method offered by UtenteDAO interface.
     * Second case: the username already exists.
     */
    @Test
    void doSaveUtenteTest2() {

        Utente a = new Utente();
        Date d = new Date(1999 - 1900, 10, 4);
        UtenteDAO ad = new UtenteDAOImpl();

        a.setNome("Mario");
        a.setCognome("Rossi");
        a.setPassword("esse4");
        a.setDataDiNascita(d);

        a.setEmail("mario1330@gmail.com");
        a.setUsername("Mario99");
        assertFalse(ad.doSaveUtente(a));
    }

    /**
     * Method to test the doSaveUtente method offered by UtenteDAO interface.
     * Third case: the user is saved in the database .
     */
    @Test
    void doSaveUtenteTest3() {

        Utente a = new Utente();
        Date d = new Date(1999 - 1900, 10, 4);
        UtenteDAO ad = new UtenteDAOImpl();

        a.setNome("Mario");
        a.setCognome("Rossi");
        a.setPassword("esse4");
        a.setDataDiNascita(d);

        a.setEmail("mario3330@gmail.com");
        a.setUsername("Mario3330");
        assertTrue(ad.doSaveUtente(a));
    }

    /**
     * Method to retrieve a user given his email
     * First case: the user exists
     */
    @Test
    void doRetrieveUtenteByEmailTest1() {

        String email = "mario99@gmail.com";

        Utente u = new Utente();
        u.setEmail(email);

        UtenteDAO ad = new UtenteDAOImpl();

        assertNotNull(ad.doRetrieveUtenteByEmail(u));
    }

    /**
     * Method to retrieve a user given his email
     * Second case: the user does not exists
     */
    @Test
    void doRetrieveUtenteByEmailTest2() {

        String email = "sprikkesprakke99@gmail.com";

        Utente u = new Utente();
        u.setEmail(email);

        UtenteDAO ad = new UtenteDAOImpl();

        assertNull(ad.doRetrieveUtenteByEmail(u));
    }
}
