import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Salata implements Serializable {
    static File file = new File("myObjectss.txt");
    static File Afisare = new File("Afisare.txt");
    static FileWriter pw;

    static {
        try {
            pw = new FileWriter(Afisare);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public Salata() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);
        String opt;
        System.out.print("-> Tastati 1 pentru a face o salata. ");
        opt = cin.next();
        while (!opt.matches("1")) {
            System.out.print("-> Introduceti doar cifra 1. ");
            opt = cin.next();
        }
        int optt = Integer.valueOf(opt);
        if (optt == 1) {
            Meniu();
        }
    }

    public static void Meniu() throws Exception {
        ArrayList<Leguma> listaG = new ArrayList<Leguma>();
        ArrayList<Leguma> salataPregatire = new ArrayList<>();
        if (file.isFile()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            listaG = (ArrayList<Leguma>) ois.readObject();
            ois.close();
        }


        Scanner cin = new Scanner(System.in);
        int optiune = 0;
        do {
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

            do {
                boolean esteNumar = false;
                do {
                    try {
                        System.out.println();
                        System.out.print("-> Introdu optiunea: ");
                        optiune = cin.nextByte();
                        System.out.println();
                        esteNumar = true;
                    } catch (InputMismatchException e) {
                        cin.nextLine();
                        System.out.println("Valorea tastata nu e optiune. Tastati din nou: ");
                    }
                } while (!esteNumar);
            } while (optiune < 0 || optiune > 14);

            switch (optiune) {
                case 1:
                    Citire(listaG);
                    System.out.println();
                    break;
                case 2:
                    byte af;
                    do {
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
                        af = cin.nextByte();
                        System.out.println();
                        if (af >= 0 && af <= 8) {
                            switch (af) {
                                case 1 -> AfisareLegume(listaG);
                                case 2 -> Cartof.AfisareCartofi(listaG);
                                case 3 -> Morcov.AfisareMorcovi(listaG);
                                case 4 -> Varza.AfisareVerze(listaG);
                                case 5 -> Castravete.AfisareCastraveti(listaG);
                                case 6 -> Porumb.AfisareArdei(listaG);
                                case 7 -> Ceapa.AfisareCepi(listaG);
                            }
                        }
                    } while (af != 0);
                    break;
                case 3:
                    String s;
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
                        af = cin.nextByte();
                        System.out.println();
                        if (af >= 1 && af <= 6) {
                            switch (af) {
                                case 1 -> {
                                    Cartof leguma;
                                    leguma
                                            = (Cartof) (CitireLeguma(1, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                                case 2 -> {
                                    Morcov leguma;
                                    leguma = (Morcov) (CitireLeguma(2, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                                case 3 -> {
                                    Varza leguma;
                                    leguma = (Varza) (CitireLeguma(3, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                                case 4 -> {
                                    Castravete leguma;
                                    leguma = (Castravete) (CitireLeguma(4, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                                case 5 -> {
                                    Porumb leguma;
                                    leguma = (Porumb) (CitireLeguma(5, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                                case 6 -> {
                                    Ceapa leguma;
                                    leguma = (Ceapa) (CitireLeguma(6, listaG));
                                    listaG.add(leguma);
                                    oos = new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(listaG);
                                    oos.close();
                                }
                            }
                        }
                    } while (af != 0);
                    break;
                case 4:
                    AfisareLegume(listaG);
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
                        af = cin.nextByte();
                        System.out.println();
                        if (af >= 1 && af <= 6) {
                            switch (af) {
                                case 1 -> {
                                    salataPregatire = AdaugareInSalata(1, listaG, salataPregatire);
                                }
                                case 2 -> {
                                    salataPregatire = AdaugareInSalata(2, listaG, salataPregatire);
                                }
                                case 3 -> {
                                   salataPregatire =  AdaugareInSalata(3, listaG, salataPregatire);
                                }
                                case 4 -> {
                                   salataPregatire =  AdaugareInSalata(4, listaG, salataPregatire);
                                }
                                case 5 -> {
                                   salataPregatire =  AdaugareInSalata(5, listaG, salataPregatire);
                                }
                                case 6 -> {
                                    salataPregatire = AdaugareInSalata(6, listaG, salataPregatire);
                                }
                            }
                        }
                    } while (af != 0);
                    break;

                case 5:
                    Predicate<Leguma> pr = leguma -> (leguma instanceof Cartof || leguma instanceof Ceapa);
                    listaG.removeIf(pr);
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(listaG);
                    oos.close();
                    System.out.println();
                    break;
                case 6:
                    SortbyCalorii(salataPregatire);
                    System.out.println();
                    break;
                case 7:
                    Diapazon(listaG);
                    break;
                case 8:
                    CaloriiMax(salataPregatire);
                    break;
                case 9:
                    Nr100Grame(salataPregatire);
                    break;
                case 10:
                    String idd;
                    int id;
                    do {
                        System.out.print("-> ID" + " (introdu doar numere, care sa fie incluse intre " + "0 " + "si " + listaG.size() + "): ");
                        idd = cin.next();
                        while (!idd.matches("[0-9]+")) {
                            System.out.print("-> ID" + " (introdu doar numere, care sa fie incluse intre " + "0 " + "si " + listaG.size() + "): ");
                            idd = cin.next();
                        }
                        id = Integer.parseInt(idd);
                    } while (id > listaG.size());
                    System.out.println("---------------------------------------------------------------------------------------------------------");
                    TabelAtr();
                    int finalId = id;
                    listaG.stream().filter(leguma -> leguma.getId() == finalId).forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------------");
                    break;
                case 11:
                    salataPregatire.clear();
                    listaG.clear();
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(listaG);
                    System.out.println("------------------------------------------------------------------------------------");
                    System.out.println("LEGUMELE AU FOST STERSE.");
                    System.out.println("------------------------------------------------------------------------------------");
                    break;
                case 12:
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
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Cartof")).count();
                    pw.write("Cartof: " + count + "\n");
                    System.out.println("Cartof: " + count);
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Castravete")).count();
                    pw.write("Castravete: " + count + "\n");
                    System.out.println("Castrevete: " + count);
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Ceapa")).count();
                    pw.write("Ceapa: " + count + "\n");
                    System.out.println("Ceapa: " + count);
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Morcov")).count();
                    pw.write("Morcov: " + count + "\n");
                    System.out.println("Morcov: " + count);
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Porumb")).count();
                    pw.write("Porumb: " + count + "\n");
                    System.out.println("Porumb: " + count);
                    count = salataPregatire.stream().filter(leguma -> leguma.getNume().equals("Varza")).count();
                    pw.write("Varza: " + count + "\n");
                    System.out.println("Varza: " + count);
                    System.out.println("----------------------");
                    //pw.close();
                    break;
            }
            if (optiune == 0) {
                pw.close();
            }
        } while (optiune != 0);
    }

    static ArrayList<Leguma> Citire(ArrayList<Leguma> listaG) throws IOException {
        Porumb porumb = new Porumb(0, "Porumb", "rosu", "Ceros", 100, 5);
        Cartof cartof = new Cartof(1, "Cartof", "rosu", "Bundrea", 150, 3);
        Castravete castravete = new Castravete(2, "Castravete", "verde", "Kavi", 200, 2);
        Morcov morcov = new Morcov(3, "Morcov", "portocaliu", "Bacani", 120, 2.5);
        Varza varza = new Varza(4, "Varza", "verde", "Broccoli", 200, 15);
        Ceapa ceapa = new Ceapa(5, "Ceapa", "galben", "Ceapa spaniola", 100, 3);
        Cartof cartof2 = new Cartof(6, "Cartof", "rosu", "Cumidava", 50, 4.5);
        Morcov morcov2 = new Morcov(7, "Morcov", "negru", "Nantes", 50, 2);
        Castravete castravete2 = new Castravete(8, "Castravete", "verde", "Gerda", 50, 3);
        Ceapa ceapa2 = new Ceapa(9, "Ceapa", "alb", "Vidalia", 100, 2);
        Ceapa ceapa3 = new Ceapa(10, "Ceapa", "alb", "Polska", 100, 3.5);
        for (int i = 0; i < listaG.size(); i++) {
            for (int j = i; j < listaG.size(); j++) {
                if (listaG.get(i).getId() == listaG.get(j).getId()) {
                    listaG.remove(i);
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(listaG);
                    oos.close();
                    throw new IllegalArgumentException();
                }
            }
        }
        listaG.addAll(List.of(porumb, cartof, castravete, morcov, varza, ceapa, cartof2, morcov2, castravete2, ceapa2, ceapa3));
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(listaG);
        oos.close();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("LEGUMELE AU FOST CITITE.");
        System.out.println("------------------------------------------------------------------------------------");
        return listaG;
    }

    static ArrayList<Leguma> AdaugareInSalata(int optiune, ArrayList<Leguma> x, ArrayList<Leguma> salataPregatire) {
        Scanner cin = new Scanner(System.in);
        Leguma object = null;
        System.out.println("---------------------------------------------------------------------------------------------------------");
        TabelAtr();
        ArrayList <Leguma> v = new ArrayList<>();
        if (optiune == 1) {
            object = (Cartof) object;
            v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Cartof).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        if (optiune == 2) {
            object = (Castravete) object;
           v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Castravete).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        if (optiune == 3) {
            object = (Ceapa) object;
            v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Ceapa).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        if (optiune == 4) {
            object = (Morcov) object;
            v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Morcov).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        if (optiune == 5) {
            object = (Porumb) object;
            v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Porumb).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        if (optiune == 6) {
            object = (Varza) object;
            v = (ArrayList<Leguma>) x.stream().filter(leguma -> leguma instanceof Varza).collect(Collectors.toList());
            v.forEach(leguma -> System.out.println(leguma.toString()));
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
        String idd;
        System.out.print("-> Introdu ID-ul legumei: "); idd = cin.next();
        while (!idd.matches("[0-9]+")) {
            System.out.print("Eroare. Introdu un ID existent.");
            idd = cin.next();
        }
        int id = Integer.valueOf(idd);
        boolean Gasit = false;
        for (Leguma i : v) {
            if (id == i.getId()) {
                salataPregatire.add(i);
                x.remove(i);
                Gasit = true;
            }
        }
        if (!Gasit) {
            System.out.println("------ Nu exista o astfel de leguma.");
            System.out.println();
        }
        else {
            System.out.println("------ Leguma cu ID-ul " + idd + " a fost adaugata.");
            System.out.println();
        }
        return salataPregatire;
    }

    static String TabelAtr() {
        Formatter fmt = new Formatter();
        fmt.format("%10s%15s%15s%17s%15s%15s%15s", "ID", "Nume", "Culoare", "Sort", "Grame", "Pret", "Calorii");
        System.out.println(fmt);
        return String.valueOf(fmt);
    }

    static void AfisareLegume(ArrayList<? extends Leguma> v) throws IOException, FileNotFoundException, ClassNotFoundException {

        try {
            if (v.isEmpty()) {
                throw new IllegalArgumentException();
            } else {
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                TabelAtr();
                v.forEach(af -> System.out.println(af.toString()));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");
        } catch (IllegalArgumentException e) {
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("Lista nu are elemente!!!");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
        }
    }

    static double SumaPret(ArrayList<Leguma> v) {
        double sum = v.stream().filter(leguma -> leguma.getPretKg() > 0).mapToDouble(Leguma::getPretKg).sum();
        return sum;
    }

    static Object CitireLeguma (int optiune, ArrayList<Leguma> listaG) throws IOException {
        Scanner cin = new Scanner(System.in);
        Object leguma = null;
        int id;
        Leguma object = listaG.stream().reduce((first, second) -> second)
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

        if (optiune == 1) {
            leguma = new Cartof(id, "Cartof", culoare, sort, grame, pret);
        }
        if (optiune == 2) {
            leguma = new Morcov(id, "Morcov", culoare, sort, grame, pret);
        }
        if (optiune == 3) {
            leguma = new Varza(id, "Varza", culoare, sort, grame, pret);
        }
        if (optiune == 4) {
            leguma = new Castravete(id, "Castravete", culoare, sort, grame, pret);
        }
        if (optiune == 5) {
            leguma = new Porumb(id, "Porumb", culoare, sort, grame, pret);
        }
        if (optiune == 6) {
            leguma = new Ceapa(id, "Ceapa", culoare, sort, grame, pret);
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
