package com.pitchfinder.autenticazione.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class Utente {

    /**
     * This is the user email.
     */
    private String email;

    /**
     * This is the user username.
     */
    private String username;

    /**
     * This is the user name.
     */
    private String nome;

    /**
     * This is the user surname.
     */
    private String cognome;

    /**
     * This is the user password.
     */
    private String passwordHash;

    /**
     * This is the user date of birth.
     */
    private Date dataDiNascita;

    /**
     * Default constructor.
     */
    public Utente() {

    }

    /**
     *
     * @param email is the user email
     * @param username is the user username
     * @param nome is the user name
     * @param cognome is the user surname
     * @param passwordHash is the user password
     * @param dataDiNascita is the user date of birth
     */
    public Utente(String email, String username, String nome, String cognome,
                  String passwordHash, Date dataDiNascita) {
        this.email = email;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.passwordHash = passwordHash;
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * This method returns the value od the variable email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the value of the variable email.
     * @param emailPar is the user email
     */
    public void setEmail(final String emailPar) {
        this.email = emailPar;
    }

    /**
     * This method returns the value of the variable username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the value of the variable username.
     * @param usernamePar is the user username
     */
    public void setUsername(final String usernamePar) {
        this.username = usernamePar;
    }

    /**
     * This method returns the value of the variable nome.
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * This sets the value of the variable nome.
     * @param nomePar is the user name
     */
    public void setNome(final String nomePar) {
        this.nome = nomePar;
    }

    /**
     * This method returns the value of the variable cognome.
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * This method sets the value of the variable cognome.
     * @param cognomePar is the user surname
     */
    public void setCognome(final String cognomePar) {
        this.cognome = cognomePar;
    }

    /**
     * This method returns the value of the variable passwordHash.
     * @return passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * This method sets the value of the variable passwordHash.
     * @param password is the user password
     */
    public void setPassword(final String password) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));

            this.passwordHash = String.format("%040x",
                    new BigInteger(1, digest.digest()));

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * This method returns the value of the variable dataDiNascita.
     * @return dataDiNascita
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * This method sets the vaue of the variable dataDiNascita.
     * @param dataDiNascitaPar is the user date of birth
     */
    public void setDataDiNascita(final Date dataDiNascitaPar) {

        this.dataDiNascita = dataDiNascitaPar;
    }
}
