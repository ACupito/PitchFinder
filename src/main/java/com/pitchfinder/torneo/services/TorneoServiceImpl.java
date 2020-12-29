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
           throw new IllegalArgumentException("Creazione fallita: Save Torneo fallito");
        }

        return true;
    }

    /**
     * This method allows to remove a tournament.
     * @param idCampo pitch identifier
     * @param nome nome of the tournament
     * @param dataInizio dataInizio of the tournament
     * @return boolean -> true : created / false : failed creation
     */
    @Override
    public boolean deleteTorneo(int idCampo, String nome, Date dataInizio) {

        Torneo torneo = new Torneo();
        torneo.setCampoIdentificativo(idCampo);
        torneo.setNome(nome);
        torneo.setDataInizio(dataInizio);

        boolean result = tdao.doRemoveTorneo(torneo);
        if (!result) {
            throw new IllegalArgumentException("Eliminazione fallita");
        }
        return true;
    }

    /**
     * This method allows to get all tournaments.
     * @return A list of Torneo items
     */
    @Override
    public List<Torneo> getAllTornei() {

        List<Torneo> tornei = tdao.doRetrieveAllTornei();
        return tornei;

    }

    /**
     * This method call doCheckTorneo.
     * @see com.pitchfinder.torneo.dao.TorneoDAOImpl#doCheckTorneo(Date, Date, int)
     * @return boolean : true -> there are other tournaments / false -> empty at the time
     */
    @Override
    public boolean checkScheduledTorneo(Date dataInizio, Date dataFine, int IdCampo) {

        return tdao.doCheckTorneo(dataInizio,dataFine,IdCampo);
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
        int dayNumber = 0;
        switch (giornoPartite) { //get day number
            case "Domenica": dayNumber = 1; break;
            case "Lunedì": dayNumber = 2; break;
            case "Martedì": dayNumber = 3; break;
            case "Mercoledì": dayNumber = 4; break;
            case "Giovedì": dayNumber = 5; break;
            case "Venerdì": dayNumber = 6; break;
            case "Sabato": dayNumber = 7; break;
        }

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

                if (campo.checkOccupazioneExistence(idCampo, dateCurrent, timeInizio, timeFine)) { //check match
                    campo.doRemoveOccupazione(idCampo, dateCurrent, timeInizio, timeFine);
                    partita.doRemovePartite(idCampo,dateCurrent,timeInizio,timeFine);
                }

                try {
                    campo.doSaveOccupazione(idCampo,dateCurrent,timeInizio,timeFine,adminUsername);
                } catch (RuntimeException e) {
                    return false;
                }

            }
            calendar.add(Calendar.DATE, 1); //advance in the days until the end date of the tournament
            days--;
        }

        return true;

    }
}
