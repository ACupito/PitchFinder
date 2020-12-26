package com.pitchfinder.evento.services;

import com.pitchfinder.evento.entity.Evento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Method to test the createEvento method offered by EventoService interface.
 * All the parameters are correct.
 */
public class EventoServiceImplTest {

    /**
     *  This is an instance of TorneoService.
     */
    private static EventoService eService;

    /**
     * This method setup the enviroment.
     */
    @BeforeAll
    public static void setUp() {
        eService = new EventoServiceImpl();
    }

    /**
     * This method tests the method createEvento offered by EventoServiceImpl class.
     */
    @Test
    void check1() {

        String nome = "Evento di prova Bello";
        String immagineStr = "web/evento/immagini/eventoImage1";
        String ospite = "Giuseppe Verdi";
        String descrizione = "Questo è un evento bello però fittizio";
        Time orarioInizio = new Time(12,0,0);
        Time orarioFine = new Time(16,0,0);
        Date data = new Date(2020 - 1900, 12 - 1, 31 );
        int postiDisponibili = 150;
        String adminUsername = "memex99";

        //check if the method return true
        assertNotNull(eService.createEvento(nome, immagineStr,orarioInizio, orarioFine,
                data, ospite, descrizione, postiDisponibili, adminUsername));
    }

    /**
     * This method tests the method getAllEventi offered by EventoServiceImpl class.
     */
    @Test
    void check2(){

        assertNotNull(eService.getAllEventi());

    }
    /**
     * This method tests the method getEvento offered by EventoServiceImpl class.
     */
    @Test
    void check3(){

        String nome = "Evento di prova Bello";
        Date data = new Date(2020 - 1900, 12 - 1, 31 );

        assertNotNull(eService.getEvento(nome,data));
    }

    /**
     * This method tests the method findNumeroPartecipantiByEvento offered by EventoServiceImpl class.
     */
    @Test
    void check4(){

        String nome = "Evento di prova Bello";
        String immagineStr = "web/evento/immagini/eventoImage1";
        String ospite = "Giuseppe Verdi";
        String descrizione = "Questo è un evento bello però fittizio";
        Time orarioInizio = new Time(12,0,0);
        Time orarioFine = new Time(16,0,0);
        Date data = new Date(2020 - 1900, 12 - 1, 31 );
        int postiDisponibili = 150;
        String adminUsername = "memex99";

        Evento evento = new Evento();
        evento.setName(nome);
        evento.setImage(immagineStr);
        evento.setGuest(ospite);
        evento.setDescription(descrizione);
        evento.setStartHour(orarioInizio);
        evento.setEndHour(orarioFine);
        evento.setDate(data);
        evento.setAvailableSits(postiDisponibili);
        evento.setAdmin(adminUsername);

        assertNotNull(eService.findNumeroPartecipantiByEvento(evento));
    }

    /**
     * This method tests the method removeEvento offered by EventoServiceImpl class.
     */
    @Test
    void check5() {

        String nome = "Evento di prova Bello";
        String immagineStr = "web/evento/immagini/eventoImage1";
        String ospite = "Giuseppe Verdi";
        String descrizione = "Questo è un evento bello però fittizio";
        Time orarioInizio = new Time(12,0,0);
        Time orarioFine = new Time(16,0,0);
        Date data = new Date(2020 - 1900, 12 - 1, 31 );
        int postiDisponibili = 150;
        String adminUsername = "memex99";

        Evento evento = new Evento();
        evento.setName(nome);
        evento.setImage(immagineStr);
        evento.setGuest(ospite);
        evento.setDescription(descrizione);
        evento.setStartHour(orarioInizio);
        evento.setEndHour(orarioFine);
        evento.setDate(data);
        evento.setAvailableSits(postiDisponibili);
        evento.setAdmin(adminUsername);

        assertTrue(eService.removeEvento(evento));
    }


}