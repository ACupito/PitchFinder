package com.pitchfinder.prenotazione.dao;

import com.pitchfinder.prenotazione.entity.Prenotazione;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class PrenotazioneDAOImpl implements PrenotazioneDAO {

    /**
     * If prenotazione is correctly SAVED in database return true else return false.
     * @param prenotazione - prenotazione.
     * @return boolean
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
            throw new RuntimeException(throwables);
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
                    con.prepareStatement("DELETE FROM prenotazione WHERE UtenteEmail = ? && EventoNome = ? && EventoData = ?");

            ps.setString(1, prenotazione.getUtenteEmail());
            ps.setString(2, prenotazione.getEventoNome());
            ps.setDate(3, prenotazione.getEventoData());

            return ps.executeUpdate() == 1;

        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }
    }

    /**
     * Do retrieve prenotazione.
     * @param email - email.
     * @param nomeEvento - nomeEvento.
     * @param dataEvento - dataEvento.
     * @return Prenotazione
     */
    @Override
    public Prenotazione doRetrievePrenotazione(String email, String nomeEvento, Date dataEvento) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT UtenteEmail, EventoNome, EventoData,"
                            + " CodicePrenotazione FROM prenotazione "
                            + "WHERE UtenteEmail = ? && EventoNome = ? && EventoData = ?");

            ps.setString(1, email);
            ps.setString(2, nomeEvento);
            ps.setDate(3, dataEvento);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setUtenteEmail(rs.getString(1));
                prenotazione.setEventoNome(rs.getString(2));
                prenotazione.setEventoData(rs.getDate(3));
                prenotazione.setCodicePrenotazione(rs.getInt(4));
                return prenotazione;
            }
            return  null;
        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }
    }


}
