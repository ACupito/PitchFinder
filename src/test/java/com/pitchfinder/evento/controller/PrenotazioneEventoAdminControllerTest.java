package com.pitchfinder.evento.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
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
public class PrenotazioneEventoAdminControllerTest {

    private PrenotazioneEventoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;

    private Admin admin;
    private Evento evento;
    private EventoDAO eventoDAO = new EventoDAOImpl();

    @BeforeAll
    public void start(){
        servlet = new PrenotazioneEventoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);


        //Create admin
        admin = new Admin("admin", "lucia", "gaeta", "ciao");
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

        //Create evento
        evento = new Evento("NomeEvento", "immagine", Time.valueOf("13:00:00"), Time.valueOf("17:00:00"), Date.valueOf("2021-12-11"), "Lucia", "Descrizione", 100, admin.getUsername());
        eventoDAO.doSaveEvento(evento);

    }

    /**
     *Test case: the lenght of email isn't valid.
     */
    @Test
    public void TC_1_2_1(){
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Prenotati!");
        Mockito.when(mockedRequest.getParameter("eventDate")).thenReturn("2021-12-11");
        Mockito.when(mockedRequest.getParameter("eventName")).thenReturn("NomeEvento");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        String message = "La prenotazione all’evento non va a buon fine la lunghezza dell’email non è valida.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test case: The format of email isn't valid.
     */
    @Test
    public void TC_1_2_2(){
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Prenotati!");
        Mockito.when(mockedRequest.getParameter("eventDate")).thenReturn("2021-12-11");
        Mockito.when(mockedRequest.getParameter("eventName")).thenReturn("NomeEvento");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("c.Salvat!!@jwi.com");
        String message = "La prenotazione all’evento non va a buon fine il formato dell’email non è valido.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test case: the booking is correctly saved.
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_1_2_3() throws ServletException, IOException {
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Prenotati!");
        Mockito.when(mockedRequest.getParameter("eventDate")).thenReturn("2021-12-11");
        Mockito.when(mockedRequest.getParameter("eventName")).thenReturn("NomeEvento");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("c.Salvato@gmail.com");
        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/evento/prenotazioneEvento.jsp");

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("La prenotazione all’evento va a buon fine.");

    }

    /**
     * Test case: the email is null.
     */
    @Test
    public void emailNULL(){
        Mockito.when(mockedRequest.getParameter("Conferma")).thenReturn("Prenotati!");
        Mockito.when(mockedRequest.getParameter("eventDate")).thenReturn("2021-12-11");
        Mockito.when(mockedRequest.getParameter("eventName")).thenReturn("NomeEvento");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(null);
        String message = "Email non valida.";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @AfterAll
    public void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;


        //remove evento
        eventoDAO.doRemoveEvento(evento);

        //remove admin
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM admin where Username = ?");
            ps.setString(1, admin.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //remove prenotazione
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM prenotazione where UtenteEmail = ?");
            ps.setString(1, "c.Salvato@gmail.com");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


}}
