package konzola;

import java.util.Scanner;

import logika.IgricaBrickBreaker;

public class IgrajBrickBreaker {
	private static Scanner sc;
	private static String potez;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IgricaBrickBreaker ib = new IgricaBrickBreaker();
		while(!ib.kraj()) {
			prikaziStanje(ib.dajStanje());
			potez = sljedeciPotez();
			ib.odigrajPotez(potez);
		}
	}
	private static void prikaziStanje(String[][] stanje) {
		for(int i=0; i<stanje.length; i++) {
			for(int j=0; j<stanje[0].length; j++) {
				System.out.print(stanje[i][j]);
			}
			System.out.println("");
		}
	}
	private static String sljedeciPotez() {
		sc = new Scanner(System.in);
		System.out.print("Naredni smjer (Lijevo [A] | Mirovanje [bilo sta drugo] | Desno [D]): ");
		potez = sc.next();
		return potez;
	}
}
