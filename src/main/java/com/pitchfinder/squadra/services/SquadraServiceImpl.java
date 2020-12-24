package com.pitchfinder.squadra.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.entity.Torneo;

public class SquadraServiceImpl implements SquadraService {

    /**
     * Create a new Squadra, the team is registered for the tournament.
     * @param nome
     * @param torneo
     * @param numeroMembri
     * @param capitano
     * @param utente
     * @return
     */
    @Override
    public Squadra createSquadra(String nome, Torneo torneo, int numeroMembri, String capitano, Utente utente) {

        Squadra squadra =
                new Squadra(nome, torneo.getNome(), torneo.getDataInizio(),
                        torneo.getCampoIdentificativo(), numeroMembri, capitano,
                        utente.getEmail());

        SquadraDAO squadraDAO = new SquadraDAOImpl();

            if (squadraDAO.doSaveSquadra(squadra)) {
                return squadra;
            }

            return null;

    }
}
