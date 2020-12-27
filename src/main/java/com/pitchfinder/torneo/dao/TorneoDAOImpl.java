package com.pitchfinder.torneo.dao;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class manages the Torneo dao.
 */

public class TorneoDAOImpl implements TorneoDAO {

    /**
     *
     * This method makes the Torneo object persist
     * in the database.
     * @param torneo object
     * @return boolean -> true: execute success / false: execute failed
     */
    @Override
    public boolean doSaveTorneo(final Torneo torneo) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into Torneo(Nome, DataInizio,"
                    + "CampoIdentificativo, AdminUsername, Tipo, Struttura, NumeroSquadre, DataFine,"
                    + "MinNumeroPartecipantiSquadra, MaxNumeroPartecipantiSquadra, GiornoPartite)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, torneo.getNome());
            ps.setDate(2, torneo.getDataInizio());
            ps.setInt(3, torneo.getCampoIdentificativo());
            ps.setString(4, torneo.getAdminUsername());
            ps.setString(5, torneo.getTipo());
            ps.setString(6, torneo.getStruttura());
            ps.setInt(7, torneo.getNumeroSquadre());
            ps.setDate(8, torneo.getDataFine());
            ps.setInt(9, torneo.getMinNumeroPartecipantiPerSquadra());
            ps.setInt(10, torneo.getMaxNumeroPartecipantiPerSquadra());
            ps.setString(11, torneo.getGiornoPartite());

            return ps.executeUpdate() == 1;
        } catch (SQLException s) {
            return false;
        }
    }

    /**
     * This method remove Torneo object
     * from database.
     * @param torneo object
     * @return boolean -> true: execute success / false: execute failed
     */
    @Override
    public boolean doRemoveTorneo(Torneo torneo) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from Torneo where Nome = ? and DataInizio = ? and CampoIdentificativo = ?");

            ps.setString(1, torneo.getNome());
            ps.setDate(2, torneo.getDataInizio());
            ps.setInt(3, torneo.getCampoIdentificativo());

            return ps.executeUpdate() == 1;

        } catch (SQLException s) {
            return false;
        }
    }

    /**
     * This method allows to get all the tournaments
     * from the database.
     * @return A List of Torneo items.
     */
    public List<Torneo> doRetrieveAllTornei() {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from torneo");
            ResultSet rs = ps.executeQuery();

            List<Torneo> tornei = new ArrayList<>();
            Torneo t = new Torneo();

            while (rs.next()) {

                t.setNome(rs.getString(1));
                t.setDataInizio(rs.getDate(2));
                t.setCampoIdentificativo(rs.getInt(3));
                t.setAdminUsername(rs.getString(4));
                t.setTipo(rs.getString(5));
                t.setStruttura(rs.getString(6));
                t.setNumeroSquadre(rs.getInt(7));
                t.setDataFine(rs.getDate(8));
                t.setMinNumeroPartecipantiPerSquadra(rs.getInt(9));
                t.setMaxNumeroPartecipantiPerSquadra(rs.getInt(10));
                t.setGiornoPartite(rs.getString(11));
                tornei.add(t);

            }

            return tornei;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

}
