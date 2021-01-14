package com.pitchfinder.partita.controller;


import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.singleton.ConPool;
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
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreazionePartitaControllerTest{
    private CreazionePartitaController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;
    private HttpSession session;
    private UtenteDAO utenteTest;
    private CampoDAO daoCampo = new CampoDAOImpl();

    private Utente userTest;
    private static final String BTN_VALUE = "Conferma";
    private static final String ID_CAMPO = "1002";
    private static final String ORARIO_INIZIO = "15:30";
    private static final String ORARIO_FINE = "16:30";
    private static final String DATA = "2021-11-15";
    private static final String MAXGIOCATORI = "1";
    private static final String PLAYER_NAME = "Pippo";
    private static final String PLAYER_SURNAME = "Baudo";

    @BeforeAll
    void start(){
        //Servlet, mockedRequest, mockedResponse and Session instantiation ecc...
        servlet = new CreazionePartitaController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        session = Mockito.mock(HttpSession.class);

        //User creation for testing
        userTest = new Utente();
        userTest.setEmail("test99@gmail.com");
        userTest.setUsername("Testing");
        userTest.setNome("Test");
        userTest.setCognome("De Test");
        userTest.setPassword("Testing99@");
        userTest.setDataDiNascita(Date.valueOf("1999-10-10"));
        utenteTest = new UtenteDAOImpl();
        utenteTest.doSaveUtente(userTest);

        //Admin creation for TC_matchCreateError()
        Admin admin = new Admin();
        admin.setNome("Eugenio");
        admin.setCognome("De Simone");
        admin.setUsername("eugenio123");
        admin.setPassword("password");

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Admin (Nome, Cognome, Username, Password) VALUES(?,?,?,?)");
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getCognome());
            ps.setString(3, admin.getUsername());
            ps.setString(4, admin.getPasswordHash());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Adding user (Session)
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(userTest);
    }

    /**
     * Utente == null
     */
    @Test
    void TC_userNull(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(null);

        String message = "Utente non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

        Mockito.when(mockedRequest.getSession().getAttribute("utente")).thenReturn(userTest);
    }

    /**
     * idCampo == null
     */
    @Test
    void TC_campoNull(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Campo non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * idCampo ==  ""
     */
    @Test
    void TC_campoEmpty(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Campo non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Impossible Parse for idCampo
     */
    @Test
    void TC_campoNotInt(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn("ABC");
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Campo non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Impossible valueof() for date
     */
    @Test
    void TC_dateValueOf(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn("10-10-10");
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Formato Data non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Impossible date.before() for date
     */
    @Test
    void TC_dateBefore(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn("2010-10-10");
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Formato Data non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid start time for the match
     */
    @Test
    void TC_startInvalid(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn("25:72");
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Formato Orario di Inizio non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
    /**
     * Invalid end time for the match
     */
    @Test
    void TC_endInvalid(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn("25:72");
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Formato Orario di Fine non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid time for the match
     */
    @Test
    void TC_invalidMatchTime(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn("15:00");
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Formato Orario partita non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid time for the match, too long, first case
     */
    @Test
    void TC_tooLongMatchTime1(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn("19:00");
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Durata partita troppo lunga";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid time for the match, too long, second case
     */
    @Test
    void TC_tooLongMatchTime2(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn("17:40");
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Durata partita troppo lunga";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid number of players, null object
     */
    @Test
    void TC_invalidNPlayers1(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Numero massimo giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid number of players, empty String
     */
    @Test
    void TC_invalidNPlayers2(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Numero massimo giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid number of players, impossible parse String
     */
    @Test
    void TC_invalidNPlayers3(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn("AbC");
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Numero massimo giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid number of players max>3
     */
    @Test
    void TC_invalidNPlayers4(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn("10");
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Numero massimo giocatori non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid name of players , null object
     */
    @Test
    void TC_nullPlayerNamer(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid name of players , empty String
     */
    @Test
    void TC_emptyPlayerName(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid name of players , invalid name format
     */
    @Test
    void TC_formatPlayerName(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn("Luc1a");
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid name of players , too long
     */
    @Test
    void TC_lengthPlayerName1(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn("UnoDueTreQuattroCinque");
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid name of players , too short
     */
    @Test
    void TC_lengthPlayerName2(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn("L");
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        String message = "Nome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid surname of players , null object
     */
    @Test
    void TC_nullPlayerSurname(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(null);

        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid surname of players , empty String
     */
    @Test
    void TC_emptyPlayerSurname(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn("");

        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid surname of players , invalid name format
     */
    @Test
    void TC_formatPlayerSurname(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn("Ga3ta");

        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid surname of players , too long
     */
    @Test
    void TC_lengthPlayerSurname1(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn("UnoDueTreQuattroCinque");

        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Invalid surname of players , too short
     */
    @Test
    void TC_lengthPlayerSurname2(){
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn("G");

        String message = "Cognome giocatore non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Perfect, match created succesfully!
     */
    @Test
    void TC_matchCreated() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(ID_CAMPO);
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/partita/matchCreation.jsp");

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Creazione avvenuta!");
    }

    /**
     * Ops, match already exists!
     */
    @Test
    void TC_matchCreateError() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn(BTN_VALUE);
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn("1003");
        Mockito.when(mockedRequest.getParameter("date")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("start")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("end")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("maxGiocatori")).thenReturn(MAXGIOCATORI);
        Mockito.when(mockedRequest.getParameter("nameG1")).thenReturn(PLAYER_NAME);
        Mockito.when(mockedRequest.getParameter("surnameG1")).thenReturn(PLAYER_SURNAME);


        daoCampo.doSaveOccupazione(1003,
                Date.valueOf(DATA), Time.valueOf(ORARIO_INIZIO.concat(":00")),
                Time.valueOf(ORARIO_FINE.concat(":00")),"eugenio123");

        String message = "Impossibile creare una partita!";

        IllegalArgumentException exception;

        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Selected button.equals("Conferma") == null
     */
    @Test
    void TC_cancelButton() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("btnMatchCreation")).thenReturn("Annulla");

        String message = "Operazione annullata!";

        IllegalArgumentException exception;

        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }


    @AfterAll
    void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;
        //Remove Utente
        utenteTest.doRemoveUtente(userTest);

        //Remove Occupazione
        daoCampo.doRemoveOccupazione(Integer.parseInt(ID_CAMPO),
                Date.valueOf(DATA), Time.valueOf(ORARIO_INIZIO.concat(":00")),
                Time.valueOf(ORARIO_FINE.concat(":00")));
        daoCampo.doRemoveOccupazione(1003,
                Date.valueOf(DATA), Time.valueOf(ORARIO_INIZIO.concat(":00")),
                Time.valueOf(ORARIO_FINE.concat(":00")));
        //Remove Admin
        Admin admin = new Admin();
        admin.setNome("Eugenio");
        admin.setCognome("De Simone");
        admin.setUsername("eugenio123");
        admin.setPassword("password");

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Admin where username=? && password=?");

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPasswordHash());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
