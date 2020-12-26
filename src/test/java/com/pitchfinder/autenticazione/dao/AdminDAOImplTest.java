package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.dao.AdminDAO;
import com.pitchfinder.autenticazione.dao.AdminDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDAOImplTest {

    /**
     * Method to test the checkAdmin method offered by AdminDAOImpl interface.
     * First case: username and password are correct.
     * Second case: username is wrong.
     */
    @Test
    void checkAdminTest() {

        Admin a = new Admin();
        AdminDAO ad = new AdminDAOImpl();

        a.setUsername("memex99");
        a.setPassword("esse3");
        assertNotNull(ad.checkAdmin(a));

        a.setUsername("memex98");
        a.setPassword("Yes99_55");
        assertNull(ad.checkAdmin(a));
    }
}
