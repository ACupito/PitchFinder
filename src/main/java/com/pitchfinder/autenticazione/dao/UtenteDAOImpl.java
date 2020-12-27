package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.singleton.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOImpl implements UtenteDAO {

    /**
     * Constant for index number 1.
     */
    private static final int INDEX1 = 1;

    /**
     * Constant for index number 2.
     */
    private static final int INDEX2 = 2;

    /**
     * Constant for index number 3.
     */
    private static final int INDEX3 = 3;

    /**
     * Constant for index number 4.
     */
    private static final int INDEX4 = 4;

    /**
     * Constant for index number 5.
     */
    private static final int INDEX5 = 5;

    /**
     * Constant for index number 6.
     */
    private static final int INDEX6 = 6;

    /**
     * This method memorizes a user into the database.
     * @param utente is the user who want do join the platform
     * @return boolean
     */
    public boolean doSaveUtente(final Utente utente) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "insert into utente (Email, Username, Nome, Cognome, "
                            + "Password, DataDiNascita) "
                            + "values (?, ?, ?, ?, ?, ?)");

            ps.setString(INDEX1, utente.getEmail());
            ps.setString(INDEX2, utente.getUsername());
            ps.setString(INDEX3, utente.getNome());
            ps.setString(INDEX4, utente.getCognome());
            ps.setString(INDEX5, utente.getPasswordHash());
            ps.setDate(INDEX6, utente.getDataDiNascita());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            return false;
        }
    }

    /**
     * This method checks weather the subject who is trying to
     * access is a registered user o not.
     * @param utente is the user who is logging in
     * @return utente
     */
    public Utente checkUtente(final Utente utente) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            int nUtenti = checkUtenteExistence(utente);

            PreparedStatement ps = con.prepareStatement(
                    "select * from Utente where Email=?");
            ps.setString(INDEX1, utente.getEmail());

            if (nUtenti == 1) {

                ResultSet rs = ps.executeQuery();
                rs.next();

                if (rs.getString(INDEX5).
                        equalsIgnoreCase(utente.getPasswordHash())) {
                    utente.setUsername(rs.getString(INDEX2));
                    utente.setNome(rs.getString(INDEX3));
                    utente.setCognome(rs.getString(INDEX4));
                    utente.setDataDiNascita(rs.getDate(INDEX6));

                    return utente;

                } else {
                    throw new IllegalArgumentException("Il login non va a buon "
                            + "fine perché la password inserita non è corretta");
                }

            } else {
                return null;
            }

        } catch (SQLException e) {

            return null;
        }
    }

    private int checkUtenteExistence(final Utente utente) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) from Utente where Email=?");
            ps.setString(INDEX1, utente.getEmail());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
              return rs.getInt(INDEX1);
            }

        } catch (SQLException e) {

            return 0;
        }

        return -1;
    }
}
