package com.pitchfinder.partita.controller;


import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModificaDispCampoControllerTest {
    private ModificaDispCampoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private CampoDAO daoCampo = new CampoDAOImpl();
    private static final String ID_CAMPO = "1002";
    private static final String ORARIO_INIZIO = "15:30";
    private static final String ORARIO_FINE = "16:30";
    private static final String DATA = "2021-11-15";

    @BeforeAll
    void start() {
        //Servlet, mockedRequest, mockedResponse and Session instantiation.
        servlet = new ModificaDispCampoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");

        //session setting.
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
    }
    @AfterAll
    void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;


        //Remove Occupazione
        daoCampo.doRemoveOccupazione(Integer.parseInt(ID_CAMPO),
                Date.valueOf(DATA), Time.valueOf(ORARIO_INIZIO.concat(":00")),
                Time.valueOf(ORARIO_FINE.concat(":00")));
    }

    /**
     * Occupazione's data is empity.
     */
    @Test
    void TC_52_1() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché la data non è selezionata";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's data is not valid.
     */
    @Test
    void TC_52_2() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("sadaf");
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché la data non rispetta il formato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's inizio is empity.
     */
    @Test
    void TC_52_3() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché l’orario di inizio non è stato selezionato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's inizio is not valid.
     */
    @Test
    void TC_52_4() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn("12:000:0000");
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché l’orario di inizio non rispetta il formato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's fine is empity.
     */
    @Test
    void TC_52_5() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché l’orario di fine non è stato selezionato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's fine is not valid.
     */
    @Test
    void TC_52_6() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn("shdajkska");
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "La modifica fallisce perché l’orario di fine non rispetta il formato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Occupazione's creation is valid.
     */
    @Test
    void TC_52_7() {
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("La modifica va a buon fine");

    }
}
