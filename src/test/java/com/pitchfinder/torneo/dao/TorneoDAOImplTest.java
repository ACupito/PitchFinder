package com.pitchfinder.torneo.dao;

import com.pitchfinder.autenticazione.dao.UtenteDAO;
import com.pitchfinder.autenticazione.dao.UtenteDAOImpl;
import com.pitchfinder.autenticazione.entity.Utente;
import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.torneo.entity.Torneo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class manages the test for TorneoDAOImpl.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TorneoDAOImplTest {

    /**
     *  This is an instance of TorneoDAO.
     */
    private TorneoDAO tdao;

    /**
     *  This is an instance of SquadraDAO.
     */
    private SquadraDAO sdao;

    /**
     *  This is an instance of SquadraDAO.
     */
    private UtenteDAO udao = new UtenteDAOImpl();

    /**
     * Parameters declaration.
     */
    private static final String USERNAME_ADMIN = "memex99";
    private static final String NOME = "Champions";
    private static final int ID_CAMPO = 1002;
    private static final String TIPO = "Gironi";
    private static final String STRUTTURA = "Partite singole";
    private static final int MAX_SQUADRE = 12;
    private static final int MIN_PARTECIPANTI = 1;
    private static final int MAX_PARTECIPANTI = 5;
    private static final String GIORNO_PARTITE = "Lunedì";
    private static final String DATA_INIZIO = "2020-12-24";
    private static final String DATA_FINE = "2020-12-31";

    /**
     * This is an instance of Torneo entity.
     */
    private Torneo t, tcount;

    /**
     * This is an instance of Squadra entity.
     */
    private Squadra s;
    private Squadra s1;

    /**
     * This is an instance of Utente entity.
     */
    private Utente u;
    /**
     * This method set up the enviroment.
     */
    @BeforeAll
    public void setUp() {
        tdao = new TorneoDAOImpl();
        sdao = new SquadraDAOImpl();
        t = new Torneo();
        u = new Utente();
        tcount = new Torneo();
    }

    /**
     * This method tests the method doSaveTorneo in case it fails.
     */
    @Test
    void check_1() {

        t.setAdminUsername("");
        t.setNome("");
        t.setCampoIdentificativo(0);
        t.setTipo(TIPO);
        t.setStruttura(STRUTTURA);
        t.setNumeroSquadre(MAX_SQUADRE);
        t.setMinNumeroPartecipantiPerSquadra(MIN_PARTECIPANTI);
        t.setMaxNumeroPartecipantiPerSquadra(MAX_PARTECIPANTI);
        t.setGiornoPartite(GIORNO_PARTITE);
        t.setDataInizio(Date.valueOf(DATA_INIZIO));
        t.setDataFine(Date.valueOf(DATA_FINE));

        assertFalse(tdao.doSaveTorneo(t));

    }

    /**
     * This method tests the method doSaveTorneo in case it successful.
     */
    @Test
    void check_2() {

        t.setAdminUsername(USERNAME_ADMIN);
        t.setNome(NOME);
        t.setCampoIdentificativo(ID_CAMPO);
        t.setTipo(TIPO);
        t.setStruttura(STRUTTURA);
        t.setNumeroSquadre(MAX_SQUADRE);
        t.setMinNumeroPartecipantiPerSquadra(MIN_PARTECIPANTI);
        t.setMaxNumeroPartecipantiPerSquadra(MAX_PARTECIPANTI);
        t.setGiornoPartite(GIORNO_PARTITE);
        t.setDataInizio(Date.valueOf(DATA_INIZIO));
        t.setDataFine(Date.valueOf(DATA_FINE));

        assertTrue(tdao.doSaveTorneo(t));

    }

    /**
     * This method tests the method doCheckTorneo in case it fails.
     */
    @Test
    void check_3() {

        assertTrue(tdao.doCheckTorneo(Date.valueOf(DATA_INIZIO), Date.valueOf(DATA_FINE), ID_CAMPO));

    }

    /**
     * This method tests the method doCheckTorneo in case it successful.
     */
    @Test
    void check_4() {
        assertFalse(tdao.doCheckTorneo(Date.valueOf("2021-01-01"), Date.valueOf("2021-01-05"), ID_CAMPO));
    }

    /**
     * This method tests the method doRetrieveAllTornei.
     */
    @Test
    void check_5() {
        assertNotNull(tdao.doRetrieveAllTornei());
    }

    /**
     * This method tests the method doRetrieveTorneo in case it fails.
     */
    @Test
    void check_6() {
        assertNull(tdao.doRetrieveTorneo(null,Date.valueOf(DATA_INIZIO),0));
    }

    /**
     * This method tests the method doRetrieveTorneo in case it successful.
     */
    @Test
    void check_7() {
        assertNotNull(tdao.doRetrieveTorneo(NOME,Date.valueOf(DATA_INIZIO),ID_CAMPO));
    }


    /**
     * This method tests the method doRemoveTorneo in case it fails.
     */
    @Test
    void check_8() {

        t.setAdminUsername("");
        t.setNome("");
        t.setCampoIdentificativo(0);
        t.setTipo(TIPO);
        t.setStruttura(STRUTTURA);
        t.setNumeroSquadre(MAX_SQUADRE);
        t.setMinNumeroPartecipantiPerSquadra(MIN_PARTECIPANTI);
        t.setMaxNumeroPartecipantiPerSquadra(MAX_PARTECIPANTI);
        t.setGiornoPartite(GIORNO_PARTITE);
        t.setDataInizio(Date.valueOf(DATA_INIZIO));
        t.setDataFine(Date.valueOf(DATA_FINE));


        assertFalse(tdao.doRemoveTorneo(t));

    }

    /**
     * This method tests the method doRemoveTorneo in case it successful.
     */
    @Test
    void check_9() {

        t.setAdminUsername(USERNAME_ADMIN);
        t.setNome(NOME);
        t.setCampoIdentificativo(ID_CAMPO);
        t.setTipo(TIPO);
        t.setStruttura(STRUTTURA);
        t.setNumeroSquadre(MAX_SQUADRE);
        t.setMinNumeroPartecipantiPerSquadra(MIN_PARTECIPANTI);
        t.setMaxNumeroPartecipantiPerSquadra(MAX_PARTECIPANTI);
        t.setGiornoPartite(GIORNO_PARTITE);
        t.setDataInizio(Date.valueOf(DATA_INIZIO));
        t.setDataFine(Date.valueOf(DATA_FINE));

        assertTrue(tdao.doRemoveTorneo(t));

    }

    @Test
    void countIscritti() {

        tcount.setAdminUsername("memex99");
        tcount.setNome("Torneo");
        tcount.setCampoIdentificativo(1002);
        tcount.setTipo(TIPO);
        tcount.setStruttura(STRUTTURA);
        tcount.setNumeroSquadre(MAX_SQUADRE);
        tcount.setMinNumeroPartecipantiPerSquadra(MIN_PARTECIPANTI);
        tcount.setMaxNumeroPartecipantiPerSquadra(MAX_PARTECIPANTI);
        tcount.setGiornoPartite(GIORNO_PARTITE);
        tcount.setDataInizio(Date.valueOf(DATA_INIZIO));
        tcount.setDataFine(Date.valueOf(DATA_FINE));
        tdao.doSaveTorneo(tcount);
        u = new Utente("ciao@gmail.com", "test", "test", "test","test", Date.valueOf("1998-10-12"));
        udao.doSaveUtente(u);
        s = new Squadra("juve", tcount.getNome(), tcount.getDataInizio(), tcount.getCampoIdentificativo(),2,"lucia","ciao@gmail.com");
        sdao.doSaveSquadra(s);
        s1 = new Squadra("ve", tcount.getNome(), tcount.getDataInizio(), tcount.getCampoIdentificativo(),2,"lucia","ciao@gmail.com");
        sdao.doSaveSquadra(s1);
        assertEquals(2,tdao.doRetrieveNIscritti(tcount));

    }


    /**
     * Cleanup the environment.
     */
    @AfterAll
    void tearDown() {
        tdao.doRemoveTorneo(t);
        tdao.doRemoveTorneo(tcount);
        tdao = null;
        t = null;
        tcount = null;
        udao.doRemoveUtente(u);
        sdao.doRemoveSquadra(s);
        sdao.doRemoveSquadra(s1);
    }

}