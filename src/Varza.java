package src;

import java.io.Serializable;

public class Varza extends Leguma implements Serializable {
    private static final int caloriiPer100 = 31;
    public Varza(int id, String nume, String culoare, String sort, int grameFolosite, double pret) {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Varza() {
    }


}
