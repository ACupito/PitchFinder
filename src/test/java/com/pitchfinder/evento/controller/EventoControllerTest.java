package com.pitchfinder.evento.controller;

import com.pitchfinder.autenticazione.entity.Admin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventoControllerTest {

        private EventoController servlet;
        private HttpServletRequest mockedRequest;
        private HttpServletResponse mockedResponse;
        private HttpSession session;
        @BeforeAll
        void setUp() {
            servlet = new EventoController();
            mockedRequest = Mockito.mock(HttpServletRequest.class);
            mockedResponse = Mockito.mock(HttpServletResponse.class);
            session = Mockito.mock(HttpSession.class);
            Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        }

        /**
         * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
         * All the parameters are correct.
         */
        @Test
        void TC_11_1() {

            Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
            Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("images/events/eventoImage1");
            Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
            Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
            Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("15:30");
            Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
            Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
            Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
            String message = "Errato: lunghezza nome non valida";
            Admin admin = new Admin();
            admin.setNome("Emanuele");
            admin.setCognome("Mezzi");
            admin.setUsername("memex99");
            admin.setPassword("password");
            Mockito.when(mockedRequest.getSession()).thenReturn(session);
            Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                servlet.doGet(mockedRequest, mockedResponse);
            });
            assertEquals(message, exception.getMessage());

            }

        /**
         * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
         * Start Hour is incorrect
         */
        @Test
        void TC_11_2() {

            Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Evento%%&&&£");
            Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("images/events/eventoImage1");
            Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
            Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
            Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("15:30");
            Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
            Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
            Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
            String message = "Errato: formato non valido";
            Admin admin = new Admin();
            admin.setNome("Emanuele");
            admin.setCognome("Mezzi");
            admin.setUsername("memex99");
            admin.setPassword("password");
            Mockito.when(mockedRequest.getSession()).thenReturn(session);
            Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                servlet.doGet(mockedRequest, mockedResponse);
            });
            assertEquals(message, exception.getMessage());

        }

        @Test
        void TC_11_3(){
            Mockito.when(mockedRequest.getParameter("nome")).thenReturn("NomeEvento");
            Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("images/events/eventoImage1.jpg");
            Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
            Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
            Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("15:30");
            Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
            Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
            Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
            String message = "Formato non valido";
            Admin admin = new Admin();
            admin.setNome("Emanuele");
            admin.setCognome("Mezzi");
            admin.setUsername("memex99");
            admin.setPassword("password");
            Mockito.when(mockedRequest.getSession()).thenReturn(session);
            Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                servlet.doGet(mockedRequest, mockedResponse);
            });
            assertEquals(message, exception.getMessage());
        }

        @Test
        void TC_11_4(){
            Mockito.when(mockedRequest.getParameter("nome")).thenReturn("NomeEvento");
            Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("images/events/eventoImage1.jpg");
            Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
            Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
            Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("15:30");
            Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
            Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
            Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
            String message = "Lunghezza nome non valida";
            Admin admin = new Admin();
            admin.setNome("Emanuele");
            admin.setCognome("Mezzi");
            admin.setUsername("memex99");
            admin.setPassword("password");
            Mockito.when(mockedRequest.getSession()).thenReturn(session);
            Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                servlet.doGet(mockedRequest, mockedResponse);
            });
            assertEquals(message, exception.getMessage());
        }

        @Test
        void TC_11_5(){
                Mockito.when(mockedRequest.getParameter("nome")).thenReturn("NomeEvento");
                Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("tiyftyfifift.jpg");
                Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
                Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
                Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("15:30");
                Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
                Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
                Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
                String message = "Errato: orario non selezionato";
                Admin admin = new Admin();
                admin.setNome("Emanuele");
                admin.setCognome("Mezzi");
                admin.setUsername("memex99");
                admin.setPassword("password");
                Mockito.when(mockedRequest.getSession()).thenReturn(session);
                Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        servlet.doGet(mockedRequest, mockedResponse);
        });
        assertEquals(message, exception.getMessage());

        }

        @Test
        void TC_11_6(){
            Mockito.when(mockedRequest.getParameter("nome")).thenReturn("NomeEvento");
            Mockito.when(mockedRequest.getParameter("immagine")).thenReturn("tiyftyfifift.jpg");
            Mockito.when(mockedRequest.getParameter("ospite")).thenReturn("Giuseppe Verdi");
            Mockito.when(mockedRequest.getParameter("descrizione")).thenReturn("Questo è un evento bello però fittizio");
            Mockito.when(mockedRequest.getParameter("orarioInizio")).thenReturn("03:78");
            Mockito.when(mockedRequest.getParameter("orarioFine")).thenReturn("18:30");
            Mockito.when(mockedRequest.getParameter("data")).thenReturn(String.valueOf(new Date(2020-1900, 12-1,31)));
            Mockito.when(mockedRequest.getParameter("postiDisponibili")).thenReturn(String.valueOf(150));
            String message = "Errato: formato non valido";
            Admin admin = new Admin();
            admin.setNome("Emanuele");
            admin.setCognome("Mezzi");
            admin.setUsername("memex99");
            admin.setPassword("password");
            Mockito.when(mockedRequest.getSession()).thenReturn(session);
            Mockito.when(mockedRequest.getSession().getAttribute("admin")).thenReturn(admin);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                servlet.doGet(mockedRequest, mockedResponse);
            });
            assertEquals(message, exception.getMessage());

        }

        @Test
        void TC_11_7(){

        }

        @Test
        void TC_11_8(){

        }

        @Test
        void TC_11_9(){

        }

        @Test
        void TC_11_10(){

        }

        @Test
        void TC_11_11(){

        }

        @Test
        void TC_11_12(){

        }

        @Test
        void TC_11_13(){

        }

        @Test
        void TC_11_14(){

        }
        @Test
        void TC_11_15(){

        }
        @Test
        void TC_11_16(){

        }




    }

