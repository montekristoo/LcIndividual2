import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Morcov extends Leguma implements Serializable{
    final int caloriiPer100 = 41;
    public Morcov(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    static void AfisareMorcovi (ArrayList<? extends Leguma> v) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        Leguma.Tabel();
        v.stream().filter(leguma -> leguma instanceof Morcov).forEach(leguma -> System.out.println(leguma));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }
}
