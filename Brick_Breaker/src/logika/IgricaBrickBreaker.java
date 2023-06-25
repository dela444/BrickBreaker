package logika;

import java.awt.Rectangle;

public class IgricaBrickBreaker {
	private final int VELICINA_LOPTICE = 16;
    private final int DASKA_SIRINA = 100;
    private final int DASKA_VISINA = 8;
    private final int CIGLA_SIRINA = 50;
    private final int CIGLA_VISINA = 20;
    private final int SIRINA = 1000;
    private final int VISINA = 500;
    
    private int pomjeriX;
    private int pomjeriY;
    private int brojCigli;
    private int trenutnoCigli;
    private Rectangle loptica;
    private Rectangle daska;
    private Cigla[] cigle;
    private String[][] stanje;
    private boolean kraj;
    private String poruka;
    private int lopticaX;
    private int lopticaY;
    private int pomjeriPoX,pomjeriPoY;
    private int daskaX;
    
    public void pomjeriLijevo() {
    	daska.x -= 30;
    }
    public void pomjeriDesno() {
    	daska.x += 30;
    }
    /**
     * Konstruktor bez parametara koji sluzi da inicijalizira sve atribute klase IgricaBrickBreaker
     */
    public IgricaBrickBreaker() {
    	pomjeriX = 15;
        pomjeriY = 15;
        brojCigli = 114;
        trenutnoCigli = brojCigli;
        loptica = new Rectangle(300, 190, VELICINA_LOPTICE, VELICINA_LOPTICE);
        daska = new Rectangle((SIRINA - DASKA_SIRINA) / 2, 430, DASKA_SIRINA, DASKA_VISINA);
        cigle = new Cigla[brojCigli];
        int trenutniX = 2,trenutniY = 2;
        for (int i = 0; i < brojCigli; i++) {
            cigle[i] = new Cigla(trenutniX, trenutniY, CIGLA_SIRINA, CIGLA_VISINA);
            trenutniX += CIGLA_SIRINA + 2;
            if (trenutniX + CIGLA_SIRINA > SIRINA) {
                trenutniX = 2;
                trenutniY += CIGLA_VISINA + 2;
            }
        }
        kraj = false;
 	   	stanje= new String[15][133];
 	   	poruka="";
 	   	lopticaY=8;
 	   	lopticaX=50;
 	   	pomjeriPoX=1;
 	   	pomjeriPoY=1;
 	   	daskaX=60;
 	   	for (int i = 0; i < 15; i++) {
 	   		if (i < 6) {
 	   			String temp = "(CIGLA)";
 	   			int index = 0;
 	   			for (int j = 0; j < 133; j++) {
 	   				if (index == temp.length()) {
 	   					index = 0;
 	   				}
 	   				stanje[i][j] = temp.charAt(index) + "";
 	   				index++;
 	   			}
 	   		}else if(i==14){
 	   			String temp = "(DAASSKKAA)";
	   			int index = 0;
	   			for(int j=0; j<133; j++) {
	   				if(j>=60 && j<=70) {
	   					stanje[i][j] = temp.charAt(index) +"";
	   					index++;
	   				}else {
	   					stanje[i][j]=" ";
	   				}
	   			}
 	   		}else {
 	   			for (int j = 0; j < 133; j++) {
 	   				if(i==lopticaY && j==lopticaX) {
 	   					stanje[i][j] = "O";
 	   				}else {
 	   					stanje[i][j] = " ";
 	   				}
 	   			}
 		   }
 	   }
    }
    /**
     * Metoda odigrajPotez sluzi da promjeni stanje igrice nakon unosa poteza od korisnika za ispis u konzolu
     */
    public void odigrajPotez(String potez) {
    	potez = potez.charAt(0) + "";
    	int i = lopticaY , j = lopticaX;
    	String loptica = stanje[i+ pomjeriPoY][j + pomjeriPoX];
    	int c = j;
    	if(potez.equals("a") || potez.equals("A")) {
    		if(daskaX-1>=0) {
    			daskaX-=1;
    			String temp = "(DAASSKKAA)";
	   			int index = 0;
	   			for(int k=daskaX; k<daskaX+11; k++) {	
	   				stanje[14][k] = temp.charAt(index) +"";
	   				index++;
	   			}
	   			stanje[14][daskaX+11]=" ";
    		}
    	}else if(potez.equals("d") || potez.equals("D")) {
    		if(daskaX+12<133) {
    			daskaX+=1;
    			String temp = "(DAASSKKAA)";
	   			int index = 0;
	   			for(int k=daskaX; k<daskaX+11; k++) {	
	   				stanje[14][k] = temp.charAt(index) +"";
	   				index++;
	   			}
	   			stanje[14][daskaX-1]=" ";
    		}
    	}
    	if(i!=13) {
    		if(loptica.equals("(") || loptica.equals("C") || loptica.equals("I") || loptica.equals("G") || loptica.equals("L")
    			|| loptica.equals("A") || loptica.equals(")")) {
    		if(loptica.equals("(")) c=j+pomjeriPoX;
    		else if(loptica.equals("C")) c=j+pomjeriPoX-1;
    		else if(loptica.equals("I")) c=j+pomjeriPoX-2;
    		else if(loptica.equals("G")) c=j+pomjeriPoX-3;
    		else if(loptica.equals("L")) c=j+pomjeriPoX-4;
    		else if(loptica.equals("A")) c=j+pomjeriPoX-5;
    		else c=j+pomjeriPoX-6;
    		for(int k=c; k<c+7; k++) {
    			stanje[i-1][k] = " ";
    		}
    		trenutnoCigli--;
    		if(trenutnoCigli==0) {
    			System.out.println("Pobjedili ste!");
    			System.exit(0);
    		}
    		pomjeriPoY = -pomjeriPoY;
    		lopticaY += pomjeriPoY;
    		lopticaX += pomjeriPoX;
    		stanje[i][j]=" ";
    		stanje[lopticaY][lopticaX]="O";
    		}else if(j+pomjeriPoX >= stanje[0].length-1 || j+pomjeriPoX<=0) {
    			pomjeriPoX = -pomjeriPoX;
    			lopticaX += pomjeriPoX;
    			lopticaY += pomjeriPoY;
    			stanje[i][j]=" ";
    			stanje[lopticaY][lopticaX]="O";
    		}else {
    			lopticaX += pomjeriPoX;
    			lopticaY += pomjeriPoY;
    			stanje[i][j]=" ";
    			stanje[lopticaY][lopticaX]="O";
    		}
    	}else {
    		if(loptica.equals("(") || loptica.equals("D") || loptica.equals("A") || loptica.equals("S") || loptica.equals("K")
        			|| loptica.equals("A") || loptica.equals(")")) {
    			pomjeriPoY = -pomjeriPoY;
    			lopticaX += pomjeriPoX;
    			lopticaY += pomjeriPoY;
    			stanje[i][j]=" ";
    			stanje[lopticaY][lopticaX]="O";
    		}else {
    			System.out.println("Broj unistenih cigli: " + (brojCigli-trenutnoCigli));
    			System.out.println("Izgubili ste!");
    			System.exit(0);
    		}
    	}
    }
    /**
     * Metoda update sluzi da azurira stanje igrice i vrsi provjeru da li se loptica susrela sa zidom, daskom ili ciglom
     */
    public void update() {
    	loptica.x += pomjeriX;
        loptica.y += pomjeriY;

        if (loptica.x <= 0 || loptica.x + VELICINA_LOPTICE >= SIRINA) {
            pomjeriX = -pomjeriX;
        }
        if (loptica.y <= 0) {
            pomjeriY = -pomjeriY;
        }
        if (loptica.intersects(daska)) {
            pomjeriY = -pomjeriY;
        }
        if (loptica.y + VELICINA_LOPTICE >= VISINA) {
        	kraj=true;
        	poruka="Izgubili ste!";
            //System.exit(0);
        }
        boolean firstCollision = false;
        for (int i = 0; i < brojCigli; i++) {
        	Cigla c = cigle[i];
            if (loptica.intersects(new Rectangle(c.dajX(),c.dajY(),c.dajS(),c.dajV())) && !firstCollision) {
            	firstCollision = true;
                pomjeriY = -pomjeriY;
                if(c.dajBroj()==2) {
                	cigle[i].smanjiBroj();
                }else if(c.dajBroj()==1) {
                	cigle[i].smanjiBroj();
                	trenutnoCigli--;
                	cigle[i] = new Cigla(0, 0, 0, 0);
                }
                if(trenutnoCigli==0) {
                    kraj=true;
                    poruka="Pobijedili ste!";
                }
            }
        }
    }
    /**
     * Ostatak ove klase su geteri
     */
    public Rectangle dajLopticu() {
    	return loptica;
    }
    public Rectangle dajDasku() {
    	return daska;
    }
    public int dajBrojCigli() {
    	return brojCigli;
    }
    public Cigla[] dajCigle() {
    	return cigle;
    }
    public String[][] dajStanje(){
    	return stanje;
    }
    public boolean kraj() {
    	return kraj;
    }
    public String poruka() {
    	return poruka;
    }
}
