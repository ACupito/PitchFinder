package com.pitchfinder.autenticazione.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AutenticazioneServiceImplTest {

    private AutenticazioneService as = new AutenticazioneServiceImpl();

    /**
     * Start
     */
    @BeforeAll
    static void start() {

        Date d = new Date(1999 - 1900, 10, 4);

        AutenticazioneService as1 = new AutenticazioneServiceImpl();

        as1.registraUtente("mario129@gmail.com", "Mariox129",
                "Mario", "Rossi", "esse3", d);
        as1.registraUtente("mario130@gmail.com", "Mariox130",
                "Mario", "Rossi", "esse3", d);
    }

    /**
     * Method to test the loginUtente method offered by the
     * AutenticazioneService interface.
     * First case: username and password are correct.
     */
    @Test
    void loginUtenteTest1() {

        assertNotNull(as.loginUtente("Mariox129", "esse3"));
    }


    /**
     * Method to test the loginUtente method offered by the
     * AutenticazioneService interface.
     * Second case: username is wrong.
     */
    @Test
    void loginUtenteTest2() {

        assertNull(as.loginUtente("Mario2300", "esse3"));
    }

    /**
     * Method to test the loginUtente method offered by the
     * AutenticazioneService interface.
     * Third case: password is wrong.
     */
    @Test
    void loginUtenteTest3() {

        String message = "Il login non va a buon " +
                "fine perché la password inserita non " +
                "è corretta";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    as.loginUtente("Mariox129", "PitchFinder 57");
                });

        assertEquals(message, exception.getMessage());
    }

    /**
     * Method to test the loginAdmin method offered by the
     * AutenticazioneService interface.
     * First case: the username and password are correct.
     */
    @Test
    void loginAdminTest1() {

        assertNotNull(as.loginAdmin("memex99", "esse3"));
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
     * Method to test the loginAdmin method offered by the
     * AutenticazioneService interface.
     * Second case: password is wrong.
     */
    @Test
    void loginAdminTest3() {

        String message = "Il login non va a buon " +
                "fine perché la password inserita non " +
                "è corretta";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    as.loginUtente("memex99", "PitchFinder 57");
                });

        assertEquals(message, exception.getMessage());
    }

    /**
     * Method to test the registraUtente method offered by
     * AutenticazioneService interface.
     * First case: the email already exists
     */
    @Test
    void registraUtenteTest1() {

        Date d = new Date(1999 - 1900, 10, 23);
        assertFalse(as.registraUtente("mario129@gmail.com", "Mario1200",
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
        assertFalse(as.registraUtente("mario1700@gmail.com", "Mariox129",
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

    /**
     * Method to test prelevaUtenteByEmail
     * First case: the user is retrieved
     */
    @Test
    void prelevaUtenteByEmailTest1() {

        assertNotNull(as.prelevaUtenteByEmail("mario129@gmail.com"));
    }

    /**
     * Method to test prelevaUtenteByEmail
     * Second case: the user is not retrieved
     */
    @Test
    void prelevaUtenteByEmailTest2() {

        assertNull(as.prelevaUtenteByEmail("sprikkesprakke99@gmail.com"));
    }

    /**
     * After All
     */
    @AfterAll
    static void remove() {

        AutenticazioneService as1 = new AutenticazioneServiceImpl();
        as1.removeUtente("Mariox129");
        as1.removeUtente("Mariox130");
        as1.removeUtente("Mario2900");
    }
}
