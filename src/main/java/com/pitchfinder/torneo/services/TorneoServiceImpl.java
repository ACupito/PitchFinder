package com.pitchfinder.torneo.services;

import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import com.pitchfinder.torneo.dao.TorneoDAO;
import com.pitchfinder.torneo.dao.TorneoDAOImpl;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class manages the TorneoService implementation.
 */
public class TorneoServiceImpl implements TorneoService {

    /**
     *  This is an instance of TorneoDAO.
     */
    private final TorneoDAO tdao;

    /**
     * Constructor.
     */
    public TorneoServiceImpl() {
        tdao = new TorneoDAOImpl();
    }

    /**
     * This method allows to create a tournament and invoke daSaveTorneo.
     * @param usernameAdmin admin username
     * @param idCampo pitch identifier
     * @param nome name of the tournament
     * @param tipo type of the tournament
     * @param struttura structure of the tournament
     * @param maxSquadre maximum number of the teams
     * @param dataInizio start date of the tournament
     * @param dateFine end date of the tournament
     * @param minPartecipanti minimum number of players for the team
     * @param maxPartecipanti maximum number of players for the team
     * @param giornoPartite match day of the tournament
     * @return boolean -> true : created / false : failed creation
     */
    @Override
    public boolean createTorneo(String usernameAdmin, int idCampo, String nome, String tipo, String struttura,
                                int maxSquadre, Date dataInizio, Date dateFine, int minPartecipanti,
                                int maxPartecipanti, String giornoPartite) {

        Torneo torneo = new Torneo(nome, tipo, struttura, giornoPartite, usernameAdmin,
                maxSquadre, minPartecipanti, maxPartecipanti, dataInizio,
                dateFine, idCampo);

        if(!insertOccupazione(idCampo, dataInizio, dateFine, giornoPartite, usernameAdmin)) {
            throw new IllegalArgumentException("Creazione fallita: Insert Occupazione fallita");
        }

        if (!tdao.doSaveTorneo(torneo)) {
            deleteOccupazione(idCampo, dataInizio, dateFine, giornoPartite);
           throw new IllegalArgumentException("Creazione fallita: Save Torneo fallito");
        }

        return true;
    }

    /**
     * This method allows to remove a tournament.
     * @param idCampo pitch identifier
     * @param nome nome of the tournament
     * @param dataInizio start date of the tournament
     * @param dataFine end date of the tournament
     * @param giornoPartite match day of the tournament
     * @return boolean -> true : created / false : failed creation
     */

    @Override
    public boolean deleteTorneo(int idCampo, String nome, Date dataInizio, Date dataFine, String giornoPartite) {

        Torneo torneo = new Torneo();
        torneo.setCampoIdentificativo(idCampo);
        torneo.setNome(nome);
        torneo.setDataInizio(dataInizio);
        torneo.setDataFine(dataFine);
        torneo.setGiornoPartite(giornoPartite);

        if (!tdao.doRemoveTorneo(torneo)) {
            throw new IllegalArgumentException("Eliminazione fallita");
        }
        if (!deleteOccupazione(torneo.getCampoIdentificativo(), torneo.getDataInizio(), torneo.getDataFine(), torneo.getGiornoPartite())) {
            Torneo t = tdao.doRetrieveTorneo(torneo.getNome(), torneo.getDataInizio(), torneo.getCampoIdentificativo());
            tdao.doSaveTorneo(t);
            throw new IllegalArgumentException("Eliminazione occupazione fallita");
        }
        return true;
    }

    /**
     * This method allows to get all tournaments.
     * @return A list of Torneo items
     */
    @Override
    public List<Torneo> getAllTornei() {

        return tdao.doRetrieveAllTornei();

    }

    /**
     * This method call doCheckTorneo.
     * @see com.pitchfinder.torneo.dao.TorneoDAOImpl#doCheckTorneo(Date, Date, int)
     * @return boolean : true -> there are other tournaments / false -> empty at the time
     */
    @Override
    public boolean checkScheduledTorneo(Date dataInizio, Date dataFine, int IdCampo) {

         boolean result =  tdao.doCheckTorneo(dataInizio,dataFine,IdCampo);
        if (result) {
           return true;
        }
        return false;
    }

    /**
     * This method allows to get a tournament.
     * @param nome name of the tournament
     * @param dataInizio start date of the tournament
     * @param IdCampo pitch identifier
     * @return Torneo item
     */
    public Torneo getTorneo(String nome, Date dataInizio, int IdCampo) {

        Torneo t = tdao.doRetrieveTorneo(nome, dataInizio, IdCampo);
        if (t == null) {
            throw new IllegalArgumentException("Get Torneo fallito");
        }
        else return t;
    }

