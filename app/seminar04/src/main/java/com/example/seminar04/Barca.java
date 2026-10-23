package com.example.seminar04;

import java.io.Serializable;

public class Barca implements Serializable {

    int nrLocuri;
    String culoare;
    boolean areMotor;
    String marca;
    boolean areVeste;

    public Barca(int nrLocuri, String culoare, boolean areMotor, String marca, boolean areVeste) {
        this.nrLocuri = nrLocuri;
        this.culoare = culoare;
        this.areMotor = areMotor;
        this.marca = marca;
        this.areVeste = areVeste;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public boolean isAreMotor() {
        return areMotor;
    }

    public void setAreMotor(boolean areMotor) {
        this.areMotor = areMotor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isAreVeste() {
        return areVeste;
    }

    public void setAreVeste(boolean areVeste) {
        this.areVeste = areVeste;
    }

    @Override
    public String toString() {
        return "Barca{" +
                "nrLocuri=" + nrLocuri +
                ", culoare='" + culoare + '\'' +
                ", areMotor=" + areMotor +
                ", marca='" + marca + '\'' +
                ", areVeste=" + areVeste +
                '}';
    }
}
