package com.pitchfinder.prenotazione.entity;

import java.util.Date;
import java.util.Objects;

public class Prenotazione {
    /**
     * Is the user email.
     *
     */
    private String utenteEmail;
    /**
     * Is the event name.
     */
    private String eventoNome;
    /**
     * Is the event's date.
     */
    private Date eventoData;
    /**
     * Is the code of booking.
     */
    private int codicePrenotazione;

    /**
     * Is the void constructor.
     */
    public Prenotazione() {
    }

    /**
     *Is the Constructor.
     * @param utenteemail - is the user email.
     * @param eventonome - is the name of the event.
     * @param eventodata - is the date of the event.
     * @param codiceprenotazione - is the code of booking.
     */
    public Prenotazione(final String utenteemail, final String eventonome, final Date eventodata, final int codiceprenotazione) {
        this.utenteEmail = utenteemail;
        this.eventoNome = eventonome;
        this.eventoData = eventodata;
        this.codicePrenotazione = codiceprenotazione;
    }

    /**
     * Return utenteEmail.
     * @return String
     */
    public String getUtenteEmail() {
        return utenteEmail;
    }

    /**
     * Sets the variable utenteEmail.
     * @param utenteemail - is the utente email.
     */
    public void setUtenteEmail(final String utenteemail) {
        this.utenteEmail = utenteemail;
    }

    /**
     * Returns eventoNome.
     * @return string
     */
    public String getEventoNome() {
        return eventoNome;
    }

    /**
     * Sets eventoNome.
     * @param eventonome - is the name of event
     */
    public void setEventoNome(final String eventonome) {
        this.eventoNome = eventonome;
    }

    /**
     * Returns eventoData.
     * @return Date
     */
    public Date getEventoData() {
        return eventoData;
    }

    /**
     * Sets eventoData.
     * @param eventodata - is the date of the even.
     */
    public void setEventoData(final Date eventodata) {
        this.eventoData = eventoData;
    }

    /**
     * Returns codiceP.
     * @return int
     */
    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    /**
     * Sets the booking code.
     * @param codiceprenotazione - is the code of booking.
     */
    public void setCodicePrenotazione(final int codiceprenotazione) {
        this.codicePrenotazione = codiceprenotazione;
    }

    /**
     * Takes Object o and if o is equal to this return true else return false.
     * @param o - is istance of the class Prenotazione.
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prenotazione that = (Prenotazione) o;
        return codicePrenotazione == that.codicePrenotazione && Objects.equals(utenteEmail, that.utenteEmail) && Objects.equals(eventoNome, that.eventoNome) && Objects.equals(eventoData, that.eventoData);
    }

    /**
     * Returns a hash value for an object.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(utenteEmail, eventoNome, eventoData, codicePrenotazione);
    }

    /**
     *Returns a string that can be considered as the
     * "textual representation" of the object
     * on which it is invoked.
     * @return String
     */
    @Override
    public String toString() {
        return "Prenotazione{" + "utenteEmail='" + utenteEmail + '\''
                + ", eventoNome='" + eventoNome + '\''
                + ", eventoData=" + eventoData
                + ", codicePrenotzione=" + codicePrenotazione
                + '}';
    }
}
