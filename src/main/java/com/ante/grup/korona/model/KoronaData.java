package com.ante.grup.korona.model;

import org.springframework.data.annotation.Id;

public class KoronaData {
    @Id
    public String id;
    private String sehir;
    private int vaka;
    private int vefat;
    private int taburcu;
    private String tarih;

    @Override
    public String toString() {
        return "KoronaData{" +
                "id='" + id + '\'' +
                ", sehir='" + sehir + '\'' +
                ", vaka=" + vaka +
                ", vefat=" + vefat +
                ", taburcu=" + taburcu +
                ", tarih=" + tarih +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public int getVaka() {
        return vaka;
    }

    public void setVaka(int vaka) {
        this.vaka = vaka;
    }

    public int getVefat() {
        return vefat;
    }

    public void setVefat(int vefat) {
        this.vefat = vefat;
    }

    public int getTaburcu() {
        return taburcu;
    }

    public void setTaburcu(int taburcu) {
        this.taburcu = taburcu;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
