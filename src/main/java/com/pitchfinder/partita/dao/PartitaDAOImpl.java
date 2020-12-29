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
     * @return boolean
     */
    @Override
    public boolean doSavePartita(Partita partita) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps0 =
                    con.prepareStatement("INSERT INTO Occupazione "
                            + "(Data, OrarioInizio, OrarioFine, CampoIdentificativo) VALUES(?, ?, ?, ?)");
            ps0.setDate(1, partita.getData());
            ps0.setTime(2, partita.getOrarioInizio());
            ps0.setTime(3, partita.getOrarioFine());
            ps0.setInt(4, partita.getIdCampo());
            ps0.executeUpdate();

            PreparedStatement ps =
                    con.prepareStatement("INSERT into partita(CampoIdentificativo, "
                            + "UtenteEmail, Data, OrarioInizio, OrarioFine)"
                            + " values(?, ?, ?, ?, ?)");
            ps.setInt(1, partita.getIdCampo());
            ps.setString(2, partita.getEmailUtente());
            ps.setDate(3, partita.getData());
            ps.setTime(4, partita.getOrarioInizio());
            ps.setTime(5, partita.getOrarioFine());
           return ps.executeUpdate() == 1;

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

            String query = "SELECT *"
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

    /**
     * This method create an instance of giocatore in table Giocatore.
     * @param idPartita idPartita
     * @param nome      nome
     * @param cognome   cognome
     * @return boolean
     */
    @Override
    public boolean doSaveGiocatore(int idPartita, String nome, String cognome) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT into giocatore(Nome,Cognome,"
                            + "PartitaIdentificativoPartita) " + " values(?, ?, ?)");
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setInt(3, idPartita);

            return ps.executeUpdate() == 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method return all the players from the table Giocatore.
     * @param idPartita
     * @return List<String>
     */
    @Override
    public List<String> doRetrieveAllGiocatori(int idPartita) {
        try (Connection con = ConPool.getInstance().getConnection()) {

            String query = "SELECT Nome, Cognome FROM giocatore WHERE PartitaIdentificativoPartita = ?";
            PreparedStatement ps =
                    con.prepareStatement(query);
            ps.setInt(1, idPartita);

            ResultSet rs =  ps.executeQuery();
            List<String> giocatori = new ArrayList<String>();
            while (rs.next()) {
                String nome = rs.getString(1);
                String cognome = rs.getString(2);
                giocatori.add(nome);
                giocatori.add(cognome);
            }
            return giocatori;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
