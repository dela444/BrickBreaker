package logika;

public class Cigla {
	private int x,y,sirina,visina,broj;
	public Cigla(int x1, int y1,int s, int v) {
		x=x1;
		y=y1;
		sirina=s;
		visina=v;
		broj=2;
	}
	public int dajBroj() {
		return broj;
	}
	public void smanjiBroj() {
		broj-=1;
	}
	public int dajX() {
		return x;
	}
	public int dajY() {
		return y;
	}
	public int dajS() {
		return sirina;
	}
	public int dajV() {
		return visina;
	}
}
