# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 40 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

TESTER at alt er oppe og går i github

Merknader:
- Har tatt som utgangspunkt å ha 4-8 linjer per METODE, da jeg ønsker å beskrive hver metode så forståelig som mulig (slik at jeg også selv 
i ettertid kan gå tilbake og skjønne hva jeg har gjort om jeg trenger dette til senere arbeid/ arbeidsliv).

* Oppgave 1: 
    - Løste ved å implementere 5.2.3 a) fra kompendiet
    - Metoden oppretter en node p som peker på rotnoden, som går igjennom treet så lenge det finnes noder, og setter q som p sin forelder:
        - Her sammenlignes verdien med p sin verdi --> og så lenge den er større går man til høyre subtre og
            så lenge den er mindre går man til venstre subtre.
    - Når man har kommet til enden (dypeste nivå) og q ikke lenger har noen barn (p er null), opprettes en ny node med verdi
        - Her måtte jeg i tillegg legge inn forelder-peker (q) til den nye noden som opprettes (p = new Node<T>(verdi, q);)
    - Så sjekker den om q finnes - hvis den ikke finnes opprettes p som rotnode
    - Hvis q finnes skal p legges inn som barn til q: sjekker igjen om den skal gå til høyre eller venstre subtre og legge inn

* Oppgave 2: 
    - Tatt utgangspunkt i avsnitt 5.2.6 i kompendiet
    - Hvis det ikke er noen verdier i treet skal den returnere 0
    - Så laget jeg en p-node som skal søke gjennmo treet - starter søket på rot-noden
    - I tillegg har jeg laget en int teller som skal telle antall ganger jeg finner verdien jeg leter etter i treet
    - Så sjekker jeg p (pekeren vår) og sammenligner verdien med p sin verdi
    - Hvis verdien er mindre enn p sin verdi, går man til venstre i binærtreet
    - Hvis verdien er (større eller) lik p sin verdi teller man denne verdien, og går til høyre i treet og søker etter flere tilfeller av verdien
    - Til slutt returneres teller som har talt opp antall tilfeller man har funnet verdien
    
* Oppgave 3: 
    - Tatt utgangspunkt i avsnitt 5.1.7 og 5.1.15 (og programkode 5.1.7 h) i kompendiet
    - Huskeregeler for postorden som jeg har tatt utgangspunkt i: 
        - venstre --> høyre --> node
        - rot er alltid den siste som blir skrevet ut i postorden
        
    - FørstePostorden: 
        - I metoden førstePostorden starter jeg i rot-noden og går så først igjennom venstre subtre, også høyre subtre.
        - Så lenge p har et barn så skal metoden kalle på seg selv ved å gå nedover i venstre subtre helt til p ikke har noe venstre barn
        - Det samme skal så gjøres i høyre subtre, bare at man sjekker at p har et høyrebarn og stopper når den ikke har noe høyre barn. 
        - Når både venste- og høyre subtre har blitt gått igjennom - skal funksjonen returnere noden p
       
    - NestePostorden:
        - Når man har funnet første i postorden må man tenke at man går videre til neste. For å finne neste må man derfor sjekke om
            p har søsken eller ikke, for å avgjøre om neste blir høyre søsken-node eller forelder-noden.
        - Må derfor sjekke om p har søsken: 
            - hvis p er høyrebarnet blir det forelder som er neste i postorden 
            - men hvis p er enebarn (kun er venstrebarn) trenger man ikke gå innom noe høyrebarn først og forelder er også da neste 
            - men hvis p _har_ et høyre søsken, så er det denne som er neste i postorden: derfor kaller man på førstePostorden(p.forelder.høyre)

* Oppgave 4: 
    - Tatt utgangspunkt i avsnitt 5.1.10, 5.1.15 og 5.1.7 (og opppg 7 og programkode i) i kompendiet.
    - Metoden postorden: skal være en iterativ metode - dvs at funksjonen ikke skal kalle på seg selv
        - Det første jeg må gjøre er å finne den første noden i postorden --> traverserer postorden-metoden fra rot-noden til man finner p
        - Så bruker jeg metoden nestePostorden for å finne p sin neste i postorden --> traverserer nestePostorden-metoden for å finne p her også
        - Må huske å ha et basistilfelle som her er at metoden nestePostorden skal kjøres så lenge det er flere noder (så lenge p ikke er lik null)
        - Når det ikke er flere noder har vi funnet p, og postorden-metoden skriver ut p sin verdi
        
    - Metoden postordenRecursive: er en rekursiv metode - her kaller metoden på seg selv for å traverserer treet i postorden rekkefølge.
        - Metoden skal her kalle på seg selv så lenge p har venstre barn og høyre barn (går dypere og dypere nedover i nivå i treet)
        - Når man har gått igjennom både venstre og høyre subtre og p ikke har flere barn skal den til slutt returnere p sin verdi

