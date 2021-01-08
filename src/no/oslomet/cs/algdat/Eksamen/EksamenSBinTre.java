package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør som tar stort sett alle tilfeller, bortsett fra rotnoden
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør for tomt tre
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    // Denne metoden avgjør om en verdi ligger i treet eller ikke
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    // Oppgave 1
    // Basert på programkode 5.2.3 a) fra kompendiet

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot;
        Node<T> q = null;
        int cmp = 0;

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
            // hvis cmp er mindre enn 0 så setter man inne venstre barn
            // hvis den er større så setter man inn høyre barn
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q); // gjorde endringer her: satte inn q som forelder

        if (q == null) {
            rot = p;
        }
        else if (cmp < 0) {
            q.venstre = p;
        }
        else {
            q.høyre = p;
        }
        antall++;
        endringer++;
        return true;
    }

    // Oppgave 6
    // Basert på programkode 5.2.8 d) fra kompendiet

    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> p = rot, q = null; // q er forelder til p

        // går til venstre eller høyre inntil vi havner "utenfor treet" - dvs ingen flere noder
        while (p != null)
        {
            int cmp = comp.compare(verdi,p.verdi); // sammenligner verdi og p sin verdi
            if (cmp < 0) { // er den mindre enn går man til venstre subtre
                q = p;
                p = p.venstre;
            }
            else if (cmp > 0) { // er den større enn går man til høyre subtre
                q = p;
                p = p.høyre;
            }
            else {
                break;
            }
        }

        if (p == null) { // finner ikke verdi
            return false;
        }

        // Tilfelle 1 og 2: hvis p kun har ett barn (enten høyre eller venstre)
        if (p.venstre == null || p.høyre == null)
        {
            Node<T> b; // barne-node
            if (p.venstre != null) { // hvis venstre node finnes settes b som venstre barn
                b = p.venstre;
            }
            else {
                b = p.høyre; // hvis venstre ikke finnes settes b som høyre barn
            }

            // Endringen er gjort her: Siden vi har satt p sitt barn til b, må vi huske å sette riktig pekere på b sin forelder
            if (b != null) {
                b.forelder = q;
            }

            if (p == rot) {
                rot = b;
            }
            else if (p == q.venstre) { // hvis forelder q har venstrebarn p
                q.venstre = b; // settes denne til b
            }
            else { // hvis q ikke har venstrebarn p
                q.høyre = b; // settes q sin høyre til b
            }
        }

        // Tilfelle 3: hvis p har BÅDE høyre og venstre barn
        else
        {
            Node<T> s = p, r = p.høyre; // node s peker på p, r peker på p sin høyre
            while (r.venstre != null) // så lenge r ikke har venstre barn
            {
                s = r; // setets s til å være r sin forelder
                r = r.venstre;
            }

            p.verdi = r.verdi; // kopierer verdien r til p

            if (s != p) {
                s.venstre = r.høyre; // s sin venstre settes til å være r sin høyre
            }
            else {
                s.høyre = r.høyre; // s sin høyre settes til å være r sin høyre
            }
        }
        antall--;   // det er nå én node mindre i treet
        return true;
    }

    // Oppgave 6
    // Tatt utgangspunkt i Kompendiet 5.2.8 (oppgave 3)
    // Gjentatte kall på fjern-metoden inntill det ikke er flere forekomster av verdi igjen i treet

    public int fjernAlle(T verdi) {
        int antallFjernet = 0; // en teller

        if (tom()) {
            return 0;
        }

        // gjentatte kall på fjern-metoden inntill det ikke er flere forekomster av verdi igjen i treet
        while (fjern(verdi)) {
            antallFjernet++;
        }
        return antallFjernet;
    }


    // Oppgave 2
    // Tatt utgangspunkt i avsnitt 5.2.6 i kompendiet

    public int antall(T verdi) {

        if (verdi == null) {
            return 0;
        }

        Node<T> p = rot;
        int antallLike = 0; // hjelpevariabel som teller

        while (p != null) // sjekker at noden p finnes
        {
            int cmp = comp.compare(verdi,p.verdi); // sammenligner verdi og p sin verdi
            if (cmp < 0) { // mindre enn - går til venstre subtre
                p = p.venstre;
            }
            else // større enn - går til høyre subtre
            {
                if (cmp == 0) { // lik verdi funnet
                    antallLike++;
                }
                p = p.høyre;
            }
        }
        return antallLike;
    }

    // Oppgave 6
    // Tatt utgangspunkt i kompendiet avsnitt 5.1.7 (oppgave 8)

    // public metode som kaller på den private metoden
    public void nullstill() {

        if (!tom()) {
            nullstill(rot);
            rot = null;
            antall = 0;
        }
    }

    // private metoden som rekursivt kaller seg selv og nuller ut alle noder i venstre og høyre subtre
    private void nullstill (Node < T > p) {
        if (p.venstre != null) {
            nullstill(p.venstre); // venstre subtre
            p.venstre = null;
        }
        if (p.høyre != null) {
            nullstill(p.høyre); // høyre subtre
            p.høyre = null;
        }
        p.verdi = null;
        //endringer++;
    }

    // Oppgave 3
    // Tatt utgangspunkt i avsnitt 5.1.7 og 5.1.15 (og programkode 5.1.7 h) i kompendiet

    private static <T> Node<T> førstePostorden(Node<T> p) {

        // Sjekker om p (roten) har et venstrebarn og høyrebarn rekursivt, helt til den ikke har barn
        if (p.venstre != null) {
            return førstePostorden(p.venstre);
        }
        else if (p.høyre != null) {
            return førstePostorden(p.høyre);
        }
        else {
            return p;
        }
    }

    // Oppgave 3
    // Tatt utgangspunkt i 5.1.7 og 5.1.15 fra kompendiet

    private static <T> Node<T> nestePostorden(Node<T> p) {

        if (p.forelder == null) {
            return null;
        }
        if (p.forelder.høyre == p) { // sjekker om p er høyrebarn
            return p.forelder; // da er neste postorden forelderen til p
        }
        else if (p.forelder.høyre == null) { // hvis p er venstre barn, sjekkes det om p er har et høyre søsken
            return p.forelder; // hvis ikke så er neste postorden forelderen til p
        }
        else { // hvis p er venstrebarn og har høyre søsken er det denne som er neste postorden
            return førstePostorden(p.forelder.høyre);
        }
    }

    // Oppgave 4
    // Tatt utgangspunkt i avsnitt 5.1.10, 5.1.15 og 5.1.7 (og opppg 7 og programkode i) i kompendiet

    // Iterativ funksjon
    public void postorden(Oppgave<? super T> oppgave) {

        Node<T> p = rot;
        p = førstePostorden(p); // finne den første postorden

        while (p != null) { // basistilfelle: hvis p er null skal det stoppes
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }


    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    // Oppgave 4
    // Tatt utgangspunkt i 5.1.7 opppg 7 i kompendiet

    // Rekursiv funksjon, postorden
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        if (p.venstre != null) { // går igjennom venstre subtre
            postordenRecursive(p.venstre, oppgave); // metoden kalles med p sin venstre som parameter
        }
        if (p.høyre != null) { // går igjennom høyre subtre
            postordenRecursive(p.høyre, oppgave); // metoden kalles med p sin høyre som parameter
        }
        oppgave.utførOppgave(p.verdi);
    }

    // Oppgave 5
    // Tatt utgangspunkt i avsnitt 5.1.6 og programkode 5.1.6 d)

    public ArrayList<T> serialize() {

        if (tom()) {
            return null;
        }

        ArrayList<Node<T>> kø = new ArrayList<>(); // køen
        ArrayList<T> skrivUt = new ArrayList<>(); // listen som skal skrives ut

        kø.add(rot);

        while (!kø.isEmpty()) {
            // 1. Ta ut første fra køen
            Node<T> p = kø.remove(0);
            skrivUt.add(p.verdi);

            // 2. Legg til p sine to barn til køen
            if (p.venstre != null) {
                kø.add(p.venstre);
            }
            if (p.høyre != null) {
                kø.add(p.høyre);
            }
        }
        // 3. Skriv ut
        return skrivUt;
    }

    // Oppgave 5

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        EksamenSBinTre<K> binærtre = new EksamenSBinTre<>(c); // lager et tomt tre
        for (K verdi : data) {
            binærtre.leggInn(verdi); // og legger inn en og en verdi (fra serialize) inn i det tomme treet
        }
        return binærtre;
    }

} // ObligSBinTre
