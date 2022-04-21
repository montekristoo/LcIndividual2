import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Ceapa extends Leguma{
    final int caloriiPer100 = 96;
    public Ceapa(int id, String nume, String culoare, String sort, int grameFolosite, double pret) throws IOException {
        super(id, nume, culoare, sort, grameFolosite, pret);
    }

    static void AfisareCepi (ArrayList<Leguma> v) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        Leguma.Tabel();
        v.stream().filter(leguma -> leguma instanceof Ceapa).forEach(leguma -> System.out.println(leguma));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public double CaloriiCant() {
        double per100 = (double) grameFolosite / 100;
        return (caloriiPer100*per100);
    }

}
