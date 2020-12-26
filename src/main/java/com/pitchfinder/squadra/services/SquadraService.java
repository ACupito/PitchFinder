package com.pitchfinder.squadra.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.entity.Torneo;

public interface SquadraService {
    /**
     * Create a new Squadra.
     * @param nome - nome.
     * @param torneo - torneo.
     * @param numeroMembri - numeroMembri.
     * @param capitano - capitano.
     * @param utente - utente.
     * @return Squadra
     */
    Squadra createSquadra(String nome, Torneo torneo, int numeroMembri, String capitano, Utente utente);

    /**
     * Create a new player.
     * @param nome - nome.
     * @param cognome - cognome.
     * @param squadra - squadra.
     * @return String
     */
    String createGiocatoreSquadra(String nome, String cognome, Squadra squadra);


}
