package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.campo.entity.Campo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoControllerTest {
    private TorneoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    @BeforeAll
    void setUp() {
        servlet = new TorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
    }

    @Test
    void TC_2_11() {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        String message = "Lunghezza nome non valida";
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");
        Campo campo = new Campo();
        campo.setIdentificativo(1001);
        campo.setSport("Tennis");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        Mockito.when(mockedRequest.getSession().getAttribute("campo")).thenReturn(campo);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_2_12() {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("?<<Champions?F");
        String message = "Formato nome non valido";
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");
        Campo campo = new Campo();
        campo.setIdentificativo(1001);
        campo.setSport("Tennis");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        Mockito.when(mockedRequest.getSession().getAttribute("campo")).thenReturn(campo);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_2_13() {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions Five");
        String message = "Tipo non selezionato";
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");
        Campo campo = new Campo();
        campo.setIdentificativo(1001);
        campo.setSport("Tennis");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        Mockito.when(mockedRequest.getSession().getAttribute("campo")).thenReturn(campo);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_2_14() {
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Champions Five");
        Mockito.when(mockedRequest.getParameter("tipo")).thenReturn("Gironi");
        String message = "Struttura non selezionata";
        Admin admin = new Admin();
        admin.setNome("Emanuele");
        admin.setCognome("Mezzi");
        admin.setUsername("memex99");
        admin.setPassword("password");
        Campo campo = new Campo();
        campo.setIdentificativo(1001);
        campo.setSport("Tennis");
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        Mockito.when(mockedRequest.getSession().getAttribute("campo")).thenReturn(campo);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doGet(mockedRequest, mockedResponse));
        assertEquals(message, exception.getMessage());
    }
}