package com.fibaro.model;

import javax.validation.constraints.Null;

import java.time.LocalDate;
import java.util.Objects;


public class FileContent {
    private Long numer_kontrahenta;
    private String numer_zamowienia;
    private Integer numer_pozycji;
    private String indeks;
    @Null
    private LocalDate pr_termin;
    private Integer ilosc_do_przyjecia;

    public FileContent(Long numer_kontrahenta, String numer_zamowienia, Integer numer_pozycji, String indeks, LocalDate pr_termin, Integer ilosc_do_przyjecia) {
        this.numer_kontrahenta = numer_kontrahenta;
        this.numer_zamowienia = numer_zamowienia;
        this.numer_pozycji = numer_pozycji;
        this.indeks = indeks;
        this.pr_termin = pr_termin;
        this.ilosc_do_przyjecia = ilosc_do_przyjecia;
    }

    public FileContent() {
    }


    public Long getNumer_kontrahenta() {
        return numer_kontrahenta;
    }

    public void setNumer_kontrahenta(Long numer_kontrahenta) {
        this.numer_kontrahenta = numer_kontrahenta;
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

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public LocalDate getPr_termin() {
        return pr_termin;
    }

    public void setPr_termin(LocalDate pr_termin) {
        this.pr_termin = pr_termin;
    }

    public Integer getIlosc_do_przyjecia() {
        return ilosc_do_przyjecia;
    }

    public void setIlosc_do_przyjecia(Integer ilosc_do_przyjecia) {
        this.ilosc_do_przyjecia = ilosc_do_przyjecia;
    }

    @Override
    public String toString() {
        return "FileContent{" +" numer_kontrahenta=" + numer_kontrahenta +
                ", numer_zamowienia='" + numer_zamowienia + '\'' +
                ", numer_pozycji=" + numer_pozycji +
                ", indeks='" + indeks + '\'' +
                ", pr_termin=" + pr_termin +
                ", ilosc_do_przyjecia=" + ilosc_do_przyjecia +
                '}';
    }
    public int compareTo(FileContent f) {
        if (numer_kontrahenta.compareTo(f.numer_kontrahenta) == 0) {
            if (numer_zamowienia.compareTo(f.numer_zamowienia) == 0) {
                if (numer_pozycji.compareTo(f.numer_pozycji) == 0)   {
                    return indeks.compareTo(f.indeks);
                }  else {return numer_pozycji.compareTo(f.numer_pozycji);}
            } else {return numer_zamowienia.compareTo(f.numer_zamowienia);}
        } else {return numer_kontrahenta.compareTo(f.numer_kontrahenta);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileContent that = (FileContent) o;
        return
                Objects.equals(this.getNumer_kontrahenta(), that.getNumer_kontrahenta()) &&
                Objects.equals(getNumer_zamowienia(), that.getNumer_zamowienia()) &&
                Objects.equals(getNumer_pozycji(), that.getNumer_pozycji()) &&
                Objects.equals(getIndeks(), that.getIndeks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumer_kontrahenta(), getNumer_zamowienia(), getNumer_pozycji());
    }
}
