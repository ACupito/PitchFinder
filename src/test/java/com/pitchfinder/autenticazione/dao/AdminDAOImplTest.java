package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDAOImplTest {

    /**
     * Method to test the checkAdmin method offered by AdminDAOImpl interface.
     * First case: username and password are correct.
     */
    @Test
    void checkAdminTest1() {

        Admin a = new Admin();
        AdminDAO ad = new AdminDAOImpl();

        a.setUsername("memex99");
        a.setPassword("esse3");
        assertNotNull(ad.checkAdmin(a));
    }

    /**
     * Method to test the checkAdmin method offered by AdminDAOImpl interface.
     * Second case: username is wrong.
     */
    @Test
    void checkAdminTest2() {

        Admin a = new Admin();
        AdminDAO ad = new AdminDAOImpl();

        a.setUsername("memex98");
        a.setPassword("Yes99_55");
        assertNull(ad.checkAdmin(a));
    }

    /**
     * Method to test the checkAdmin method offered by AdminDAOImpl interface.
     * Third case: password is wrong.
     */
    @Test
    void checkAdminTest3() {

        Admin a = new Admin();
        AdminDAO ad = new AdminDAOImpl();

        a.setUsername("memex99");
        a.setPassword("SprikkePerugina98");

        String message = "Il login non va a buon " +
                "fine perché la password inserita non " +
                "è corretta";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    ad.checkAdmin(a);
                });

        assertEquals(message, exception.getMessage());
    }
}
