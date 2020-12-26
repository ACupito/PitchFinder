package squadra.services;

import com.pitchfinder.autenticazione.entity.Utente;
import static org.junit.jupiter.api.Assertions.*;

import com.pitchfinder.squadra.entity.Squadra;
import com.pitchfinder.squadra.services.SquadraService;
import com.pitchfinder.squadra.services.SquadraServiceImpl;
import com.pitchfinder.torneo.entity.Torneo;
import org.junit.Test;

import java.sql.Date;



public class SquadraServiceImplTest {
    SquadraService squadraService = new SquadraServiceImpl();

    /**
     * Success, create a new Squadra!
     */
    @Test
    public void checkCreateSquadraNotNull(){
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Date dataFine = new Date(2022-1900, 12-1, 11);
        Date dataDiNascita = new Date(1998-1900,12-1,10);
        Torneo torneo = new Torneo("serieA", "tipo", "gironi", "lunedi",
                "memex99", 20, 10,
                12, dataInizio, dataFine, 1002);
        Utente utente = new Utente("manuzzi97@gmail.com","memex97","Emanuele","Mezzi","ciao", dataDiNascita);

        assertNotNull(squadraService.createSquadra("Palermo",torneo, 10, "Insigne",utente));

    }

    /**
     * Failure, torneo there isn't in the database!
     */
    @Test
    public void checkCreateSquadraException(){
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Date dataFine = new Date(2022-1900, 12-1, 11);
        Date dataDiNascita = new Date(1998-1900,12-1,10);
        Torneo torneo = new Torneo("seriec", "tipo", "gironi", "lunedi",
                "memex99", 20, 10,
                12, dataInizio, dataFine, 1002);
        Utente utente = new Utente();
        utente.setEmail("lucia.gaeta.12@gmail.com");

        assertThrows(RuntimeException.class, ()->{squadraService.createSquadra("Torino",torneo, 10, "Lucia",utente);});

    }

    /**
     * Success.
     */
    @Test
    public void checkCreateGiocatoreSuccess(){
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventus", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertNotNull(squadraService.createGiocatoreSquadra("Eugenio","DeSimone", squadra));
    }

    /**
     * Failure, there isn't a team called Juventusss.
     */
    @Test
    public void checkCreateGiocatoreFailure(){
        Date dataInizio = new Date(2022-1900, 11-1, 11);
        Squadra squadra = new Squadra("Juventusss", "serieA", dataInizio, 1002, 10, "Lucia", "mario96@gmail.com");
        assertThrows(RuntimeException.class,()->{squadraService.createGiocatoreSquadra("Eugenio","DeSimone", squadra);});
    }



}
