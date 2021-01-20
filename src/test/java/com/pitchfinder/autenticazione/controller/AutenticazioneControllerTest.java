package com.pitchfinder.autenticazione.controller;

import com.pitchfinder.autenticazione.services.AutenticazioneService;
import com.pitchfinder.autenticazione.services.AutenticazioneServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutenticazioneControllerTest extends Mockito {

    private final String email = "mario99@gmail.com";
    private final String username = "Mariox99";
    private final String nome = "Mario";
    private final String cognome = "Rossi";
    private final String password = "PitchFinder_57";
    private final String data = "1999-6-08";

    IllegalArgumentException exception = null;

    private AutenticazioneController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private ServletContext mockedServletContext;
    private RequestDispatcher mockedDispatcher;
    private HttpSession mockedSession;

    @BeforeAll
    void setUp() throws ServletException {

        servlet = new AutenticazioneController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
        mockedServletContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        mockedSession = Mockito.mock(HttpSession.class);

        AutenticazioneService as = new AutenticazioneServiceImpl();

        Date d = new Date(1999 - 1900, 10, 4);
        as.registraUtente("mario8890@gmail.com", "Mariox8890", "Mario",
                "Rossi", "PitchFinder57", d);
    }

    @Test
    void TC_4_1_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);

        Mockito.doReturn(mockedSession).when(mockedRequest).getSession(true);

        String message = "La username inserita non rispetta la " +
                "lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_1_2() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("Mariox 99");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);

        Mockito.doReturn(mockedSession).when(mockedRequest).getSession(true);

        String message = "La username inserita non rispetta il formato corretto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_1_3() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("Mariox8890");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("PitchFind57");

        Mockito.doReturn(mockedSession).when(mockedRequest).getSession(true);

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/autenticazione/loginResult.jsp");

        servlet.doPost(mockedRequest, mockedResponse);
    }

    @Test
    void TC_4_1_4() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("Mariox8890");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("PitchFinder57");

        Mockito.doReturn(mockedSession).when(mockedRequest).getSession(true);
        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/index.jsp");

        servlet.doPost(mockedRequest, mockedResponse);

        Mockito.verify(mockedResponse).setContentType("Benvenuto!");
    }

    @Test
    void TC_4_1_4_1() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("AdminEmanuele99");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Esse3");

        Mockito.doReturn(mockedSession).when(mockedRequest).getSession(true);
        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/autenticazione/admin.jsp");

        servlet.doPost(mockedRequest, mockedResponse);

        Mockito.verify(mockedResponse).setContentType("Il login admin è avvenuto correttamente");

    }

    @Test
    void TC_4_2_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "L'email inserita non " +
                "rispetta la lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_2() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("mario99 @gmail.com");
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "L’email inserita " +
                "non rispetta il formato richiesto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_3() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La username inserita non " +
                "rispetta la lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_4() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn("AdminMariox99");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La username inserita " +
                "non rispetta il formato corretto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_5() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "Il nome inserito non " +
                "rispetta la lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_6() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario99");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "Il nome inserito non " +
                "rispetta il formato richiesto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_7() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "Il cognome inserito non rispetta " +
                "la lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_8() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("Rossi99");
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "Il cognome inserito " +
                "non rispetta il formato richiesto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_9() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La password inserita non rispetta la " +
                "lunghezza corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_10() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn("PitchFinder 57");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La password inserita non "
                + "rispetta il formato richiesto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_11() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(null);

        String message = "Data di nascita non selezionata";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }


    @Test
    void TC_4_2_12() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("1999-13-31");

        String message = "La data di nascita " +
                "non rispetta il formato corretto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });


        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_13() throws ServletException, IOException {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("username_")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password_")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.doReturn(mockedDispatcher).when(mockedServletContext).getRequestDispatcher("/view/autenticazione/avvenutaRegistrazione.jsp");

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("La registrazione è avvenuta con successo");
    }

    @AfterAll
    static void remove() {

        AutenticazioneService as = new AutenticazioneServiceImpl();

        as.removeUtente("Mariox8890");
        as.removeUtente("Mariox99");
    }
}