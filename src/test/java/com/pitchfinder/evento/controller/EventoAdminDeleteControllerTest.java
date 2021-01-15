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
public class EventoAdminDeleteControllerTest {

    private EventoAdminDeleteController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;
    private HttpSession session;
    private Admin admin = new Admin();

    /**
     * Parameters declaration.
     */
    private static final String NOME = "EventoTennis";
    private static final String IMMAGINE = "default";
    private static final String ORARIO_INIZIO = "10:00";
    private static final String ORARIO_FINE = "12:00";
    private static final String DATA = "2021-12-31";
    private static final String OSPITE = "Mariani Fittipaldi";
    private static final String DESCRIZIONE = "Evento per aggiornare gli interessati su nuove regole arbitrali";
    private static final String POSTI_DISPONIBILI = "150";

    /**
     *  Setting up the enviroment.
     */
    @BeforeAll
    void setUp() {

        //Servlet, mockedRequest, mockedResponse and Session instantiation.
        servlet = new EventoAdminDeleteController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
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
    void TC_11_16() {

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);

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
    void TC_11_17() {

        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Evento%%&&&£");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);

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
    void TC_11_18(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("");

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
    void TC_11_19(){
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("2019-12-22");

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
    void TC_11_20() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(DATA);

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/autentication?flag=5");

        servlet.doPost(mockedRequest, mockedResponse);
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
        mockedServletContext = null;
        mockedDispatcher = null;
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