package com.pitchfinder.squadra.dao;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.squadra.entity.Squadra;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class SquadraDAOImplTest {
    SquadraDAO squadraDAO = new SquadraDAOImpl();
    UtenteDAO utenteDAO = new UtenteDAOImpl();
    private Utente utente ;

    @BeforeAll
    void before(){
        utente = new Utente("mario96@gmail.com", "MarioNoi", "Mario", "Noi", "ciao",Date.valueOf("1996-12-03"));
        utenteDAO.doSaveUtente(utente);
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Palermo", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        squadraDAO.doSaveSquadra(squadra);

    }

    /**
     * Team correctly Saved.
     */
    @Test
    public void checkDoSaveSquadra(){

        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");

        assertTrue(squadraDAO.doSaveSquadra(squadra));
    }

    @Test
    public void checkDoSaveSquadraError(){

        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra(null, "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");

        assertThrows(RuntimeException.class,()->{squadraDAO.doSaveSquadra(squadra);});
    }

    /**
     * Success.
     */
    @Test
    public void checkDoRemove(){

        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Palermo", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertTrue(squadraDAO.doRemoveSquadra(squadra));
    }

    /**
     * Success.
     */
    @Test
    public void checkDoSaveGiocatoreSquadra() {
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertTrue(squadraDAO.doSaveGiocatoreSquadra("Lucia","Gaeta", squadra));

    }

    @AfterAll
    void after(){
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");

        squadraDAO.doRemoveSquadra(squadra);
        utenteDAO.doRemoveUtente(utente);

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM giocatore WHERE Nome = ? && Cognome = ? ");

            ps.setString(1, "Lucia");
            ps.setString(2,"Gaeta");
            ps.executeUpdate();

        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }

    }


}