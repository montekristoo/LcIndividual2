package src;

import java.io.IOException;
import java.util.ArrayList;

public class Cartof extends Leguma{
    final int caloriiPer100 = 87;
    public Cartof(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Cartof() {
        super();
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }
}
