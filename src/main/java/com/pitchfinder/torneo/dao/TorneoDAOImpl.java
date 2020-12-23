package com.pitchfinder.torneo.dao;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * This interface manages the Torneo dao.
 */

public class TorneoDAOImpl implements TorneoDAO {

    /**
     *
     * This method makes the Torneo object persist
     * in the database.
     * @param torneo object
     * @return boolean -> true execute success / false execute failed
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

}
