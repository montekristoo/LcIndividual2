package src;

import java.io.IOException;
import java.util.ArrayList;

public class Cartof extends Leguma{
    final int caloriiPer100 = 87;
    public Cartof(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    static void AfisareCartofi (ArrayList<? extends Leguma> v) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        Leguma.Tabel();
        v.stream().filter(leguma -> leguma instanceof Cartof).forEach(leguma -> System.out.println(leguma));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }
}
