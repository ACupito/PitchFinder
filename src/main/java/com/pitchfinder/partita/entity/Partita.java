package com.pitchfinder.partita.entity;

import java.sql.Time;
import java.sql.Date;


public class Partita {
    /** This attribute contains the code of the match.*/
    private int idPartita;
    /** This attribute contains the code of the pitch.*/
    private int idCampo;
    /** This attribute contains the email of the user associated with the match.*/
    private String emailUtente;
    /** This attribute contains the data of the match.*/
    private Date data;
    /** This attribute contains the start time of the match.*/
    private Time orarioInizio;
    /** This attribute contains the end time of the match.*/
    private Time orarioFine;

    /**
     * getter for idPartita attribute.
     * @return int
     */
    public int getIdPartita() {
        return idPartita;
    }

    /**
     * setter for idPartita attribute.
     * @param idPartita idPartita.
     */
    public void setIdPartita(int idPartita) {
        this.idPartita = idPartita;
    }

    /**
     * getter for idCampo attribute.
     * @return int
     */
    public int getIdCampo() {
        return idCampo;
    }

    /**
     * setter for idCampo attribute.
     * @param idCampo idCampo.
     */
    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    /**
     * getter for emailUtente.
     * @return String
     */
    public String getEmailUtente() {
        return emailUtente;
    }

    /**
     * setter for emailUtente.
     * @param emailUtente emailUtente.
     */
    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    /**
     * getter for data attribute.
     * @return Date
     */
    public Date getData() {
        return data;
    }

    /**
     * setter for data attribute.
     * @param data data.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * getter for orarioInizio attribute.
     * @return Time
     */
    public Time getOrarioInizio() {
        return orarioInizio;
    }

    /**
     * setter for orarioInizio attribute.
     * @param orarioInizio orarioInizio.
     */
    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    /**
     * getter for orarioFine attribute.
     * @return Time
     */
    public Time getOrarioFine() {
        return orarioFine;
    }

    /**
     * setter for orarioFine attribute.
     * @param orarioFine orarioFine.
     */
    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }
}
