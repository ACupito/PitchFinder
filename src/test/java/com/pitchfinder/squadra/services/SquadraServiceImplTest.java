package com.pitchfinder.squadra.services;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import static org.junit.jupiter.api.Assertions.*;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SquadraServiceImplTest {
    SquadraService squadraService = new SquadraServiceImpl();
    private Utente utente, utenteDue;
    private UtenteDAO utenteDAO = new UtenteDAOImpl();
    @BeforeAll
    public void sava(){
        utente = new Utente("mario96@gmail.com", "MarioNoi", "Mario", "Noi", "ciao",Date.valueOf("1996-12-03"));
        utenteDAO.doSaveUtente(utente);
        utenteDue = new Utente("manuzzi97@gmail.com","memex97","Emanuele","Mezzi","ciao",Date.valueOf("1998-12-10"));
        utenteDAO.doSaveUtente(utenteDue);
    }
    /**
     * Success, create a new Squadra!
     */
    @Test
    public void checkCreateSquadra(){
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Date dataFine = new Date(2022-1900, 12-1, 11);
        Date dataDiNascita = new Date(1998-1900,12-1,10);
        Torneo torneo = new Torneo("serieA", "tipo", "gironi", "lunedi",
                "memex99", 20, 10,
                12, dataInizio, dataFine, 1002);
        Utente utente = new Utente("manuzzi97@gmail.com","memex97","Emanuele","Mezzi","ciao", dataDiNascita);
        assertNotNull(squadraService.createSquadra("Parma",torneo, 10, "Insigne",utente));

    }

    /**
     * Success.
     */
    @Test
    public void checkCreateGiocatore(){
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Squadra squadra = new Squadra("Padova", "serieA", dataInizio, 1002, 10, "Squillante", "mario96@gmail.com");
        assertNotNull(squadraService.createGiocatoreSquadra("Eugenio","DeSimone", squadra));
    }

    @AfterAll
    void after(){
        Date dataInizio = new Date(2020-1900, 1-1, 8);
        Date dataFine = new Date(2022-1900, 12-1, 11);
        Date dataDiNascita = new Date(1998-1900,12-1,10);
        Torneo torneo = new Torneo("serieA", "tipo", "gironi", "lunedi",
                "memex99", 20, 10,
                12, dataInizio, dataFine, 1002);
        Utente utente = new Utente("manuzzi97@gmail.com","memex97","Emanuele","Mezzi","ciao", dataDiNascita);
        Squadra squadra = new Squadra("Parma", torneo.getNome(), torneo.getDataInizio(), torneo.getCampoIdentificativo(), 10, "Insigne", utenteDue.getEmail());
        SquadraDAO squadraDAO = new SquadraDAOImpl() ;
        squadraDAO.doRemoveSquadra(squadra);

        utenteDAO.doRemoveUtente(utente);
        utenteDAO.doRemoveUtente(utenteDue);

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM giocatore WHERE Nome = ? && Cognome = ? ");

            ps.setString(1, "Eugenio");
            ps.setString(2,"DeSimone");
            ps.executeUpdate();

        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }
    }




}
