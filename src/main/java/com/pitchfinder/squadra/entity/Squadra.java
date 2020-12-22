package com.pitchfinder.squadra.entity;

import java.util.Date;
import java.util.Objects;

public class Squadra {
    /**
     * Is the name of the team
     */
    private String nome;

    /**
     * Is the name of the tournament
     */
    private String torneoNome;

    /**
     * The start date of the tournament
     */
    private Date torneoDataInizio;

    /**
     * Is the id of the tournament's pitch
     */
    private int torneoCampoIdentificativo;

    /**
     * Is the number of players
     */
    private int numeroMembri;

    /**
     * Is the captain of the team
     */
    private String capitano;

    /**
     * Is the user email
     */
    private String utenteEmail;

    /**
     * Constructor void
     */
    public Squadra() {
    }

    public Squadra(String nome, String torneoNome, Date torneoDataInizio, int torneoCampoIdentificativo, int numeroMembri, String capitano, String utenteEmail) {
        this.nome = nome;
        this.torneoNome = torneoNome;
        this.torneoDataInizio = torneoDataInizio;
        this.torneoCampoIdentificativo = torneoCampoIdentificativo;
        this.numeroMembri = numeroMembri;
        this.capitano = capitano;
        this.utenteEmail = utenteEmail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTorneoNome() {
        return torneoNome;
    }

    public void setTorneoNome(String torneoNome) {
        this.torneoNome = torneoNome;
    }

    public Date getTorneoDataInizio() {
        return torneoDataInizio;
    }

    public void setTorneoDataInizio(Date torneoDataInizio) {
        this.torneoDataInizio = torneoDataInizio;
    }

    public int getTorneoCampoIdentificativo() {
        return torneoCampoIdentificativo;
    }

    public void setTorneoCampoIdentificativo(int torneoCampoIdentificativo) {
        this.torneoCampoIdentificativo = torneoCampoIdentificativo;
    }

    public int getNumeroMembri() {
        return numeroMembri;
    }

    public void setNumeroMembri(int numeroMembri) {
        this.numeroMembri = numeroMembri;
    }

    public String getCapitano() {
        return capitano;
    }

    public void setCapitano(String capitano) {
        this.capitano = capitano;
    }

    public String getUtenteEmail() {
        return utenteEmail;
    }

    public void setUtenteEmail(String utenteEmail) {
        this.utenteEmail = utenteEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return torneoCampoIdentificativo == squadra.torneoCampoIdentificativo && numeroMembri == squadra.numeroMembri && Objects.equals(nome, squadra.nome) && Objects.equals(torneoNome, squadra.torneoNome) && Objects.equals(torneoDataInizio, squadra.torneoDataInizio) && Objects.equals(capitano, squadra.capitano) && Objects.equals(utenteEmail, squadra.utenteEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, torneoNome, torneoDataInizio, torneoCampoIdentificativo, numeroMembri, capitano, utenteEmail);
    }

    @Override
    public String toString() {
        return "Squadra{" +
                "nome='" + nome + '\'' +
                ", torneoNome='" + torneoNome + '\'' +
                ", torneoDataInizio=" + torneoDataInizio +
                ", torneoCampoIdentificativo=" + torneoCampoIdentificativo +
                ", numeroMembri=" + numeroMembri +
                ", capitano='" + capitano + '\'' +
                ", utenteEmail='" + utenteEmail + '\'' +
                '}';
    }
}
