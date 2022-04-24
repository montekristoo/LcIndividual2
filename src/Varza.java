package src;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Varza extends Leguma implements Serializable {
    final int caloriiPer100 = 31;
    public Varza(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Varza() {
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }

}
