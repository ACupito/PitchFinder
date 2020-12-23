package com.pitchfinder.campo.entity;


public class Campo {
    /**
     * is the id of the Campo.
     */
    private int identificativo;
    /**
     * is the name of the sport.
     */
    private String sport;

    /**
     *
     * @param identificativo
     * @param sport
     */
    public Campo(int identificativo, String sport) {
        this.identificativo = identificativo;
        this.sport = sport;
    }

    /**
     *
     */
    public Campo() {
    }

    /**
     * return identificativo.
     * @return int
     */
    public int getIdentificativo() {
        return identificativo;
    }

    /**
     *set identificativo.
     * @param identificativo
     */
    public void setIdentificativo(int identificativo) {
        this.identificativo = identificativo;
    }

    /**
     * return sport.
     * @return String
     */
    public String getSport() {
        return sport;
    }

    /**
     * set sport.
     * @param sport
     */
    public void setSport(String sport) {
        this.sport = sport;
    }
}
