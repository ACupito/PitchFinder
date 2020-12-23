package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.singleton.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampoDAOImpl implements CampoDAO {
    /**
     * get the campo using the id.
     *
     * @param id
     * @return Campo
     */
    @Override
    public Campo getCampo(int id) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Identificativo, Sport FROM Campo WHERE Identificativo=?");
            ps.setInt(1, id);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Campo u = new Campo();
                u.setIdentificativo(rs.getInt(1));
                u.setSport(rs.getString(2));
                return u;

            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * save in the Database the Occupazione.
     *
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @param username_admin
     * @return boolean
     */


    @Override
    public boolean doSaveOccupazione(int id_campo, Date data, Time inizio, Time fine, String username_admin) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Occupazione (Data, OrarioInizio, OrarioFine, CampoIdentificativo, AdminUsername) VALUES(?,?,?,?,?)");
            ps.setDate(1, data);
            ps.setTime(2, inizio);
            ps.setTime(3, fine);
            ps.setInt(4, id_campo);
            ps.setString(5, username_admin);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * delete an Occupazione from Database.
     *
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    @Override
    public boolean doRemoveOccupazione(int id_campo, Date data, Time inizio, Time fine) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Occupazione where CampoIdentificativo=? && Data=? && OrarioInizio=? && OrarioFine=?");

            ps.setInt(1, id_campo);
            ps.setDate(2, data);
            ps.setTime(3, inizio);
            ps.setTime(4, fine);

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * save in the Database the Disponibilita.
     *
     * @param email_utente
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return boolean
     */
    @Override
    public boolean doSaveDisponibilita(String email_utente, int id_campo, Date data, Time inizio, Time fine) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Disponibilita (CampoIdentificativo, UtenteEmail, Data, OrarioInizio, OrarioFine) VALUES(?,?,?,?,?)");
            ps.setInt(1, id_campo);
            ps.setString(2, email_utente);
            ps.setDate(3, data);
            ps.setTime(4, inizio);
            ps.setTime(5, fine);


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * delete a Disponibilita from Database.
     *
     * @param email_utente
     * @param id_campo
     * @return boolean
     */

    @Override
    public boolean doRemoveDisponibilita(String email_utente, int id_campo) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Disponibilita where UtenteEmail=? && CampoIdentificativo=? ");

            ps.setString(1, email_utente);
            ps.setInt(2, id_campo);

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * take all the email by the Disponibilita giving the id_campo, data and time to find the ones that the user needs.
     *
     * @param id_campo
     * @param data
     * @param inizio
     * @param fine
     * @return List<String>
     */

    @Override
    public List<String> doRetriveEmailByDisponibilita(int id_campo, Date data, Time inizio, Time fine) {
        ArrayList<String> d = new ArrayList<>();
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Email FROM Disponibilita WHERE CampoIdentificativo=? && Data=? && OrarioInizio=? && OrarioFine=?");
            ps.setInt(1, id_campo);
            ps.setDate(2, data);
            ps.setTime(3, inizio);
            ps.setTime(4, fine);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                d.add(rs.getString(1));
            }
            return d;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




