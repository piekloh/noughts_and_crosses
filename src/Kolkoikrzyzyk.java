import java.util.Scanner;

public class Kolkoikrzyzyk {
    public static void main(String[] args) {

        String ktoWygrywa = "nikt"; //posłuży nam to ustalenia, kto wygrał
        int y = 0; //potrzebna do skończenia gry w przypadku remisu
        int numer = 1; //służy do numerowania pól na tablicy
        String krzyzyk = " x "; //krzyżyk w niezapełnionym polu
        String kolko = " o "; // kółko w niezapełnionym polu

        Scanner skaner = new Scanner(System.in); //definiowanie skanera
        String[][] tablicaKiK = new String[3][3]; //definiowanie wymiarów i typu tablicy


        //przypisywanie polom wartości typu: "| liczba |"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablicaKiK[i][j] = "|" + numer + "|";
                numer++;
            }
        }

        System.out.println("");
        System.out.println("Gramy w kółko i krzyżyk!");
        System.out.println("Gracz 1. Podaj swoje imię i zatwierdź Enterem.");
        String imie1 = skaner.nextLine();
        System.out.println("Gracz 1: " + imie1);
        System.out.println("Teraz Gracz 2. Podaj swoje imię i zatwierdź Enterem.");
        String imie2 = skaner.nextLine();
        System.out.println("Gracz 2: " + imie2);

        //wyświetlamy pustą tablicę do grania w kółko i krzyżyk
        wyswietlAktualnaTablice(tablicaKiK);

        //////////////////////////początek while//////////////////////////////////
        while (ktoWygrywa == "nikt" && y < 9) {
            System.out.println("Teraz ruch ma " + imie1);
            System.out.println("Wybierz cyfrę, w miejscu której chcesz postawić KRZYŻYK i zatwierdź Enterem");

            //Sprawdzanie czy wskazano puste pole
            int a = czyPrawidlowePole(tablicaKiK); //pole wybrane przez pierwszego gracza i sprawdzone czy dozwolone

            //Wstawianie "x" w tablicy
            numer = 1;
            tablicaKiK = wypelnionaTablica(tablicaKiK, numer, a, krzyzyk);

            //Wyświetlanie zaktualizowanej tablicy
            wyswietlAktualnaTablice(tablicaKiK);

            //Sprawdzanie, czy "x" wygrywa (różne możliwości)
            ktoWygrywa=czyWygral(tablicaKiK, imie1, krzyzyk);
            if(!ktoWygrywa.equals("nikt")) break;

            y++;

            //Jeśli po ruchu iksa "x" nie wygrywa, a było 9 ruchów, tzn. że remis
            if (y == 9) {
                System.out.println("REMIS!");
                break;
            }

            System.out.println("Teraz ruch ma " + imie2);
            System.out.println("Wybierz cyfrę, w miejscu której chcesz postawić KÓłKO i zatwierdź Enterem");

            //Sprawdzanie czy wskazano puste pole
            int b = czyPrawidlowePole(tablicaKiK); //pole wybrane przez pierwszego gracza i sprawdzone czy dozwolone

            //Wstawianie "o" w tablicy
            numer = 1;
            tablicaKiK = wypelnionaTablica(tablicaKiK, numer, b, kolko);

            //Wyświetlanie zaktualizowanej tablicy
            wyswietlAktualnaTablice(tablicaKiK);

            //Sprawdzanie, czy "o" wygrywa (różne możliwości)

            ktoWygrywa=czyWygral(tablicaKiK, imie2, kolko);

            y++;
        }//koniec while

        //kto wygrał(wiadomo, że ktoś, bo już jest po wyjściu z pętli, ewentualnie remis)
        if (ktoWygrywa.equals(imie2)) {
            System.out.println("Wygrał: " + imie2);
        } else if (ktoWygrywa.equals(imie1)) {
            System.out.println("Wygrał: " + imie1);
        }
        System.out.println("KONIEC!");
    }

    public static String czyWygral(String[][] tablica, String imie, String symbol) {
        String ktoWygrywa = "nikt";

        if ((tablica[0][0].equals(symbol) && tablica[0][1].equals(symbol) && tablica[0][2].equals(symbol))
                || (tablica[1][0].equals(symbol) && tablica[1][1].equals(symbol) && tablica[1][2].equals(symbol))
                || (tablica[2][0].equals(symbol) && tablica[2][1].equals(symbol) && tablica[2][2].equals(symbol))
                || (tablica[0][0].equals(symbol) && tablica[1][0].equals(symbol) && tablica[2][0].equals(symbol))
                || (tablica[0][1].equals(symbol) && tablica[1][1].equals(symbol) && tablica[2][1].equals(symbol))
                || (tablica[0][2].equals(symbol) && tablica[1][2].equals(symbol) && tablica[2][2].equals(symbol))
                || (tablica[0][0].equals(symbol) && tablica[1][1].equals(symbol) && tablica[2][2].equals(symbol))
                || (tablica[0][2].equals(symbol) && tablica[1][1].equals(symbol) && tablica[2][0].equals(symbol))) {
            ktoWygrywa = imie;
        }
        return ktoWygrywa;
    }

    public static int czyPrawidlowePole(String tablica [][]){

        Scanner skaner2 = new Scanner(System.in); //definiowanie skanera
        int aa = skaner2.nextInt();

        while ((aa == 1 && !tablica[0][0].equals("|1|")) || (aa == 2 && !tablica[0][1].equals("|2|"))
                || (aa == 3 && !tablica[0][2].equals("|3|")) || (aa == 4 && !tablica[1][0].equals("|4|"))
                || (aa == 5 && !tablica[1][1].equals("|5|")) || (aa == 6 && !tablica[1][2].equals("|6|"))
                || (aa == 7 && !tablica[2][0].equals("|7|")) || (aa == 8 && !tablica[2][1].equals("|8|"))
                || (aa == 9 && !tablica[2][2].equals("|9|"))) {
            System.out.println("Wskazałeś niewłaściwe pole - jest ono już zajęte. Wskaż inne");
            aa = skaner2.nextInt();
        }
        return aa;
    }

    public static void wyswietlAktualnaTablice(String tablica [][]){
        for (int i = 0; i < tablica.length; i++) {
            for (int j = 0; j < tablica[i].length; j++) {
                System.out.print(tablica[i][j]);
            }
            System.out.println("");
        }
    }
    public static String [][] wypelnionaTablica(String [][] tablica, int indeks, int liczbaA, String symbol){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (liczbaA == indeks && tablica[i][j].equals("|" + indeks + "|")) {
                    tablica[i][j] = symbol;
                }
                indeks++;
            }
        }
        return tablica;
    }
}
