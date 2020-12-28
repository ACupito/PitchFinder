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
    public boolean doSaveUtente(Utente utente) {

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
    public Utente checkUtente(Utente utente) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            int nUtenti = checkUtenteExistence(utente);

            PreparedStatement ps = con.prepareStatement(
                    "select * from Utente where Username=?");
            ps.setString(INDEX1, utente.getUsername());

            if (nUtenti == 1) {

                ResultSet rs = ps.executeQuery();
                rs.next();

                if (rs.getString(INDEX5).equalsIgnoreCase(utente.getPasswordHash())) {
                    utente.setEmail(rs.getString(INDEX1));
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

    private int checkUtenteExistence(Utente utente) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) from Utente where Username=?");
            ps.setString(INDEX1, utente.getUsername());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
              return rs.getInt(INDEX1);
            }

        } catch (SQLException e) {

            return 0;
        }

        return -1;
    }

    /**
     * Method to retrive a user given his email.
     * @param u is the user email
     * @return utente
     */
    public Utente doRetrieveUtenteByEmail(Utente u) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "select username, nome, cognome from Utente where email=?");

            ps.setString(INDEX1, u.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String username = rs.getString(INDEX1);
                String nome = rs.getString(INDEX2);
                String cognome = rs.getString(INDEX3);

                return new Utente(u.getEmail(), username, nome, cognome,
                        null, null);

            } else return null;

        } catch (SQLException e) {

            return null;
        }
    }

    /**
     * @param u is the user
     * @return boolean
     */
    public boolean doRemoveUtente(Utente u) {

        try (Connection con = ConPool.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "delete from Utente where username=?");

            ps.setString(INDEX1, u.getUsername());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            return false;
        }
    }
}
