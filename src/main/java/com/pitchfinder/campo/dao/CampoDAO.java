package com.pitchfinder.campo.dao;

import com.pitchfinder.campo.entity.Campo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CampoDAO {
    public Campo getCampo(int id);
    public boolean doSaveOccupazione(int id_campo, Date data, Time inizio, Time fine, String username_admin);
    public boolean doRemoveOccupazione(int id_campo, Date data, Time inizio, Time fine);
    public boolean doSaveDisponibilita(String email_utente, int id_campo, Date data, Time inizio, Time fine );
    public boolean doRemoveDisponibilita(String email_utente,  int id_campo);
    public List<String> doRetriveEmailByDisponibilita(int id_campo, Date data, Time inizio, Time fine);
}
