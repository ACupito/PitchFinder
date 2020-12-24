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

        Squadra squadra = new Squadra();
        squadra.setNome(nome);
        squadra.setTorneoNome(torneo.getNome());
        squadra.setNumeroMembri(numeroMembri);
        squadra.setTorneoCampoIdentificativo(torneo.getCampoIdentificativo());
        squadra.setTorneoDataInizio(torneo.getDataInizio());
        squadra.setCapitano(capitano);
        squadra.setUtenteEmail(utente.getEmail());

        SquadraDAO squadraDAO = new SquadraDAOImpl();

            if (squadraDAO.doSaveSquadra(squadra)) {
                return squadra;
            }

            return null;

    }
}
