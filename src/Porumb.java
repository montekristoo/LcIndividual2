package src;

import java.io.Serializable;

public class Porumb extends Leguma implements Serializable{
    private final static int caloriiPer100 = 96;

    public Porumb(int id, String nume, String culoare, String sort, int grameFolosite, double pret) {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Porumb() {

    }
}
