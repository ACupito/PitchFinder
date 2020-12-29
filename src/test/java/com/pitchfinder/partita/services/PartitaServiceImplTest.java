package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
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
    private CampoDAO daoCampo = new CampoDAOImpl();
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

        //Admin - used for doSaveOccupazione
        Admin admin = new Admin();
        admin.setNome("Eugenio");
        admin.setCognome("De Simone");
        admin.setUsername("eugenio123");
        admin.setPassword("password");

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
        //doSaveOccupazione
        daoCampo.doSaveOccupazione(1003,Date.valueOf("2021-7-10"),
                Time.valueOf("16:00:00"),Time.valueOf("17:00:00"),admin.getUsername());
    }

    /**
     * method used for the testing of createPartita
     */
    @Test
    void createPartitaTest() {
        Date date = new Date(2020-1900,0,15);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);
        Utente user = new Utente();
        user.setEmail("test99@gmail.com");

        assertNotNull(serviceTest.createPartita(1002,user,date,start,end));
    }

    /**
     * method used for the testing of createPartita on failure return null
     */
    @Test
    void createPartitaFailureTest() {

        Utente user = new Utente();
        user.setEmail("test99@gmail.com");

        assertNull(serviceTest.createPartita(1003,user,
                Date.valueOf("2021-7-10"), Time.valueOf("16:00:00"),
                Time.valueOf("17:00:00")));
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
        //Delete Admin
        Admin admin = new Admin();
        admin.setNome("Eugenio");
        admin.setCognome("De Simone");
        admin.setUsername("eugenio123");
        admin.setPassword("password");

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Admin where username=? && password=?");

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPasswordHash());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Delete Occupazione
        daoCampo.doRemoveOccupazione(1003,Date.valueOf("2021-7-10"),
                Time.valueOf("16:00:00"),Time.valueOf("17:00:00"));
        daoCampo.doRemoveOccupazione(1002,Date.valueOf("2020-1-15"),
                Time.valueOf("16:00:00"),Time.valueOf("18:00:00"));
        daoCampo.doRemoveOccupazione(pTest.getIdCampo(),pTest.getData()
                ,pTest.getOrarioInizio(),pTest.getOrarioFine());
    }
}