package com.pitchfinder.torneo.dao;

import com.pitchfinder.singleton.ConPool;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * This interface manages the Torneo dao.
 */

public class TorneoDAOImpl implements TorneoDAO {

    /**
     *
     * This method makes the Torneo object persist
     * in the database.
     * @param torneo object
     * @return boolean
     */
    @Override
    public boolean doSaveTorneo(Torneo torneo) {

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

            if(ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    /**
     * This method allows to delete a Torneo object
     * from the database.
     * @param torneo object
     * @return boolean
     */
    @Override
    public boolean doRemoveTorneo(Torneo torneo) {
        return false;
    }

    /**
     * This method allows to get all Torneo items
     * from the database.
     * @return List<Torneo>
     */
    @Override
    public List<Torneo> doRetrieveAllTornei() {
        return null;
    }

    /**
     * This method allows to get Torneo item
     * from the database.
     * @param nome name of the tournament.
     * @param dataInizio start date of the tournament.
     * @param idCampo pitch identifier.
     * @return Torneo
     */
    @Override
    public Torneo doRetrieveTorneo(String nome, Date dataInizio, int idCampo) {
        return null;
    }

    /**
     * This method allows to get Torneo items from filters.
     * @param dataInizio start date of the tournament.
     * @param dataFine  end date og the tournament.
     * @param tipo type of the tournament.
     * @return List<Torneo>
     */
    @Override
    public List<Torneo> doRetrieveTorneoByFilter(Date dataInizio, Date dataFine, String tipo) {
        return null;
    }

    /**
     * This method allows to get Torneo items from through a certain sport.
     * @param sport sport of the tournament.
     * @return List<Torneo>
     */
    @Override
    public List<Torneo> doRetrieveTorneoBySport(String sport) {
        return null;
    }
}