* Oppgave 5: 
    - I serialize-metoden tok jeg utgangspunkt i avsnitt 5.1.6 (og programkode d)
        - I denne metoden skal man traversere treet "bredde først" - dvs nivåorden - og må derfor gjøre dette vha en kø
        - Hvis binærtreet inneholder noder med verdier skrives verdiene ut med en verdi for hver iterasjon.
        - Må lage en (ArrayList kalt) kø der instanser av Node<T> legges inn i køen --> denne skrives ut til konsollet.
        - Derfor må man lage en liste som skal skrives ut, der først rotnoden legges inn, så barna, og slik fortsetter det 
            helt til det ikke er flere noder i køen.
        - Når det ikke er flere i køen skrives listen skrivUt ut med alle verdiene satt inn.
    
    - I deserialize-metoden har jeg tatt utgangspunkt i måten jeg har satt inn en arrayliste i et tre når jeg skal sjekke 
        metodene mine i main.
        - Da lager jeg et tomt tre, hvor jeg går gjennom ArrayList-listen min fra serialize-metoden og setter inn en og en
            verdi ved bruk av leggInn-metoden. Og til slutt returnerer jeg det utfylte binærtreet. 

* Oppgave 6: 
    - Metoden fjern:
        - Tar utgangspunkt i programkode 5.2.8 d) fra kompendiet
        - Hvis verdi eller p er null så skal metoden returnere false
        - Oppretter en p som peker på rotnoden og q som er forelder til p
        - Så lenge p finnes så skal den sjekke verdien og om den skal gå til høyre eller venstre subtre 
            --> og deretter oppdaterer q til å være foreldernoden til p og p til å være barnet til q, helt til p er null, da stopper den
        - Tilfelle 1 og 2: Hvis p kun har ett barn skal det opprettes en node b som skal settes som p sitt barn
            - Endringen jeg har gjort er å sjekke at hvis b finnes så må man sette peker til b sin forelder --> som blir q 
                (siden b er barnet til p som er satt til å være q)
        Tilfelle 3: Hvis p har begge barna skal r kopieres over i p, også fjernes r ved at s sin venstre settes lik r sin høyre. 
        
    - Metoden fjernAlle:
        - Tar utgangspunkt i avsnitt 5.2.8 (oppgave 3)
        - Her gjøres det gjentatte kall på fjern-metoden inntill det ikke er flere forekomster av verdi igjen i treet
        - Metoden skal så telle opp hver gang det fjernes en verdi, og returnerer antallet fjernet verdier
        
    - Metoden nullstill:
        - Her har jeg tatt utgangspunkt i kompendiet avsnitt 5.1.7 (oppgave 8) 
        - Derfor har jeg lagd to metoder:
            - En offentlig metode som kaller på en privat nullstill-metode om treet ikke er tomt, og 
                som nuller alle referanser og alle verdier til slutt.
            - Og en privat rekursiv metode som traverserer treet (først venstre subtre, så høyre) og
                nuller pekerene, og nuller verdien.


**EDIT: 0 warnings på siste commit**

Fikk opp en Error: File was loader in the wrong encoding: ‘UTF-8’.
                
Warnings: 
- Får to warnings (Warning:(67, 21) og (70, 35) Non-ASCII characters in an identifier), 
    men dette går kun på main-metoden jeg har opprettet for å kunne teste mine egne metoder
- Kan dermed se bort i fra disse.

Warnings:
- Fikk plutselig opp flere warnings etter at jeg tok bort kommentarene mine i EksamensSBinTre:
- Ser ut som det er pga at det er brukt bokstavene æ, ø, å:
    - Warning:(10, 34) Non-ASCII characters in an identifier
    - Warning:(273, 32) Non-ASCII characters in an identifier
    - Warning:(350, 28) Non-ASCII characters in an identifier
    - Warning:(376, 27) Non-ASCII characters in an identifier
    - Warning:(101, 22) Explicit type argument T can be replaced with <> 
        - Denne skal være fjernet
        
        
        