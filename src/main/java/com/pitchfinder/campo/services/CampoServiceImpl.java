package com.pitchfinder.campo.services;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import com.pitchfinder.campo.dao.CampoDAOImpl;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CampoServiceImpl implements CampoService {

    /**
     * This methods manages the creation of the occupation.
     * @param idCampo       is the id of the pitch.
     * @param data          is the date of the occupation.
     * @param inizio        is the start of the occupation.
     * @param fine          is the end of the occupation.
     * @param usernameAdmin is the username of the admin.
     * @return boolean
     */
    public boolean createOccupazione(int idCampo, Date data, Time inizio, Time fine, String usernameAdmin) {

        CampoDAOImpl c = new CampoDAOImpl();
        PartitaDAOImpl pa = new PartitaDAOImpl();
        List<Partita> listPartite = new ArrayList<Partita>();
        Partita p = new Partita();


            if (c.checkOccupazioneExistence(idCampo, data, inizio, fine)) {

                listPartite.addAll(pa.doRetrieveAll());

                for (int i = 0; i < listPartite.size(); i++) {
                    p = listPartite.get(i);
                    if (p.getOrarioInizio().getTime() >= inizio.getTime() || p.getOrarioFine().getTime() <= fine.getTime()) {
                    pa.doRemovePartite(idCampo, data, inizio, fine);
                    c.doRemoveOccupazione(idCampo, data, inizio, fine);

                    }
                }

        }
        c.doSaveOccupazione(idCampo, data, inizio, fine, usernameAdmin);
            return true;



    }

    /**
     *This methods manages the deletion of the occupation.
     * @param idCampo       is the id of the pitch.
     * @param data          is the date of the occupation.
     * @param inizio        is the start of the occupation.
     * @param fine          is the end of the occupation.
     * @return boolean
     */
    public boolean deleteOccupazione(int idCampo, Date data, Time inizio, Time fine) {
        CampoDAOImpl c = new CampoDAOImpl();
        PartitaDAOImpl pa = new PartitaDAOImpl();

        c.doRemoveOccupazione(idCampo, data, inizio, fine);
        pa.doRemovePartite(idCampo, data, inizio, fine);
            return true;


    }

    /**
     * This methods checks the existence of the occupation.
     * @param id     is the id of the pitch.
     * @param data   is the date of the occupation.
     * @param inizio is the start of the occupation.
     * @param fine   is the end of the occupation.
     * @return boolean
     */
    public boolean controllaOccupazione(int id, Date data, Time inizio, Time fine) {
        CampoDAOImpl c = new CampoDAOImpl();
        if (c.checkOccupazioneExistence(id, data, inizio, fine)) {
            return true;
        }
        return false;
    }

    /**
     * This methods manages the creation of the availability.
     * @param emailUtente is the email of the user.
     * @param idCampo     is the id of the pitch.
     * @param data        is the date of the availability.
     * @param inizio      is the start of the availability.
     * @param fine        is the end of the availability.
     * @return boolean
     */
    public boolean createDisponibilita(String emailUtente, int idCampo, Date data, Time inizio, Time fine) {
        CampoDAOImpl c = new CampoDAOImpl();
        c.doSaveDisponibilita(emailUtente, idCampo, data, inizio, fine);
         return true;
    }

    /**
     * This methods manages the viewing of the availabilities.
     *
     * @param idCampo is the id of the pitch.
     * @param data    is the date of the availability.
     * @param inizio  is the start of the availability.
     * @param fine    is the end of the availability.
     * @return List<Utente>
     */

    public List<Utente> showAllDisponibilita(int idCampo, Date data, Time inizio, Time fine) {
        CampoDAOImpl c = new CampoDAOImpl();
        AutenticazioneServiceImpl ut = new AutenticazioneServiceImpl();
        Utente u = new Utente();
        List<Utente> listaUtenti = new ArrayList<>();
        int i;

        List<String> emailList = new ArrayList<>();
        emailList.addAll(c.doRetriveEmailByDisponibilita(idCampo, data, inizio, fine));
        for (i = 0; i < emailList.size(); i++) {
            String email = emailList.get(i);
            u = ut.prelevaUtenteByEmail(email);
            listaUtenti.add(u);
        }
        return listaUtenti;
    }
}


