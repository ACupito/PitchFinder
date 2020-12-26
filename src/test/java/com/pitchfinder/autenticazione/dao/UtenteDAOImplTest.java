package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class UtenteDAOImplTest {

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * First case: the email and password are correct.
     */
    @Test
    void checkUtenteTest1() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setEmail("mario97@gmail.com");
        a.setPassword("esse3");
        assertNotNull(ad.checkUtente(a));
    }

    /**
     * Method to test the checkAdmin method offered by UtenteDAO interface.
     * Second case: the email is wrong.
     */
    @Test
    void checkUtenteTest2() {

        Utente a = new Utente();
        UtenteDAO ad = new UtenteDAOImpl();

        a.setEmail("mario1300@gmail.com");
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

        a.setEmail("mario99@gmail.com");
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
}
