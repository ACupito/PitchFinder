package com.pitchfinder.partita.dao;

import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the PartitaDAO implementation.
 */
public class PartitaDAOImpl implements PartitaDAO {
    /**
     * This method create an instance of partita in table partita.
     * @param partita partita
     */
    @Override
    public boolean doSavePartita(Partita partita) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT into partita(CampoIdentificativo, "
                            + "UtenteEmail, Data, OrarioInizio, OrarioFine)"
                            + " values(?, ?, ?, ?, ?)");
            ps.setInt(1, partita.getIdCampo());
            ps.setString(2, partita.getEmailUtente());
            ps.setDate(3, partita.getData());
            ps.setTime(4, partita.getOrarioInizio());
            ps.setTime(5, partita.getOrarioFine());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method return all the partita instance from table partita.
     * @return List<Partita>
     */
    @Override
    public List<Partita> doRetrieveAll() {
        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "SELECT IdentificativoPartita, CampoIdentificativo,"
                    + " UtenteEmail, Data, OrarioInizio, OrarioFine"
                    + "FROM partita";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ResultSet rs =  ps.executeQuery();
            List<Partita> partite = new ArrayList<Partita>();
            while (rs.next()) {
                Partita nuovo = new Partita(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDate(4),
                                rs.getTime(5), rs.getTime(6));
                partite.add(nuovo);
            }
            return partite;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
