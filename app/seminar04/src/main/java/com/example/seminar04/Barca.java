package com.example.seminar04;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Barca implements Parcelable {

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

    protected Barca(Parcel in) {
        nrLocuri = in.readInt();
        culoare = in.readString();
        areMotor = in.readByte() != 0;
        marca = in.readString();
        areVeste = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nrLocuri);
        dest.writeString(culoare);
        dest.writeByte((byte) (areMotor ? 1 : 0));
        dest.writeString(marca);
        dest.writeByte((byte) (areVeste ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Barca> CREATOR = new Creator<Barca>() {
        @Override
        public Barca createFromParcel(Parcel in) {
            return new Barca(in);
        }

        @Override
        public Barca[] newArray(int size) {
            return new Barca[size];
        }
    };

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
