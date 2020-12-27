package com.pitchfinder.evento.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Evento {
    /** The Event's name. */
    private String name;
    /** The Event's image path. */
    private String image;
    /** The Event's start time. */
    private Time startHour;
    /** The Event's end time. */
    private Time endHour;
    /** The Event's date. */
    private Date date;
    /** The Event's Guest. */
    private String guest;
    /** The Event's Description. */
    private String description;
    /** The Event's available sits. */
    private int availableSits;
    /** The Event's admin Username. */
    private String admin;

    /**
     *
     * @param nome - event name
     * @param immagine - path of the event's image
     * @param oraInizio - start hour of the event
     * @param oraFine - ending hour of the event
     * @param data - date of the event
     * @param ospite - guest of the event
     * @param descrizione - description of the event
     * @param postiDisponibili - available sits of the event
     * @param adminUsername - admin's username
     */
    public Evento(final String nome,
                  final String immagine,
                  final Time oraInizio,
                  final Time oraFine,
                  final Date data,
                  final String ospite,
                  final String descrizione,
                  final int postiDisponibili,
                  final String adminUsername) {

        this.name = nome;
        this.image = immagine;
        this.startHour = oraInizio;
        this.endHour = oraFine;
        this.date = data;
        this.guest = ospite;
        this.description = descrizione;
        this.availableSits = postiDisponibili;
        this.admin = adminUsername;
    }

    /**
     * Void Construct.
     */
    public Evento() {

    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getImage() {
        return image;
    }

    /**
     * @return String
     */
    public Time getStartHour() {
        return startHour;
    }

    /**
     * @return String
     */
    public Time getEndHour() {
        return endHour;
    }

    /**
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return String
     */
    public String getGuest() {
        return guest;

    }

    /**
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return int
     */
    public int getAvailableSits() {
        return availableSits;
    }

    /**
     * @return String
     */
    public String getAdmin() {
        return admin;
    }

    /**
     *
     * @param nome - name of the event
     */
    public void setName(final String nome) {
        this.name = nome;
    }

    /**
     *
     * @param immagine - image of the event
     */
    public void setImage(final String immagine) {
        this.image = immagine;
    }

    /**
     *
     * @param oraInizio - start hour of the event
     */
    public void setStartHour(final Time oraInizio) {
        this.startHour = oraInizio;
    }

    /**
     *
     * @param oraFine - end hour start of the event
     */
    public void setEndHour(final Time oraFine) {
        this.endHour = oraFine;
    }

    /**
     *
     * @param data - date of the event
     */
    public void setDate(final Date data) {
        this.date = data;
    }

    /**
     *
     * @param ospite - guest of the event
     */
    public void setGuest(final String ospite) {
        this.guest = ospite;
    }

    /**
     *
     * @param descrizione - description of the event
     */
    public void setDescription(final String descrizione) {
        this.description = descrizione;
    }

    /**
     *
     * @param postiDisponibili - available sits of the event
     */
    public void setAvailableSits(final int postiDisponibili) {
        this.availableSits = postiDisponibili;
    }

    /**
     *
     * @param admin - admin's username
     */
    public void setAdmin(final String admin) {
        this.admin = admin;
    }

    /**
     *
     * @param o - the event to compare for the equals
     * @return Boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Evento evento = (Evento) o;

        final Boolean p = availableSits == evento.availableSits;
        final Boolean n = Objects.equals(name, evento.name);
        final Boolean i = Objects.equals(image, evento.image);
        final Boolean oI = Objects.equals(startHour, evento.startHour);
        final Boolean oF = Objects.equals(endHour, evento.endHour);
        final Boolean dA = Objects.equals(date, evento.date);
        final Boolean oS = Objects.equals(guest, evento.guest);
        final Boolean dE = Objects.equals(description, evento.description);

        return p && n && i && oI && oF && dA && oS && dE;
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(name,
                            image,
                            startHour,
                            endHour,
                            date,
                            guest,
                            description,
                            availableSits);
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Evento{"
                + "nome='"
                + name
                + '\''
                + ", immagine='"
                + image
                + '\''
                + ", oraInizio='"
                + startHour
                + '\''
                + ", oraFine='"
                + endHour
                + '\''
                + ", data="
                + date
                + ", ospite='"
                + guest
                + '\''
                + ", descrizione='"
                + description
                + '\''
                + ", postiDisponibili="
                + availableSits
                + '}';
    }

}

