package src;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Morcov extends Leguma implements Serializable{
    final int caloriiPer100 = 41;
    public Morcov(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Morcov() {

    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }
}
