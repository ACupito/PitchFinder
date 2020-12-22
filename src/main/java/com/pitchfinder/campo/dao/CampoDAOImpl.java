package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;
import com.pitchfinder.singleton.ConPool;

import java.sql.*;
import java.util.List;

public class CampoDAOImpl implements CampoDAO{

    @Override
    public Campo getCampo(int id) {
        try (Connection con = ConPool.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Identificativo, Sport FROM Campo WHERE Identificativo=?");
            ps.setInt(1,id );


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




    @Override
    public boolean doSaveOccupazione(int id_campo, Date data, Time inizio, Time fine, String username_admin) {

            try (Connection con = ConPool.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO Occupazione (Data, OrarioInizio, OrarioFine, CampoIdentificativo, AdminUsername) VALUES(?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1,data);
                ps.setTime(2,inizio);
                ps.setTime(3,fine);
                ps.setInt(4,id_campo);
                ps.setString(5,username_admin);

                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        return false;
    }

    @Override
    public boolean doRemoveOccupazione(int id_campo, Date data, Time inizio, Time fine) {
        return false;
    }

    @Override
    public boolean doSaveDisponibilita(String email_utente, int id_campo, Date data, Time inizio, Time fine) {
        return false;
    }

    @Override
    public boolean doRemoveDisponibilita(String email_utente, int id_campo) {
        return false;
    }

    @Override
    public List<String> doRetriveEmailByDisponibilita(int id_campo, Date data, Time inizio, Time fine) {
        return null;
    }
}
