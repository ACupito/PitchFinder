package com.pitchfinder.autenticazione.dao;

import com.pitchfinder.autenticazione.entity.Admin;

public interface AdminDAO {

    /**
     * This method checks weather the subject who is trying to access
     * is a registered admin.
     * @param admin is the admin who wants to log in
     * @return boolean
     */
    Admin checkAdmin(Admin admin);
}
