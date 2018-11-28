package com.fibaro.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class FullContent {
    private String numer_zamowienia;
    private Integer numer_pozycji;
    private String indeks_czesci;
    private String indeks;
    private String nazwa_czesci;
    private LocalDate termin_dostawcy;
    private Integer ilosc_do_przyjecia_wg_dostawcy;
    private Date kl_termin;
    private Date pr_termin;
    private Integer ilosc_zlecona;
    private Integer ilosc_do_przyjecia;
    private String uwagi;
    private Long numer_kontrahenta;
    private String nazwa_pelna;

    public FullContent() {
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

    public LocalDate getTermin_dostawcy() {
        return termin_dostawcy;
    }

    public void setTermin_dostawcy(LocalDate termin_dostawcy) {
        this.termin_dostawcy = termin_dostawcy;
    }

    public Integer getIlosc_do_przyjecia_wg_dostawcy() {
        return ilosc_do_przyjecia_wg_dostawcy;
    }

    public void setIlosc_do_przyjecia_wg_dostawcy(Integer ilosc_do_przyjecia_wg_dostawcy) {
        this.ilosc_do_przyjecia_wg_dostawcy = ilosc_do_przyjecia_wg_dostawcy;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullContent that = (FullContent) o;
        return Objects.equals(getNumer_zamowienia(), that.getNumer_zamowienia()) &&
                Objects.equals(getNumer_pozycji(), that.getNumer_pozycji()) &&
                Objects.equals(getIndeks(), that.getIndeks()) &&
                Objects.equals(getNumer_kontrahenta(), that.getNumer_kontrahenta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumer_zamowienia(), getNumer_pozycji(), getIndeks(), getNumer_kontrahenta());
    }


   public int compareTo(FullContent f) {
        if (numer_kontrahenta.compareTo(f.numer_kontrahenta) == 0) {
            if (numer_zamowienia.compareTo(f.numer_zamowienia) == 0) {
                if (numer_pozycji.compareTo(f.numer_pozycji) == 0) {
                    return indeks.compareTo(f.indeks);
                } else {return numer_pozycji.compareTo(f.numer_pozycji);}
            } else {return numer_zamowienia.compareTo(f.numer_zamowienia);}
        } else {return numer_kontrahenta.compareTo(f.numer_kontrahenta);}
   }
}