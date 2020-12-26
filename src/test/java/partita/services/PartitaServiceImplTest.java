package partita.services;

import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class PartitaServiceImplTest {

    @Test
    void createPartita() {
        Date date = new Date(2020-1900,0,7);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita match = new Partita(1002, "mario99@gmail.com",
                date, start, end);
        PartitaDAO daoTest = new PartitaDAOImpl();
        daoTest.doSavePartita(match);

        assertNotNull(match);

    }
}