package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartitaServiceImplTest {
    private PartitaService serviceTest;
    private Partita pTest;

    @BeforeAll
    void start(){
        serviceTest = new PartitaServiceImpl();
        PartitaDAO daoPartita = new PartitaDAOImpl();

        UtenteDAO daoUtente = new UtenteDAOImpl();
        Utente userTest = new Utente();
        userTest.setEmail("test99@gmail.com");
        userTest.setUsername("Testing");
        userTest.setNome("Test");
        userTest.setCognome("De Test");
        userTest.setPassword("Testing99@");
        Date dateOfBirth = new Date(1999-1900,11,28);
        userTest.setDataDiNascita(dateOfBirth);

        daoUtente.doSaveUtente(userTest);

        Date date = new Date(2021-1900,0,10);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        pTest = new Partita(1002,"test99@gmail.com",
                date,start,end);

        daoPartita.doSavePartita(pTest);
        List<Partita> partite = daoPartita.doRetrieveAll();
        for (Partita p: partite) {
            if(p.getEmailUtente().equals("test99@gmail.com")){
                pTest = p;
            }
        }

        daoPartita.doSaveGiocatore(pTest.getIdPartita(), "Eugenio", "Montale");

    }

    /**
     * method used for the testing of createPartita
     */
    @Test
    void createPartitaTest() {
        Date date = new Date(2020-1900,0,7);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);
        Utente user = new Utente();
        user.setEmail("test99@gmail.com");

        assertNotNull(serviceTest.createPartita(1002,user,date,start,end));
    }

    /**
     * method used for the testing of showGiocatori
     */
    @Test
    void showGiocatoriTest(){
        assertNotNull(serviceTest.showGiocatori(pTest));
    }

    /**
     * method used for the testing of createGiocatorePartita
     */
    @Test
    void createGiocatorePartitaTest(){
        assertTrue(serviceTest.createGiocatorePartita(pTest.getIdPartita(), "Catello", "Ercolano"));
    }

    @AfterAll
    void clear(){
        //Delete user used for the testing
        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "DELETE FROM utente "
                    + "WHERE Email = 'test99@gmail.com' ";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Delete match used for the testing
        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "DELETE FROM partita "
                    + "WHERE UtenteEmail = 'test99@gmail.com' ";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Delete player used for the testing

        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "DELETE FROM giocatore "
                    + "WHERE PartitaIdentificativoPartita = ?";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.setInt(1, pTest.getIdPartita());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}