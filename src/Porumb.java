package src;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Porumb extends Leguma implements Serializable{
    final int caloriiPer100 = 96;

    public Porumb(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Porumb() {

    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }

}
