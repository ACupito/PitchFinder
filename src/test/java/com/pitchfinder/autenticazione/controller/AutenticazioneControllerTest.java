package com.pitchfinder.autenticazione.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutenticazioneControllerTest extends Mockito {

    private final String email = "mario99@gmail.com";
    private final String username = "Mariox99";
    private final String nome = "Mario";
    private final String cognome = "Rossi";
    private final String password = "PitchFinder57";
    private final String data = "1999-6-08";

    IllegalArgumentException exception = null;

    private AutenticazioneController servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;

    @BeforeAll
    void setUp() {
        servlet = new AutenticazioneController();
        mockedRequest = Mockito.mock(HttpServletRequest.class);
        mockedResponse = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void TC_4_1_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);

        String message = "Il login non va a buon fine " +
                "perché l’e-mail non rispetta la " +
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
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("mario99 @gmail.com");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);

        String message = "Il login non va a buon fine " +
                "perché il formato dell’e-mail non è corretto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_1_3() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("PitchFind57");

        String message = "Il login non va a buon " +
                "fine perché la password inserita non è corretta";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_1_4() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn(email);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);

        servlet.doGet(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("Il login è avvenuto correttamente");
    }

    @Test
    void TC_4_2_1() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione " +
                "non va a buon fine perché l’email inserita non " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon " +
                "fine perché l’email inserita " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va " +
                "a buon fine perché la username non " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("Mariox 99");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine " +
                "perché la username inserita " +
                "non rispetta il formato richiesto";

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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine " +
                "perché il nome inserito non " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario99");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine " +
                "perché il nome inserito non " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine " +
                "perché il cognome inserito non rispetta " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("Rossi99");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon " +
                "fine perché il cognome inserito " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine " +
                "perché la password inserita non rispetta la " +
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("PitchFinder 57");
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        String message = "La registrazione non va a buon fine "
                + "perché la password inserita non "
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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(null);

        String message = "La registrazione non va a buon fine perché " +
                "la data di nascita non è stata selezionata";

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
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn("1999-13-31");

        String message = "La registrazione non va a buon fine " +
                "perché la data di nascita " +
                "non rispetta il formato richiesto";

        exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    servlet.doPost(mockedRequest, mockedResponse);
                });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void TC_4_2_13() {

        Mockito.when(mockedRequest.getParameter("flag")).thenReturn("1");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("mario8890@gmail.com");
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("mario8890");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn(nome);
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn(cognome);
        Mockito.when(mockedRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockedRequest.getParameter("data")).thenReturn(data);

        servlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedResponse).setContentType("La registrazione è avvenuta correttamente");
    }
}