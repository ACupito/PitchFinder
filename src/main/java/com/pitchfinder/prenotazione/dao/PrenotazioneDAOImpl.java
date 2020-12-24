package com.pitchfinder.prenotazione.dao;

import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
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
                    con.prepareStatement("INSERT INTO Prenotazione VALUES (?,?,?,?)");

            ps.setString(1, prenotazione.getUtenteEmail());
            ps.setString(2, prenotazione.getEventoNome());
            ps.setDate(3, prenotazione.getEventoData());
            ps.setInt(4, prenotazione.getCodicePrenotazione());

            return ps.executeUpdate() == 1;

        } catch (SQLException throwables) {
           throw  new RuntimeException(throwables);
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

            return ps.executeUpdate() == 1;

        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }
    }

}
