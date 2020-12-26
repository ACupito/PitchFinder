package com.pitchfinder.campo.controller;


import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.campo.entity.Campo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.Time;



/**
 *  This class manages the test for CampoDAOImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CampoDAOImplTest {

    /**
     *  This is an instance of CampoDAO.
     */
    private CampoDAO cdao;

    /**
     * Parameters declaration.
     */
    private static final String USERNAME_ADMIN = "memex99";
    private static final int ID_CAMPO = 1002;
    private static final String DATA = "2020-10-10";
    private static final String TEMPO_INIZIO = "10:00";
    private static final String TEMPO_FINE = "11:00";

    /**
     * This is an instance of Campo entity.
     */
    private Campo c;

    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    public void setUp() {

        cdao = new CampoDAOImpl();
        c = new Campo();

    }

    /**
     * This method tests the method doSaveOccupazione.
     * In case of success.
     */
    @Test
    void doSaveOccupazioneTest() {

        assertFalse(cdao.doSaveOccupazione(ID_CAMPO, Date.valueOf(DATA), Time.valueOf(TEMPO_INIZIO.concat(":00")), Time.valueOf(TEMPO_FINE.concat(":00")), USERNAME_ADMIN));

    }
    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {
        cdao = null;
        c = null;
    }
}
