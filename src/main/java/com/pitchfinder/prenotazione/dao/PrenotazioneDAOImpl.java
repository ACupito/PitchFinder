package com.pitchfinder.prenotazione.dao;

import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PrenotazioneDAOImpl implements PrenotazioneDAO {

    /**
     * If prenotazione is correctly SAVED in database return true else return false.
     * @param prenotazione - prenotazione.
     * @return
     */
    @Override
    public boolean doSavePrenotazione(Prenotazione prenotazione) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO prenotazione(UtenteEmail, EventoNome, EventoData, CodicePrenotazione) "
                            + "VALUES (?, ?, ?, ?)");
            ps.setString(1, prenotazione.getUtenteEmail());
            ps.setString(2, prenotazione.getEventoNome());
            ps.setDate(3, (Date) prenotazione.getEventoData());
            ps.setInt(4, prenotazione.getCodicePrenotazione());

            if (ps.executeUpdate() != 1) {
                return false;
            }

            return true;

        } catch (SQLException throwables) {
            return false;
        }
    }

    /**
     * If prenotazione is correctly REMOVED from database return true else return false.
     * @param prenotazione - prenotazione.
     * @return
     */
    @Override
    public boolean doRemovePrenotazione(Prenotazione prenotazione) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM prenotazione WHERE CodicePrenotazione = ?");

            ps.setInt(1, prenotazione.getCodicePrenotazione());

            if (ps.executeUpdate() != 1) {
                return false;
            }

            return true;

        } catch (SQLException throwables) {
            return false;
        }
    }

}
