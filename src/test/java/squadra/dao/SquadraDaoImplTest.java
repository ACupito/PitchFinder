package squadra.dao;

import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import org.junit.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SquadraDaoImplTest {
    SquadraDAO squadraDAO = new SquadraDAOImpl();

    /**
     * Team correctly Saved.
     */
    @Test
    public void checkDoSaveSquadraTr(){

        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");

        assertTrue(squadraDAO.doSaveSquadra(squadra));
    }

    /**
     * Failure, there isn't a tournament called serieAA in database.
     */
    @Test
    public void checkDoSaveSquadraFailure(){

        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventus", "serieAA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertThrows(RuntimeException.class ,() -> {squadraDAO.doSaveSquadra(squadra);});

    }

    /**
     * Success.
     */
    @Test
    public void checkDoRemoveTr(){

        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertTrue(squadraDAO.doRemoveSquadra(squadra));
    }

    /**
     * Failure, there isn't a team with the name Juventusss.
     */
    @Test
    public void checkDoRemoveFailure(){

        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventusss", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertFalse(squadraDAO.doRemoveSquadra(squadra));

    }

    /**
     * Success.
     */
    @Test
    public void checkDoSaveGiocatoreSquadraTr() {
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertTrue(squadraDAO.doSaveGiocatoreSquadra("Lucia","Gaeta", squadra));

    }

    /**
     * Failure, there isn't a team with name Juventusss.
     */
    @Test
    public void checkDoSaveGiocatoreSquadraFailure() {
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventusss", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertThrows(RuntimeException.class,()->{squadraDAO.doSaveGiocatoreSquadra("Lucia","Gaeta", squadra);});
    }

}
