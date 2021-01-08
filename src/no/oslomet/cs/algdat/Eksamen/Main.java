package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        // Tester innledning 0:
        System.out.println("Tester innledning 0:");

        EksamenSBinTre<String> tre1 = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre1.antall()); //Utskrift: 0
        EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre2.antall()); //Utskrift: 0
        EksamenSBinTre<Character> tre3 = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre3.antall()); //Utskrift: 0

        // Tester oppgave 1:
        System.out.println();
        System.out.println("Tester oppgave 1:");

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre4 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre4.leggInn(verdi);
        }
        System.out.println(tre4.antall()); //Utskrift: 10
        //System.out.println(tre4.toStringPostOrder());

        Integer[] a1 = {1,2,3};
        EksamenSBinTre<Integer> tre5 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a1) {
            tre5.leggInn(verdi);
        }
        System.out.println(tre5.antall()); //Utskrift: 3

        Integer[] a3 = {1,2,3,3,3};
        EksamenSBinTre<Integer> tre6 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a3) {
            tre6.leggInn(verdi);
        }
        System.out.println(tre6.antall()); //Utskrift: 5

        // Tester oppgave 2:
        // Lage trær der man legger inn flere verdier og sjekk at metoden gir rett svar
        System.out.println();
        System.out.println("Tester oppgave 2:");

        Integer[] q = {4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre10 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : q) {
            tre10.leggInn(verdi);
        }

        System.out.println(tre10.antall());        // Utskrift: 10
        System.out.println(tre10.antall(5)); // Utskrift: 0
        System.out.println(tre10.antall(4)); // Utskrift: 3
        System.out.println(tre10.antall(7)); // Utskrift: 2
        System.out.println(tre10.antall(10)); // Utskrift: 1


        // Tester oppgave 3:
        // Første Postorden
        System.out.println();
        System.out.println("Tester oppgave 3:");

        Character[] første = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //Character[] første = {'M', 'D', 'P', 'C', 'E', 'N', 'Q', 'F', 'O'};

        EksamenSBinTre<Character> førsteTre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (char verdi : første) {
            førsteTre.leggInn(verdi);
        }

        System.out.println(førsteTre.antall());
        System.out.println(førsteTre.toStringPostOrder()); // Utskrift: D (E B F G C A)

        // Tester oppgave 6
        // Nullstill
        System.out.println();
        System.out.println("Tester funksjonen nullstill:");

        /*
        Integer[] nulle = {4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> nullstill = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : nulle) {
            nullstill.leggInn(verdi);
        }
        nullstill.nullstill();
        System.out.println(nullstill.toString()); // Utskrift: null
         */

        // Tester oppgave 6
        // Nullstill
        int[] nulle2 = {4,7,2,9,4,10,8,7,4,6,1};
        EksamenSBinTre<Integer> nullstillTre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : nulle2) {
            nullstillTre.leggInn(verdi);
        }
        System.out.println(nullstillTre);

        System.out.println(nullstillTre.fjernAlle(4)); // Utskrift: 3

        nullstillTre.fjernAlle(7);
        nullstillTre.fjern(8);

        System.out.println(nullstillTre.antall()); // Utskrift: 5

        System.out.println(nullstillTre.toStringPostOrder());
        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
        // OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()
    }
}