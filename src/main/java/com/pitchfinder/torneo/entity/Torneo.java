package com.pitchfinder.torneo.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * This class manages the Torneo entity.
 */

public class Torneo {

    /**
     * This is the name of the tournament.
     */
    private String nome;

    /**
     * This is the type of the tournament.
     */
    private String tipo;

    /**
     * This is the structure of the tournament.
     */
    private String struttura;

    /**
     * This is the day all tournament matches are played.
     */
    private String giornoPartite;

    /**
     * This is the username of the Admin who created the tournament.
     */
    private String adminUsername;

    /**
     * This is the maximum number of teams that can register to the tournament.
     */
    private int numeroSquadre;

    /**
     * This is the minimum number of players for the team.
     */
    private int minNumeroPartecipantiPerSquadra;

    /**
     * This is the maximum number of players for the team.
     */
    private int maxNumeroPartecipantiPerSquadra;

    /**
     * This is the start date of the tournament.
     */
    private Date dataInizio;

    /**
     * This is the end date of the tournament.
     */
    private Date dataFine;

    /**
     * This is the pitch identifier.
     */
    private int campoIdentificativo;

    /**
     * Constructor for the Torneo entity.
     * @param name name of the tournament
     * @param type type of the tournament
     * @param structure structure of the tournament
     * @param matchDay match day of the tournament
     * @param username admin username
     * @param numberTeams maximum number of the teams
     * @param minNumberPlayers minimum number of players for the team
     * @param maxNumberPlayers maximum number of players for the team
     * @param startDate start date of the tournament
     * @param endDate end date of the tournament
     * @param pitchId identifier of the pitch
     */

    public Torneo(final String name, final String type,
                  final String structure, final String matchDay,
                  final String username, final int numberTeams,
                  final int minNumberPlayers, final int maxNumberPlayers,
                  final Date startDate, final Date endDate,
                  final int pitchId) {
        this.nome = name;
        this.tipo = type;
        this.struttura = structure;
        this.giornoPartite = matchDay;
        this.adminUsername = username;
        this.numeroSquadre = numberTeams;
        this.minNumeroPartecipantiPerSquadra = minNumberPlayers;
        this.maxNumeroPartecipantiPerSquadra = maxNumberPlayers;
        this.dataInizio = startDate;
        this.dataFine = endDate;
        this.campoIdentificativo = pitchId;
    }

    /**
     * Constructor empty for the Torneo entity.
     */
    public Torneo() {

    }

    /**
     * This method returns the name of the tournament.
     * @return Nome : String
     */
    public String getNome() {
        return nome;
    }

    /**
     * This method allows to set the name of the tournament.
     * @param name name of the tournament
     */
    public void setNome(final String name) {
        this.nome = name;
    }

    /**
     * This method returns the type of the tournament.
     * @return Tipo : String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * This method allows to set the type of the tournament.
     * @param type type of the tournament
     */
    public void setTipo(final String type) {
        this.tipo = type;
    }

    /**
     * This method returns the structure of the tournament.
     * @return Struttura : String
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * This method allows to set the structure of the tournament.
     * @param structure structure of the tournament
     */
    public void setStruttura(final String structure) {
        this.struttura = structure;
    }

    /**
     * This method returns the match day of the tournament.
     * @return GiornoPartite : String
     */
    public String getGiornoPartite() {
        return giornoPartite;
    }

    /**
     * This method allows to set the match day of the tournament.
     * @param matchDay match day of the tournament
     */
    public void setGiornoPartite(final String matchDay) {
        this.giornoPartite = matchDay;
    }

    /**
     * This method returns the username of admin who created the tournament.
     * @return AdminUsername : String
     */
    public String getAdminUsername() {
        return adminUsername;
    }

    /**
     * This method allows to set username of admin who created the tournament.
     * @param username admin username
     */
    public void setAdminUsername(final String username) {
        this.adminUsername = username;
    }

    /**
     * This method returns the maximum number of teams
     * that can register to the tournament.
     * @return NumeroSquadre : int
     */
    public int getNumeroSquadre() {
        return numeroSquadre;
    }

    /**
     * This method allows to set the maximum number of teams
     * that can register to the tournament.
     * @param numberTeams maximum number of the teams
     */
    public void setNumeroSquadre(final int numberTeams) {
        this.numeroSquadre = numberTeams;
    }

    /**
     * This method returns the minimum number of players for the team.
     * @return MinNumeroPartecipantiPerSquadra : int
     */
    public int getMinNumeroPartecipantiPerSquadra() {
        return minNumeroPartecipantiPerSquadra;
    }

    /**
     * This method allows to set the minimum number of players for the team.
     * @param minNumberPlayers minimum number of player for the team
     */
    public void setMinNumeroPartecipantiPerSquadra(
            final int minNumberPlayers) {
        this.minNumeroPartecipantiPerSquadra = minNumberPlayers;
    }

    /**
     * This method returns the maximum number of players for the team.
     * @return MaxNumeroPartecipantiPerSquadra : int
     */
    public int getMaxNumeroPartecipantiPerSquadra() {
        return maxNumeroPartecipantiPerSquadra;
    }

    /**
     * This method allows to set the maximum number of players for the team.
     * @param maxNumberPlayers maximum number of players for the team
     */
    public void setMaxNumeroPartecipantiPerSquadra(
            final int maxNumberPlayers) {
        this.maxNumeroPartecipantiPerSquadra = maxNumberPlayers;
    }

    /**
     * This method returns the start date of the tournament.
     * @return DataInizio : java.sql.Date
     */
    public Date getDataInizio() {
        return dataInizio;
    }

    /**
     * This method allows to set the start date of the tournament.
     * @param startDate start date of the tournament
     */
    public void setDataInizio(final Date startDate) {
        this.dataInizio = startDate;
    }

    /**
     * This method returns the end date of the tournament.
     * @return DataFine : Date
     */
    public Date getDataFine() {
        return dataFine;
    }

    /**
     * This method allows to set the end date of the tournament.
     * @param endDate end date of the tournament
     */
    public void setDataFine(final Date endDate) {
        this.dataFine = endDate;
    }

    /**
     * This method returns the pitch identifier.
     * @return CampoIdentificativo : int
     */
    public int getCampoIdentificativo() {
        return campoIdentificativo;
    }

    /**
     * This method allows to set the pitch identifier.
     * @param pitchId pitch identifier
     */
    public void setCampoIdentificativo(
            final int pitchId) {
        this.campoIdentificativo = pitchId;
    }
    
}
