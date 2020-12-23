package partita.dao;

import com.pitchfinder.partita.dao.PartitaDAO;
import com.pitchfinder.partita.dao.PartitaDAOImpl;
import com.pitchfinder.partita.entity.Partita;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

public class PartitaDAOImplTest {
    /**
     * This method check correct behaviour of doSavePartita
     */
    @Test
    public void checkdoSavePartitaTr() {
        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita pTest = new Partita(1002,"mario96@gmail.com",
                date,start,end);

        PartitaDAO dao = new PartitaDAOImpl();

        assertTrue(dao.doSavePartita(pTest));
    }
    /**
     * This method check bad behaviour of doSavePartita
     */
    @Test
    public void checkdoSavePartitaFl() {
        Date date = new Date(2020-1900,11,28);
        Time start = new Time(16,00,00);
        Time end = new Time(18,00,00);

        Partita pTest = new Partita(9999,"mario96@gmail.com",
                date,start,end);

        PartitaDAO dao = new PartitaDAOImpl();

        assertThrows(RuntimeException.class ,() -> {dao.doSavePartita(pTest);});
    }
}
