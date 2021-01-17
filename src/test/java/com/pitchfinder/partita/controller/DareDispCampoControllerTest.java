package com.pitchfinder.partita.controller;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.sql.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DareDispCampoControllerTest {
    private DareDispCampoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;
    private final CampoDAO daoCampo = new CampoDAOImpl();
    private static final String ID_CAMPO = "1002";
    private static final String ORARIO_INIZIO = "15:30";
    private static final String ORARIO_FINE = "16:30";
    private static final String DATA = "2021-11-15";
    private static final String email = "manuzzisadad99@gmail.com";

    @BeforeAll
    void start() {
        //Servlet, mockedRequest, mockedResponse and Session instantiation.
        servlet = new DareDispCampoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);

        AutenticazioneService as3 = new AutenticazioneServiceImpl();
        Date d = new Date(1999 - 1900, 10, 4);
        as3.registraUtente(email, "Mar232323",
                "Mari", "Ros", "es23", d);
        Utente ut=as3.prelevaUtenteByEmail(email);
        //session setting.
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(ut);
    }
    @AfterAll
    void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        mockedDispatcher=null;
        mockedServletContext=null;
        session = null;

        AutenticazioneService as3 = new AutenticazioneServiceImpl();
        as3.removeUtente("Mar232323");
        //Remove Disponibilita
        daoCampo.doRemoveDisponibilita(email, Integer.parseInt(ID_CAMPO),Date.valueOf(DATA));

    }


    /**
     * Disponibilita's data is empity.
     */
    @Test
    void TC_31_1() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché la data non è stata selezionata.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's data is not valid.
     */
    @Test
    void TC_31_2() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("sadaf");
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché la data non rispetta il formato.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's inizio is empity.
     */
    @Test
    void TC_31_3() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché l’orario di inizio non è stato selezionato.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's inizio is not valid.
     */
    @Test
    void TC_31_4() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn("12:aaa000:0000");
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché il formato dell’orario di inizio non è valido.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's fine is empity.
     */
    @Test
    void TC_31_5() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché l’orario di inizio non è stato selezionato.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's fine is not valid.
     */
    @Test
    void TC_31_6() {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn("shdajkska");
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);


        String message = "Non viene memorizzata la disponibilità poiché il formato dell’orario di fine non è valido.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }
    /**
     * Disponibilita's creation is valid.
     */
    @Test
    void TC_31_7() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Conferma");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("inizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("fine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("idcampo")).thenReturn(ID_CAMPO);
        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/disponibilitaCampo/dareDisponibilita.jsp");
        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("La disponibilità viene memorizzata con successo.");

    }



}
