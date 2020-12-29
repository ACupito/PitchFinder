package com.pitchfinder.partita.dao;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
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
    private Partita pTest, deleteP;
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

        daoUtente.doSaveUtente(userTest);

        Date date = new Date(2021-1900,0,12);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        pTest = new Partita(1003,"test99@gmail.com",
                date,start,end);

        daoTest.doSavePartita(pTest);
        List<Partita> partite = daoTest.doRetrieveAll();
        for (Partita p: partite) {
            if(p.getEmailUtente().equals("test99@gmail.com")){
                pTest = p;
            }
        }
        daoTest.doSaveGiocatore(pTest.getIdPartita(),"Pasquale","Gaeta");

        deleteP = new Partita(1004,"test99@gmail.com",Date.valueOf("2021-1-21"),
                Time.valueOf("16:00:00"),Time.valueOf("17:00:00"));
        daoTest.doSavePartita(deleteP);
    }

    /**
     * This method check correct behaviour of doSavePartita.
     */
    @Test
    public void checkdoSavePartita() {
        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita pTestlocal = new Partita(1003,"test99@gmail.com",
                date,start,end);

        assertTrue(daoTest.doSavePartita(pTestlocal));
    }
    @Test
    public void checkdoSavePartitaFailure() {

        assertThrows( RuntimeException.class ,()->{ daoTest.doSavePartita(pTest);} );
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

        assertTrue(daoTest.doSaveGiocatore(pTest.getIdPartita(),"Andrea","Rossi"));
    }

    /**
     * This method check throw  of doSaveGiocatore.
     */
    @Test
    public void checkdoSaveGiocatoreFailure(){

        assertThrows(RuntimeException.class,() ->{daoTest.doSaveGiocatore(-973,"Andrea","Rossi");});
    }

    /**
     * This method check correct behaviour of doRetrieveAllGiocatori
     */
    @Test
    public void doRetrieveAllGiocatoriTest(){
        List<String> giocatori = daoTest.doRetrieveAllGiocatori(pTest.getIdPartita());

        assertNotNull(giocatori);
    }

    /**
     * This method check correct behaviour of doRemovePartita
     */
    @Test
    public void doRemovePartitaTest(){

        assertTrue(daoTest.doRemovePartita(deleteP.getIdCampo(),deleteP.getData(),
                deleteP.getOrarioInizio(),deleteP.getOrarioFine()));
    }

    /**
     * This method check bad behaviour of doRemovePartita
     */
    @Test
    public void doRemovePartitaTestFailure(){

        assertFalse(daoTest.doRemovePartita(deleteP.getIdCampo(),Date.valueOf("2021-2-10"),
                deleteP.getOrarioInizio(),deleteP.getOrarioFine()));
    }

    @AfterAll
    public void clean(){
        //Delete player used for the testing

        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "DELETE FROM giocatore "
                    + "WHERE PartitaIdentificativoPartita = ?";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.setInt(1,pTest.getIdPartita());
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

        //Delete occupazione used for the testing
        CampoDAO daoCampo = new CampoDAOImpl();
        daoCampo.doRemoveOccupazione(pTest.getIdCampo(),pTest.getData(),
                pTest.getOrarioInizio(),pTest.getOrarioFine());

        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        daoCampo.doRemoveOccupazione(1003,date,start,end);
        daoCampo.doRemoveOccupazione(deleteP.getIdCampo(),deleteP.getData(),
                deleteP.getOrarioInizio(),deleteP.getOrarioFine());


    }
}
