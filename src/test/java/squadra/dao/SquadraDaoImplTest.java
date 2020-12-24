package squadra.dao;

import com.pitchfinder.squadra.dao.SquadraDAO;
import com.pitchfinder.squadra.dao.SquadraDAOImpl;
import com.pitchfinder.squadra.entity.Squadra;
import org.junit.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SquadraDaoImplTest {
    Squadra squadra = new Squadra();
    SquadraDAO squadraDAO = new SquadraDAOImpl();

    /**
     * Team correctly Saved.
     */
    @Test
    public void checkDoSaveSquadraTr(){
        squadra.setNome("Juventus");
        squadra.setTorneoNome("serieA");
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        squadra.setTorneoDataInizio(dataInizio);
        squadra.setTorneoCampoIdentificativo(1002);
        squadra.setNumeroMembri(10);
        squadra.setCapitano("Lucia");
        squadra.setUtenteEmail("mario96@gmail.com");

        assertTrue(squadraDAO.doSaveSquadra(squadra));

    }

    /**
     * The team is duplicated.
     */
    @Test
    public void checkDoSaveSquadraFailure(){
        squadra.setNome("Juventus");
        squadra.setTorneoNome("serieA");
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        squadra.setTorneoDataInizio(dataInizio);
        squadra.setTorneoCampoIdentificativo(1002);
        squadra.setNumeroMembri(10);
        squadra.setCapitano("Lucia");
        squadra.setUtenteEmail("mario96@gmail.com");

        assertThrows(RuntimeException.class ,() -> {squadraDAO.doSaveSquadra(squadra);});

    }

    /**
     * Success.
     */
    @Test
    public void checkDoRemoveTr(){
        squadra.setNome("Juventus");
        squadra.setTorneoNome("serieA");
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        squadra.setTorneoDataInizio(dataInizio);
        squadra.setTorneoCampoIdentificativo(1002);
        squadra.setNumeroMembri(10);
        squadra.setCapitano("Lucia");
        squadra.setUtenteEmail("mario96@gmail.com");

        assertTrue(squadraDAO.doRemoveSquadra(squadra));


    }

    /**
     * Failure, there isn't a team with the name Juventusss.
     */
    @Test
    public void checkDoRemoveFailure(){
        squadra.setNome("Juventusss");
        squadra.setTorneoNome("serieA");
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        squadra.setTorneoDataInizio(dataInizio);
        squadra.setTorneoCampoIdentificativo(1002);
        squadra.setNumeroMembri(10);
        squadra.setCapitano("Lucia");
        squadra.setUtenteEmail("mario96@gmail.com");

        assertFalse(squadraDAO.doRemoveSquadra(squadra));

    }


}
