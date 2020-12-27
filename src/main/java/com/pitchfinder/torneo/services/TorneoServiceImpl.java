package com.pitchfinder.torneo.services;

import com.pitchfinder.torneo.dao.TorneoDAO;
import com.pitchfinder.torneo.dao.TorneoDAOImpl;
import com.pitchfinder.torneo.entity.Torneo;

import java.sql.Date;
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

        Torneo torneo = new Torneo();

        torneo.setAdminUsername(usernameAdmin);
        torneo.setCampoIdentificativo(idCampo);
        torneo.setNome(nome);
        torneo.setTipo(tipo);
        torneo.setStruttura(struttura);
        torneo.setNumeroSquadre(maxSquadre);
        torneo.setDataInizio(dataInizio);
        torneo.setDataFine(dateFine);
        torneo.setMinNumeroPartecipantiPerSquadra(minPartecipanti);
        torneo.setMaxNumeroPartecipantiPerSquadra(maxPartecipanti);
        torneo.setGiornoPartite(giornoPartite);

        return tdao.doSaveTorneo(torneo);

    }

    /**
     * This method allows to remove a tournament.
     * @param idCampo pitch identifier
     * @param nome nome of the tournament
     * @param dataInizio dataInizio of the tournament
     * @return boolean -> true : created / false : failed creation
     */
    public boolean deleteTorneo(int idCampo, String nome, Date dataInizio) {

        Torneo torneo = new Torneo();
        torneo.setCampoIdentificativo(idCampo);
        torneo.setNome(nome);
        torneo.setDataInizio(dataInizio);

        return tdao.doRemoveTorneo(torneo);

    }

    /**
     * This method allows to get all tournaments.
     * @return A list of Torneo items
     */
    public List<Torneo> getAllTornei() {

        return tdao.doRetrieveAllTornei();

    }
}
