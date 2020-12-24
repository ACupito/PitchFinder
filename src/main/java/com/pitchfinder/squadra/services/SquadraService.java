package com.pitchfinder.squadra.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.entity.Torneo;

public interface SquadraService {
    /**
     * Create a new Squadra.
     * @param nome
     * @param torneo
     * @param numeroMwmbri
     * @param capitano
     * @param utente
     * @return Squadra
     */
    Squadra createSquadra(String nome, Torneo torneo, int numeroMwmbri, String capitano, Utente utente);


}
