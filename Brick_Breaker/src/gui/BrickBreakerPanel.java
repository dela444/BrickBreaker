package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logika.Cigla;
import logika.IgricaBrickBreaker;

public class BrickBreakerPanel extends JPanel implements Runnable{
    IgricaBrickBreaker ib = new IgricaBrickBreaker();
    /**
     * Ova klasa MojListener sluzi za unos korisnika i pomjeranje daske desno ili lijevo
     */
    class MojListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
				ib.pomjeriLijevo();
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
				ib.pomjeriDesno();
			}
		}
	}

    public BrickBreakerPanel() {
        addKeyListener(new MojListener());
        setFocusable(true);//potrebno da se "vidi" Mojlistener klasa
    }

    public void update() {
    	ib.update();
        repaint();
        try {
			TimeUnit.MILLISECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    /**
     * Sluzi za crtanje svih elemenata igrice
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(47, 97, 173));
        Rectangle loptica = ib.dajLopticu();
        g.fillOval(loptica.x, loptica.y, loptica.width, loptica.height);
        g.setColor(new Color(60,153,220));
        Rectangle daska = ib.dajDasku();
        g.fillRect(daska.x, daska.y, daska.width, daska.height);
        Cigla[] cigle = ib.dajCigle();
        for (int i = 0; i < ib.dajBrojCigli(); i++) {
        	if(cigle[i].dajBroj()==2) {
        		g.setColor(new Color(15,82,152));
        		g.fillRect(cigle[i].dajX(), cigle[i].dajY(), cigle[i].dajS(), cigle[i].dajV());
        	}else {
        		g.setColor(new Color(46, 153, 220));
        		g.fillRect(cigle[i].dajX(), cigle[i].dajY(), cigle[i].dajS(), cigle[i].dajV());
        	}
        }
    }
    /**
     * sluzi za pokretanje igre
     */
    @Override
    public void run() {
    	if(ib.kraj()) {
    		JOptionPane.showMessageDialog(this, ib.poruka(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
    		System.exit(0);
    	}
        update();
        run();
    }
}
