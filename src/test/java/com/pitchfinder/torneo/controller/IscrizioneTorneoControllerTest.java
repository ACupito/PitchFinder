package com.pitchfinder.torneo.controller;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.evento.controller.PrenotazioneEventoController;
import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.torneo.dao.TorneoDAO;
import com.pitchfinder.torneo.dao.TorneoDAOImpl;
import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IscrizioneTorneoControllerTest {

    private IscrizioneTorneoController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession session;
    private Utente utente;
    private Torneo torneo;
    private TorneoDAO torneoDAO = new TorneoDAOImpl();
    private UtenteDAO utenteDAO = new UtenteDAOImpl();

    @BeforeAll
    public void start(){
        servlet = new IscrizioneTorneoController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        //Create tournament
        torneo = new Torneo("serieA", "tipo", "gironi", "lunedi", "memex99", 20, 10, 12, Date.valueOf("2022-12-10"),  Date.valueOf("2023-12-10"), 1002);
        torneoDAO.doSaveTorneo(torneo);

        Mockito.doReturn(session).when(mockedRequest).getSession();
        Mockito.doReturn(torneo).when(session).getAttribute("torneo");
    }
}
