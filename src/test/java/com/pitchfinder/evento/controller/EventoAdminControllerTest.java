package com.pitchfinder.evento.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.evento.services.EventoService;
import com.pitchfinder.evento.services.EventoServiceImpl;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventoAdminControllerTest {

    private EventoAdminController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private Admin admin = new Admin();

    /**
     * Parameters declaration.
     */
    private static final String NOME = "NomeEvento";
    private static final String IMMAGINE = "src/main/webapp/images/events/imageTest.jpg";
    private static final String OSPITE = "Giuseppe Verdi";
    private static final String DESCRIZIONE = "Questo è un evento bello però fittizio";
    private static final String ORARIO_INIZIO = "15:30";
    private static final String ORARIO_FINE = "18:30";
    private static final String DATA = "2021-12-31";
    private static final String POSTI_DISPONIBILI = "150";

    /**
     *  Setting up the enviroment.
     */
    @BeforeAll
        void setUp() {

            //Servlet, mockedRequest, mockedResponse and Session instantiation.
            servlet = new EventoAdminController();
            mockedRequest = Mockito.mock(HttpServletRequest.class);
            mockedResponse = Mockito.mock(HttpServletResponse.class);
            session = Mockito.mock(HttpSession.class);



            //Admin creation for the session.
            admin.setNome("Paolo");
            admin.setCognome("DB");
            admin.setUsername("testAdmin05");
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
                //session setting.
                Mockito.when(mockedRequest.getSession()).thenReturn(session);
                Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);

    }

    /**
     * Evento's name is empity.
     */
    @Test
    void TC_11_1() {

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: lunghezza nome non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

        }

    /**
     * Evento's name is not valid.
     */
    @Test
    void TC_11_2() {

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Evento%%&&&£");
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's image is not valid.
     */
    @Test
    void TC_11_3(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("src/main/webapp/images/events/imageTest.gif");
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: Formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's name is highe than 2MB.
     */
    @Test
    void TC_11_4(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("src/main/webapp/images/events/hdPicture.jpg");
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: dimensione non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's starHour is empity.
     */
    @Test
    void TC_11_5(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: orario non selezionato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's startHour is not valid.
     */
    @Test
    void TC_11_6(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("03:78");
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's endHour is not empity.
     */
    @Test
    void TC_11_7(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: orario non selezionato";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's endHour is not valid.
     */
    @Test
    void TC_11_8(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("43:77");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's data is not empity.
     */
    @Test
    void TC_11_9(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: data non selezionata";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's data is not valid.
     */
    @Test
    void TC_11_10(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("2019-12-22");
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's guest is empity .
     */
    @Test
    void TC_11_11(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: lunghezza non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's guest is not valid.
     */
    @Test
    void TC_11_12(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giu€PP£");
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's description is empity.
     */
    @Test
    void TC_11_13(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: lunghezza non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's description is not valid.
     */
    @Test
    void TC_11_14(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("#@Descrizione <> Evento");
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Evento's available_sits is empity.
     */
    @Test
    void TC_11_15(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn("");

        String message = "Errato: lunghezza non valida";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());

    }

    /**
     * Evento's available_sits is not valid.
     */
    @Test
    void TC_11_16(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn("@");

        String message = "Errato: formato non valido";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Everything is working fine! Good job.
     */
    @Test
    void TC_11_17() {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("immagine")).thenReturn(IMMAGINE);
        Mockito.when(mockedRequest.getParameter("ospite")).thenReturn(OSPITE);
        Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn(DESCRIZIONE);
        Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn(ORARIO_INIZIO);
        Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn(ORARIO_FINE);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);
        Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(POSTI_DISPONIBILI);

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Creazione avvenuta");


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
        EventoService es = new EventoServiceImpl();
        Evento evento = new Evento();
        evento.setName(NOME);
        evento.setImage(IMMAGINE);
        evento.setDate(Date.valueOf(DATA));
        evento.setStartHour(Time.valueOf(ORARIO_INIZIO.concat(":00")));
        evento.setEndHour(Time.valueOf(ORARIO_FINE.concat(":00")));
        evento.setGuest(OSPITE);
        evento.setDescription(DESCRIZIONE);
        evento.setAvailableSits(Integer.parseInt(POSTI_DISPONIBILI));
        evento.setAdmin(admin.getUsername());
        es.removeEvento(evento);

        admin.setNome("Paolo");
        admin.setCognome("DB");
        admin.setUsername("testAdmin05");
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