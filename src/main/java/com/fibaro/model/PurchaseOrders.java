package com.fibaro.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class PurchaseOrders {
    private String numer_zamowienia;
    private Integer numer_pozycji;
    private String indeks_czesci;
    private String indeks;
    private String nazwa_czesci;
    private Date kl_termin;
    private Date pr_termin;
    private Integer ilosc_zlecona;
    private Integer ilosc_do_przyjecia;
    private String uwagi;
    private Long numer_kontrahenta;
    private String nazwa_pelna;



    public PurchaseOrders() {
    }

    public PurchaseOrders(String numer_zamowienia, Integer numer_pozycji, String indeks_czesci, String indeks, String nazwa_czesci, Date kl_termin, Date pr_termin, Integer ilosc_zlecona, Integer ilosc_do_przyjecia, String uwagi, Long numer_kontrahenta, String nazwa_pelna) {
        this.numer_zamowienia = numer_zamowienia;
        this.numer_pozycji = numer_pozycji;
        this.indeks_czesci = indeks_czesci;
        this.indeks = indeks;
        this.nazwa_czesci = nazwa_czesci;
        this.kl_termin = kl_termin;
        this.pr_termin = pr_termin;
        this.ilosc_zlecona = ilosc_zlecona;
        this.ilosc_do_przyjecia = ilosc_do_przyjecia;
        this.uwagi = uwagi;
        this.numer_kontrahenta = numer_kontrahenta;
        this.nazwa_pelna = nazwa_pelna;
    }

    public String getNumer_zamowienia() {
        return numer_zamowienia;
    }

    public void setNumer_zamowienia(String numer_zamowienia) {
        this.numer_zamowienia = numer_zamowienia;
    }

    public Integer getNumer_pozycji() {
        return numer_pozycji;
    }

    public void setNumer_pozycji(Integer numer_pozycji) {
        this.numer_pozycji = numer_pozycji;
    }

    public String getIndeks_czesci() {
        return indeks_czesci;
    }

    public void setIndeks_czesci(String indeks_czesci) {
        this.indeks_czesci = indeks_czesci;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getNazwa_czesci() {
        return nazwa_czesci;
    }

    public void setNazwa_czesci(String nazwa_czesci) {
        this.nazwa_czesci = nazwa_czesci;
    }

    public Date getKl_termin() {
        return kl_termin;
    }

    public void setKl_termin(Date kl_termin) {
        this.kl_termin = kl_termin;
    }

    public Date getPr_termin() {
        return pr_termin;
    }

    public void setPr_termin(Date pr_termin) {
        this.pr_termin = pr_termin;
    }

    public Integer getIlosc_zlecona() {
        return ilosc_zlecona;
    }

    public void setIlosc_zlecona(Integer ilosc_zlecona) {
        this.ilosc_zlecona = ilosc_zlecona;
    }

    public Integer getIlosc_do_przyjecia() {
        return ilosc_do_przyjecia;
    }

    public void setIlosc_do_przyjecia(Integer ilosc_do_przyjecia) {
        this.ilosc_do_przyjecia = ilosc_do_przyjecia;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Long getNumer_kontrahenta() {
        return numer_kontrahenta;
    }

    public void setNumer_kontrahenta(Long numer_kontrahenta) {
        this.numer_kontrahenta = numer_kontrahenta;
    }

    public String getNazwa_pelna() {
        return nazwa_pelna;
    }

    public void setNazwa_pelna(String nazwa_pelna) {
        this.nazwa_pelna = nazwa_pelna;
    }

    @Override
    public String toString() {
        return "PurchaseOrders{" +
                "numer_zamowienia='" + numer_zamowienia + '\'' +
                ", numer_pozycji='" + numer_pozycji + '\'' +
                ", indeks_czesci='" + indeks_czesci + '\'' +
                ", indeks='" + indeks + '\'' +
                ", nazwa_czesci='" + nazwa_czesci + '\'' +
                ", kl_termin=" + kl_termin +
                ", pr_termin=" + pr_termin +
                ", ilosc_zlecona=" + ilosc_zlecona +
                ", ilosc_do_przyjecia=" + ilosc_do_przyjecia +
                ", uwagi='" + uwagi + '\'' +
                ", numer_kontrahenta=" + numer_kontrahenta +
                ", nazwa_pelna='" + nazwa_pelna + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrders that = (PurchaseOrders) o;
        return Objects.equals(getNumer_zamowienia(), that.getNumer_zamowienia()) &&
                Objects.equals(getNumer_pozycji(), that.getNumer_pozycji()) &&
                Objects.equals(getIndeks(), that.getIndeks()) &&
                Objects.equals(getNumer_kontrahenta(), that.getNumer_kontrahenta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumer_zamowienia(), getNumer_pozycji(), getIndeks(), getNumer_kontrahenta());
    }

    public int compareTo(PurchaseOrders p) {
        if (numer_kontrahenta.compareTo(p.numer_kontrahenta) == 0) {
            if (numer_zamowienia.compareTo(p.numer_zamowienia) == 0) {
                if (numer_pozycji.compareTo(p.numer_pozycji) == 0)   {
                    return indeks.compareTo(p.indeks);
                }  else {return numer_pozycji.compareTo(p.numer_pozycji);}
            } else {return numer_zamowienia.compareTo(p.numer_zamowienia);}
        } else {return numer_kontrahenta.compareTo(p.numer_kontrahenta);}

    }
}
