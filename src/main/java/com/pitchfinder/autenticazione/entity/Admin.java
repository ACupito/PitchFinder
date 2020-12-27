package com.pitchfinder.autenticazione.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin {

    /**
     * This is the username.
     */
    private String username;

    /**
     * This is the name.
     */
    private String nome;

    /**
     * This is the surname.
     */
    private String cognome;

    /**
     * This is the password.
     */
    private String passwordHash;

    /**
     * Default constructor.
     */
    public Admin() {

    }

    /**
     * @param username is the admin username
     * @param nome is the admin name
     * @param cognome is the admin surname
     * @param passwordHash is the admin password
     */
    public Admin(String username, String nome, String cognome,
                 String passwordHash) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.passwordHash = passwordHash;
    }

    /**
     * This method returns the value of the variable username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method set the value of the variable username.
     * @param usernamePar the admin username
     */
    public void setUsername(String usernamePar) {
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
     * This method sets the value of the variable nome.
     * @param nomePar is the admin name
     */
    public void setNome(String nomePar) {
        this.nome = nomePar;
    }

    /**
     * This method returns the value of the cognome.
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * This method sets the value of the variable cognome.
     * @param cognomePar is the admin surname
     */
    public void setCognome(String cognomePar) {
        this.cognome = cognomePar;
    }

    /**
     * This method returns the value of the passwordHash.
     * @return password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * This method sets the value of the passwordHash.
     * @param password is the admin password
     */
    public void setPassword(String password) {

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
}
