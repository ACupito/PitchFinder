package com.pitchfinder.prenotazione.entity;

import java.util.Date;
import java.util.Objects;

public class Prenotazione {

    private String utenteEmail;
    private String eventoNome;
    private Date eventoData;
    private int codicePrenotzione;

    public Prenotazione() {
    }

    public Prenotazione(String utenteEmail, String eventoNome, Date eventoData, int codicePrenotzione) {
        this.utenteEmail = utenteEmail;
        this.eventoNome = eventoNome;
        this.eventoData = eventoData;
        this.codicePrenotzione = codicePrenotzione;
    }

    public String getUtenteEmail() {
        return utenteEmail;
    }

    public void setUtenteEmail(String utenteEmail) {
        this.utenteEmail = utenteEmail;
    }

    public String getEventoNome() {
        return eventoNome;
    }

    public void setEventoNome(String eventoNome) {
        this.eventoNome = eventoNome;
    }

    public Date getEventoData() {
        return eventoData;
    }

    public void setEventoData(Date eventoData) {
        this.eventoData = eventoData;
    }

    public int getCodicePrenotzione() {
        return codicePrenotzione;
    }

    public void setCodicePrenotzione(int codicePrenotzione) {
        this.codicePrenotzione = codicePrenotzione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return codicePrenotzione == that.codicePrenotzione && Objects.equals(utenteEmail, that.utenteEmail) && Objects.equals(eventoNome, that.eventoNome) && Objects.equals(eventoData, that.eventoData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utenteEmail, eventoNome, eventoData, codicePrenotzione);
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "utenteEmail='" + utenteEmail + '\'' +
                ", eventoNome='" + eventoNome + '\'' +
                ", eventoData=" + eventoData +
                ", codicePrenotzione=" + codicePrenotzione +
                '}';
    }
}
