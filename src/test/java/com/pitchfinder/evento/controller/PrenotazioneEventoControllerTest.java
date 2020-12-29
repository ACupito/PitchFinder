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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrenotazioneEventoControllerTest {

    private PrenotazioneEventoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private Admin admin;
    private Evento evento;
    private EventoDAO eventoDAO = new EventoDAOImpl();

    @BeforeAll
    public void start(){
        servlet = new PrenotazioneEventoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

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
        Mockito.doReturn(session).when(mockedRequest).getSession();
        Mockito.doReturn(evento).when(session).getAttribute("evento");
    }
    @Test
    public void TC_1_2_1(){

        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        String message = "Lunghezza email non corretta";

        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class,
                () -> servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    /**@Test
    public void TC_1_2_2(){

    }

    @Test
    public void TC_1_2_3(){

    }
*/
    @AfterAll
    public void clean(){
        servlet = null;
        mockedRequest = null;
        mockedResponse = null;
        session = null;

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


}}
