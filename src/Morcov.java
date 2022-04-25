package src;

import java.io.Serializable;

public class Morcov extends Leguma implements Serializable{
    private static final int caloriiPer100 = 41;
    public Morcov(int id, String nume, String culoare, String sort, int grameFolosite, double pret) {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Morcov() {
    }

}
