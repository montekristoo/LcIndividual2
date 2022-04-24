package src;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Formatter;



abstract class Leguma extends Salata implements Serializable, CalculareCalorii {

    String nume, culoare, sort;
    int grameFolosite, id;
    double pret;
    double calorii;
    final int caloriiPer100 = 0;

    //src.Leguma() {
///
   // }

    public Leguma(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
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

    public Leguma (String nume, String culoare, String sort) throws IOException {
        this.nume = nume;
        this.culoare = culoare;
        this.culoare = sort;
    }

   public Leguma (String nume, String culoare, int grameFolosite) throws IOException {
       this.nume = nume;
        this.culoare = culoare;
      this.grameFolosite = grameFolosite;
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


    @Override
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