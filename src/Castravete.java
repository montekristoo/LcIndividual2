package src;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;

public class Castravete extends Leguma{
    final int caloriiPer100 = 12;
    public Castravete(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    static void AfisareCastraveti (ArrayList<? extends Leguma> v) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        Leguma.Tabel();
        v.stream().filter(leguma -> leguma instanceof Castravete).forEach(leguma -> System.out.println(leguma));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }

}