    /**
     * This method inserts the tuples for the occupation of the pitch in all the days
     * in which the matches take place.
     * @param idCampo pitch identifier
     * @param startDate start date of the tournament
     * @param endDate end date of the tournament
     * @param giornoPartite match day of the tournament
     */
    private boolean insertOccupazione(int idCampo, Date startDate, Date endDate, String giornoPartite, String adminUsername) {

        CampoDAO campo = new CampoDAOImpl();
        PartitaDAO partita = new PartitaDAOImpl();
        int dayNumber = getNumberDay(giornoPartite);
        //remaining days between start date and end date
        long days = ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate); //set time to start date of the tournament
        Time timeInizio = Time.valueOf("00:00:00"); //start time of occupation
        Time timeFine = Time.valueOf("23:59:59"); //end time of occupation

        while (days > 0) { //get date form start date to end date of the tournament
            if (calendar.get(Calendar.DAY_OF_WEEK) == dayNumber) { //check if the date is a match day

                int day = calendar.get(Calendar.DATE); //get day
                int month = calendar.get(Calendar.MONTH); //get month
                int year = calendar.get(Calendar.YEAR); //get year

                String date = year+"-"+(month+1)+"-"+day; //create date
                Date dateCurrent = Date.valueOf(date);

                List<Partita> allPartite = partita.doRetrieveAll(); //all match
                List<Partita> partiteOccupation = new ArrayList<>(); //all match for rollback
                List<Date> dateOccupation = new ArrayList<>(); //date for rollback

                if (campo.checkOccupazioneExistence(idCampo, dateCurrent, timeInizio, timeFine)) { //check match

                    for (Partita value : allPartite) {
                        if (value.getData().getTime() == dateCurrent.getTime()) {
                            partiteOccupation.add(value);
                        }
                    }

                    dateOccupation.add(dateCurrent);

                    campo.doRemoveOccupazione(idCampo, dateCurrent, timeInizio, timeFine);
                    partita.doRemovePartite(idCampo,dateCurrent,timeInizio,timeFine);
                }

                try {

                    campo.doSaveOccupazione(idCampo,dateCurrent,timeInizio,timeFine,adminUsername);

                } catch (RuntimeException e) { //rollback
                    for (Date d : dateOccupation) {
                        campo.doRemoveOccupazione(idCampo, d, timeInizio, timeFine);
                    }
                    for (Partita p : partiteOccupation) {
                        partita.doSavePartita(p);
                    }
                    return false;
                }

            }
            calendar.add(Calendar.DATE, 1); //advance in the days until the end date of the tournament
            days--;
        }

        return true;

    }

    /**
     * This method eliminates occupation after the tournament is eliminated.
     * @param idCampo pitch identifier
     * @param startDate start date of the tournament
     * @param endDate end date of the tournament
     * @param giornoPartite match day of the tournament
     * @return boolean : true -> successful | false -> failed
     */
    private boolean deleteOccupazione(int idCampo, Date startDate, Date endDate, String giornoPartite) {

        CampoDAO campo = new CampoDAOImpl();

        int dayNumber = getNumberDay(giornoPartite);
        //remaining days between start date and end date
        long days = ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate); //set time to start date of the tournament
        Time timeInizio = Time.valueOf("00:00:00"); //start time of occupation
        Time timeFine = Time.valueOf("23:59:59"); //end time of occupation

        while (days > 0) { //get date form start date to end date of the tournament
            if (calendar.get(Calendar.DAY_OF_WEEK) == dayNumber) { //check if the date is a match day

                int day = calendar.get(Calendar.DATE); //get day
                int month = calendar.get(Calendar.MONTH); //get month
                int year = calendar.get(Calendar.YEAR); //get year

                String date = year+"-"+(month+1)+"-"+day; //create date
                Date dateCurrent = Date.valueOf(date);

                try {
                    campo.doRemoveOccupazione(idCampo, dateCurrent, timeInizio, timeFine);
                } catch (RuntimeException e) {
                    return false;
                }

            }
            calendar.add(Calendar.DATE, 1); //advance in the days until the end date of the tournament
            days--;
        }

        return true;

    }

    private int getNumberDay(String giornoPartite) {
        switch (giornoPartite) { //get day number
            case "Domenica": return  1;
            case "Lunedì": return 2;
            case "Martedì": return  3;
            case "Mercoledì": return 4;
            case "Giovedì": return 5;
            case "Venerdì": return 6;
            case "Sabato": return 7;
        }
        return 0;
    }
}
