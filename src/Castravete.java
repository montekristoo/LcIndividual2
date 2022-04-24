package src;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;

public class Castravete extends Leguma{
    final int caloriiPer100 = 12;
    public Castravete(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    public Castravete() {

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
