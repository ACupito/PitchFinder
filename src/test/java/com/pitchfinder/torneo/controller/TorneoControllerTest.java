package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.torneo.dao.TorneoDAO;
import com.pitchfinder.torneo.dao.TorneoDAOImpl;
import com.pitchfinder.torneo.entity.Torneo;
import com.pitchfinder.torneo.services.TorneoService;
import com.pitchfinder.torneo.services.TorneoServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoControllerTest {

    /**
     * object instances: TorneoController, HttpServletRequest, HttpServletResponse,
     * HttpSession.
     */
    private TorneoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;

    /**
     * Parameters declaration.
     */
    private static final String NOME = "Champions Five";
    private static final String SPORT = "Tennis";
    private static final String TIPO = "Eliminazione diretta";
    private static final String STRUTTURA = "Partite singole";
    private static final String DATA_INIZIO = "2021-12-5";
    private static final String DATA_FINE = "2021-12-15";
    private static final String GIORNO_PARTITE = "Giovedì";
    private static final String MAX_SQUADRE = "12";
    private static final String MIN_PARTECIPANTI = "1";
    private static final String MAX_PARTECIPANTI = "5";
    private static final int ID_CAMPO = 1002;

    /**
     * This method setup the enviroment.
     */
    @BeforeAll
    void setUp() {

        //instantiation servlet, mockedRequest, mockedResponse and session.
        servlet = new TorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);

        //Admin creation for the session.
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");


        //session setting.
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);

        //idCampo setting.
        Mockito.when(mockedRequest.getParameter("idCampo")).thenReturn(String.valueOf(ID_CAMPO));

        //creation instance for tests remove tournament.
        Torneo t = new Torneo("Champions", TIPO, STRUTTURA, "Sabato", "memex99", Integer.parseInt(MAX_SQUADRE),
                Integer.parseInt(MIN_PARTECIPANTI), Integer.parseInt(MAX_PARTECIPANTI),
                Date.valueOf("2022-10-10"), Date.valueOf("2022-10-30"), ID_CAMPO);
        TorneoDAO tdao = new TorneoDAOImpl();
        tdao.doSaveTorneo(t);

    }

    /**
     * Test case: TC_21_1 -> Lunghezza nome non valida.
     */
    @Test
    void TC_21_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Lunghezza nome non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_2 -> Formato nome non valido.
     */
    @Test
    void TC_21_2() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("?<<Champions?F");
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato nome non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_3 -> Sport non selezionato.
     */
    @Test
    void TC_21_3() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Sport non selezionato";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_4 -> Tipo non selezionato.
     */
    @Test
    void TC_21_4() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Tipo non selezionato";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_5 -> Struttura non selezionata.
     */
    @Test
    void TC_21_5() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Struttura non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_6 -> Data inizio non selezionata.
     */
    @Test
    void TC_21_6() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Data inizio non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_7 -> Formato data inizio non valido.
     */
    @Test
    void TC_21_7() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("5/13/1980");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato data inizio non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_8 -> Data fine non selezionata.
     */
    @Test
    void TC_21_8() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Data fine non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_9 -> Formata data fine non valido.
     */
    @Test
     void TC_21_9() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("1970-13-10");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato data fine non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_10 -> Lunghezza giorno partite non valida.
     */
    @Test
    void TC_21_10() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Lunghezza giorno partite non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_11 -> Formato giorno partite non valido.
     */
    @Test
    void TC_21_11() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("<<>/Giove/dì");
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato giorno partite non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_10 -> Formato giorno partite non valido.
     */
    @Test
    void TC_21_12() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn("n");
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato numero squadre non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_13 -> Numero di squadre non valido.
     */
    @Test
    void TC_21_13() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn("51");
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Numero di squadre non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_14 -> Formato minimo partecipanti non valido.
     */
    @Test
    void TC_21_14() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn("an");
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Formato minimo partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_15 -> Numero minimo di partecipanti non valido.
     */
    @Test
    void TC_21_15() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn("0");
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        String message = "Numero minimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_16 -> Numero massimo di partecipanti non valido.
     */
    @Test
    void TC_21_16() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn("aap");

        String message = "Numero massimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test case: TC_21_17 -> Numero massimo di partecipanti non valido.
     */
    @Test
    void TC_21_17() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn("20");

        String message = "Numero massimo di partecipanti non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Test case: TC_21_18 -> Dati corretti - Creazione torneo avvenuta.
     */
    @Test
    void TC_21_18() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("sport")).thenReturn(SPORT);
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn(TIPO);
        Mockito.when(mockedRequest.getParameter("struttura")).thenReturn(STRUTTURA);
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);
        Mockito.when(mockedRequest.getParameter("maxSquadre")).thenReturn(MAX_SQUADRE);
        Mockito.when(mockedRequest.getParameter("minPartecipanti")).thenReturn(MIN_PARTECIPANTI);
        Mockito.when(mockedRequest.getParameter("maxPartecipanti")).thenReturn(MAX_PARTECIPANTI);

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/autentication?flag=5");

        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Creazione avvenuta");

    }

    /**
     * This method tests the method for obtaining all tournaments.
     */
    @Test
    void CheckTest_19() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("3");

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/torneo/visualizzaTornei.jsp");

        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Tornei ottenuti");

    }

    /**
     * This method tests the method of obtaining a tournament -> Lunghezza nome non valida.
     */
    @Test
    void CheckTest_20() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("4");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);

        String message = "Lunghezza nome non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method of obtaining a tournament -> Formato nome non valido.
     */
    @Test
    void CheckTest_21() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("4");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("<<Jss>>??");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);

        String message = "Formato nome non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method of obtaining a tournament -> Data inizio non selezionata.
     */
    @Test
    void CheckTest_22() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("4");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(null);

        String message = "Data inizio non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method of obtaining a tournament -> Formato data inizio non valida.
     */
    @Test
    void CheckTest_23() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("4");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("5/13/1980");

        String message = "Formato data inizio non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method of obtaining a tournament -> Corretto.
     */
    @Test
    void CheckTest_24() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("4");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("2022-10-30");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("Sabato");

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/torneo/dettagliTorneo.jsp");

        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Torneo ottenuto");

    }

    /**
     * This method tests the method for removing a tournament -> Lunghezza nome non valida.
     */
    @Test
    void CheckTest_25() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Lunghezza nome non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Formato nome non valido.
     */
    @Test
    void CheckTest_26() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("<<Jss>>??");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(DATA_INIZIO);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Formato nome non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Data inizio non selezionata.
     */
    @Test
    void CheckTest_27() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Data inizio non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Formato data inizio non valido.
     */
    @Test
    void CheckTest_28() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("5/13/1980");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(DATA_FINE);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Formato data inizio non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Data fine non selezionata.
     */
    @Test
    void CheckTest_29() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn(null);
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Data fine non selezionata";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Formato data fine non valido.
     */
    @Test
    void CheckTest_30() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("10/13/1980");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn(GIORNO_PARTITE);

        String message = "Formato data fine non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Lunghezza giorno partite non valida.
     */
    @Test
    void CheckTest_31() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("2022-10-30");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("");

        String message = "Lunghezza giorno partite non valida";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Formato giorno partite non valido.
     */
    @Test
    void CheckTest_32() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("2022-10-30");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("<<??Sab?<>");

        String message = "Formato giorno partite non valido";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doPost(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * This method tests the method for removing a tournament -> Corretto.
     */
    @Test
    void CheckTest_33() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions");
        Mockito.when(mockedRequest.getParameter("dataInizio")).thenReturn("2022-10-10");
        Mockito.when(mockedRequest.getParameter("dataFine")).thenReturn("2022-10-30");
        Mockito.when(mockedRequest.getParameter("giornoPartite")).thenReturn("Sabato");

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/autentication?flag=5");


        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Eliminazione avvenuta");

    }


    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {

        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;
        TorneoService ts = new TorneoServiceImpl();
        ts.deleteTorneo(ID_CAMPO, NOME, Date.valueOf(DATA_INIZIO), Date.valueOf(DATA_FINE), GIORNO_PARTITE);

    }
}