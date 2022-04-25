package src;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;

abstract class Leguma  implements Serializable {
    String nume, culoare, sort;
    int grameFolosite, id;
    double pret;
    final int caloriiPer100 = 0;
    public Leguma() {}

    protected Leguma(int id, String nume, String culoare, String sort, int grameFolosite, double pret) {
        this.id = id;
        this.nume = nume;
        this.culoare = culoare;
        this.sort = sort;
        this.grameFolosite = grameFolosite;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getSort() {
        return sort;
    }

    public int getGrameFolosite() {
        return grameFolosite;
    }

    public double getPretKg() {
        return pret;
    }

    public double CaloriiCant() {
        double per100 = (double)grameFolosite / 100;
        return (caloriiPer100*per100);
    }

    public String toString() {
        Formatter fmt = new Formatter();
        BigDecimal bd = new BigDecimal(CaloriiCant()).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
        fmt.format("%10s%15s%15s%17s%15s%15s%15s", id, nume, culoare, sort, grameFolosite, pret, value);
        return String.valueOf(fmt);
    }

    public void Afisare() {
        Formatter fmt = new Formatter();
        BigDecimal bd = new BigDecimal(CaloriiCant()).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
        fmt.format("%10s%15s%15s%17s%15s%15s15s", getId(), getNume(), getCuloare(), getSort(), getGrameFolosite(), getPretKg(), value);
        System.out.println(fmt);
    }

    static public void Tabel() {
        Formatter fmt = new Formatter();
        fmt.format("%10s%15s%15s%17s%15s%15s%15s", "ID", "Nume", "Culoare", "Sort", "Grame", "Pret", "Calorii");
        System.out.println(fmt);
    }
}