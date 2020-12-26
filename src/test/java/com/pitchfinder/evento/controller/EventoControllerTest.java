package com.pitchfinder.evento.controller;

import com.pitchfinder.evento.dao.EventoDAO;
import com.pitchfinder.evento.dao.EventoDAOImpl;
import com.pitchfinder.evento.entity.Evento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class EventoControllerTest {

        /**
         * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
         * All the parameters are correct.
         */
        @Test
        void TC_11_1() {

            // Mockito.when(mockedRequest.getParameter("nome")).thenReturn("nomeEvento");

            }

        /**
         * Method to test the doSaveEvento method offered by EventoDAOImpl interface.
         * Start Hour is incorrect
         */
        @Test
        void TC_11_2() {

            Evento evento = new Evento();
            EventoDAO eventoDAO = new EventoDAOImpl();

            evento.setName("Evento di Prova 11");
            evento.setDate(new Date(2000 - 1900, 10 - 1, 3 ));
            evento.setImage("web/evento/immagini/eventoImage1");
            evento.setGuest("Giuseppe Verdi");
            evento.setDescription("Questo è un evento fittizio di prova");
            evento.setStartHour(new Time(04,00,00));
            evento.setEndHour(new Time(18,00,00));
            evento.setAvailableSits(150);
            evento.setAdmin("memex99");
            assertNotNull(eventoDAO.doSaveEvento(evento));

            assertThrows(RuntimeException.class, () ->{eventoDAO.doSaveEvento(evento);});
        }

        @Test
        void TC_11_3(){

        }

        @Test
        void TC_11_4(){

        }

        @Test
        void TC_11_5(){

        }

        @Test
        void TC_11_6(){

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

