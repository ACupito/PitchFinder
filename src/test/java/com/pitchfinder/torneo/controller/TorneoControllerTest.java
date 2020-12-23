package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.entity.Admin;
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

    @BeforeAll
    void setUp() {
        servlet = new TorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
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
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(mockedRequest.getSession()).thenReturn(session);
        Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(mockedRequest, mockedResponse);
        });
        assertEquals(message, exception.getMessage());
    }

}