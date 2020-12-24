package com.pitchfinder.squadra.dao;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.squadra.entity.Squadra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SquadraDAOImpl implements SquadraDAO {

    /**
     * If squadra is correctly SAVED in db return true else return false.
     * @param squadra - Squadra that wants to be saved in the database.
     * @return boolean
     */
    @Override
    public boolean doSaveSquadra(Squadra squadra) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO squadra "
                            + "(Nome, TorneoNome, TorneoDataInizio, TorneoCampoIdentificativo, NumeroMembri, Capitano, UtenteEmail) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, squadra.getNome());
            ps.setString(2, squadra.getTorneoNome());
            ps.setDate(3, (Date) squadra.getTorneoDataInizio());
            ps.setInt(4, squadra.getTorneoCampoIdentificativo());
            ps.setInt(5, squadra.getNumeroMembri());
            ps.setString(6, squadra.getCapitano());
            ps.setString(7, squadra.getUtenteEmail());

            if (ps.executeUpdate() != 1) {

                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * If squadra is correctly REMOVED from database return true else return false.
     * @param squadra - Squadra that wants to be removed from database
     * @return
     */
    @Override
    public boolean doRemoveSquadra(Squadra squadra) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM squadra "
                            + "WHERE Nome = ? AND TorneoNome = ? AND TorneoDataInizio = ? AND  TorneoCampoIdentificativo = ?");

                    ps.setString(1, squadra.getNome());
                    ps.setString(2, squadra.getTorneoNome());
                    ps.setDate(3, (Date) squadra.getTorneoDataInizio());
                    ps.setInt(4, squadra.getTorneoCampoIdentificativo());

                    if (ps.executeUpdate() != 1) {

                        return false;
                    }

                    return true;

        } catch (SQLException throwables) {

            throw new RuntimeException(throwables);

        }

    }

}
