package com.pitchfinder.squadra.dao;


import com.pitchfinder.squadra.entity.Squadra;

public class SquadraDAOImpl implements SquadraDAO {

    /**
     * If squadra is correctly SAVED in db return true else return false.
     * @param squadra - Squadra that wants to be saved in the database.
     * @return boolean
     */
    @Override
    public boolean doSaveSquadra(Squadra squadra) {

        return false;

    }

    /**
     * If squadra is correctly REMOVED from database return true else return false.
     * @param squadra - Squadra that wants to be removed from database
     * @return
     */
    @Override
    public boolean doRemoveSquadra(Squadra squadra) {

        return false;

    }

}
