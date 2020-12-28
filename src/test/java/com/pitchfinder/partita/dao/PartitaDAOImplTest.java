package com.pitchfinder.partita.dao;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PartitaDAOImplTest {
    private PartitaDAO daoTest;

    @BeforeAll
    public void start(){
        daoTest = new PartitaDAOImpl();

        UtenteDAO daoUtente = new UtenteDAOImpl();
        Utente userTest = new Utente();
        userTest.setEmail("test99@gmail.com");
        userTest.setUsername("Testing");
        userTest.setNome("Test");
        userTest.setCognome("De Test");
        userTest.setPassword("Testing99@");

        Date dateOfBirth = new Date(1999-1900,11,28);
        userTest.setDataDiNascita(dateOfBirth);

        daoTest.doSaveGiocatore(4,"Pasquale","Gaeta");
        daoUtente.doSaveUtente(userTest);
    }

    /**
     * This method check correct behaviour of doSavePartita.
     */
    @Test
    public void checkdoSavePartita() {
        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita pTest = new Partita(1002,"test99@gmail.com",
                date,start,end);

        assertTrue(daoTest.doSavePartita(pTest));
    }

    /**
     * This method check correct behaviour of doRetrieveAll.
     */
    @Test
    public void checkdoRetrieveAll() {
        List<Partita> partite = daoTest.doRetrieveAll();

        assertNotNull(partite);
    }

    /**
     * This method check correct behaviour of doSaveGiocatore.
     */
    @Test
    public void checkdoSaveGiocatore(){

        assertTrue(daoTest.doSaveGiocatore(4,"Andrea","Rossi"));
    }

    /**
     * This method check correct behaviour of doRetrieveAllGiocatori
     */
    @Test
    public void doRetrieveAllGiocatori(){
        List<String> giocatori = daoTest.doRetrieveAllGiocatori(4);

        assertNotNull(giocatori);
    }


    @AfterAll
    public void clean(){
        //Delete player used for the testing

        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "DELETE FROM giocatore "
                    + "WHERE PartitaIdentificativoPartita = 4";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    }
}
