package com.pitchfinder.prenotazione.services;

import com.pitchfinder.autenticazione.entity.Admin;
import com.pitchfinder.evento.entity.Evento;

import com.pitchfinder.prenotazione.dao.PrenotazioneDAO;
import com.pitchfinder.prenotazione.dao.PrenotazioneDAOImpl;
import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrenotazioneServiceImplTest {
    private Admin admin;

    @BeforeAll
    public void save(){
        /**Create an admin.*/
        admin = new Admin("memex", "emanuele", "mezzi", "ciao");
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
    }

    @Test
    public void checkCreatePrenotazione(){
        Date data = new Date(2021-1900, 12-1, 18);
        Time inizio = new Time(13,00,00);
        Time fine = new Time(15, 00, 00);

        Evento evento = new Evento("NomeEvento","Immaginet",inizio,fine,data,"Ospitee", "Descrizionee",100,"memex");
        PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();

        assertNotNull(prenotazioneService.createPrenotazione("AndreSquillante@gmail.com", evento));

    }

    @AfterAll
    void clean(){
        Date data = new Date(2021-1900, 12-1, 18);
        Time inizio = new Time(13,00,00);
        Time fine = new Time(15, 00, 00);
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
        Evento evento = new Evento("NomeEvento","Immaginet",inizio,fine,data,"Ospitee", "Descrizionee",100,"memex");
        Prenotazione prenotazione = new Prenotazione("AndreSquillante@gmail.com", evento.getName(), evento.getDate());
        prenotazioneDAO.doRemovePrenotazione(prenotazione);

        //remove admin
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM admin where Username = ?");
            ps.setString(1, admin.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}