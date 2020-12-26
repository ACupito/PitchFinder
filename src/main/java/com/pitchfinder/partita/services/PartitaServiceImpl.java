package com.pitchfinder.partita.services;

import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;

import java.sql.Date;
import java.sql.Time;

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
        PartitaDAO dao = new PartitaDAOImpl();
        if (dao.doSavePartita(match)) {
            return match;
        }

        return null;
    }
}
