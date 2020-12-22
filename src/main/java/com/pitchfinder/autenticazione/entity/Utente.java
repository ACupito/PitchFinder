package com.pitchfinder.autenticazione.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
     * This method returns the value od the variable email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the value of the variable email.
     * @param emailPar
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
     * @param usernamePar
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
     * @param nomePar
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
     * @param cognomePar
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
     * @param password
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
     * @param dataDiNascitaPar
     */
    public void setDataDiNascita(final Date dataDiNascitaPar) {
        this.dataDiNascita = dataDiNascitaPar;
    }
}
