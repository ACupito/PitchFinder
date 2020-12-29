package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.campo.dao.CampoDAO;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PartitaServiceImpl implements PartitaService {
    /**
     * This method is used by the user during a match booking.
     * @param idCampo idCampo
     * @param utente utente
     * @param date date
     * @param start start
     * @param end end
     * @return Partita
     */
    @Override
    public Partita createPartita(int idCampo, Utente utente, Date date, Time start, Time end) {
        Partita match = new Partita(idCampo, utente.getEmail(), date, start, end);
        PartitaDAO daoPartita = new PartitaDAOImpl();
        CampoDAO daoCampo = new CampoDAOImpl();

        if (!daoCampo.checkOccupazioneExistence(idCampo, date, start, end)) {
            if (daoPartita.doSavePartita(match)) {
                return match;
            }
        }
        return null;
    }

    /**
     * This method is used to view the players participating in a match.
     * @param partita Partita
     * @return List<String>
     */
    @Override
    public List<String> showGiocatori(Partita partita) {
        PartitaDAO dao = new PartitaDAOImpl();
        List<String> giocatori = dao.doRetrieveAllGiocatori(partita.getIdPartita());

        return giocatori;
    }

    /**
     * This method is used to add a player to the players of a match.
     *
     * @param idPartita idPartita
     * @param nome      nome
     * @param cognome   cognome
     * @return boolean
     */
    @Override
    public boolean createGiocatorePartita(int idPartita, String nome, String cognome) {
        PartitaDAO dao = new PartitaDAOImpl();
        dao.doSaveGiocatore(idPartita, nome, cognome);
        return true;

    }


}
