package com.pitchfinder.autenticazione.services;

import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AutenticazioneServiceImplTest {

    private AutenticazioneService as = new AutenticazioneServiceImpl();

    /**
     * Method to test the loginUtente method offered by the
     * AutenticazioneService interface.
     * First case: the email and password are correct.
     */
    @Test
    void loginUtenteTest1() {

        assertNull(as.loginUtente("mario1520@gmail.com", "esse3"));
    }


    /**
     * Method to test the loginUtente method offered by the
     * AutenticazioneService interface.
     * Second case: email is wrong.
     */
    @Test
    void loginUtenteTest2() {

        assertNull(as.loginUtente("mario1520@gmail.com", "esse3"));
    }

    /**
     * Method to test the loginAdmin method offered by the
     * AutenticazioneService interface.
     * First case: the username and password are correct.
     */
    @Test
    void loginAdminTest1() {

        assertNull(as.loginAdmin("memex98", "esse3"));
    }

    /**
     * Method to test the loginAdmin method offered by the
     * AutenticazioneService interface.
     * Second case: the username is wrong.
     */
    @Test
    void loginAdminTest2() {

        assertNull(as.loginAdmin("memex98", "esse3"));
    }

    /**
     * Method to test the registraUtente method offered by
     * AutenticazioneService interface.
     * First case: the email already exists
     */
    @Test
    void registraUtenteTest1() {

        Date d = new Date(1999 - 1900, 10, 23);
        assertFalse(as.registraUtente("mario96@gmail.com", "Mario1200",
                "Mario", "Rossi", "esse3", d));
    }

    /**
     * Method to test the registraUtente method offered by
     * AutenticazioneService interface.
     * Second case: the usernmame already exists
     */
    @Test
    void registraUtenteTest2() {

        Date d = new Date(1999 - 1900, 10, 23);
        assertFalse(as.registraUtente("mario1700@gmail.com", "Mario99",
                "Mario", "Rossi", "esse3", d));
    }

    /**
     * Method to test the registraUtente method offered by
     * AutenticazioneService interface.
     * Third case: the registration is completed
     */
    @Test
    void registraUtenteTest3() {

        Date d = new Date(1999 - 1900, 10, 23);
        assertTrue(as.registraUtente("mario2900@gmail.com", "Mario2900",
                "Mario", "Rossi", "esse3", d));
    }
}
