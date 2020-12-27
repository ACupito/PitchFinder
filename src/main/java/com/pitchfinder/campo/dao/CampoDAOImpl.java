package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.singleton.ConPool;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CampoDAOImpl implements CampoDAO {
    /**
     * get the campo using the id.
     * @param id of the pitch.
     * @return Campo
     */
    @Override
    public Campo doRetriveCampo(int id) {
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
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @param usernameAdmin is the admin username.
     * @return boolean
     */


    @Override
    public boolean doSaveOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin) {

        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO Occupazione "
                            + "(Data, OrarioInizio, OrarioFine, CampoIdentificativo, AdminUsername) VALUES(?,?,?,?,?)");
            ps.setDate(1, data);
            ps.setTime(2, inizio);
            ps.setTime(3, fine);
            ps.setInt(4, idCampo);
            ps.setString(5, usernameAdmin);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * delete an Occupazione from Database.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    @Override
    public boolean doRemoveOccupazione(int idCampo, Date data, Time inizio, Time fine) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Occupazione "
                            + "where CampoIdentificativo=? and Data=? and OrarioInizio=? and OrarioFine=?");

            ps.setInt(1, idCampo);
            ps.setDate(2, data);
            ps.setTime(3, inizio);
            ps.setTime(4, fine);

            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * check if the Occupazione exists.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine is the end of the occupation.
     * @return boolean
     */
    public boolean checkOccupazioneExistence(int idCampo, Date data, Time inizio, Time fine) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT CampoIdentificativo, Data, OrarioInizio, OrarioFine FROM Occupazione "
                            + "WHERE CampoIdentificativo=? && Data=? && OrarioInizio=? && OrarioFine=?");
            ps.setInt(1, idCampo);
            ps.setDate(2, data);
            ps.setTime(3, inizio);
            ps.setTime(4, fine);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return true;
            }

        } catch (SQLException e) {
            return false;
        }

        return false;
    }


    /**
     * save in the Database the Disponibilita.
     * @param emailUtente is the email of the user.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return boolean
     */
    @Override
    public boolean doSaveDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Disponibilita (CampoIdentificativo, UtenteEmail, Data, OrarioInizio, OrarioFine) VALUES(?,?,?,?,?)");
            ps.setInt(1, idCampo);
            ps.setString(2, emailUtente);
            ps.setDate(3, data);
            ps.setTime(4, inizio);
            ps.setTime(5, fine);


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * delete a Disponibilita from Database.
     * @param emailUtente is the email of the user.
     * @param idCampo is the id of the pitch.
     * @return boolean
     */

    @Override
    public boolean doRemoveDisponibilita(String emailUtente, int idCampo) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from Disponibilita where UtenteEmail=? && CampoIdentificativo=? ");

            ps.setString(1, emailUtente);
            ps.setInt(2, idCampo);

            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * take all the email by the Disponibilita giving the id_campo, data and time to find the ones that the user needs.
     * @param idCampo is the id of the pitch.
     * @param data is the date of the availability.
     * @param inizio is the start of the availability.
     * @param fine is the end of the availability.
     * @return List<String>
     */

    @Override
    public List<String> doRetriveEmailByDisponibilita(int idCampo, Date data, Time inizio, Time fine) {
        ArrayList<String> d = new ArrayList<>();
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT UtenteEmail FROM Disponibilita "
                            + "WHERE CampoIdentificativo=? && Data=? && OrarioInizio=? && OrarioFine=?");
            ps.setInt(1, idCampo);
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




