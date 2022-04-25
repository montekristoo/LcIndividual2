package src;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// todo: replace useless throws with try-catches
public class Salata implements Serializable {
    static File file = new File("myObjectss.txt");
    static File Afisare = new File("Afisare.txt");
    static FileWriter pw;
    private ArrayList<Leguma> legume;
    private ArrayList<Leguma> salataPregatire;

    // todo: figure out wtf this does
    static {
        try {
            pw = new FileWriter(Afisare);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public Salata() {
    }

    private int citesteInt() {
        Scanner cin = new Scanner(System.in);
        int n;
        try {
            n = cin.nextInt();
        } catch (IllegalArgumentException ie) {
            System.out.println("Nu ati introdus un numar; introduceti din nou");
            return citesteInt();
        }
        return n;
    }

    public void start() {
        System.out.print("-> Tastati 1 pentru a face o salata. ");
        int opt = citesteInt();
        while (opt != 1) {
            System.out.print("-> Introduceti doar cifra 1. ");
            opt = citesteInt();
        }

        Meniu();
    }

    // todo: split this into multiple functions
    public void Meniu() {
//       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//       this.legume = (ArrayList<Leguma>) ois.readObject();
//       ois.close();

        System.out.println("1.  Citirea legumelor.");
        System.out.println("2.  Afisarea legumelor.");
        System.out.println("3.  Adaugarea unei legume in ingrediente..");
        System.out.println("4.  Adaugare leguma in salata.");
        System.out.println("5.  Stergerea cartofilor si a cepelor.");
        System.out.println("6.  Sortarea legumelor descrescatoare dupa calorii.");
        System.out.println("7.  Legumele a caror calorii sunt intr-un anumit diapazon");
        System.out.println("8.  Legumele cu calorii maxime.");
        System.out.println("9.  Nr. legumelor cu caloriile mai mari sau egale ca 100");
        System.out.println("10. Afisarea legumei cu id-ul tastat: ");
        System.out.println("11. Stergerea salatei.");
        System.out.println("12. Detaliile salatei: ");
        System.out.println("0.  Iesire.");

        System.out.print("-> Introdu optiunea: ");
        int optiune = citesteInt();

        switch (optiune) {
            case 1 -> citesteLegumeDinFisier();
            case 2 -> meniuAfisareLegume();
            case 3 -> meniuAdaugaIngrediente();
            case 4 -> meniuAdaugaSalata();
            case 5 -> stergeCartofiSiCepe();
            case 6 -> { SortbyCalorii(salataPregatire);
                System.out.println(); }
            case 7 -> Diapazon(legume);
            case 8 -> CaloriiMax(salataPregatire);
            case 9 -> Nr100Grame(salataPregatire);
            case 10 -> afiseazaLegumeCuID();
            case 11 -> stergeSalata();
            case 12 -> afiseazaSalata();
            case 0 -> pw.close();
        }
    }

    private void afiseazaSalata() throws IOException {
        pw.write("\n");
        pw.write("------------------------------------------------------------------------------------" + "\n");
        pw.write("Informatii despre salata" + "\n");
        pw.write("Caloriile salatei: " + CaloriiTotal(salataPregatire) + "\n" +
                "Pretul salatei: " + SumaPret(salataPregatire) + "\n" + "Pretul salatei: " + SumaPret(salataPregatire)
                + "\n");
        System.out.println("Caloriile salatei: " + CaloriiTotal(salataPregatire));
        System.out.println("Pretul salatei: " + SumaPret(salataPregatire));
        System.out.println("----------------------");
        System.out.println("Ingrendientele salatei: ");
        long count;
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Cartof")).count();
        pw.write("src.Cartof: " + count + "\n");
        System.out.println("src.Cartof: " + count);
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Castravete")).count();
        pw.write("src.Castravete: " + count + "\n");
        System.out.println("Castrevete: " + count);
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Ceapa")).count();
        pw.write("src.Ceapa: " + count + "\n");
        System.out.println("src.Ceapa: " + count);
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Morcov")).count();
        pw.write("src.Morcov: " + count + "\n");
        System.out.println("src.Morcov: " + count);
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Porumb")).count();
        pw.write("src.Porumb: " + count + "\n");
        System.out.println("src.Porumb: " + count);
        count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("src.Varza")).count();
        pw.write("src.Varza: " + count + "\n");
        System.out.println("src.Varza: " + count);
        System.out.println("----------------------");
    }

    private void stergeSalata() {
        salataPregatire.clear();
        legume.clear();
        // not sure what this does?
//        oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(legume);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("LEGUMELE AU FOST STERSE.");
        System.out.println("------------------------------------------------------------------------------------");
    }

    private void afiseazaLegumeCuID() {
        String idd;
        int id;
        do {
            System.out.print("-> ID" + " (introdu doar numere, care sa fie incluse intre " + "0 " + "si " + legume.size() + "): ");
            idd = cin.next();
            while (!idd.matches("[0-9]+")) {
                System.out.print("-> ID" + " (introdu doar numere, care sa fie incluse intre " + "0 " + "si " + legume.size() + "): ");
                idd = cin.next();
            }
            id = Integer.parseInt(idd);
        } while (id > legume.size());
        System.out.println("---------------------------------------------------------------------------------------------------------");
        TabelAtr();
        int finalId = id;
        legume.stream().filter(leguma -> leguma.getId() == finalId).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

    private void stergeCartofiSiCepe() {
        Predicate<Leguma> pr = leguma -> (leguma instanceof Cartof || leguma instanceof Ceapa);
        legume.removeIf(pr);
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(legume);
        oos.close();
        System.out.println();
    }


    private void meniuAfisareLegume() {
        int af = 1; // todo: replace with something better
        do {
            af = citesteInt();
            System.out.println("       | 1. Afisarea tuturor legumelor.");
            System.out.println("       | 2. Afisarea cartofilor.");
            System.out.println("       | 3. Afisarea morcovilor.");
            System.out.println("       | 4. Afisarea varze.");
            System.out.println("       | 5. Afisarea castravetelor.");
            System.out.println("       | 6. Afisarea porumbului.");
            System.out.println("       | 7. Afisarea cepelor.");
            System.out.println("       | 0. Iesire.");
            System.out.println();
            System.out.print("-> Introdu optiunea: ");
            System.out.println();

            if (af >= 0 && af <= 8) {
                switch (af) {
                    case 1 -> legume.forEach(Leguma::Afisare);
                    case 2 -> AfisareLegume(new Cartof());
                    case 3 -> AfisareLegume(new Morcov());
                    case 4 -> AfisareLegume(new Varza());
                    case 5 -> AfisareLegume(new Castravete());
                    case 6 -> AfisareLegume(new Porumb());
                    case 7 -> AfisareLegume(new Ceapa());
                }
            }
        } while (af != 0);
    }

    private void meniuAdaugaIngrediente() {
        int opt = 1;
        do {
            System.out.println("       | 1. Adauga cartof.");
            System.out.println("       | 2. Adauga morcov.");
            System.out.println("       | 3. Adauga varza.");
            System.out.println("       | 4. Adauga castravete.");
            System.out.println("       | 5. Adauga porumb.");
            System.out.println("       | 6. Adauga ceapa.");
            System.out.println("       | 0. Iesire.");
            System.out.println();
            System.out.print("-> Introdu optiunea: ");
            opt = citesteInt();
            System.out.println();
            if (opt >= 1 && opt <= 6) {
                Leguma leguma = CitireLeguma(opt);
                legume.add(leguma);

                // todo: fix this
//                oos = new ObjectOutputStream(new FileOutputStream(file));
//                oos.writeObject(legume);
//                oos.close();
            }
        } while (opt != 0);
    }

    private void meniuAdaugaSalata() {
        legume.forEach(this::AfisareLegume);
        int opt = 1;
        do {
            System.out.println("       | 1. Adauga cartof.");
            System.out.println("       | 2. Adauga castravete.");
            System.out.println("       | 3. Adauga ceapa.");
            System.out.println("       | 4. Adauga morcov.");
            System.out.println("       | 5. Adauga porumb.");
            System.out.println("       | 6. Adauga varza.");
            System.out.println("       | 0. Iesire.");
            System.out.println();
            System.out.print("-> Introdu optiunea: ");
            opt = citesteInt();
            System.out.println();
            if (opt >= 1 && opt <= 6) {
                AdaugareInSalata(opt);
            }
        } while (opt != 0);
    }

    // todo: make ids unique
    private void citesteLegumeDinFisier()  {
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(legume);
            oos.close();
        } catch (IOException ioe) {
            System.err.println("Fisierul " + ioe + " nu exista");
        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("LEGUMELE AU FOST CITITE.");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
    }

    private void adaugaLegumeTest() {
        Porumb porumb = new Porumb(0, "src.Porumb", "rosu", "Ceros", 100, 5);
        Cartof cartof = new Cartof(1, "src.Cartof", "rosu", "Bundrea", 150, 3);
        Castravete castravete = new Castravete(2, "src.Castravete", "verde", "Kavi", 200, 2);
        Morcov morcov = new Morcov(3, "src.Morcov", "portocaliu", "Bacani", 120, 2.5);
        Varza varza = new Varza(4, "src.Varza", "verde", "Broccoli", 200, 15);
        Ceapa ceapa = new Ceapa(5, "src.Ceapa", "galben", "src.Ceapa spaniola", 100, 3);
        Cartof cartof2 = new Cartof(6, "src.Cartof", "rosu", "Cumidava", 50, 4.5);
        Morcov morcov2 = new Morcov(7, "src.Morcov", "negru", "Nantes", 50, 2);
        Castravete castravete2 = new Castravete(8, "src.Castravete", "verde", "Gerda", 50, 3);
        Ceapa ceapa2 = new Ceapa(9, "src.Ceapa", "alb", "Vidalia", 100, 2);
        Ceapa ceapa3 = new Ceapa(10, "src.Ceapa", "alb", "Polska", 100, 3.5);

        legume.addAll(List.of(porumb, cartof, castravete, morcov, varza, ceapa, cartof2, morcov2, castravete2, ceapa2, ceapa3));
    }

    void AdaugareInSalata(int optiune) {
        Scanner cin = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        TabelAtr();
        ArrayList <Leguma> v;
        Leguma tip = null;
        switch (optiune) {
            case 1 -> tip = new Cartof();
            case 2 -> tip = new Castravete();
            case 3 -> tip = new Ceapa();
            case 4 -> tip = new Morcov();
            case 5 -> tip = new Porumb();
            case 6 -> tip = new Varza();
        }

        // todo: check if this works and how to improve it
        Leguma finalTip = tip;
        v = (ArrayList<Leguma>) legume.stream()
                .filter(leguma -> leguma.getClass() == finalTip.getClass())
                .collect(Collectors.toList());

        // todo: split this into new function
//        System.out.println("---------------------------------------------------------------------------------------------------------");
//        String idd;
//        System.out.print("-> Introdu ID-ul legumei: "); idd = cin.next();
//        while (!idd.matches("[0-9]+")) {
//            System.out.print("Eroare. Introdu un ID existent.");
//            idd = cin.next();
//        }
//        int id = Integer.valueOf(idd);
//        boolean Gasit = false;
//        for (Leguma i : v) {
//            if (id == i.getId()) {
//                salataPregatire.add(i);
//                legume.remove(i);
//                Gasit = true;
//            }
//        }
//        if (!Gasit) {
//            System.out.println("------ Nu exista o astfel de leguma.");
//            System.out.println();
//        }
//        else {
//            System.out.println("------ src.Leguma cu ID-ul " + idd + " a fost adaugata.");
//            System.out.println();
//        }
    }

    static String TabelAtr() {
        Formatter fmt = new Formatter();
        fmt.format("%10s%15s%15s%17s%15s%15s%15s", "ID", "Nume", "Culoare", "Sort", "Grame", "Pret", "Calorii");
        System.out.println(fmt);
        return String.valueOf(fmt);
    }

    void AfisareLegume(Leguma tip) {
        legume.stream().filter(leguma -> leguma.getClass() == tip.getClass())
                .forEach(Leguma::Afisare);
    }

    double SumaPret(ArrayList<Leguma> v) {
        double sum = v.stream().filter(leguma -> leguma.getPretKg() > 0).mapToDouble(Leguma::getPretKg).sum();
        return sum;
    }

    Leguma CitireLeguma (int optiune) {
        Scanner cin = new Scanner(System.in);
        Leguma leguma = null;
        int id;
        Leguma object = legume.stream().reduce((first, second) -> second)
                .orElse(null);
        if (object == null) {
            id = 0;
        } else {
            id = object.getId() + 1;
        }
        String culoare;
        System.out.print("-> Culoare: ");
        culoare = cin.next();
        while (!culoare.matches("[a-zA-Z]+")) {
            System.out.println("Eroare. Introdu doar litere: ");
            System.out.print("-> Culoare: ");
            culoare = cin.next();
        }
        String sort;
        System.out.print("-> Sort: ");
        sort = cin.next();
        while (!sort.matches("[a-zA-Z]+")) {
            System.out.println("Eroare. Introdu doar litere: ");
            System.out.print("-> Sort: ");
            sort = cin.next();
        }
        String g;
        System.out.print("-> Grame: ");
        g = cin.next();
        while (!g.matches("[0-9]+")) {
            System.out.println("Eroare. Introdu doar cifre(pozitive): ");
            System.out.print("-> Grame: ");
            g = cin.next();
        }
        int grame = Integer.parseInt(g);
        String pr;
        System.out.print("-> Pret: ");
        pr = cin.next();
        while (!pr.matches("[0-9]+")) {
            System.out.println("Eroare. Introdu doar cifre: ");
            System.out.print("-> Pret: ");
            pr = cin.next();
        }
        int pret = Integer.parseInt(pr);

        switch (optiune) {
            case 1 -> leguma = new Cartof(id, "src.Cartof", culoare, sort, grame, pret);
            case 2 -> leguma = new Morcov(id, "src.Morcov", culoare, sort, grame, pret);
            case 3 -> leguma = new Varza(id, "src.Varza", culoare, sort, grame, pret);
            case 4 -> leguma = new Castravete(id, "src.Castravete", culoare, sort, grame, pret);
            case 5 -> leguma = new Porumb(id, "src.Porumb", culoare, sort, grame, pret);
            case 6 -> leguma = new Ceapa(id, "src.Ceapa", culoare, sort, grame, pret);
        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("LEGUMA A FOST ADAUGATA!!!");
        System.out.println("------------------------------------------------------------------------------------");
        return leguma;
    }

    static void SortbyCalorii(ArrayList<Leguma> v) throws IOException {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        pw.write("----------------------------------------------------------------------------------------------------------" + "\n");
        pw.write("Sortarea descrescatoare dupa calorii a legumelor salatei: " + "\n");
        pw.write("----------------------------------------------------------------------------------------------------------" + "\n");
        pw.write(TabelAtr() + "\n");
        v.stream().sorted(Comparator.comparing(Leguma::CaloriiCant).reversed()).forEach(leguma -> {
            System.out.println(leguma.toString());
            try {
                pw.write(leguma.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    static double CaloriiTotal(ArrayList<Leguma> v) {
        double sum = v.stream().filter(leguma -> leguma.getGrameFolosite() > 0).mapToDouble(Leguma::CaloriiCant).sum();
        return sum;
    }

    static void Diapazon(ArrayList<Leguma> v) {
        Scanner cin = new Scanner(System.in);
        String calorii1;
        String calorii2;
        System.out.println("Introdu prima valoare: ");
        calorii1 = cin.next();
        System.out.println("Introdu a doua valoare: ");
        calorii2 = cin.next();
        while (!(calorii1.matches("[0-9]+") && calorii2.matches("[0-9]+")) || calorii1.equals(calorii2)) {
            System.out.println("Eroare. Introduceti doar cifre, a caror valoare este mai mare de 0, unde numerele nu vor fi egale.");
            calorii1 = cin.next();
            calorii2 = cin.next();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
        int finalCalorii = Integer.parseInt(calorii1);
        int finalCalorii1 = Integer.parseInt(calorii2);
        TabelAtr();
        if (finalCalorii < finalCalorii1) {
            v.stream().filter(leguma -> leguma.CaloriiCant() > finalCalorii && leguma.CaloriiCant() < finalCalorii1).forEach(System.out::println);
        } else if (finalCalorii1 < finalCalorii) {
            v.stream().filter(leguma -> leguma.CaloriiCant() > finalCalorii1 && leguma.CaloriiCant() < finalCalorii).forEach(System.out::println);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    static void CaloriiMax(ArrayList<Leguma> v) {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        TabelAtr();
        Leguma max = v.stream().max(Comparator.comparing(Leguma::CaloriiCant)).orElseThrow(NoSuchElementException::new);
        v.stream().filter(leguma -> leguma.CaloriiCant() == max.CaloriiCant()).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    static void Nr100Grame(ArrayList<Leguma> v) {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        int count = Math.toIntExact(v.stream().filter(leguma -> leguma.CaloriiCant() >= 100).count());
        System.out.println("Nr = " + count);
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}
